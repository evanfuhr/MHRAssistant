package com.example.evan.mhrassistant;

/**
 * Created by evanf_000 on 4/3/2015.
 */
public class Hero {

    private int _id;
    private String _hero_name;
    private int _plot_points;
    private int _opportunities;
    private int _xp;
    private String _secret_identity;


    public Hero() {

    }

    //Use this constructor to access a hero object
    public Hero(int id, String hero_name, int plot_points, int opportunities, int xp, String secret_identity) {
        this._id = id;
        this._hero_name = hero_name;
        this._plot_points = plot_points;
        this._opportunities = opportunities;
        this._xp = xp;
        this._secret_identity = secret_identity;
    }

    //Use this constructor to create a new hero object
    public Hero(String hero_name, int plot_points, int opportunities, int xp, String secret_identity) {
        this._hero_name = hero_name;
        this._plot_points = plot_points;
        this._opportunities = opportunities;
        this._xp = xp;
        this._secret_identity = secret_identity;
    }

    public Hero(String hero_name) {
        this._hero_name = hero_name;
    }

    public int getID() {
        return this._id;
    }
    public void setID(int id) {
        this._id = id;
    }

    public String getHeroName() {
        return this._hero_name;
    }
    public void setHeroName(String hero_name) {
        this._hero_name = hero_name;
    }

    public int getPlotPoints() {
        return _plot_points;
    }
    public void setPlotPoints(Integer plot_points) {
        this._plot_points = plot_points;
    }

    public int getOpportunities() {
        return _opportunities;
    }
    public void setOpportunities(int opportunities) {
        this._opportunities = opportunities;
    }

    public int getXP() {
        return _xp;
    }
    public void setXP(int xp) {
        this._xp = xp;
    }

    public String getSecretIdentity() {
        return _secret_identity;
    }
    public void setSecretIdentity(String secret_identity) {
        this._secret_identity = secret_identity;
    }

}
