package fr.utbm.lpp.ffbad.data;

public class Sale {
    private long id;
    private Customer customer;
    private Shuttlecock shuttlecock;
    private int quantity;
    private boolean isPaid;

    public Sale(long id, Customer customer, Shuttlecock shuttlecock, int quantity, boolean isPaid) {
        setId(id);
        setCustomer(customer);
        setShuttlecock(shuttlecock);
        setQuantity(quantity);
        setPaid(isPaid);
    }

    public long getId() {
        return id;
    }

    private void setId(long id) {
        this.id = id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Shuttlecock getShuttlecock() {
        return shuttlecock;
    }

    public void setShuttlecock(Shuttlecock shuttlecock) {
        this.shuttlecock = shuttlecock;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        if (quantity < 0) {
            this.quantity = 0;
        } else {
            this.quantity = quantity;
        }
    }

    public boolean isPaid() {
        return isPaid;
    }

    public void setPaid(boolean paid) {
        isPaid = paid;
    }

    @Override
    public String toString() {
        return "[id=" + id + ", quantity=" + getQuantity() + ", isPaid=" + isPaid() + ", customer=" + getCustomer() + ", shuttlecock=" + getShuttlecock() + "]";
    }
}
