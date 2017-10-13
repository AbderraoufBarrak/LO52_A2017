package fr.utbm.lpp.ffbad;

import android.os.Bundle;
import android.support.design.widget.NavigationView;

public class Purchase extends BasicActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stock);    //TODO : change the layout with activity_purchase (doesn't work yet)

        setupDrawerLayout();
    }


}
