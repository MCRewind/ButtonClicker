package com.palepail.buttonclicker;

import android.support.v7.app.ActionBarActivity;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.palepail.buttonclicker.Upgrades.*;

import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import android.widget.TextView;

public class UpgradeScreen extends ActionBarActivity {

    Clicks clicks;
    ClicksPerSecond CPS;
    Upgrade upgrade1= new Upgrade1();
    ButtonActivity buttonActivity;
    private ScheduledExecutorService scheduleTaskExecutor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upgrade);

        if (clicks == null) {
            clicks = Clicks.getClicksObject();
        }
        if (CPS == null) {
            CPS = ClicksPerSecond.getCPSObject();
        }
        if (upgrade1 == null) {
            upgrade1 = Upgrade1.getUpgradeObject();
        }
        if (scheduleTaskExecutor == null) {
            scheduleTaskExecutor = ButtonActivity.getScheduleTaskExecutor();
        }

        updateCosts();


        // This schedule a task to run every 1 second:
        scheduleTaskExecutor.scheduleAtFixedRate(new Runnable() {
            public void run() {
                // If you need update UI, simply do this:
                runOnUiThread(new Runnable() {
                    public void run() {
                        // update your UI component here.
                        updateCounter();
                        updateCosts();
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

    @Override
    protected void onRestart() {
        super.onRestart();
        updateCounter();
        updateCosts();
    }

    @Override
    protected void onResume() {
        super.onResume();
        updateCounter();
        updateCosts();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.upgrade_screen, menu);
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

    public void buyUpgrade1(View v) {
        if (upgrade1.getCost() <= clicks.getClicks()) {
            upgrade1.buyUpgrade();
            updateCosts();
        }
    }

    private void updateCounter() {
        TextView counter = (TextView) findViewById(R.id.upgradeCounter);
        counter.setText(String.valueOf(clicks.getClicks()));
    }

    private void updateCosts() {
        TextView cost = (TextView) findViewById(R.id.upgrade1Cost);
        if (upgrade1.getLevel() > 0) {
            cost.setText(String.valueOf(upgrade1.getCost()));
        } else
            cost.setText(String.valueOf((upgrade1.getCost() * upgrade1.getCostGrowth()) * upgrade1.getLevel()));
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
            View rootView = inflater.inflate(R.layout.fragment_upgrade, container, false);
            return rootView;
        }
    }

}
