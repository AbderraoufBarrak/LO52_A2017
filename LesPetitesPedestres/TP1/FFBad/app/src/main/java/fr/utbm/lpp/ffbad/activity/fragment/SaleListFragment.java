package fr.utbm.lpp.ffbad.activity.fragment;


import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import fr.utbm.lpp.ffbad.FFBadApplication;
import fr.utbm.lpp.ffbad.R;
import fr.utbm.lpp.ffbad.data.adapter.SaleCursorAdapter;

public class SaleListFragment extends ListFragment {

    public static final String TAG = "SaleListFragment";

    private OnSaleListFragmentEvent eventManager;

    public static SaleListFragment newInstance() {
        Bundle args = new Bundle();
        SaleListFragment fragment = new SaleListFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_listview, container, false);
        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        this.eventManager = (OnSaleListFragmentEvent) getActivity();
        setupListView();

        getView().findViewById(R.id.fab_add).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                eventManager.onNewSaleElement();
            }
        });
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        eventManager.onEditSaleElement(id);
    }

    private void setupListView() {
        final String MY_QUERY = "" +
                "SELECT * " +
                "FROM sale " +
                "INNER JOIN shuttlecock ON shuttlecock._id = sale.shuttlecock_id " +
                "INNER JOIN customer ON customer._id = sale.customer_id ";

        Log.d("SQL", MY_QUERY);
        FFBadApplication app = (FFBadApplication) getActivity().getApplication();
        Cursor cursor = app.getDb().rawQuery(MY_QUERY, null);

        SaleCursorAdapter cursorAdapter = new SaleCursorAdapter(getContext(), cursor);

        setListAdapter(cursorAdapter);
//        cursor.close();
    }

    public interface OnSaleListFragmentEvent {
        /**
         * Event triggered for creating a new element
         */
        void onNewSaleElement();

        /**
         * Event triggered for editing an element
         * @param id the id of the sale to edit
         */
        void onEditSaleElement(long id);
    }
}
