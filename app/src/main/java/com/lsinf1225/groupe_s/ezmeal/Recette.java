package com.lsinf1225.groupe_s.ezmeal;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.util.Date;

/**
 * Write a description of class Ingredient here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Recette implements Serializable
{
    private int cookingTime;
    private String date;
    private String difficulty;
    private String name;
    private int nbPeople;
    private String photo;
    private int preparationTime;
    private String quote;
    private int recommanded;
    private String subtype;
    private String type;
    private Ingredient[] listIngredients;
    private Etape[] listSteps;
    private String user;
    
    public Recette(int cookingTime, String date, String difficulty, String name, int nbPeople, String photo, int preparationTime, String quote, int recommanded, String subtype, String type, Ingredient[] listIngredients, Etape[] listSteps, String user)
    {
        this.cookingTime = cookingTime;
        this.date = date;
        this.difficulty = difficulty;
        this.name = name;
        this.nbPeople = nbPeople;
        this.photo = photo;
        this.preparationTime = preparationTime;
        this.quote = quote;
        this.recommanded = recommanded;
        this.subtype = subtype;
        this.type = type;
        this.listIngredients = listIngredients;
        this.listSteps = listSteps;
        this.user = user;
    }

    public int getCookingTime()
    {
        return this.cookingTime;
    }
    
    public String getDate()
    {
        return this.date;
    }
    
     public String getDifficulty()
    {
        return this.difficulty;
    }
    
    public int getNbPeople()
    {
        return this.nbPeople;
    }
    
    public String getName()
    {
        return this.name;
    }
      
    public String getPhoto()
    {
        return this.photo;
    }
       
    public int getPreparationTime()
    {
        return this.preparationTime;
    }
    
    public String getQuote()
    {
        return this.quote;
    }
   
    public int getRecommanded()
    {
        return this.recommanded;
    }
    
    public String getSubtype()
    {
        return this.subtype;
    }
    
    public String getType()
    {
        return this.type;
    }

    public String getUser()
    {
        return this.user;
    }

    public Ingredient[] getIngredients() {
        return this.listIngredients;
    }

    public Etape[] getSteps() {
        return this.listSteps;
    }

}