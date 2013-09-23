package br.mackenzie.myplaces;

import java.util.Calendar;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import br.mackenzie.myplaces.R;

public class ProfileActivity extends Activity {
    /** Private members of the class */
    private TextView inicioDisplayDate;
    private TextView fimDisplayDate;
    private Button pPickDate;
    private Button fPickDate;
    private int pYear;
    private int pMonth;
    private int pDay;
    /** This integer will uniquely define the dialog to be used for displaying date picker.*/
    static final int DATE_DIALOG_ID = 0;
     
    /** Callback received when the user "picks" a date in the dialog */
    /** Data inicial **/
    private DatePickerDialog.OnDateSetListener pDateSetListener =
            new DatePickerDialog.OnDateSetListener() {
 
                public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                    pYear = year;
                    pMonth = monthOfYear;
                    pDay = dayOfMonth;
                    updateDisplay(0);
                    displayToast(0);
                }
            };
            
     /** Data inicial **/
     private DatePickerDialog.OnDateSetListener fDateSetListener =
              new DatePickerDialog.OnDateSetListener() {
         
                  public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                      pYear = year;
                      pMonth = monthOfYear;
                      pDay = dayOfMonth;
                      updateDisplay(1);
                      displayToast(1);
                 }
              };
                    
    /** Updates the date in the TextView */
    private void updateDisplay(int data) {
    	//Data inicial
    	if(data==0){
        inicioDisplayDate.setText(
            new StringBuilder()
                    // Month is 0 based so add 1
                    .append(pDay).append("/")
                    .append(pMonth + 1).append("/")                    
                    .append(pYear).append(" "));
    	}
    	//Data final
    	else if(data==1){
    	fimDisplayDate.setText(
             new StringBuilder()
                   // Month is 0 based so add 1
                     .append(pDay).append("/")
                     .append(pMonth + 1).append("/")
                     .append(pYear).append(" "));
    	}
    }
     
    /** Displays a notification when the date is updated */
    private void displayToast(int data) {
    	if(data==0){
    		Toast.makeText(this, new StringBuilder().append("Date choosen is ").append(inicioDisplayDate.getText()),  Toast.LENGTH_SHORT).show();
    	}
    	if(data==1){
    		Toast.makeText(this, new StringBuilder().append("Date choosen is ").append(fimDisplayDate.getText()),  Toast.LENGTH_SHORT).show();
    	}
    }
     
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile_layout);
 
        /** Capture our View elements */
        inicioDisplayDate = (TextView) findViewById(R.id.inicioDisplayDate);
        fimDisplayDate = (TextView) findViewById(R.id.fimDisplayDate);
        //pPickDate = (Button) findViewById(R.id.pickDate);
        //fPickDate = (Button) findViewById(R.id.fpickDate);
 
        /** Listener for click event of the button */
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
 
        /** Get the current date */
        final Calendar cal = Calendar.getInstance();
        pYear = cal.get(Calendar.YEAR);
        pMonth = cal.get(Calendar.MONTH);
        pDay = cal.get(Calendar.DAY_OF_MONTH);
 
        /** Display the current date in the TextView */
        updateDisplay(0);
        updateDisplay(1);
        
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
}