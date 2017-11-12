package utbm.fr.ffbad.entity;

/**
 * Created by Antoine on 12/11/2017.
 */

public class PurchaseLine{

    private String buyerName;

    private Double price;

    private Integer quantity;

    private String ref;

    private boolean Paid;

    public PurchaseLine(String buyerName, Double price, Integer quantity, String ref, boolean Paid) {
        this.buyerName = buyerName;
        this.price = price;
        this.quantity = quantity;
        this.ref = ref;
        this.Paid = Paid;
    }

    public String getBuyerName() {
        return buyerName;
    }

    public Double getPrice() {
        return price;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public String getRef() {
        return ref;
    }

    public boolean isPaid() {
        return Paid;
    }
}
