package com.example.evan.mhrassistant;

public class Distinction {

    private int _id;
    private String _distinction_1;
    private String _distinction_2;
    private String _distinction_3;

    public Distinction() {

    }

    //Use this constructor to access an affiliation set
    public Distinction(int id, String distinction_1, String distinction_2, String distinction_3) {
        this._id = id;
        this._distinction_1 = distinction_1;
        this._distinction_2 = distinction_2;
        this._distinction_3 = distinction_3;
    }

    public int getID() {
        return  this._id;
    }
    public void setID(int id) {
        this._id = id;
    }

    public String getDistinction1() {
        return this._distinction_1;
    }
    public void setDistinction1(String distinction_1) {
        this._distinction_1 = distinction_1;
    }

    public String getDistinction2() {
        return this._distinction_2;
    }
    public void setDistinction2(String distinction_2) {
        this._distinction_2 = distinction_2;
    }

    public String getDistinction3() {
        return this._distinction_3;
    }
    public void setDistinction3(String distinction_3) {
        this._distinction_3 = distinction_3;
    }
}
