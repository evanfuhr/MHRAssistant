package com.example.evan.mhrassistant;

public class StressTrauma {

    private int _id;
    private int _stress_physical;
    private int _stress_mental;
    private int _stress_emotional;
    private int _trauma_physical;
    private int _trauma_mental;
    private int _trauma_emotional;

    public StressTrauma() {

    }

    public StressTrauma(int id, int stress_physical, int stress_mental, int stress_emotional, int trauma_physical, int trauma_mental, int trauma_emotional) {
        this._id = id;
        this._stress_physical = stress_physical;
        this._stress_mental = stress_mental;
        this._stress_emotional = stress_emotional;
        this._trauma_physical = trauma_physical;
        this._trauma_mental = trauma_mental;
        this._trauma_emotional = trauma_emotional;
    }

    public int getID() {
        return this._id;
    }
    public void setID(int id) {
        this._id = id;
    }

    public int getStressPhysical() {
        return  this._stress_physical;
    }
    public void setStressPhysical(int stress_physical) {
        this._stress_physical = stress_physical;
    }

    public int getStressMental() {
        return  this._stress_mental;
    }
    public void setStressMental(int stress_mental) {
        this._stress_mental = stress_mental;
    }

    public int getStressEmotional() {
        return  this._stress_emotional;
    }
    public void setStressEmotional(int stress_emotional) {
        this._stress_emotional = stress_emotional;
    }

    public int getTraumaPhysical() {
        return  this._trauma_physical;
    }
    public void setTraumaPhysical(int trauma_physical) {
        this._trauma_physical = trauma_physical;
    }

    public int getTraumaMental() {
        return  this._trauma_mental;
    }
    public void setTraumaMental(int trauma_mental) {
        this._trauma_mental = trauma_mental;
    }

    public int getTraumaEmotional() {
        return  this._trauma_emotional;
    }
    public void setTraumaEmotional(int trauma_emotional) {
        this._trauma_emotional = trauma_emotional;
    }
}
