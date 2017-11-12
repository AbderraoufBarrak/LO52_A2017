package utbm.fr.ffbad.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import utbm.fr.ffbad.R;
import utbm.fr.ffbad.entity.PurchaseLine;

/**
 * Created by Antoine on 12/11/2017.
 */

public class PurchaseListAdapter extends BaseAdapter {

    private List<PurchaseLine> purchaseLines;
    private static LayoutInflater inflate = null;
    private Context context;

    public PurchaseListAdapter(Context ctx, List<PurchaseLine> purchaseLines) {
        this.purchaseLines = purchaseLines;
        this.context = ctx;
        inflate = (LayoutInflater) ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return purchaseLines.size();
    }

    @Override
    public Object getItem(int i) {
        return purchaseLines.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View vi = view;
        if(vi==null){
            vi = inflate.inflate(R.layout.purchase_line_layout,null);
        }

        TextView txtView = (TextView) vi.findViewById(R.id.label);
        ImageView icon = (ImageView) vi.findViewById((R.id.icon));
        PurchaseLine line = this.purchaseLines.get(i);
        txtView.setText(line.getBuyerName() +" - " + line.getQuantity() + " x " + line.getRef() +" (" + line.getPrice() +" €)");
        icon.setImageDrawable(context.getDrawable(line.isPaid() ? R.drawable.greenlight : R.drawable.redlight));

        return vi;
    }
}
