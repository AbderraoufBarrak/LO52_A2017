package fr.utbm.lpp.ffbad.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import fr.utbm.lpp.ffbad.FFBadApplication;
import fr.utbm.lpp.ffbad.R;
import fr.utbm.lpp.ffbad.activity.fragment.InfoFragment;
import fr.utbm.lpp.ffbad.activity.fragment.SaleFormFragment;
import fr.utbm.lpp.ffbad.activity.fragment.SaleListFragment;
import fr.utbm.lpp.ffbad.activity.fragment.StockListFragment;
import fr.utbm.lpp.ffbad.data.db.FFbadDbHelper;


public class MainActivity extends AppCompatActivity
    implements NavigationView.OnNavigationItemSelectedListener, SaleListFragment.OnSaleListFragmentEvent,
        SaleFormFragment.OnSaleFormFragmentEvent {
    public static final String TAG = "MainActivity";

    private DrawerLayout drawer;

    private NavigationView navigationView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        navigationView = (NavigationView) findViewById(R.id.nav_view);

        setupDrawerLayout();

        if (savedInstanceState == null) {
            setupInitialFragment();
        }
    }

    protected void setupDrawerLayout() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(this);
    }

    private void setupInitialFragment() {
        navigationView.getMenu().getItem(0).setChecked(true);
        onNavigationItemSelected(navigationView.getMenu().getItem(0));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if (id == R.id.action_reset) {
            FFBadApplication app = (FFBadApplication) getApplication();
            FFbadDbHelper.resetEntries(app.getDb());
            setupInitialFragment();
            return true;
        } else if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.nav_operations) {
            setFragment(SaleListFragment.newInstance());
        } else if (id == R.id.nav_stock) {
            setFragment(StockListFragment.newInstance());
        } else if (id == R.id.nav_purchase) {
            setFragment(SaleFormFragment.newInstance());
        } else if (id == R.id.nav_info) {
            setFragment(InfoFragment.newInstance());
        }
        return true;
    }

    private void setFragment(Fragment fragment, boolean addToStack) {
        if (!addToStack) {
            clearFragmentStack();
        }
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.container, fragment);
        transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_CLOSE);
        if(addToStack) {
            transaction.addToBackStack(null);
        }
        transaction.commit();
        drawer.closeDrawer(GravityCompat.START);
    }

    private void setFragment(Fragment fragment) {
        setFragment(fragment, false);
    }

    private void clearFragmentStack() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
    }

    @Override
    public void onNewSaleElement() {
        setFragment(SaleFormFragment.newInstance(), true);
    }

    @Override
    public void onEditSaleElement(long id) {
        // FIXME: 03/11/17 Incorrect id due to multiple '_id' columns with JOIN
        setFragment(SaleFormFragment.newInstance(id), true);
    }

    @Override
    public void onSaleFormCompleted() {
        setFragment(SaleListFragment.newInstance());
    }
}
