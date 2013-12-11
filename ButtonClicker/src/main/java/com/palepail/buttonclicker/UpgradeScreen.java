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

import java.util.ArrayList;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import android.widget.TextView;

public class UpgradeScreen extends ActionBarActivity {

    Clicks clicks;
    ClicksPerSecond CPS;
    ArrayList<Upgrade> upgrades= new ArrayList<Upgrade>();
    ArrayList<TextView> costs = new ArrayList<TextView>();


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
        if (upgrades.size() == 0) {
           upgrades = Upgrade.getUpgrades();
        }

        if (scheduleTaskExecutor == null) {
            scheduleTaskExecutor = ButtonActivity.getScheduleTaskExecutor();
        }
        costs.add((TextView)(findViewById(R.id.upgrade1Cost)));
        costs.add((TextView)(findViewById(R.id.upgrade2Cost)));
        costs.add((TextView)(findViewById(R.id.upgrade3Cost)));


        doOnce();
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

    private void doOnce(){
        ArrayList<TextView> values = new ArrayList<TextView>();
        ArrayList<TextView> names = new ArrayList<TextView>();

        values.add((TextView)(findViewById(R.id.upgrade1Value)));
        values.add((TextView)(findViewById(R.id.upgrade2Value)));
        values.add((TextView)(findViewById(R.id.upgrade3Value)));


        names.add((TextView)(findViewById(R.id.upgrade1Text)));
        names.add((TextView)(findViewById(R.id.upgrade2Text)));
        names.add((TextView)(findViewById(R.id.upgrade3Text)));

        for(Upgrade u : upgrades)
        {
            values.get(upgrades.indexOf(u)).setText("Value: "+ u.getValue());
            names.get(upgrades.indexOf(u)).setText(u.getName());
        }

    }

    public void buyUpgrade1(View v) {
        if (upgrades.get(0).getCost() <= clicks.getClicks()) {
            upgrades.get(0).buyUpgrade();
            updateCosts();
            updateCosts();
            updateCounter();
        }
    }

    public void buyUpgrade2(View v) {
        if (upgrades.get(1).getCost() <= clicks.getClicks()) {
            upgrades.get(1).buyUpgrade();
            updateCosts();
            updateCosts();
            updateCounter();
        }
    }

    private void updateCounter() {
        TextView counter = (TextView) findViewById(R.id.upgradeCounter);
        counter.setText(String.valueOf(clicks.getClicks()));
        counter = (TextView) findViewById(R.id.upgradeCPS);
        counter.setText(String.valueOf(CPS.getCPS() + " CPS"));
    }

    private void updateCosts() {

       for(Upgrade u:upgrades)
       {
             costs.get(upgrades.indexOf(u)).setText(String.valueOf(u.getCost()));
       }

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
