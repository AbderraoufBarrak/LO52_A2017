package fr.utbm.lpp.ffbad.activity.fragment;

import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ListFragment;

import fr.utbm.lpp.ffbad.FFBadApplication;
import fr.utbm.lpp.ffbad.data.adapter.ShuttlecockCursorAdapter;
import fr.utbm.lpp.ffbad.data.db.FFBadDbContract;

public class StockListFragment extends ListFragment {

    public static StockListFragment newInstance() {
        Bundle args = new Bundle();
        StockListFragment fragment = new StockListFragment();
        fragment.setArguments(args);
        return fragment;
    }

//    @Override
//    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        View v = inflater.inflate(R.layout.fragment_listview, container, false);
////
////        setupListView(v);
//
//        return v;
//    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setupListView();
    }

    private void setupListView() {

        String[] projection = {
                FFBadDbContract.Shuttlecock._ID,
                FFBadDbContract.Shuttlecock.COLUMN_NAME_BRAND,
                FFBadDbContract.Shuttlecock.COLUMN_NAME_REFERENCE,
                FFBadDbContract.Shuttlecock.COLUMN_NAME_STOCK,
                FFBadDbContract.Shuttlecock.COLUMN_NAME_RATING
        };

        FFBadApplication app = (FFBadApplication) getActivity().getApplication();

        Cursor cursor = app.getDb().query(
                FFBadDbContract.Shuttlecock.TABLE_NAME,
                projection,
                null,
                null,
                null,
                null,
                null
        );

        ShuttlecockCursorAdapter cursorAdapter = new ShuttlecockCursorAdapter(this.getContext(), cursor);
        setListAdapter(cursorAdapter);

//        ListView list = (ListView) v.findViewById(R.id.listView);
//        list.setAdapter(cursorAdapter);
    }
}
