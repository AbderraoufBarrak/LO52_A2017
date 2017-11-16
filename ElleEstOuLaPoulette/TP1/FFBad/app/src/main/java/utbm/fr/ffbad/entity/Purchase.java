package utbm.fr.ffbad.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by heros on 16/11/2017.
 */

public class Purchase {

    private boolean isPaid;
    private String cmdRef;
    private String buyer;
    private Double totalPrice;
    private List<PurchaseLine> purchaseLines;

    public Purchase(boolean isPaid, String buyer, String cmdRef) {
        this.isPaid = isPaid;
        this.buyer = buyer;
        this.totalPrice = 0.0;
        this.purchaseLines = new ArrayList<>();
        this.cmdRef = cmdRef;
    }

    public boolean isPaid() {
        return isPaid;
    }

    public String getBuyer() {
        return buyer;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public List<PurchaseLine> getPurchaseLines() {
        return purchaseLines;
    }

    public String getCmdRef() {
        return cmdRef;
    }

    public void addLine(PurchaseLine line){
        this.purchaseLines.add(line);
        totalPrice += line.getPrice();
    }
}
