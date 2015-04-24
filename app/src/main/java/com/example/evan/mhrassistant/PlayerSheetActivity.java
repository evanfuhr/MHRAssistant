package com.example.evan.mhrassistant;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;


public class PlayerSheetActivity extends ActionBarActivity {

    Hero _hero;
    Affiliation _affiliation;
    Distinction _distinction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player_sheet);

        //Get hero id passed to this activity
        Intent intent = getIntent();
        int hero_id = intent.getIntExtra(MainActivity.HERO_ID, 0);

        //Get the hero
        DatabaseHelper db = new DatabaseHelper(this);
        _hero = db.getSingleHero(hero_id);
        _affiliation = db.getAffiliation(hero_id);
        _distinction = db.getDistinction(hero_id);

        TextView textView = (TextView) findViewById(R.id.textView);
        textView.setText((CharSequence) _hero.getHeroName());

        Toast.makeText(this, "Hero " + hero_id, Toast.LENGTH_SHORT).show();
        setAffiliations();
        setDistinctions();
        setOtherData();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_player_sheet, menu);
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

    void setAffiliations() {
        RadioButton solo = (RadioButton) findViewById(R.id.radioButton_affiliations_solo);
        RadioButton buddy = (RadioButton) findViewById(R.id.radioButton_affiliations_buddy);
        RadioButton team = (RadioButton) findViewById(R.id.radioButton_affiliations_team);

        solo.setText(Integer.toString(_affiliation.getSolo()));
        buddy.setText(Integer.toString(_affiliation.getBuddy()));
        team.setText(Integer.toString(_affiliation.getTeam()));
    }

    void setDistinctions() {
        TextView distinction_1 = (TextView) findViewById(R.id.textView_distinction1);
        TextView distinction_2 = (TextView) findViewById(R.id.textView_distinction2);
        TextView distinction_3 = (TextView) findViewById(R.id.textView_distinction3);

        distinction_1.setText(_distinction.getDistinction1());
        distinction_2.setText(_distinction.getDistinction2());
        distinction_3.setText(_distinction.getDistinction3());
    }

    void setStress() {

    }

    void setTrauma() {

    }

    void setOtherData() {
        //int plot_points_count = _hero.getPlotPoints();
        EditText plot_points = (EditText) findViewById(R.id.editText_plot_points_count);
        plot_points.setText((CharSequence) Integer.toString(_hero.getPlotPoints()));

        EditText opportunities = (EditText) findViewById(R.id.editText_opportunities_count);
        opportunities.setText( Integer.toString(_hero.getOpportunities()));

        EditText xp = (EditText) findViewById(R.id.editText_experience_count);
        xp.setText((Integer.toString(_hero.getXP())));
    }
}
