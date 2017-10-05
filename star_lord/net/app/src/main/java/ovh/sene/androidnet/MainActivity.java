package ovh.sene.androidnet;

import java.util.List;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;

public class MainActivity extends ListActivity {
    private TubeDataSource datasource;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        datasource = new TubeDataSource(this);
        datasource.open();

        List<Tube> values = datasource.getAllTube();

        // utilisez SimpleCursorAdapter pour afficher les
        // éléments dans une ListView
        ArrayAdapter<Tube> adapter = new ArrayAdapter<Tube>(this, android.R.layout.simple_list_item_1, values);
        setListAdapter(adapter);
    }

    // Sera appelée par l'attribut onClick
    // des boutons déclarés dans main.xml
    public void onClick(View view) {
        @SuppressWarnings("unchecked")
        ArrayAdapter<Tube> adapter = (ArrayAdapter<Tube>) getListAdapter();
        Tube tube = null;
        switch (view.getId()) {
            case R.id.add:
                // enregistrer le nouveau commentaire dans la base de données
                EditText editText = (EditText) findViewById(R.id.editText);
                tube = datasource.createTube(editText.getText().toString(), editText.getText().toString(), editText.getText().toString());
                adapter.add(tube);
                break;
            case R.id.delete:
                if (getListAdapter().getCount() > 0) {
                    tube = (Tube) getListAdapter().getItem(0);
                    datasource.deleteTube(tube);
                    adapter.remove(tube);
                }
                break;
            case R.id.infos:
                Intent intent = new Intent(this, DisplayMessageActivity.class);
                startActivity(intent);
                break;
        }
        adapter.notifyDataSetChanged();
    }

    @Override
    protected void onResume() {
        datasource.open();
        super.onResume();
    }

    @Override
    protected void onPause() {
        datasource.close();
        super.onPause();
    }
}