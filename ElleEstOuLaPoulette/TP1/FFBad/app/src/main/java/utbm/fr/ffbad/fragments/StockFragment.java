package utbm.fr.ffbad.fragments;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.List;

import utbm.fr.ffbad.R;
import utbm.fr.ffbad.adapter.StockListAdapter;
import utbm.fr.ffbad.db.DbHelper;
import utbm.fr.ffbad.entity.StockLine;

/**
 * Created by Antoine on 11/11/2017.
 */

public class StockFragment extends Fragment {

    private Button achats_btn;

    private ListView stock_listView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        DbHelper dbHelper = new DbHelper(getActivity(),DbHelper.DATABASE_NAME,null,1);
        Log.v("TEST",dbHelper.getDatabaseName());

        //Retrieving widgets
        this.achats_btn = (Button) getActivity().findViewById(R.id.achats_btn);
        this.stock_listView = (ListView) getActivity().findViewById(R.id.stock_listView);

        //Populating the listView
        List<StockLine> stockLines = dbHelper.getStock();
        ListAdapter adapter = new StockListAdapter(getActivity(), stockLines);
        this.stock_listView.setAdapter(adapter);


        return inflater.inflate(R.layout.stock_layout,container,false);

    }

}
