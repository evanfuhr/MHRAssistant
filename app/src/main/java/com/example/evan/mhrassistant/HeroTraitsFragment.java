package com.example.evan.mhrassistant;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
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
    Hero _hero;
    Affiliation _affiliation;
    Distinction _distinction;
    StressTrauma _stressTrauma;

    SeekBar seekBarStressPhysical;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Get the hero
        DatabaseHelper db = new DatabaseHelper(getActivity());
        _hero = db.getSingleHero(_hero_id);
        _affiliation = db.getAffiliation(_hero_id);
        _distinction = db.getDistinction(_hero_id);
        _stressTrauma = db.getStressTrauma(_hero_id);


        seekBarStressPhysical = (SeekBar) getView().findViewById(R.id.seekBar_stress_physical);


        Toast.makeText(getActivity(), "Hero " + _hero_id, Toast.LENGTH_SHORT).show();
        setAffiliations();
        setDistinctions();
        setStress();
        setTrauma();
        setOtherData();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View theView = inflater.inflate(R.layout.fragment_hero_traits, container, false);

        return theView;
    }

    void setAffiliations() {
        RadioButton solo = (RadioButton) getView().findViewById(R.id.radioButton_affiliations_solo);
        RadioButton buddy = (RadioButton) getView().findViewById(R.id.radioButton_affiliations_buddy);
        RadioButton team = (RadioButton) getView().findViewById(R.id.radioButton_affiliations_team);

        solo.setText(Integer.toString(_affiliation.getSolo()));
        buddy.setText(Integer.toString(_affiliation.getBuddy()));
        team.setText(Integer.toString(_affiliation.getTeam()));
    }

    void setDistinctions() {
        TextView distinction_1 = (TextView) getView().findViewById(R.id.textView_distinction1);
        TextView distinction_2 = (TextView) getView().findViewById(R.id.textView_distinction2);
        TextView distinction_3 = (TextView) getView().findViewById(R.id.textView_distinction3);

        distinction_1.setText(_distinction.getDistinction1());
        distinction_2.setText(_distinction.getDistinction2());
        distinction_3.setText(_distinction.getDistinction3());
    }

    void setStress() {
        //Find the seekbars
        SeekBar seekBarStressPhysical = (SeekBar) getView().findViewById(R.id.seekBar_stress_physical);
        SeekBar seekBarStressMental = (SeekBar) getView().findViewById(R.id.seekBar_stress_mental);
        SeekBar seekBarStressEmotional = (SeekBar) getView().findViewById(R.id.seekBar_stress_emotional);

        //Find the value textviews
        final TextView textViewStressPhysical = (TextView) getView().findViewById(R.id.textView_stress_value_physical);
        final TextView textViewStressMental = (TextView) getView().findViewById(R.id.textView_stress_value_mental);
        final TextView textViewStressEmotional = (TextView) getView().findViewById(R.id.textView_stress_value_emotional);

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
        SeekBar traumaPhysical = (SeekBar) getView().findViewById(R.id.seekBar_trauma_physical);
        SeekBar traumaMental = (SeekBar) getView().findViewById(R.id.seekBar_trauma_mental);
        SeekBar traumaEmotional = (SeekBar) getView().findViewById(R.id.seekBar_trauma_emotional);

        traumaPhysical.setProgress(_stressTrauma.getTraumaPhysical());
        traumaMental.setProgress(_stressTrauma.getTraumaMental());
        traumaEmotional.setProgress(_stressTrauma.getTraumaEmotional());
    }

    void setOtherData() {
        //int plot_points_count = _hero.getPlotPoints();
        EditText plot_points = (EditText) getView().findViewById(R.id.editText_plot_points_count);
        plot_points.setText((CharSequence) Integer.toString(_hero.getPlotPoints()));

        EditText opportunities = (EditText) getView().findViewById(R.id.editText_opportunities_count);
        opportunities.setText(Integer.toString(_hero.getOpportunities()));

        EditText xp = (EditText) getView().findViewById(R.id.editText_experience_count);
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
}
