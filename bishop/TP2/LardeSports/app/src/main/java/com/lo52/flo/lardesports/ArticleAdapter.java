package com.lo52.flo.lardesports;

/**
 * Created by Flo on 23.10.2017.
 */

import java.util.LinkedList;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Classe permettant l'affichage du stock via un ArrayAdapter
 */
public class ArticleAdapter extends ArrayAdapter<Article> {

    private final Context context;
    private LinkedList<Article> articles;

    public ArticleAdapter(Context context, int resource, LinkedList<Article> articles) {
        super(context, resource, articles);
        this.context = context;
        this.articles = articles;
    }

    /**
     * Fonction surchargée permettant l'affichage
     * @param position
     * @param convertView
     * @param parent
     * @return
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.article_layout, parent, false);
        } else {
            convertView = (LinearLayout) convertView;
        }

        ImageView imageView = (ImageView) convertView.findViewById(R.id.picture_article);
        imageView.setBackgroundResource(articles.get(position).getPicture());

        TextView viewMarque = (TextView) convertView.findViewById(R.id.marque_article);
        viewMarque.setText(articles.get(position).getMarque());
        viewMarque.setTag(articles.get(position).getMarque());

        TextView viewRef = (TextView) convertView.findViewById(R.id.ref_article);
        viewRef.setText(articles.get(position).getRef());
        viewRef.setTag(articles.get(position).getRef());

        TextView viewStock = (TextView) convertView.findViewById(R.id.stock_article);
        viewStock.setText(String.valueOf(articles.get(position).getQuantite()));
        viewStock.setTag(String.valueOf(articles.get(position).getQuantite()));

        return convertView;
    }
}
