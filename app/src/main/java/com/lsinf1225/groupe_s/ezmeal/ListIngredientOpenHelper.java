package com.lsinf1225.groupe_s.ezmeal;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by jeremy on 01.05.17.
 */

public class ListIngredientOpenHelper extends SQLiteOpenHelper{
    /**
     * Nom du fichier de la base de donnees.
     */
    private static final String DATABASE_NAME = "EzMeal.sqlite";

    /**
     * Version de la base de donnees (à incrementer en cas de modification de celle-ci afin que la
     * methode onUpgrade soit appelee).
     *
     * @note Le numero de version doit changer de maniere monotone.
     */
    private static final int DATABASE_VERSION = 1;

    /**
     * Le contexte de l'application
     */
    private static Context context;

    /**
     * Constructeur. Instancie l'utilitaire de gestion de la base de donnees.
     *
     * @param context Contexte de l'application.
     */
    public ListIngredientOpenHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        ListIngredientOpenHelper.context = context;
    }

    /**
     * Methode d'initialisation appelee lors de la creation de la base de donnees.
     * Ici on y cree les tables de la base de donnees et les remplit.
     *
     * @param db Base de donnees à initialiser.
     * @param db Base de donnees à initialiser
     */
    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("DROP TABLE IF EXISTS \"ListIngredient\";");
        db.execSQL("CREATE TABLE ListeIngredients (ID_Recette INTEGER, ID INTEGER, Valeur INTEGER NOT NULL,  FOREIGN KEY(ID_Recette) REFERENCES Recette(ID_Recette) ON UPDATE CASCADE ON DELETE CASCADE, FOREIGN KEY(ID) REFERENCES Ingredient(ID));");
    }

    /**
     * Ouvre la base de donnees en ecriture
     */
    public boolean open() {
        try {
            getWritableDatabase();
            return true;
        } catch (Throwable t) {
            return false;
        }
    }

    /**
     * Supprime la table dans la base de donnees.
     *
     * @param db Base de donnees.
     * @post La tables de la base de donnees passee en argument est effacee.
     */
    private void deleteDatabase(SQLiteDatabase db) {
        db.execSQL("DROP TABLE IF EXISTS \"Ingredient\";");
    }

    /**
     * Methode de mise à jour lors du changement de version de la base de donnees.
     *
     * @param db         Base de donnees à mettre à jour.
     * @param oldVersion Numero de l'ancienne version.
     * @param newVersion Numero de la nouvelle version.
     * @pre La base de donnees est dans la version oldVersion.
     * @post La base de donnees a ete mise à jour vers la version newVersion.
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        deleteDatabase(db);
        onCreate(db);
    }

    public boolean insert_listingredient(int id_recette, int id_ingredient, int value) {
        ContentValues contentValues = new ContentValues();
        if (id_recette > 0) {
            contentValues.put("ID_Recette", id_recette);
        }
        if (id_ingredient > 0) {
            contentValues.put("ID", id_ingredient);
        }
        if (value > 0) {
            contentValues.put("Valeur", value);
        }

        return this.getWritableDatabase().insertOrThrow("ListIngredient", "", contentValues) != -1;

    }

    public double[] search_value(String idrecette){


        SQLiteDatabase db = getReadableDatabase();
        // Les resultats de la requête sont mis dans un "curseur"
        Cursor c = db.query("\"ListIngredient\"", // La table
                null,                           //Colonnes
                "ID_Recette=?", // Colonnes pour la clause WHERE
                new String[]{idrecette}, // Valeurs pour la clause WHERE
                null, // ne pas grouper les lignes
                null, // ne pas filtrer par goupe de ligne
                null  // pas d'ordre
        );

        int n = c.getCount();
        double[] tab = null;

        if (c.moveToFirst()) {
            tab = new double[n];
            for(int i = 0;i<n;i++) {
                double valeur = c.getDouble(c.getColumnIndexOrThrow("Valeur"));

                tab[i]=valeur;
                c.moveToNext();
            }
        }

        c.close();
        return tab;
    }

    public int[] search_idingredient(String idrecette){
        SQLiteDatabase db = getReadableDatabase();
        // Les resultats de la requête sont mis dans un "curseur"
        Cursor c = db.query("\"ListIngredient\"", // La table
                null,                           //Colonnes
                "ID_Recette=?", // Colonnes pour la clause WHERE
                new String[]{idrecette}, // Valeurs pour la clause WHERE
                null, // ne pas grouper les lignes
                null, // ne pas filtrer par goupe de ligne
                null  // pas d'ordre
        );

        int n = c.getCount();
        int[] tab = null;

        if (c.moveToFirst()) {
            tab = new int[n];
            for(int i = 0;i<n;i++) {
                int id = c.getInt(c.getColumnIndexOrThrow("ID"));

                tab[i]=id;
                c.moveToNext();
            }
        }

        c.close();
        return tab;
    }
}
