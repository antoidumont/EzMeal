package com.lsinf1225.groupe_s.ezmeal;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;



public class SignUp extends AppCompatActivity {
    Spinner spinner;
    ArrayAdapter<CharSequence> adapter;
    EditText username;
    EditText name;
    EditText firstName;
    EditText address;
    EditText age;
    EditText password;
    EditText mail;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup);
        spinner = (Spinner) findViewById(R.id.spinner);
        adapter = ArrayAdapter.createFromResource(this, R.array.choose_sex, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        username = (EditText) findViewById(R.id.username);
        name = (EditText) findViewById(R.id.name);
        firstName = (EditText) findViewById(R.id.firstname);
        address = (EditText) findViewById(R.id.address);


    }

    public void clickHandler(View view) {
        String text = ((Button) view).getText().toString();
        if (text.equals("Cancel")) {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        } else if (text.equals("Confirm")) {


            UtilisateurOpenHelper db = new UtilisateurOpenHelper(this);
            //Recuperer toutes les entrees de la base de donnees
            if (db.open()) {
                boolean insertion;
                try {
                    insertion = db.insert_user(((EditText) findViewById(R.id.username)).getText().toString(), ((EditText) findViewById(R.id.name)).getText().toString(), ((EditText) findViewById(R.id.firstname)).getText().toString(), ((EditText) findViewById(R.id.address)).getText().toString(), ((Spinner) findViewById(R.id.spinner)).getSelectedItem().toString(), Integer.parseInt(((EditText) findViewById(R.id.age)).getText().toString()), ((EditText) findViewById(R.id.password)).getText().toString(), ((EditText) findViewById(R.id.mail)).getText().toString());
                }

                catch (Exception e) {
                    insertion = false;
                }
                //              boolean insertion = db.insert_user(((EditText) findViewById(R.id.username)).getText().toString(), ((EditText) findViewById(R.id.name)).getText().toString(), ((EditText) findViewById(R.id.firstname)).getText().toString(), ((EditText) findViewById(R.id.address)).getText().toString(), ((Spinner) findViewById(R.id.spinner)).getSelectedItem().toString(), Integer.parseInt(((EditText) findViewById(R.id.age)).getText().toString()), ((EditText) findViewById(R.id.password)).getText().toString(), ((EditText) findViewById(R.id.mail)).getText().toString());
                if (insertion) {
                    Intent intent = new Intent(this, HomeActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(SignUp.this, "Error", Toast.LENGTH_LONG).show();
                }
            } else {
                // erreur ouverture bd
                Toast.makeText(SignUp.this, "Error DB", Toast.LENGTH_LONG).show();
            }

            //Intent intent = new Intent(this, HomeActivity.class);
            //startActivity(intent);
        }
    }
}