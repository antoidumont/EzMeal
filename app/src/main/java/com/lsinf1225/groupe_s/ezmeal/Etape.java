package com.lsinf1225.groupe_s.ezmeal;

import java.io.Serializable;

/**
 * Write a description of class Ingredient here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Etape implements Serializable {
    private String description;
    private int stepNumber;

    public Etape(int stepNumber, String description) {
        this.stepNumber = stepNumber;
        this.description = description;
    }

    public int getStepNumber() {
        return this.stepNumber;
    }

    public String getDescription() {
        return this.description;
    }

}