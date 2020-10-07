package com.atfotiad.simplealarm;

import android.app.AlarmManager;
import android.app.Dialog;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.format.DateFormat;

import java.util.Calendar;
import java.util.Locale;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.preference.PreferenceManager;

import static android.content.Context.ALARM_SERVICE;
import static java.lang.String.format;

public class TimePickerFragment extends DialogFragment {
    private int hour;
    private int minute;
    private String id;

    public TimePickerFragment(String id) {
        this.id = id;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String savedTime = PreferenceManager.getDefaultSharedPreferences(requireActivity()).getString(id, "12:00");
        assert savedTime != null;
        String[] timePieces = savedTime.split(":");
        hour = Integer.parseInt(timePieces[0]);
        minute = Integer.parseInt(timePieces[1]);
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        return new TimePickerDialog(getActivity(), (view, hourOfDay, minute) -> {
            SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(requireContext());
            Calendar calendar = Calendar.getInstance();
            switch (id) {
                case "time_monday":
                    calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
                    break;
                case "time_tuesday":
                    calendar.set(Calendar.DAY_OF_WEEK, Calendar.TUESDAY);
                    break;
                case "time_wednesday":
                    calendar.set(Calendar.DAY_OF_WEEK, Calendar.WEDNESDAY);
                    break;
                case "time_thursday":
                    calendar.set(Calendar.DAY_OF_WEEK, Calendar.THURSDAY);
                    break;
                case "time_friday":
                    calendar.set(Calendar.DAY_OF_WEEK, Calendar.FRIDAY);
                    break;
                case "time_saturday":
                    calendar.set(Calendar.DAY_OF_WEEK, Calendar.SATURDAY);
                    break;
                case "time_sunday":
                    calendar.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
                    break;
            }
            calendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
            calendar.set(Calendar.MINUTE, minute);
            calendar.set(Calendar.SECOND, 0);
            if (calendar.before(Calendar.getInstance())) {
                calendar.add(Calendar.DATE, 7);
            }
            String time = format(Locale.getDefault(), "%02d", hourOfDay) + ":" + format(Locale.getDefault(),
                    "%02d", minute);

            Intent intent = new Intent(getContext(), AlertReceiver.class);
            intent.putExtra("day", id);
            AlarmManager alarmManager = (AlarmManager) requireActivity().getSystemService(ALARM_SERVICE);

            switch (id) {
                case "time_monday":
                    PendingIntent pendingIntent1 = PendingIntent.getBroadcast(getContext(), 1, intent, PendingIntent.FLAG_UPDATE_CURRENT);
                    alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), AlarmManager.INTERVAL_DAY * 7, pendingIntent1);
                    sharedPreferences.edit().putString("time_monday", time).apply();
                    break;
                case "time_tuesday":
                    PendingIntent pendingIntent2 = PendingIntent.getBroadcast(getContext(), 2, intent, PendingIntent.FLAG_UPDATE_CURRENT);
                    alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), AlarmManager.INTERVAL_DAY * 7, pendingIntent2);
                    sharedPreferences.edit().putString("time_tuesday", time).apply();
                    break;
                case "time_wednesday":
                    PendingIntent pendingIntent3 = PendingIntent.getBroadcast(getContext(), 3, intent, PendingIntent.FLAG_UPDATE_CURRENT);
                    alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), AlarmManager.INTERVAL_DAY * 7, pendingIntent3);
                    sharedPreferences.edit().putString("time_wednesday", time).apply();
                    break;
                case "time_thursday":
                    PendingIntent pendingIntent4 = PendingIntent.getBroadcast(getContext(), 4, intent, PendingIntent.FLAG_UPDATE_CURRENT);
                    alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), AlarmManager.INTERVAL_DAY * 7, pendingIntent4);
                    sharedPreferences.edit().putString("time_thursday", time).apply();
                    break;
                case "time_friday":
                    PendingIntent pendingIntent5 = PendingIntent.getBroadcast(getContext(), 5, intent, PendingIntent.FLAG_UPDATE_CURRENT);
                    alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), AlarmManager.INTERVAL_DAY * 7, pendingIntent5);
                    sharedPreferences.edit().putString("time_friday", time).apply();
                    break;
                case "time_saturday":
                    PendingIntent pendingIntent6 = PendingIntent.getBroadcast(getContext(), 6, intent, PendingIntent.FLAG_UPDATE_CURRENT);
                    alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), AlarmManager.INTERVAL_DAY * 7, pendingIntent6);
                    sharedPreferences.edit().putString("time_saturday", time).apply();
                    break;
                default:
                    PendingIntent pendingIntent7 = PendingIntent.getBroadcast(getContext(), 7, intent, PendingIntent.FLAG_UPDATE_CURRENT);
                    alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), AlarmManager.INTERVAL_DAY * 7, pendingIntent7);
                    sharedPreferences.edit().putString("time_sunday", time).apply();
                    break;
            }
        }, hour, minute, DateFormat.is24HourFormat(getActivity()));
    }

}

