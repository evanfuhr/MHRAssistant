package com.example.evan.mhrassistant;

/**
 * Created by evanf_000 on 4/8/2015.
 */
public class Affiliation {

    private int _id;
    private int _solo;
    private int _buddy;
    private int _team;

    public Affiliation() {

    }

    //Use this constructor to access an affiliation set
    public Affiliation(int id, int solo, int buddy, int team) {
        this._id = id;
        this._solo = solo;
        this._buddy = buddy;
        this._team = team;
    }

    public int getID() {
        return  this._id;
    }
    public void setID(int id) {
        this._id = id;
    }

    public int getSolo() {
        return this._solo;
    }
    public void setSolo(int solo) {
        this._solo = solo;
    }

    public int getBuddy() {
        return this._buddy;
    }
    public void setBuddy(int buddy) {
        this._buddy = buddy;
    }

    public int getTeam() {
        return this._team;
    }
    public void setTeam(int team) {
        this._team = team;
    }
}
