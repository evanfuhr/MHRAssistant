package com.example.evan.mhrassistant;

import android.app.Fragment;
import android.os.Bundle;
//import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class HeroTraitsFragment extends Fragment {

    int _hero_id;

    //Models
    Hero _hero;
    Affiliation _affiliation;
    Distinction _distinction;
    StressTrauma _stressTrauma;

    //Views
    RadioButton _solo;
    RadioButton _buddy;
    RadioButton _team;

    TextView _distinction_1;
    TextView _distinction_2;
    TextView _distinction_3;

    TextView _textViewStressPhysical;
    TextView _textViewStressMental;
    TextView _textViewStressEmotional;

    SeekBar _seekBarStressPhysical;
    SeekBar _seekBarStressMental;
    SeekBar _seekBarStressEmotional;

    TextView _textViewTraumaPhysical;
    TextView _textViewTraumaMental;
    TextView _textViewTraumaEmotional;

    SeekBar _seekBarTraumaPhysical;
    SeekBar _seekBarTraumaMental;
    SeekBar _seekBarTraumaEmotional;

    EditText _plot_points;
    EditText _opportunities;
    EditText _xp;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //seekBarStressPhysical = (SeekBar) getView().findViewById(R.id.seekBar_stress_physical);
    }

    //@Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View theView = inflater.inflate(R.layout.fragment_hero_traits, container, false);

        _solo = (RadioButton) theView.findViewById(R.id.radioButton_affiliations_solo);
        _buddy = (RadioButton) theView.findViewById(R.id.radioButton_affiliations_buddy);
        _team = (RadioButton) theView.findViewById(R.id.radioButton_affiliations_team);

        _distinction_1 = (TextView) theView.findViewById(R.id.textView_distinction1);
        _distinction_2 = (TextView) theView.findViewById(R.id.textView_distinction2);
        _distinction_3 = (TextView) theView.findViewById(R.id.textView_distinction3);

        _textViewStressPhysical = (TextView) theView.findViewById(R.id.textView_stress_value_physical);
        _textViewStressMental = (TextView) theView.findViewById(R.id.textView_stress_value_mental);
        _textViewStressEmotional = (TextView) theView.findViewById(R.id.textView_stress_value_emotional);

        _seekBarStressPhysical = (SeekBar) theView.findViewById(R.id.seekBar_stress_physical);
        _seekBarStressMental = (SeekBar) theView.findViewById(R.id.seekBar_stress_mental);
        _seekBarStressEmotional = (SeekBar) theView.findViewById(R.id.seekBar_stress_emotional);

        _textViewTraumaPhysical = (TextView) theView.findViewById(R.id.textView_trauma_value_physical);
        _textViewTraumaMental = (TextView) theView.findViewById(R.id.textView_trauma_value_mental);
        _textViewTraumaEmotional = (TextView) theView.findViewById(R.id.textView_trauma_value_emotional);

        _seekBarTraumaPhysical = (SeekBar) theView.findViewById(R.id.seekBar_trauma_physical);
        _seekBarTraumaMental = (SeekBar) theView.findViewById(R.id.seekBar_trauma_mental);
        _seekBarTraumaEmotional = (SeekBar) theView.findViewById(R.id.seekBar_trauma_emotional);

        _plot_points = (EditText) theView.findViewById(R.id.editText_plot_points_count);
        _opportunities = (EditText) theView.findViewById(R.id.editText_opportunities_count);
        _xp = (EditText) theView.findViewById(R.id.editText_experience_count);

        return theView;
    }

    public void setHero(int hero_id) {
        _hero_id = hero_id;
        //Get the hero
        DataBaseHelper db = new DataBaseHelper(getActivity());
        _hero = db.getSingleHero(_hero_id);
        _affiliation = db.getAffiliation(_hero_id);
        _distinction = db.getDistinction(_hero_id);
        _stressTrauma = db.getStressTrauma(_hero_id);

        Toast.makeText(getActivity(), "Hero " + _hero_id, Toast.LENGTH_SHORT).show();
        setAffiliations();
        setDistinctions();
        setStress();
        setTrauma();
        setOtherData();
    }

    void setAffiliations() {
        _solo.setText(Integer.toString(_affiliation.getSolo()));
        _buddy.setText(Integer.toString(_affiliation.getBuddy()));
        _team.setText(Integer.toString(_affiliation.getTeam()));
    }

    void setDistinctions() {
        _distinction_1.setText(_distinction.getDistinction1());
        _distinction_2.setText(_distinction.getDistinction2());
        _distinction_3.setText(_distinction.getDistinction3());
    }

    void setStress() {
        //Set the seekbars to have listeners
        _seekBarStressPhysical.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean b) {
                changeStressTrauma(_textViewStressPhysical, progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        _seekBarStressMental.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean b) {
                changeStressTrauma(_textViewStressMental, progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        _seekBarStressEmotional.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean b) {
                changeStressTrauma(_textViewStressEmotional, progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        //Set the initial progress
        _seekBarStressPhysical.setProgress(_stressTrauma.getStressPhysical());
        _seekBarStressMental.setProgress(_stressTrauma.getStressMental());
        _seekBarStressEmotional.setProgress(_stressTrauma.getStressEmotional());
    }

    void setTrauma() {
        //Set the seekbars to have listeners
        _seekBarTraumaPhysical.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean b) {
                changeStressTrauma(_textViewTraumaPhysical, progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        _seekBarTraumaMental.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean b) {
                changeStressTrauma(_textViewTraumaMental, progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        _seekBarTraumaEmotional.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean b) {
                changeStressTrauma(_textViewTraumaEmotional, progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        //Set the initial progress
        _seekBarTraumaPhysical.setProgress(_stressTrauma.getTraumaPhysical());
        _seekBarTraumaMental.setProgress(_stressTrauma.getTraumaMental());
        _seekBarTraumaEmotional.setProgress(_stressTrauma.getTraumaEmotional());
    }

    void setOtherData() {
        //int plot_points_count = _hero.getPlotPoints();
        _plot_points.setText((CharSequence) Integer.toString(_hero.getPlotPoints()));
        _opportunities.setText(Integer.toString(_hero.getOpportunities()));
        _xp.setText((Integer.toString(_hero.getXP())));
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
}
