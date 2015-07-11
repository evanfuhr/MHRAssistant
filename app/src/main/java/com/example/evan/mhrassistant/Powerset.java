package com.example.evan.mhrassistant;

public class Powerset {

    private int _id;
    private String _name;
    private int[] _powers;

    public Powerset() {

    }

    public Powerset(int id, String name, int[] powers) {
        this._id = id;
        this._name = name;
        this._powers = powers;
    }

    public int getID() {
        return this._id;
    }
    public void setID(int id) {
        this._id = id;
    }

    public String getName() {
        return this._name;
    }
    public void setName(String name) {
        this._name = name;
    }

    public int[] getPowers() {
        return this._powers;
    }
    public void setPowers(int[] powers) {
        this._powers = powers;
    }

    public boolean getPower(int id) {
        //Get power
        return true;
    }

    public void addPower(int powerID) {
        //Add power
        this._powers[this._powers.length] = powerID;
    }
}
