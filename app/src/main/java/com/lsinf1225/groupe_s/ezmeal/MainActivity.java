package com.lsinf1225.groupe_s.ezmeal;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    EditText username;
    EditText password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);

        /*currentSelection = (TextView) findViewById(R.id.current_selection);
        selectItemButton = (Button) findViewById(R.id.button_choose_item);
        selectItemButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, MyListActivity.class);
                startActivityForResult(i, REQUEST_CODE);
            }
        });*/
    }

    public void clickHandler(View view) {
        String text = ((Button) view).getText().toString();

        if(text.equals("Sign up")) {
            Intent intent = new Intent(this, SignUp.class);
            startActivity(intent);
        }

        else if(text.equals("Sign in")) {

            //boolean isConnect =
            Intent intent = new Intent(this, HomeActivity.class);
            startActivity(intent);
        }

    }



}
