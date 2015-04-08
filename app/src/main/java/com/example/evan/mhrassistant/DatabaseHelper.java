
package com.example.evan.mhrassistant;
//change1

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

    //Logcat tag
    private static final String LOG = "DatabaseHelper";

    //Database version
    private static final int DATABASE_VERSION = 1;

    //Database name
    private static final String DATABASE_NAME = "playerDataSheetManager";

    //Table names
    private static final String TABLE_HERO = "heroes";

    //Common column names
    private static final String KEY_ID = "id";

    //Hero Table - column names
    private static final String KEY_HERO_NAME = "hero_name";
    private static final String KEY_PLOT_POINTS = "plot_points";
    private static final String KEY_OPPORTUNITIES = "opportunities";
    private static final String KEY_XP = "xp";
    private static final String KEY_HERO_SECRET_IDENTITY = "hero_secret_identity";

    //Table Create Statements
    //Hero Table create statement
    public static final String CREATE_TABLE_HERO = "CREATE TABLE " + TABLE_HERO + "("
            + KEY_ID + " INTEGER PRIMARY KEY"
            + ", " + KEY_HERO_NAME + " TEXT"
            + ", " + KEY_PLOT_POINTS + " INTEGER"
            + ", " + KEY_OPPORTUNITIES + " INTEGER"
            + ", " + KEY_XP + " INTEGER"
            + ", " + KEY_HERO_SECRET_IDENTITY + " TEXT"
            + ")";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_HERO);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_HERO);

        onCreate(db);
    }

    public void createHeroTable(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_HERO);
    }


    //------------------------ Hero Table methods ------------------------------------//

    //Create hero
    public void addHero(Hero hero) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_HERO_NAME, hero.getHeroName());
        if (hero.getPlotPoints() > -1) {
            values.put(KEY_PLOT_POINTS, hero.getPlotPoints());
        }
        if (hero.getOpportunities() > -1) {
            values.put(KEY_OPPORTUNITIES, hero.getOpportunities());
        }
        if (hero.getXP() > -1) {
            values.put(KEY_XP, hero.getXP());
        }
        if (hero.getSecretIdentity() != null) {
            values.put(KEY_HERO_SECRET_IDENTITY, hero.getSecretIdentity());
        }
        //insert row
        db.insert(TABLE_HERO, null, values);
        db.close();
    }

    //Search for a single hero
    public Hero getSingleHero(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Hero hero = new Hero();

        //Raw query
        String selectQuery = "SELECT * FROM " + TABLE_HERO + " WHERE " + KEY_ID + " = " + id;
        Cursor cursor = db.rawQuery(selectQuery, null);

        //Parameterized query
        /**Cursor cursor = db.query(TABLE_HERO, new String[] {
                KEY_ID, KEY_HERO_NAME, KEY_PLOT_POINTS }
                , KEY_ID + "=?" + new String[] { String.valueOf(id) }
                , null
                , null
                , null
                , null);**/
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            hero.setID(Integer.parseInt(cursor.getString(0)));
            hero.setHeroName(cursor.getString(1));
            hero.setPlotPoints(Integer.parseInt(cursor.getString(2)));
            hero.setOpportunities(Integer.parseInt(cursor.getString(3)));
            hero.setXP(Integer.parseInt(cursor.getString(4)));
            hero.setSecretIdentity(cursor.getString(5));
        } else {
            hero.setID(-1);
            hero.setHeroName("Failed");
            hero.setPlotPoints(-1);
            hero.setOpportunities(-1);
            hero.setXP(-1);
            hero.setSecretIdentity("Failed Identity");
        }




        return hero;
    }

    //get all heroes
    public List<Hero> getAllHeroes() {
        List<Hero> heroList = new ArrayList<Hero>();
        //Select All query
        String selectQuery = "SELECT * FROM " + TABLE_HERO;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        //Loop through rows and add each to list
        if (cursor.moveToFirst()) {
            do {
                Hero hero = new Hero();
                hero.setID(Integer.parseInt(cursor.getString(0)));
                hero.setHeroName(cursor.getString(1));
                //add hero to list
                heroList.add(hero);
            } while (cursor.moveToNext());
        }

        return heroList;
    }

    //count how many heroes there are
    public int getHeroesCount() {
        String countQuery = "SELECT * FROM " + TABLE_HERO;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        //cursor.close();

        return cursor.getCount();
    }

    //Update hero record
    public int updateHero(Hero hero) {
        SQLiteDatabase db = this.getWritableDatabase();

        //Add values to update record
        ContentValues values = new ContentValues();
        if (hero.getPlotPoints() > -1) {
            values.put(KEY_PLOT_POINTS, hero.getPlotPoints());
        }
        if (hero.getOpportunities() > -1) {
            values.put(KEY_OPPORTUNITIES, hero.getOpportunities());
        }
        if (hero.getXP() > -1) {
            values.put(KEY_XP, hero.getXP());
        }

        //Update the row
        return db.update(TABLE_HERO, values, KEY_ID + " =? ", new String[] {String.valueOf(hero.getID())});
    }

}