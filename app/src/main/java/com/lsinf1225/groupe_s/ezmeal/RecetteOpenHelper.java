package com.lsinf1225.groupe_s.ezmeal;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import static android.R.id.content;

/**
 * @author Groupe S
 * @version 1
 * @see <a href="http://d.android.com/reference/android/database/sqlite/SQLiteOpenHelper.html">SQLiteOpenHelper</a>
 */
public class RecetteOpenHelper extends SQLiteOpenHelper {

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
    public RecetteOpenHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
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

        db.execSQL("DROP TABLE IF EXISTS \"Recette\";");
        db.execSQL("CREATE TABLE \"Recette\" (\"ID_Recette\" INTEGER PRIMARY KEY  AUTOINCREMENT  NOT NULL  UNIQUE, \"Nom\" VARCHAR NOT NULL, Auteur VARCHAR NOT NULL,\"Photo\" VARCHAR NOT NULL  UNIQUE , \"Citation\" VARCHAR, \"Recommande\" BOOL NOT NULL  DEFAULT FALSE, \"Date\" DATETIME NOT NULL  DEFAULT CURRENT_DATE, \"Type\" VARCHAR NOT NULL , \"SousType\" VARCHAR NOT NULL , \"NbPers\" INTEGER NOT NULL , \"Difficulte\" VARCHAR NOT NULL , \"TempsCuisson\" INTEGER NOT NULL  DEFAULT 0, \"TempsPrep\" INTEGER NOT NULL  DEFAULT 0, FOREIGN KEY(Auteur) REFERENCES Utilisateur(NomUtilisateur);");
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
        db.execSQL("DROP TABLE IF EXISTS \"Recette\";");
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

    public boolean insert_recipe(int tempsprep, String difficulte, String nom, int nbPersonnes, String photo, int tempscuisson, String citation, int recommandee, String sousType, String type, String utilisateur) {
        ContentValues contentValues = new ContentValues();
        if (tempsprep > 0) {
            contentValues.put("TempsPrep", tempsprep);
        }

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
        String date = sdf.format(new Date());
        contentValues.put("Date", date);

        if (difficulte != null) {
            contentValues.put("Difficulte", difficulte);
        }
        if (nom != null) {
            contentValues.put("Nom", nom);
        }

        if (nbPersonnes > 0) {
            contentValues.put("NbPers", nbPersonnes);
        }
        if (photo != null) {
            contentValues.put("Photo", photo);
        }
        if (tempscuisson > 0) {
            contentValues.put("TempsCuisson", tempscuisson);
        }
        if (citation != null) {
            contentValues.put("Citation", citation);
        }

        contentValues.put("Recommande", recommandee);

        if (sousType != null) {
            contentValues.put("SousType", sousType);

        }
        if (type != null) {
            contentValues.put("Type", type);
        }
        if (utilisateur != null) {
            contentValues.put("Auteur", utilisateur);
        }

        return this.getWritableDatabase().insertOrThrow("Recette", "", contentValues) != -1;

    }

    public Recette[][] search_recipe(String key) {

        SQLiteDatabase db = getReadableDatabase();
        // Les resultats de la requête sont mis dans un "curseur"
        Cursor c = db.query("\"Recette\"", // La table
                null,                           //Colonnes
                "Nom LIKE ?", // Colonnes pour la clause WHERE
                new String[]{"%" + key + "%"}, // Valeurs pour la clause WHERE
                null, // ne pas grouper les lignes
                null, // ne pas filtrer par goupe de ligne
                null  // pas d'ordre
        );

        Recette[][] tab;

        if (c.moveToFirst()) {
            int n = c.getCount();
            tab = new Recette[1][n];

            //Parcours de chaque recette

            for (int i = 0; i < n; i++) {
                int id = c.getInt(c.getColumnIndexOrThrow("ID_Recette"));
                int cookintime = c.getInt(c.getColumnIndexOrThrow("TempsCuisson"));
                String date = c.getString(c.getColumnIndexOrThrow("Date"));
                String difficulty = c.getString(c.getColumnIndexOrThrow("Difficulte"));
                String name = c.getString(c.getColumnIndexOrThrow("Nom"));
                int nbpeople = c.getInt(c.getColumnIndexOrThrow("NbPers"));
                String photo = c.getString(c.getColumnIndexOrThrow("Photo"));
                int preptime = c.getInt(c.getColumnIndexOrThrow("TempsPrep"));
                String quote = c.getString(c.getColumnIndexOrThrow("Citation"));
                int r = c.getInt(c.getColumnIndexOrThrow("Recommande"));
                String subtype = c.getString(c.getColumnIndexOrThrow("SousType"));
                String type = c.getString(c.getColumnIndexOrThrow("Type"));
                String user = c.getString(c.getColumnIndexOrThrow("Auteur"));


                String keyIngredient = Integer.toString(id);

            }

                //Recherche des valeurs et des ID ingredient dans la table ListeIngredient

                ListIngredientOpenHelper dbListIngredient = new ListIngredientOpenHelper(context);
                int[] id_ingredient = dbListIngredient.search_idingredient(keyIngredient);
                double[] value_ingredient = dbListIngredient.search_value(keyIngredient);

                if (id_ingredient == null || value_ingredient == null) {
                    return null;
                }

                dbListIngredient.close();

                Ingredient[] listeingredients = new Ingredient[id_ingredient.length];

                //Recherche des ingrédients

                for (int j = 0; j < listeingredients.length; j++) {
                    IngredientOpenHelper dbIngredient = new IngredientOpenHelper(context);
                    listeingredients[j] = dbIngredient.search_ingredient(Integer.toString(id_ingredient[j]), value_ingredient[j]);
                    dbIngredient.close();
                }

                //Recherche des étapes

                EtapeOpenHelper dbEtape = new EtapeOpenHelper(context);
                Etape[] listeetapes = dbEtape.search_etape(keyIngredient);
                dbEtape.close();

                tab[0][i] = new Recette(cookintime, date, difficulty, name, nbpeople, photo, preptime, quote, r, subtype, type, listeingredients, listeetapes, user);
                c.moveToNext();
            }

            c.close();
            return tab;
        }

        c.close();
        return null
    };

    /**
     *
     * @param arrayType
     * @param arraySubType
     * @return une liste de recettes correspondant à tous les types et tous les sous-types contenu dans les deux listes
     */
    public Recette[][] search_Recipe(ArrayList<String> arrayType, ArrayList<String> arraySubType) {

        //Conversion en tableau

        String[] type = arrayType.toArray(new String[0]);
        String[] subType = arraySubType.toArray(new String[0]);
        Recette[][] recettes = new Recette[type.length][];

        //TODO

        /*

        Faire attention :

        1) type ne peut pas être vide et subType peut être vide (null)

        2) si il n'y a pas de type, alors on return null

        3) Si il n'y a pas de sous type, alors on prend toutes les recettes contenant les types de la liste
            - on recherche toutes les recettes avec le type[0]
            => On place toutes ces recette dans recettes[0][...]
            - on recherche toutes les recettes avec le type[1]
            => On place toutes ces recette dans recettes[1][...]
            - ...

            ex : La condition de recherche sera pour type[0]  : ... WHERE Type = type[0]


        4) si les deux listes sont remplies alors :
            - pour le type[0], on recherche toutes les recettes correspondant aux sous type de la liste substype
            => On place toutes ces recette dans recettes[0][...]
            - pour le type[1],...
            => On place toutes ces recettes dans recettes[1][...]
            - ...

            La condition de recherche sera pour type[0] : ... WHERE Type = type[0] AND (SousType = subType[0] OR ... OR SousType = subType[N])


        */
        return null;

    }


    public int search_index(String username, String recipeName){

        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.query("\"Recette\"", // La table
                "ID_Recette",                           //Colonnes
                "Auteur=? and Nom=?", // Colonnes pour la clause WHERE
                new String[]{username,recipeName}, // Valeurs pour la clause WHERE
                null, // ne pas grouper les lignes
                null, // ne pas filtrer par goupe de ligne
                null  // pas d'ordre

        );
        int ID;
        for (int i = 0; i < c.getCount(); i++) {
                if(c.moveToFirst()) {
                    ID = c.getInt(c.getColumnIndexOrThrow("ID_Recette"));
                }
        }
        return ID;

    }
}
