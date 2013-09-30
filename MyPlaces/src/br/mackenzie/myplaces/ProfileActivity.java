package br.mackenzie.myplaces;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ListView;
import android.widget.TextView;

import br.mackenzie.myplaces.R;
import br.mackenzie.myplaces.adapter.AdapterRelatorio;
import br.mackenzie.myplaces.business.RelatorioBusiness;
import br.mackenzie.myplaces.utils.AndroidUtils;
import br.mackenzie.myplaces.utils.Utils;
import br.mackenzie.myplaces.vo.GastoVO;

public class ProfileActivity extends Activity {
    private TextView inicioDisplayDate;
    private TextView fimDisplayDate;
    private Button btnGerarRelatorio;
    
    private int pYear;
    private int pMonth;
    private int pDay;
    
    /** This integer will uniquely define the dialog to be used for displaying date picker.*/
    static final int DATE_DIALOG_ID = 0;
    final Calendar cal = Calendar.getInstance();
    
    private int idUsuario;
    
    private ListView listView;
    
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile_layout);
 
        carregarValores();
        
        inicializarElementos();
        inicializarEventos();
    }
    
    private void carregarValores(){
    	Bundle bundle = this.getIntent().getExtras(); 
	    if (bundle!=null){
	    	idUsuario = bundle.getInt("idUsuario");
	    }
    }
    
    private void carregarList(){
    	RelatorioBusiness bus = new RelatorioBusiness();
    	
    	String dtIni = inicioDisplayDate.getText().toString();
    	String dtFim = fimDisplayDate.getText().toString();
    	
    	
    	if (dtIni.isEmpty()){
    		AndroidUtils.showMessageDialog(this, getString(R.string.relatorio_dtinicial_vazia), false);
    		return;
    	}
    	if (dtFim.isEmpty()){
    		AndroidUtils.showMessageDialog(this, getString(R.string.relatorio_dtfinal_vazia), false);
    		return;
    	}

    	
    	try {
			List<GastoVO> gastos = bus.listarGastos(idUsuario, 
													 Utils.stringToDate(dtIni, "dd/MM/yyyy"), 
													 Utils.stringToDate(dtFim, "dd/MM/yyyy"));
			if (gastos!=null){
				final AdapterRelatorio adapter = new AdapterRelatorio(this.getApplicationContext(), gastos);
				listView.setAdapter(adapter);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
    
    private void inicializarElementos(){
    	inicioDisplayDate = (TextView) findViewById(R.id.inicioDisplayDate);
        fimDisplayDate = (TextView) findViewById(R.id.fimDisplayDate);
        listView = (ListView) findViewById(R.id.list_relatorio);
        btnGerarRelatorio = (Button) findViewById(R.id.btnGerarRelatorio);
        
        cal.setTime(new Date());
        
        pYear = cal.get(Calendar.YEAR);
        cal.add(Calendar.MONTH, -1);
        pMonth = cal.get(Calendar.MONTH);
        pDay = cal.get(Calendar.DAY_OF_MONTH);
        
        updateDisplay(0);
        cal.add(Calendar.MONTH, 1);
        pMonth = cal.get(Calendar.MONTH);
        updateDisplay(1);
    }
    
    private void inicializarEventos(){
    	inicioDisplayDate.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                showDialog(0);
            }
        });
        fimDisplayDate.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                showDialog(1);
            }
        });
        
        btnGerarRelatorio.setOnClickListener(new ButtonGerarListener());
    }
    
    private DatePickerDialog.OnDateSetListener pDateSetListener =
            new DatePickerDialog.OnDateSetListener() {
                public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                    pYear = year;
                    pMonth = monthOfYear;
                    pDay = dayOfMonth;
                    updateDisplay(0);
                }
            };
            
     private DatePickerDialog.OnDateSetListener fDateSetListener =
              new DatePickerDialog.OnDateSetListener() {
         
                  public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                      pYear = year;
                      pMonth = monthOfYear;
                      pDay = dayOfMonth;
                      updateDisplay(1);
                 }
              };
                    
    private void updateDisplay(int data) {
    	if(data==0){
    		inicioDisplayDate.setText(new StringBuilder().append(pDay).append("/").append(pMonth + 1).append("/").append(pYear).append(" "));
        }else if(data==1){
        	fimDisplayDate.setText(new StringBuilder().append(pDay).append("/").append(pMonth + 1).append("/").append(pYear).append(" "));
    	}
    }
     
    /** Create a new dialog for date picker */
    @Override
    protected Dialog onCreateDialog(int id) {
        switch (id) {
        case 0:
            return new DatePickerDialog(this, 
                        pDateSetListener,
                        pYear, pMonth, pDay);
        case 1:
            return new DatePickerDialog(this, 
            		    fDateSetListener,
                        pYear, pMonth, pDay);            
            
        }
        return null;
    }
    
    private class ButtonGerarListener implements View.OnClickListener{
		
		public ButtonGerarListener(){
		}
		
		@Override
		public void onClick(View arg0) {
			carregarList();
		}
	}
}