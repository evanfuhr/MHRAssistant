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

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //saveData();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player_sheet);

        //Get hero id passed to this activity
        Intent intent = getIntent();
        _hero_id = intent.getIntExtra(MainActivity.HERO_ID, 0);
        onHeroSelected(_hero_id);
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
        //saveData();
        Toast toast = Toast.makeText(getApplicationContext(), "Saving...", Toast.LENGTH_LONG);
        toast.show();
    }

    void onClickMenuClose(MenuItem item) {
        finish();
    }


    /*void saveData() {
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
    }*/

    @Override
    public void onHeroSelected(int hero_id) {
        FragmentManager fm = getFragmentManager();
        HeroTraitsFragment heroTraitsFragment =
                (HeroTraitsFragment) fm.findFragmentById(R.id.heroTraitsFragment);
        heroTraitsFragment.setHero(_hero_id);
    }
}
