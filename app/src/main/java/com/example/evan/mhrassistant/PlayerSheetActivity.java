package com.example.evan.mhrassistant;

import android.app.FragmentManager;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;


public class PlayerSheetActivity extends ActionBarActivity
implements OnHeroSelectedListener{

    int _hero_id;
    Hero _hero;
    Affiliation _affiliation;
    Distinction _distinction;
    StressTrauma _stressTrauma;

    SeekBar seekBarStressPhysical;

    @Override
    protected void onDestroy() {
        super.onDestroy();
        saveData();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player_sheet);

        //Get hero id passed to this activity
        Intent intent = getIntent();
        _hero_id = intent.getIntExtra(MainActivity.HERO_ID, 0);
        onHeroSelected(_hero_id);

        //Get the hero
        DatabaseHelper db = new DatabaseHelper(this);
        _hero = db.getSingleHero(_hero_id);
        _affiliation = db.getAffiliation(_hero_id);
        _distinction = db.getDistinction(_hero_id);
        _stressTrauma = db.getStressTrauma(_hero_id);

        TextView textView = (TextView) findViewById(R.id.textView);
        textView.setText((CharSequence) _hero.getHeroName());


        seekBarStressPhysical = (SeekBar) findViewById(R.id.seekBar_stress_physical);


        Toast.makeText(this, "Hero " + _hero_id, Toast.LENGTH_SHORT).show();
        //setAffiliations();
        //setDistinctions();
        //setStress();
        //setTrauma();
        //setOtherData();
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
        saveData();
        Toast toast = Toast.makeText(getApplicationContext(), "Saving...", Toast.LENGTH_LONG);
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
        //Find the seekbars
        //SeekBar seekBarStressPhysical = (SeekBar) findViewById(R.id.seekBar_stress_physical);
        SeekBar seekBarStressMental = (SeekBar) findViewById(R.id.seekBar_stress_mental);
        SeekBar seekBarStressEmotional = (SeekBar) findViewById(R.id.seekBar_stress_emotional);

        //Find the value textviews
        final TextView textViewStressPhysical = (TextView) findViewById(R.id.textView_stress_value_physical);
        final TextView textViewStressMental = (TextView) findViewById(R.id.textView_stress_value_mental);
        final TextView textViewStressEmotional = (TextView) findViewById(R.id.textView_stress_value_emotional);

        //Set the seekbars to have listeners
        seekBarStressPhysical.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean b) {
                changeStressTrauma(textViewStressPhysical, progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        seekBarStressMental.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean b) {
                changeStressTrauma(textViewStressMental, progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        seekBarStressEmotional.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean b) {
                changeStressTrauma(textViewStressEmotional, progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        //Set the initial progress
        seekBarStressPhysical.setProgress(_stressTrauma.getStressPhysical());
        seekBarStressMental.setProgress(_stressTrauma.getStressMental());
        seekBarStressEmotional.setProgress(_stressTrauma.getStressEmotional());
    }

    void setTrauma() {
        SeekBar traumaPhysical = (SeekBar) findViewById(R.id.seekBar_trauma_physical);
        SeekBar traumaMental = (SeekBar) findViewById(R.id.seekBar_trauma_mental);
        SeekBar traumaEmotional = (SeekBar) findViewById(R.id.seekBar_trauma_emotional);

        traumaPhysical.setProgress(_stressTrauma.getTraumaPhysical());
        traumaMental.setProgress(_stressTrauma.getTraumaMental());
        traumaEmotional.setProgress(_stressTrauma.getTraumaEmotional());
    }

    void setOtherData() {
        //int plot_points_count = _hero.getPlotPoints();
        EditText plot_points = (EditText) findViewById(R.id.editText_plot_points_count);
        plot_points.setText((CharSequence) Integer.toString(_hero.getPlotPoints()));

        EditText opportunities = (EditText) findViewById(R.id.editText_opportunities_count);
        opportunities.setText(Integer.toString(_hero.getOpportunities()));

        EditText xp = (EditText) findViewById(R.id.editText_experience_count);
        xp.setText((Integer.toString(_hero.getXP())));
    }

    void changeStressTrauma(TextView textView, int value) {
        String display_value;

        switch(value) {
            case 0:
                display_value = "";
                break;
            case 1:
                display_value = "d4";
                break;
            case 2:
                display_value = "d6";
                break;
            case 3:
                display_value = "d8";
                break;
            case 4:
                display_value = "d10";
                break;
            case 5:
                display_value = "d12";
                break;
            default:
                display_value = "";
        }

        textView.setText(display_value);
    }

    void saveData() {
        DatabaseHelper db = new DatabaseHelper(this);

        EditText plot_points = (EditText) findViewById(R.id.editText_plot_points_count);
        EditText opportunities = (EditText) findViewById(R.id.editText_opportunities_count);
        EditText xp = (EditText) findViewById(R.id.editText_experience_count);

        //SeekBar seekBarStressPhysical = (SeekBar) findViewById(R.id.seekBar_stress_physical);
        SeekBar stressMental = (SeekBar) findViewById(R.id.seekBar_stress_mental);
        SeekBar stressEmotional = (SeekBar) findViewById(R.id.seekBar_stress_emotional);
        SeekBar traumaPhysical = (SeekBar) findViewById(R.id.seekBar_trauma_physical);
        SeekBar traumaMental = (SeekBar) findViewById(R.id.seekBar_trauma_mental);
        SeekBar traumaEmotional = (SeekBar) findViewById(R.id.seekBar_trauma_emotional);

        _hero.setPlotPoints(Integer.parseInt(String.valueOf(plot_points.getText())));
        _hero.setOpportunities(Integer.parseInt(String.valueOf(opportunities.getText())));
        _hero.setXP(Integer.parseInt(String.valueOf(xp.getText())));

        _stressTrauma.setStressPhysical(seekBarStressPhysical.getProgress());
        _stressTrauma.setStressMental(stressMental.getProgress());
        _stressTrauma.setStressEmotional(stressEmotional.getProgress());
        _stressTrauma.setTraumaPhysical(traumaPhysical.getProgress());
        _stressTrauma.setTraumaMental(traumaMental.getProgress());
        _stressTrauma.setTraumaEmotional(traumaEmotional.getProgress());

        db.updateHero(_hero);
        db.updateStressTrauma(_stressTrauma);
    }

    @Override
    public void onHeroSelected(int hero_id) {

    }
}
