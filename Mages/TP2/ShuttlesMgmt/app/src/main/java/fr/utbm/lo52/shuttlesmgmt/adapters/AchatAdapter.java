package fr.utbm.lo52.shuttlesmgmt.adapters;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import fr.utbm.lo52.shuttlesmgmt.R;
import fr.utbm.lo52.shuttlesmgmt.models.AchatModel;


public class AchatAdapter extends RecyclerView.Adapter<AchatAdapter.MyViewHolder> {

    private List<AchatModel> achatsList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView acheteur, prix, quantite, reference;
        public LinearLayout payer;

        public MyViewHolder(View view) {
            super(view);
            acheteur = (TextView) view.findViewById(R.id.acheteur_tv);
            prix = (TextView) view.findViewById(R.id.prix_tv);
            quantite = (TextView) view.findViewById(R.id.quantite_tv);
            reference = (TextView) view.findViewById(R.id.reference_tv);
            payer = view.findViewById(R.id.payer_lv);
        }
    }


    public AchatAdapter(List<AchatModel> achatsList) {
        this.achatsList = achatsList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.achat_list_model, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        AchatModel acheter = achatsList.get(position);
        holder.acheteur.setText(acheter.getAcheteur());
        holder.prix.setText(acheter.getPrix());
        holder.quantite.setText(acheter.getQuantite());
        holder.reference.setText(acheter.getReference());
        if(acheter.getPayer() == true) holder.payer.setBackgroundColor(Color.parseColor("#41D6C0"));
        else holder.payer.setBackgroundColor(Color.parseColor("#FB3C3F"));

    }

    @Override
    public int getItemCount() {
        return achatsList.size();
    }
}
