package ovh.sene.androidnet;

public class Tube {
    private long id;
    private String tubeName;
    private String tubePrice;
    private String tubeRate;

    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }

    public String getTubeName() {
        return tubeName;
    }
    public void setTubeName(String tubeName) {
        this.tubeName = tubeName;
    }

    public String getTubePrice() {
        return tubePrice;
    }
    public void setTubePrice(String tubePrice) {
        this.tubePrice = tubePrice;
    }

    public String getTubeRate() {
        return tubeRate;
    }
    public void setTubeRate(String tubeRate) {
        this.tubeRate = tubeRate;
    }


    // Sera utilisée par ArrayAdapter dans la ListView
    @Override
    public String toString() {
        return tubeName;
    }
}
