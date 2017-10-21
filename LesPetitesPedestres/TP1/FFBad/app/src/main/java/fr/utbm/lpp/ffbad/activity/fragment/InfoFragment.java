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
        TextView flatIconsLicense = getView().findViewById(R.id.text_license_flaticons);
        String content = getContext().getString(R.string.license_flaticons);
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            flatIconsLicense.setText(Html.fromHtml(content, Html.FROM_HTML_MODE_LEGACY));
        } else {
            flatIconsLicense.setText(Html.fromHtml(content));
        }
    }
}
