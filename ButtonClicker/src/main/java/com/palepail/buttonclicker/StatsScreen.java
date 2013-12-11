package com.palepail.buttonclicker;

import android.support.v7.app.ActionBarActivity;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import java.util.Date;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.joda.time.DateTime;
import org.joda.time.Period;
import org.joda.time.format.PeriodFormatter;
import org.joda.time.format.PeriodFormatterBuilder;


public class StatsScreen extends ActionBarActivity {
    DateTime startTime;
    Clicks clicks;
    Period period;

    PeriodFormatter periodFormat = new PeriodFormatterBuilder()
            .appendDays()
            .appendSuffix("d")
            .appendSeparator(" ")
            .appendMinutes()
            .appendSuffix("m")
            .appendSeparator(" ")
            .appendSeconds()
            .appendSuffix("s")
            .toFormatter();


    private ScheduledExecutorService scheduleTaskExecutor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stats);

        if (scheduleTaskExecutor == null) {
            scheduleTaskExecutor = ButtonActivity.getScheduleTaskExecutor();
        }

        if (startTime == null) {
            startTime = Button.getFirstClick();
        }
        if (clicks == null) {
            clicks = Clicks.getClicksObject();
        }
        updateStats();

        // This schedule a task to run every 1 second:
        scheduleTaskExecutor.scheduleAtFixedRate(new Runnable() {
            public void run() {
                if (startTime!=null) {
                    period = period.plusSeconds(1);
                }
                // If you need update UI, simply do this:
                runOnUiThread(new Runnable() {
                    public void run() {
                        // update your UI component here.
                        repeatUpdateStats();

                    }
                });
            }
        }, 0, 1, TimeUnit.SECONDS);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new PlaceholderFragment())
                    .commit();
        }
    }


    private void updateStats() {
        TextView view = (TextView) findViewById(R.id.manualClicks);
        view.setText(String.valueOf(Button.getManualClicks()));
        period = new Period(startTime, new DateTime());
        view = (TextView) findViewById(R.id.timeDisplay);
        view.setText(period.toString());
    }

    private void repeatUpdateStats() {
        TextView view = (TextView) findViewById(R.id.totalClicks);
        view.setText(String.valueOf(clicks.getClicks()));


        view = (TextView) findViewById(R.id.timeDisplay);
        view.setText(periodFormat.print(period.normalizedStandard()));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.stats, menu);
        return true;
    }


    @Override
    protected void onRestart() {
        super.onRestart();

        repeatUpdateStats();
    }

    @Override
    protected void onResume() {
        super.onResume();

        repeatUpdateStats();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch (item.getItemId()) {
            case R.id.action_settings:
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_stats, container, false);
            return rootView;
        }
    }

}
