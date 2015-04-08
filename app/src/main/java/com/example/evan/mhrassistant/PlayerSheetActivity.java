package com.example.evan.mhrassistant;

import android.app.ActionBar;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class PlayerSheetActivity extends ActionBarActivity {

    Hero _hero;

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

        TextView textView = (TextView) findViewById(R.id.textView);
        textView.setText((CharSequence) _hero.getHeroName());

        Toast.makeText(this, "Hero " + hero_id, Toast.LENGTH_SHORT).show();
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

    void setOtherData() {
        //int plot_points_count = _hero.get_plot_points();
        EditText plot_points = (EditText) findViewById(R.id.editText_plot_points_count);
        plot_points.setText((CharSequence) Integer.toString(_hero.get_plot_points()));
    }
}
