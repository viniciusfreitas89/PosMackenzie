package br.mackenzie.myplaces;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;


public class CheckinCadastroActivity extends Activity {
    ListView listView ;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.checkin_cadastro_local_layout);
        
        final TextView cat = (TextView) findViewById(R.id.lbl_categoria);
        
        // Get ListView object from xml
        listView = (ListView) findViewById(R.id.list);
        
        // Defined Array values to show in ListView
        String[] categorias = new String[] {"Bar",
        									"Casa de show",
                                         	"Churrascaria",
                                         	"Lanchonete",
                                         	"Pizzaria",
                                         	"Restaurante",
                                         	"Sorveteria"
                                        	};

        // Define a new Adapter
        // First parameter - Context
        // Second parameter - Layout for the row
        // Third parameter - ID of the TextView to which the data is written
        // Forth - the Array of data

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
          android.R.layout.simple_list_item_1, android.R.id.text1, categorias);


        // Assign adapter to ListView
        listView.setAdapter(adapter); 
        
        // ListView Item Click Listener
        listView.setOnItemClickListener(new OnItemClickListener() {

              public void onItemClick(AdapterView<?> parent, View view,
                 int position, long id) {
                
               // ListView Clicked item index
               int itemPosition = position;
               
               // ListView Clicked item value
               String  itemValue = (String) listView.getItemAtPosition(position);
               //lbl_categoria
               cat.setText("Categoria "+itemValue);
                // Show Alert
               
               Toast.makeText(getApplicationContext(),
                       "Categoria : " +itemValue , Toast.LENGTH_LONG)
                       .show();             
              }
         }); 
    }

}
