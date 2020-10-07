package com.atfotiad.simplealarm;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import java.util.Objects;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;
import androidx.preference.PreferenceManager;

public class SettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings_activity);
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.settings, new SettingsFragment())
                .commit();
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
    }

    public static class SettingsFragment extends PreferenceFragmentCompat {

        SharedPreferences preferences;
        @Override
        public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
            setPreferencesFromResource(R.xml.root_preferences, rootKey);
            preferences = PreferenceManager.getDefaultSharedPreferences(requireContext());

            Preference timePickerMonday = findPreference("time_monday");
            Preference timePickerTuesday = findPreference("time_tuesday");
            Preference timePickerWednesday = findPreference("time_wednesday");
            Preference timePickerThursday = findPreference("time_thursday");
            Preference timePickerFriday = findPreference("time_friday");
            Preference timePickerSaturday = findPreference("time_saturday");
            Preference timePickerSunday = findPreference("time_sunday");


            Objects.requireNonNull(timePickerMonday).setOnPreferenceClickListener(preference -> {
                showTimeDialog("time_monday");
                return true;
            });

            Objects.requireNonNull(timePickerTuesday).setOnPreferenceClickListener(preference -> {
                showTimeDialog("time_tuesday");
                return true;
            });
            Objects.requireNonNull(timePickerWednesday).setOnPreferenceClickListener(preference -> {
                showTimeDialog("time_wednesday");
                return true;
            });

            Objects.requireNonNull(timePickerThursday).setOnPreferenceClickListener(preference -> {
                showTimeDialog("time_thursday");
                return true;
            });

            Objects.requireNonNull(timePickerFriday).setOnPreferenceClickListener(preference -> {
                showTimeDialog("time_friday");
                return true;
            });
            Objects.requireNonNull(timePickerSaturday).setOnPreferenceClickListener(preference -> {
                showTimeDialog("time_saturday");
                return true;
            });
            Objects.requireNonNull(timePickerSunday).setOnPreferenceClickListener(preference -> {
                showTimeDialog("time_sunday");
                return true;
            });



        }

        private void showTimeDialog(String id) {
            switch (id) {
                case "time_monday":
                    new TimePickerFragment("time_monday").show(getParentFragmentManager(), "time picker");
                    break;
                case "time_tuesday":
                    new TimePickerFragment("time_tuesday").show(getParentFragmentManager(), "time picker");
                    break;
                case "time_wednesday":
                    new TimePickerFragment("time_wednesday").show(getParentFragmentManager(), "time picker");
                    break;
                case "time_thursday":
                    new TimePickerFragment("time_thursday").show(getParentFragmentManager(), "time picker");
                    break;
                case "time_friday":
                    new TimePickerFragment("time_friday").show(getParentFragmentManager(), "time picker");
                    break;
                case "time_saturday":
                    new TimePickerFragment("time_saturday").show(getParentFragmentManager(), "time picker");
                    break;
                default:
                    new TimePickerFragment("time_sunday").show(getParentFragmentManager(), "time picker");
                    break;
            }
        }
    }
}