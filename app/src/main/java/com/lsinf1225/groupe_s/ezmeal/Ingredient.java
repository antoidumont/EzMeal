package com.lsinf1225.groupe_s.ezmeal;

import java.io.Serializable;

/**
 * Write a description of class Ingredient here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Ingredient implements Serializable
{
    private String name;
    private String unity;
    private double value;

    public Ingredient(String name, String unity, double value)
    {
        this.name = name;
        this.unity = unity;
        this.value = value;
    }

    public String getName()
    {
        return this.name;
    }
    
    public String getUnity()
    {
        return this.unity;
    }
    
    public void setName(String name)
    {
        this.name = name;
    }
    
    public void setUnity(String unity)
    {
        this.unity = unity;
    }
}
