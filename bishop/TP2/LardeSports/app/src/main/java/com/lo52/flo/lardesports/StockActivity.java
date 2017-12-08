package com.lo52.flo.lardesports;

import android.graphics.drawable.GradientDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import static android.graphics.drawable.GradientDrawable.Orientation.BOTTOM_TOP;

/**
 * Activité permettant l'affichage des stocks
 */
public class StockActivity extends AppCompatActivity {

    /**
     * Fonction appelée lors de la création de l'activité
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stock);

        android.support.v7.app.ActionBar ab = getSupportActionBar();
        ab.setBackgroundDrawable(new GradientDrawable(BOTTOM_TOP, new int[]{0xFFA796FF, 0xFF3529FF}));
        ab.setTitle("Stocks");

        LinkedList<Article> articles = new LinkedList<Article>();

        HashMap<Integer, Integer> listArticles = MainActivity.bdd.getStockArticles();

        for(Map.Entry<Integer, Integer> article : listArticles.entrySet()) {
            Integer article_id = article.getKey();
            Integer qte = article.getValue();

            ArticleBDD articleBdd = MainActivity.bdd.getArticleBDDById(article_id);
            String marque = MainActivity.bdd.getMarqueById(articleBdd.getMarque()).getLibelle();

            articles.add(new Article(articleBdd.getRef(), R.drawable.product_test, marque, qte));
        }

        ArticleAdapter adapter = new ArticleAdapter(getApplicationContext(), R.layout.article_layout, articles);
        ListView list_articles = (ListView) findViewById(R.id.list_articles);
        list_articles.setAdapter(adapter);
        list_articles.setOnItemClickListener(listview_listener);
    }

    AdapterView.OnItemClickListener listview_listener = new AdapterView.OnItemClickListener() {

        @Override
        public void onItemClick(AdapterView<?> parent, final View view, int position, long id) {
            //View titleView = view.findViewById(R.id.title);
            //String title = (String) titleView.getTag();
            //Toast.makeText(getApplicationContext(), title, Toast.LENGTH_SHORT).show();
        }
    };
}
