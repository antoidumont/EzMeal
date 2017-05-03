package com.lsinf1225.groupe_s.ezmeal;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;
import java.util.Arrays;
import java.util.List;

public class MyListActivity extends ListActivity {
    /*
    public static final String CHOSEN_TEXT = "texteChoisi";
    List<String> someStrings; //= Arrays.asList("java", "scala", "prolog", "smalltalk");
    String chosenString;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MySQLiteOpenHelper db = new MySQLiteOpenHelper(this);
        //Recuperer toutes les entrees de la base de donnees
        if ( db.open() ) {
            someStrings = db.getLanguages();
        } else {
            // erreur ouverture bd
            throw new Error("Impossible d'ouvrir la base de donnees");
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, someStrings); // la collection d'elements à adapter à la liste
        setListAdapter(adapter);
        getListView().setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                chosenString = someStrings.get(position);
                Toast.makeText(MyListActivity.this, "Vous avez choisi : " + chosenString, Toast.LENGTH_SHORT).show();
            }
        });
    }
    @Override
    public void finish() {
        if(chosenString != null) {
            Intent data = new Intent();
            data.putExtra(CHOSEN_TEXT, chosenString);
            setResult(RESULT_OK, data);
        }
        super.finish();
    }
    */
}
