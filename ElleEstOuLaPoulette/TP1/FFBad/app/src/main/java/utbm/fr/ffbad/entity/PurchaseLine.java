package utbm.fr.ffbad.entity;

/**
 * Created by Antoine on 12/11/2017.
 */

public class PurchaseLine{

    private Double price;

    private Integer quantity;

    private String ref;

    private String cmdRef;

    public PurchaseLine( Double price, Integer quantity, String refTube, String cmdRef) {
        this.price = price;
        this.quantity = quantity;
        this.ref = refTube;
        this.cmdRef = cmdRef;
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

    public String getCmdRef(){return this.cmdRef;}
}
