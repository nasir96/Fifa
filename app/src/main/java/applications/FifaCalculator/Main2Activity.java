package applications.FifaCalculator;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

public class Main2Activity extends AppCompatActivity implements View.OnClickListener {

    Button btnZurDB, btnBerechne, btnReset;
    RadioButton rButtonKO, rButtonLiga;
    EditText teilnehmer, dauer;
    protected TextView _text = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        btnZurDB = (Button) findViewById(R.id.btnZurDB);
        btnZurDB.setOnClickListener(this);

        btnBerechne = (Button) findViewById(R.id.btnBerechne);
        btnBerechne.setOnClickListener(this);

        _text = (TextView) findViewById((R.id.ergenisView));

        rButtonKO = (RadioButton) findViewById(R.id.btnKO);
        rButtonLiga = (RadioButton) findViewById(R.id.btnLiga);

        teilnehmer = (EditText) findViewById(R.id.teilnehmer);
        dauer = (EditText) findViewById(R.id.gameTime);

        btnReset = (Button) findViewById(R.id.btnReset);
        btnReset.setOnClickListener(this);





    }

    @Override
    public void onClick(View view) {
        if (view == btnZurDB) {
            String ergebisText = _text.getText().toString();

            Intent intent = new Intent();
            intent.putExtra("von_2_zu_1", ergebisText);
            setResult(RESULT_OK, intent);

            finish();
        }

        if (view == btnBerechne){

                Integer n,t;
                n=1;
                t=1;



            if (teilnehmer.getText().toString().length() == 0 || dauer.getText().toString().length() == 0){
                    Toast.makeText(this, "Die Liste ist aktuell noch leer", Toast.LENGTH_LONG).show();
                } else {
                    n = Integer.parseInt(teilnehmer.getText().toString());
                    t = Integer.parseInt(dauer.getText().toString());
                }


            if (rButtonKO.isChecked()){
                String erg = String.valueOf((2*n - 1) * t);
                _text.setText(erg + " Minuten");
            } else if (rButtonLiga.isChecked()){
                String erg1 = String.valueOf(((n * (n - 1)) / 2) * t);
                _text.setText(erg1 + " Minuten");
            } else {
                _text.setText("Bitte eine Turnierart auswählen");

            }
        }

        if (view == btnReset){
            teilnehmer.setText("");
            dauer.setText("");
            _text.setText("Ergebnis wurde reseted");
        }



    }
}
