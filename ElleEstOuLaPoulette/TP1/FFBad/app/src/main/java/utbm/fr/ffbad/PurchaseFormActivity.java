package utbm.fr.ffbad;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import utbm.fr.ffbad.db.DbHelper;
import utbm.fr.ffbad.entity.Purchase;
import utbm.fr.ffbad.entity.PurchaseLine;

public class PurchaseFormActivity extends AppCompatActivity {

    private EditText buyerEdit;
    private EditText qteEdit;
    private String tubeRef;
    private Double prix;
    private boolean paid = false;
    private DbHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_purchase_form);
        ((TextView)findViewById(R.id.tubeRefTxtView)).setText("Ajouter une commande");
        buyerEdit = (EditText)findViewById(R.id.buyerEditText);
        qteEdit = (EditText)findViewById(R.id.qtyEditText);
        tubeRef = getIntent().getStringExtra("TUBE_REF");
        prix = getIntent().getDoubleExtra("PRICE",0.00);
        dbHelper = new DbHelper(this,DbHelper.DATABASE_NAME,null,1);
    }

    public void submit(View v){
        final String buyer = buyerEdit.getText().toString();
        final String qte = qteEdit.getText().toString();
        final String[] ref = new String[1];
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Choisissez la référence de la commande");
        final EditText input = new EditText(this);
        input.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_FLAG_CAP_CHARACTERS);
        builder.setView(input);
        builder.setPositiveButton("PAYE", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                paid = true;
                ref[0] = input.getText().toString();
                PurchaseLine purchaseLine = new PurchaseLine(prix,Integer.parseInt(qte),tubeRef,ref[0]);
                Purchase purchase = new Purchase(paid, buyer, ref[0]);
                purchase.addLine(purchaseLine);
                dbHelper.registerPurchase(purchase);
            }
        });
        builder.setNegativeButton("NON PAYE", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                paid = false;
                ref[0] = input.getText().toString();
                PurchaseLine purchaseLine = new PurchaseLine(prix,Integer.parseInt(qte),tubeRef,ref[0]);
                Purchase purchase = new Purchase(paid, buyer, ref[0]);
                purchase.addLine(purchaseLine);
                dbHelper.registerPurchase(purchase);
            }
        });
        builder.show();
    }
}
