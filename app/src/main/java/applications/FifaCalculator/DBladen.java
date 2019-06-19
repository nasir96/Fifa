package applications.FifaCalculator;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;


public class DBladen extends AppCompatActivity {

    DBhelper myDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.liste_layout);

        ListView listView = (ListView) findViewById(R.id.listView);
        myDB = new DBhelper(this);


        ArrayList<String> theList = new ArrayList<>();

        //Inhalte der Liste werden geladen

        Cursor data = myDB.getListContents();
        if(data.getCount() == 0){
            Toast.makeText(this, "Die Liste ist aktuell noch leer",Toast.LENGTH_LONG).show();
        }else{
            while(data.moveToNext()){
                theList.add(data.getString(1));
                ListAdapter listAdapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,theList);
                listView.setAdapter(listAdapter);
            }
        }


    }
}
