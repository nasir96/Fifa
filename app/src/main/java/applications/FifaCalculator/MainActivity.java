package applications.FifaCalculator;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    EditText editText;
    DBhelper myDB;
    Button btnAdd,btnDB, btnCalc, btnHilfe;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnCalc = (Button) findViewById(R.id.btnCalc);
        btnCalc.setOnClickListener(this);

        editText = (EditText) findViewById(R.id.ergebnis);
        btnDB = (Button) findViewById(R.id.btnDB);
        btnAdd = (Button) findViewById(R.id.btnAdd);
        btnHilfe = (Button) findViewById(R.id.btnHilfe);
        myDB = new DBhelper(this);

        // Ergebnis Hinzufügen

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newEntry = editText.getText().toString();
                if(editText.length()!= 0){
                    AddData(newEntry);
                    editText.setText("");
                }else{
                    Toast.makeText(MainActivity.this, "Du musst das Ergebnis erst berechnen", Toast.LENGTH_LONG).show();
                }
            }
        });

        //In die Ansicht der DB wechseln

        btnDB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, DBladen.class);
                startActivity(intent);
            }
        });

        //In die Ansicht der Hilfe wechseln

        btnHilfe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(MainActivity.this, hilfe.class);
                startActivity(intent2);
            }
        });



    }

    // Toast wenn das Ergenis hinzugefügt wird

    public void AddData(String neuesErgebnis) {

        boolean insertData = myDB.addData(neuesErgebnis);

        if(insertData==true){
            Toast.makeText(this, "Ergebnis wurde erfolgreich hinzugefügt", Toast.LENGTH_LONG).show();
        }else{
            Toast.makeText(this, "Ergebnis wurde nicht hinzugefügt", Toast.LENGTH_LONG).show();
        }
    }


    @Override
    public void onClick(View view) {
        Intent intent = new Intent(this, Main2Activity.class);
        startActivityForResult(intent,123);
    }
    @Override
    protected void onActivityResult (int requestCode, int resultCode, Intent intent) {
        if (resultCode != RESULT_OK) {
            editText.setText("Da ist wohl was schief gegangen!");
            return;
        }

        String textEmpfangen = intent.getStringExtra("von_2_zu_1");
        editText.setText(textEmpfangen);
    }
}
