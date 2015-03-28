package com.example.evan.mhrassistant;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final TableLayout heroTable = (TableLayout) findViewById(R.id.TableLayout_hero_table);

        //Rows will be as wide as the table, but wrap content vertically
        TableRow.LayoutParams table_row_params = new TableRow.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        //Buttons will always be aligned to the right of the table
        for (int j=0;j<3;j++) {
            //Create new table row
            TableRow tableRow = new TableRow(this);
            tableRow.setLayoutParams(table_row_params);

            //Create hero textView
            TextView hero_name = new TextView(this);
            hero_name.setText("Hero "+j);
            hero_name.setTextAppearance(this, android.R.style.TextAppearance_Material_Medium);

            //Add the hero name to the table row
            tableRow.addView(hero_name);

            //Create hero button
            final Button hero_button = new Button(this);
            hero_button.setText("GO");
            //Use the hero id from the hero table when that part is built
            hero_button.setId(j+1);
            //Set click listener for the button
            hero_button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onClickButtonPlayerSheet(view);
                }
            });

            //Add the button to the table row
            tableRow.addView(hero_button);

            heroTable.addView(tableRow);

        }
    }

    private void onClickButtonPlayerSheet(View view) {
        Intent intent = new Intent(this, PlayerSheetActivity.class);
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
}
