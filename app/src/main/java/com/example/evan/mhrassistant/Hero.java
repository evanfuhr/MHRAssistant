package com.example.evan.mhrassistant;

/**
 * Created by evanf_000 on 4/3/2015.
 */
public class Hero {

    private int _id;
    private String _hero_name;
    private Integer _plot_points;

    public Hero() {

    }

    public Hero(int id, String hero_name, Integer plot_points) {
        this._id = id;
        this._hero_name = hero_name;
        this._plot_points = plot_points;
    }

    public Hero(String hero_name, Integer plot_points) {
        this._hero_name = hero_name;
        this._plot_points = plot_points;
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

    public Integer get_plot_points() {
        return _plot_points;
    }
    public void set_plot_points(Integer plot_points) {
        this._plot_points = plot_points;
    }

}
