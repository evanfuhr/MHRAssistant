
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
    private static final String TABLE_AFFILIATION = "affiliation";
    private static final String TABLE_DISTINCTION = "distinction";
    private static final String TABLE_STRESS_TRAUMA = "stress_trauma";

    //Common column names
    private static final String KEY_ID = "id";

    //Hero Table - column names
    private static final String KEY_HERO_NAME = "hero_name";
    private static final String KEY_PLOT_POINTS = "plot_points";
    private static final String KEY_OPPORTUNITIES = "opportunities";
    private static final String KEY_XP = "xp";
    private static final String KEY_HERO_SECRET_IDENTITY = "hero_secret_identity";

    //Affiliation Table - column names
    private static final String KEY_SOLO = "solo";
    private static final String KEY_BUDDY = "buddy";
    private static final String KEY_TEAM = "team";

    //Distinction Table - column names
    private static final String KEY_DISTINCTION_1 = "distinction_1";
    private static final String KEY_DISTINCTION_2 = "distinction_2";
    private static final String KEY_DISTINCTION_3 = "distinction_3";

    //StressTrauma Table - column names
    private static final String KEY_STRESS_PHYSICAL = "stress_physical";
    private static final String KEY_STRESS_MENTAL = "stress_mental";
    private static final String KEY_STRESS_EMOTIONAL = "stress_emotional";
    private static final String KEY_TRAUMA_PHYSICAL = "trauma_physical";
    private static final String KEY_TRAUMA_MENTAL = "trauma_mental";
    private static final String KEY_TRAUMA_EMOTIONAL = "trauma_emotional";

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

    //Affiliation Table create statement
    public static final String CREATE_AFFILIATION_TABLE = "CREATE TABLE " + TABLE_AFFILIATION + "("
            + KEY_ID + " INTEGER PRIMARY KEY"
            + "," + KEY_SOLO + " INTEGER"
            + "," + KEY_BUDDY + " INTEGER"
            + "," + KEY_TEAM + " INTEGER"
            + ")";

    //Distinction Table create statement
    public static final String CREATE_DISTINCTION_TABLE = "CREATE TABLE " + TABLE_DISTINCTION + "("
            + KEY_ID + " INTEGER PRIMARY KEY"
            + "," + KEY_DISTINCTION_1 + " TEXT"
            + "," + KEY_DISTINCTION_2 + " TEXT"
            + "," + KEY_DISTINCTION_3 + " TEXT"
            + ")";

    //StressTrauma Table create statement
    public static final String CREATE_STRESS_TRAUMA_TABLE = "CREATE TABLE " + TABLE_STRESS_TRAUMA + "("
            + KEY_ID + " INTEGER PRIMARY KEY"
            + "," + KEY_STRESS_PHYSICAL + " INTEGER"
            + "," + KEY_STRESS_MENTAL + " INTEGER"
            + "," + KEY_STRESS_EMOTIONAL + " INTEGER"
            + "," + KEY_TRAUMA_PHYSICAL + " INTEGER"
            + "," + KEY_TRAUMA_MENTAL + " INTEGER"
            + "," + KEY_TRAUMA_EMOTIONAL + " INTEGER"
            + ")";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_HERO);
        db.execSQL(CREATE_AFFILIATION_TABLE);
        db.execSQL(CREATE_DISTINCTION_TABLE);
        db.execSQL(CREATE_STRESS_TRAUMA_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_HERO);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_AFFILIATION);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_DISTINCTION);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_STRESS_TRAUMA);

        onCreate(db);
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

    //Read a single hero
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

    //Read all heroes
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

    //Read how many heroes there are
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
        if (hero.getXP() > -9999) {
            values.put(KEY_XP, hero.getXP());
        }
        if (!hero.getHeroName().isEmpty()) {
            values.put(KEY_HERO_NAME, hero.getHeroName());
        }

        //Update the row
        return db.update(TABLE_HERO, values, KEY_ID + " =? ", new String[]{String.valueOf(hero.getID())});
    }

    //Delete hero record
    public void deleteHero(int id) {
        SQLiteDatabase db = this.getWritableDatabase();

        db.delete(TABLE_HERO, KEY_ID + " =? ",
                new String[] {String.valueOf(id)});
    }


    //------------------------ Affiliation Table methods ------------------------------------//

    //Create affiliation set
    public void addAffiliation(Affiliation affiliation) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_ID, affiliation.getID());
        values.put(KEY_SOLO, affiliation.getSolo());
        values.put(KEY_BUDDY, affiliation.getBuddy());
        values.put(KEY_TEAM, affiliation.getTeam());

        //insert the row
        db.insert(TABLE_AFFILIATION, null, values);
        db.close();
    }

    //Read affiliation set
    public Affiliation getAffiliation(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Affiliation affiliation = new Affiliation();

        //Raw query
        String selectQuery = "SELECT * FROM " + TABLE_AFFILIATION + " WHERE " + KEY_ID + " = " + id;
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            affiliation.setSolo(Integer.parseInt(cursor.getString(1)));
            affiliation.setBuddy(Integer.parseInt(cursor.getString(2)));
            affiliation.setTeam(Integer.parseInt(cursor.getString(3)));
        } else {
            affiliation.setSolo(-6);
            affiliation.setBuddy(-8);
            affiliation.setTeam(-10);
        }

        return affiliation;
    }

    //Delete affiliation record
    public void deleteAffiliation(int id) {
        SQLiteDatabase db = this.getWritableDatabase();

        db.delete(TABLE_AFFILIATION, KEY_ID + " =? ",
                new String[] {String.valueOf(id)});
    }

    //------------------------ Distinction Table methods ------------------------------------//

    //Create distinction set
    public void addDistinction(Distinction distinction) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_ID, distinction.getID());
        values.put(KEY_DISTINCTION_1, distinction.getDistinction1());
        values.put(KEY_DISTINCTION_2, distinction.getDistinction2());
        values.put(KEY_DISTINCTION_3, distinction.getDistinction3());

        //insert the row
        db.insert(TABLE_DISTINCTION, null, values);
        db.close();
    }

    //Read distinction set
    public Distinction getDistinction(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Distinction distinction = new Distinction();

        //Raw query
        String selectQuery = "SELECT * FROM " + TABLE_DISTINCTION + " WHERE " + KEY_ID + " = " + id;
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            distinction.setDistinction1(cursor.getString(1));
            distinction.setDistinction2(cursor.getString(2));
            distinction.setDistinction3(cursor.getString(3));
        } else {
            distinction.setDistinction1("Failed 1");
            distinction.setDistinction2("Failed 2");
            distinction.setDistinction3("Failed 3");
        }

        return distinction;
    }

    //Delete distinction record
    public void deleteDistinction(int id) {
        SQLiteDatabase db = this.getWritableDatabase();

        db.delete(TABLE_DISTINCTION, KEY_ID + " =? ",
                new String[] {String.valueOf(id)});
    }

    //--------------------- Stress and Trauma Table methods ---------------------------------//

    //Create stress and trauma set
    public void addStressAndTrauma(int id) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_ID, id);
        values.put(KEY_STRESS_PHYSICAL, 0);
        values.put(KEY_STRESS_MENTAL, 0);
        values.put(KEY_STRESS_EMOTIONAL, 0);
        values.put(KEY_TRAUMA_PHYSICAL, 0);
        values.put(KEY_TRAUMA_MENTAL, 0);
        values.put(KEY_TRAUMA_EMOTIONAL, 0);

        //insert the row
        db.insert(TABLE_STRESS_TRAUMA, null, values);
        db.close();
    }

    //Read stress and trauma
    public StressTrauma getStressTrauma(int id) {
        SQLiteDatabase db = this.getWritableDatabase();

        StressTrauma stressTrauma = new StressTrauma();

        String selectQuery = "SELECT * FROM " + TABLE_STRESS_TRAUMA + " WHERE " + KEY_ID + " = " + id;
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            stressTrauma.setStressPhysical(Integer.parseInt(cursor.getString(1)));
            stressTrauma.setStressMental(Integer.parseInt(cursor.getString(2)));
            stressTrauma.setStressEmotional(Integer.parseInt(cursor.getString(3)));

            stressTrauma.setTraumaPhysical(Integer.parseInt(cursor.getString(4)));
            stressTrauma.setTraumaMental(Integer.parseInt(cursor.getString(5)));
            stressTrauma.setTraumaEmotional(Integer.parseInt(cursor.getString(6)));
        } else {
            addStressAndTrauma(id);
            cursor = db.rawQuery(selectQuery, null);

            stressTrauma.setStressPhysical(Integer.parseInt(cursor.getString(1)));
            stressTrauma.setStressMental(Integer.parseInt(cursor.getString(2)));
            stressTrauma.setStressEmotional(Integer.parseInt(cursor.getString(3)));

            stressTrauma.setTraumaPhysical(Integer.parseInt(cursor.getString(4)));
            stressTrauma.setTraumaMental(Integer.parseInt(cursor.getString(5)));
            stressTrauma.setTraumaEmotional(Integer.parseInt(cursor.getString(6)));

        }

        return stressTrauma;
    }

    //Update stress and trauma
    public int updateStressTrauma(StressTrauma stressTrauma) {
        SQLiteDatabase db = this.getWritableDatabase();

        //Add values to update record
        ContentValues values = new ContentValues();
        if (stressTrauma.getStressPhysical() > -1) {
            values.put(KEY_STRESS_PHYSICAL, stressTrauma.getStressPhysical());
        }
        if (stressTrauma.getStressMental() > -1) {
            values.put(KEY_STRESS_MENTAL, stressTrauma.getStressMental());
        }
        if (stressTrauma.getStressEmotional() > -1) {
            values.put(KEY_STRESS_EMOTIONAL, stressTrauma.getStressEmotional());
        }
        if (stressTrauma.getTraumaPhysical() > -1) {
            values.put(KEY_TRAUMA_PHYSICAL, stressTrauma.getTraumaPhysical());
        }
        if (stressTrauma.getTraumaMental() > -1) {
            values.put(KEY_TRAUMA_MENTAL, stressTrauma.getTraumaMental());
        }
        if (stressTrauma.getTraumaEmotional() > -1) {
            values.put(KEY_TRAUMA_EMOTIONAL, stressTrauma.getTraumaEmotional());
        }

        //Update the row
        return db.update(TABLE_STRESS_TRAUMA, values, KEY_ID + " =? ", new String[]{String.valueOf(stressTrauma.getID())});
    }
}