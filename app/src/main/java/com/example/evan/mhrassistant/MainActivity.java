package com.example.evan.mhrassistant;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.List;


public class MainActivity extends ActionBarActivity {
    public static final String HERO_ID = "heroID";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DatabaseHelper db = new DatabaseHelper(this);
        //Insert sample hero
        if (db.getHeroesCount() == 0) {
            Log.d("Insert: ", "Inserting ..");
            db.addHero(new Hero("Legacy", 2));
            db.addHero(new Hero("Tempest", 1));
            db.addHero(new Hero("Visionary", 4));
            db.addHero(new Hero("Ra", 0));
        }



        generateHeroButtonList();
    }

    private void onClickButtonPlayerSheet(View view) {
        //Get the ID associated to the clicked button
        int hero_id = view.getId();

        //Build the intent to load the player sheet
        Intent intent = new Intent(this, PlayerSheetActivity.class);
        //Load the hero ID to send to the player sheet
        intent.putExtra(HERO_ID, hero_id);

        startActivity(intent);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        boolean handled = true;
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        switch(id) {
            case R.id.action_show_player_sheet:
                onClickMenuPlayerSheet(item);
                break;
            case R.id.action_settings:
                onClickMenuSettings(item);
                break;
            case R.id.action_save:
                onClickMenuSave(item);
                break;
            case R.id.action_close:
                onClickMenuClose(item);
                break;
            default:
                return super.onOptionsItemSelected(item);
        }

        return handled;
    }

    void onClickMenuPlayerSheet(MenuItem item) {
        Intent intent = new Intent(this, PlayerSheetActivity.class);
        startActivity(intent);
    }

    void onClickMenuSettings(MenuItem item) {
        Toast toast = Toast.makeText(getApplicationContext(), "Settings not implemented yet", Toast.LENGTH_LONG);
        toast.show();
    }

    void onClickMenuSave(MenuItem item) {
        Toast toast = Toast.makeText(getApplicationContext(), "Save not implemented yet", Toast.LENGTH_LONG);
        toast.show();
    }

    void onClickMenuClose(MenuItem item) {
        finish();
    }

    void generateHeroButtonList() {
        final LinearLayout hero_list = (LinearLayout) findViewById(R.id.LinearLayout_hero_list);

        //Open db
        DatabaseHelper db = new DatabaseHelper(this);

        //Determine how many heroes there are
        int hero_count = db.getHeroesCount();

        List<Hero> heroes = db.getAllHeroes();

        for (Hero hero : heroes) {

            //Create hero button
            final Button hero_button = new Button(this);
            hero_button.setText(hero.getHeroName());
            //Use the hero id from the hero table when that part is built
            //noinspection ResourceType
            hero_button.setId(hero.getID());
            //Set click listener for the button
            hero_button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onClickButtonPlayerSheet(view);
                }
            });

            //Add the hero button to the table row
            hero_list.addView(hero_button);
        }

    }
}
