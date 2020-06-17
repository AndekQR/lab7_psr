package model;

import java.util.List;

public class Brigades {
    private List<FireBrigade> brigades;

    public Brigades() {
    }

    public List<FireBrigade> getBrigades() {
        return brigades;
    }

    public void setBrigades(List<FireBrigade> brigades) {
        this.brigades=brigades;
    }
}
