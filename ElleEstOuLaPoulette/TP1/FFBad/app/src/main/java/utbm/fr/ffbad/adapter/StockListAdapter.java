package utbm.fr.ffbad.adapter;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import utbm.fr.ffbad.R;
import utbm.fr.ffbad.entity.StockLine;

/**
 * Created by Clément on 20/10/2017.
 */

public class StockListAdapter extends BaseAdapter {

    private static LayoutInflater inflate = null;
    private Context context;
    private List<StockLine> liste;

    public StockListAdapter(Context ctx, List<StockLine> liste) {
        context = ctx;
        this.liste = liste;
        inflate = (LayoutInflater) ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View vi = convertView;
        if(vi==null){
            vi = inflate.inflate(R.layout.stock_line,null);
        }

        TextView text = (TextView) vi.findViewById(R.id.texteTube);
        ImageView image = (ImageView) vi.findViewById(R.id.imageTube);

        StockLine line = liste.get(position);
        text.setText("Marque : "+line.getVolant().getMarque()+"\t- Reference : "+line.getVolant().getRef()+
                    "\t- Stock : "+line.getTube().getStock());

        Uri.Builder builder = new Uri.Builder();
        builder.path(line.getTube().getImage());
        Uri uri = builder.build();
        image.setImageURI(uri);

        return vi;
    }
}
