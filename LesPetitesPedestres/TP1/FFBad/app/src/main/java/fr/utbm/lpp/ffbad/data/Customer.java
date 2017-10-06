package fr.utbm.lpp.ffbad.data;

public class Customer {
    private long id;
    private String name;
    private CustomerType type;

    public Customer(long id, String name, CustomerType type) {
        setId(id);
        setName(name);
        setType(type);
    }

    public long getId() {
        return id;
    }

    private void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CustomerType getType() {
        return type;
    }

    public void setType(CustomerType type) {
        this.type = type;
    }
}
