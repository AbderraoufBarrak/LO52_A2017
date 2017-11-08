package fr.utbm.lpp.ffbad.activity.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import fr.utbm.lpp.ffbad.R;


public class InfoFragment extends Fragment {
    public static InfoFragment newInstance() {
        Bundle args = new Bundle();
        InfoFragment fragment = new InfoFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_info, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();
        setInfo();
    }

    private void setInfo() {
        TextView text = getView().findViewById(R.id.text_info);
        text.setText("This app was made by LesPetitesPedestres !");
    }
}
