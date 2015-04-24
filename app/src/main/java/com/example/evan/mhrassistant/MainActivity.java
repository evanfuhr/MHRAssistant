package com.example.evan.mhrassistant;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

        if (db.getHeroesCount() == 0) {
            Log.d("Insert: ", "Inserting ...");
            db.addHero(new Hero("Iron Man", 2, 0, 25, "Tony Stark"));
            db.addAffiliation(new Affiliation(1, 10, 6, 8));
            db.addDistinction(new Distinction(1, "Hardheaded Futurist", "Bleeding Edge Tech", "Billionaire Playboy"));

            db.addHero(new Hero("Legacy", 2, 1, 20, "Paul Parsons"));
            db.addAffiliation(new Affiliation(2, 10, 6, 8));
            db.addDistinction(new Distinction(2, "America's Greatest Legacy", "Loving Father", "Shadow of a Tyrant"));

            db.addHero(new Hero("Tempest", 1, 0, 13, "M’kk Dall’ton"));
            db.addAffiliation(new Affiliation(3, 6, 8, 10));
            db.addDistinction(new Distinction(3, "Inhuman", "The Resistance", "Gene-Bound Destiny"));

            db.addHero(new Hero("Visionary", 4, 1, 4, "Vanessa Long"));
            db.addAffiliation(new Affiliation(4, 10, 6, 8));
            db.addDistinction(new Distinction(4, "From the Future", "Telepath at Birth", "Government Experiment"));

            db.addHero(new Hero("Ra", 0, 1, 1, "Blake Washington"));
            db.addAffiliation(new Affiliation(5, 8, 6, 10));
            db.addDistinction(new Distinction(5, "Egyptian Sun God", "Imbued with Fire", "Cursed Existence"));
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
        hero_list.removeAllViews();
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

            registerForContextMenu(hero_button);
        }
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        menu.add(0, v.getId(), 0, "Edit");
        menu.add(0, v.getId(), 1, "Delete");
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        DatabaseHelper db = new DatabaseHelper(this);
        String action = (String) item.getTitle();
        switch (action) {
            case "Edit":
                //Toast.makeText(this, "Edit hero name", Toast.LENGTH_SHORT).show();
                editHeroName(item.getItemId());
                break;
            case "Delete":
                db.deleteHero(item.getItemId());
                generateHeroButtonList();
                break;
            default:
                Toast.makeText(this, "Nothing selected", Toast.LENGTH_SHORT).show();
        }
        return true;
    }

    protected void editHeroName(int id) {
        //Setup hero object for update
        final DatabaseHelper db = new DatabaseHelper(this);
        final Hero hero = new Hero();
        hero.setID(id);

        // get prompts.xml view
        LayoutInflater layoutInflater = LayoutInflater.from(MainActivity.this);
        View promptView = layoutInflater.inflate(R.layout.edit_text_prompt, null);
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(MainActivity.this);
        alertDialogBuilder.setView(promptView);

        final EditText editText = (EditText) promptView.findViewById(R.id.editText_edit_text);
        // setup a dialog window
        alertDialogBuilder.setCancelable(false)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        //resultText.setText("Hello, " + editText.getText());
                        hero.setHeroName(editText.getText().toString());
                        db.updateHero(hero);
                        generateHeroButtonList();
                    }
                })
                .setNegativeButton("Cancel",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });

        // create an alert dialog
        AlertDialog alert = alertDialogBuilder.create();
        alert.show();
    }
}
