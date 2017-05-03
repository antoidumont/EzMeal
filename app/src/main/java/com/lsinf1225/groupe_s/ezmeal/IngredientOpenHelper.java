package com.lsinf1225.groupe_s.ezmeal;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * @author Damien Mercier
 * @version 1
 * @see <a href="http://d.android.com/reference/android/database/sqlite/SQLiteOpenHelper.html">SQLiteOpenHelper</a>
 */
public class IngredientOpenHelper extends SQLiteOpenHelper {

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
    public IngredientOpenHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        IngredientOpenHelper.context = context;
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

        db.execSQL("DROP TABLE IF EXISTS \"Ingredient\";");
        db.execSQL("CREATE TABLE Ingredient (ID INTEGER PRIMARY KEY  AUTOINCREMENT  NOT NULL , Produit VARCHAR NOT NULL , Unite VARCHAR);");
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

    public boolean insert_ingredient(String name, String unity) {
        ContentValues contentValues = new ContentValues();
        if (name != null) {
            contentValues.put("Produit", name);
        }
        if (unity != null) {
            contentValues.put("Unite", unity);
        }

        return this.getWritableDatabase().insertOrThrow("Ingredient", "", contentValues) != -1;

    }

    public Ingredient search_ingredient(String id, double value){
        SQLiteDatabase db = getReadableDatabase();
        // Les resultats de la requête sont mis dans un "curseur"
        Cursor c = db.query("\"Ingredient\"", // La table
                null,                           //Colonnes
                "ID=?", // Colonnes pour la clause WHERE
                new String[]{id}, // Valeurs pour la clause WHERE
                null, // ne pas grouper les lignes
                null, // ne pas filtrer par goupe de ligne
                null  // pas d'ordre
        );
        db.close();

        if (c.moveToFirst()) {
            String produit = c.getString(c.getColumnIndexOrThrow("Produit"));
            String unite = c.getString(c.getColumnIndexOrThrow("Unite"));

            return new Ingredient(produit,unite,value);
        }

        c.close();

        return null;
    }

    /**
     * Renvoie la liste les langages contenus dans la base de donnees
     *
     * @return null si requete sans resultat, une liste de strings contenant
     * les langages presents dans la base de donnees.
     */
    /*
    public List<String> getLanguages() {
        List<String> languages = new ArrayList<String>();
        SQLiteDatabase db = getReadableDatabase();
        // Les resultats de la requête sont mis dans un "curseur"
        Cursor c = db.query("\"languages\"", // La table
                new String[]{"\"l_id\"", "\"l_name\""},
                null, // Colonnes pour la clause WHERE
                null, // Valeurs pour la clause WHERE
                null, // ne pas grouper les lignes
                null, // ne pas filtrer par goupe de ligne
                null  // pas d'ordre
        );
        if (c.moveToFirst()) {
            for (int i = 0; i < c.getCount(); i++) {
                String s = c.getString(c.getColumnIndexOrThrow("l_name"));
                languages.add(s);
                c.moveToNext();
            }
        }
        c.close();
        return languages;
    }
    */

}