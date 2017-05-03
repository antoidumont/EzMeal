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




public class Change extends AppCompatActivity {
    Spinner spinner;
    Spinner spinner2;
    ArrayAdapter<CharSequence> adapter;
    EditText name;
    EditText firstName;
    EditText address;
    EditText age;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_edit);
        spinner = (Spinner) findViewById(R.id.planets_spinner2);
        adapter = ArrayAdapter.createFromResource(this, R.array.choose_sex, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        name = (EditText) findViewById(R.id.name);
        firstName = (EditText) findViewById(R.id.firstname);
        address = (EditText) findViewById(R.id.address);
        age = (EditText) findViewById(R.id.age);

        spinner2 = (Spinner) findViewById(R.id.planets_spinner);
        adapter = ArrayAdapter.createFromResource(this, R.array.choose_language, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner2.setAdapter(adapter);



    public void clickHandler(View view) {
        String text = ((Button) view).getText().toString();
        if (text.equals("Cancel")) {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        } else if (text.equals("Apply")) {


            UtilisateurOpenHelper db = new UtilisateurOpenHelper(this);
            //Recuperer toutes les entrees de la base de donnees
            if (db.open()) {
                boolean insertion;
                try {
                    insertion = db.insert_user( ((EditText) findViewById(R.id.name)).getText().toString(), ((EditText) findViewById(R.id.firstname)).getText().toString(), ((EditText) findViewById(R.id.address)).getText().toString(), ((Spinner) findViewById(R.id.planets_spinner2)).getSelectedItem().toString(),((Spinner) findViewById(R.id.planets_spinner)).getSelectedItem().toString(), Integer.parseInt(((EditText) findViewById(R.id.age)).getText().toString()),);
                }

                catch (Exception e) {
                    insertion = false;
                }
                //              boolean insertion = db.insert_user(((EditText) findViewById(R.id.username)).getText().toString(), ((EditText) findViewById(R.id.name)).getText().toString(), ((EditText) findViewById(R.id.firstname)).getText().toString(), ((EditText) findViewById(R.id.address)).getText().toString(), ((Spinner) findViewById(R.id.spinner)).getSelectedItem().toString(), Integer.parseInt(((EditText) findViewById(R.id.age)).getText().toString()), ((EditText) findViewById(R.id.password)).getText().toString(), ((EditText) findViewById(R.id.mail)).getText().toString());
                if (insertion) {
                    Intent intent = new Intent(this, HomeActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(Change.this, "Error", Toast.LENGTH_LONG).show();
                }
            } else {
                // erreur ouverture bd
                Toast.makeText(Change.this, "Error DB", Toast.LENGTH_LONG).show();
            }

            //Intent intent = new Intent(this, HomeActivity.class);
            //startActivity(intent);
        }
    }






    public void loadImagefromGallery(View view) {
        // Create intent to Open Image applications like Gallery, Google Photos
        Intent galleryIntent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        // Start the Intent
        startActivityForResult(galleryIntent, RESULT_LOAD_IMG);
    }

        @Override
        protected void onActivityResult(int requestCode, int resultCode, Intent data) {
            super.onActivityResult(requestCode, resultCode, data);
            try {
                // When an Image is picked
                if (requestCode == RESULT_LOAD_IMG && resultCode == RESULT_OK && null != data) {
                    // Get the Image from data

                    Uri selectedImage = data.getData();
                    String[] filePathColumn = {MediaStore.Images.Media.DATA};

                    // Get the cursor
                    Cursor cursor = getContentResolver().query(selectedImage, filePathColumn, null, null, null);
                    // Move to first row
                    // cursor.moveToFirst();

                    int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                    imgDecodableString = cursor.getString(columnIndex);
                    cursor.close();
                    ImageButton imgView = (ImageButton) findViewById(R.id.imageView3);
                    // Set the Image in ImageView after decoding the String
                    imgView.setImageBitmap(BitmapFactory.decodeFile(imgDecodableString));

                } else {
                    Toast.makeText(this, "Vous n'avez pas selectionné d'image!", Toast.LENGTH_LONG).show();
                }
            } catch (Exception e) {
                Toast.makeText(this, "Something went wrong", Toast.LENGTH_LONG).show();
            }
        }
}

