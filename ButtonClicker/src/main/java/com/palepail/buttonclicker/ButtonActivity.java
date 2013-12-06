package com.palepail.buttonclicker;

import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.content.Intent;
import android.widget.TextView;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import android.os.Build;

public class ButtonActivity extends ActionBarActivity {

    Clicks clicks;
    Button button;
    ClicksPerSecond CPS;
    private static ScheduledExecutorService scheduleTaskExecutor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.button_main);

        if (clicks == null) {
            clicks = Clicks.getClicksObject();
        }
        if (CPS == null) {
            CPS = ClicksPerSecond.getCPSObject();
        }
        button = new Button();


        scheduleTaskExecutor = Executors.newScheduledThreadPool(5);

        // This schedule a task to run every 1 second:
        scheduleTaskExecutor.scheduleAtFixedRate(new Runnable() {
            public void run() {

                clicks.addClicks(CPS.getCPS());
                // If you need update UI, simply do this:
                runOnUiThread(new Runnable() {
                    public void run() {
                        // update your UI component here.
                        updateCounter();
                        updateCPS();
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

    public static ScheduledExecutorService getScheduleTaskExecutor() {
        return scheduleTaskExecutor;
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.button, menu);
        return true;
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

    public void buttonClicked(View v) {
        clicks.addClicks(button.getValue());
        updateCounter();
    }

    public void goToUpgrades(View v) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setClassName(this, UpgradeScreen.class.getName());

        this.startActivity(intent);
    }

    private void updateCounter() {
        TextView counter = (TextView) findViewById(R.id.mainCounter);
        counter.setText(String.valueOf(clicks.getClicks()));
    }
    private void updateCPS() {
        TextView counter = (TextView) findViewById(R.id.CPSView);
        counter.setText(String.valueOf(CPS.getCPS()) + " CPS");
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
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);
            return rootView;
        }
    }

}
