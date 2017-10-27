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
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import fr.utbm.lpp.ffbad.R;
import fr.utbm.lpp.ffbad.activity.fragment.BuyFormFragment;
import fr.utbm.lpp.ffbad.activity.fragment.InfoFragment;
import fr.utbm.lpp.ffbad.activity.fragment.SaleListFragment;
import fr.utbm.lpp.ffbad.activity.fragment.StockListFragment;


public class MainActivity extends AppCompatActivity
    implements NavigationView.OnNavigationItemSelectedListener, SaleListFragment.OnSaleListFragmentEvent {
    public static final String TAG = "MainActivity";

    private DrawerLayout drawer;

    private NavigationView navigationView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        navigationView = (NavigationView) findViewById(R.id.nav_view);

        setContentView(R.layout.activity_main);
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
        getMenuInflater().inflate(R.menu.stock, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        Log.d(TAG, "" + id + ", " + R.id.nav_operations);
        if (id == R.id.nav_operations) {
            setFragment(SaleListFragment.newInstance());
        } else if (id == R.id.nav_stock) {
            setFragment(StockListFragment.newInstance());
        } else if (id == R.id.nav_purchase) {
            setFragment(BuyFormFragment.newInstance());
        } else if (id == R.id.nav_info) {
            setFragment(InfoFragment.newInstance());
        }
        return true;
    }

    private void setFragment(Fragment fragment, boolean addToStack) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.container, fragment);
        if(addToStack) {
            transaction.addToBackStack(null);
        }
        transaction.commit();
        drawer.closeDrawer(GravityCompat.START);
    }

    private void setFragment(Fragment fragment) {
        setFragment(fragment, false);
    }

    @Override
    public void onNewSaleElement() {
        setFragment(BuyFormFragment.newInstance(), true);
    }

    @Override
    public void onEditSaleElement(long id) {
        // TODO
        setFragment(BuyFormFragment.newInstance(), true);
    }
}
