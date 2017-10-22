package utbm.fr.ffbad.entity;

/**
 * Created by Clément on 20/10/2017.
 */

public class StockLine {

    private Volant volant;
    private Tube tube;

    public StockLine(Volant volant, Tube tube) {
        this.volant = volant;
        this.tube = tube;
    }

    public Volant getVolant() {
        return volant;
    }

    public void setVolant(Volant volant) {
        this.volant = volant;
    }

    public Tube getTube() {
        return tube;
    }

    public void setTube(Tube tube) {
        this.tube = tube;
    }
}
