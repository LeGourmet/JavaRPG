// Blanchard Allan
package Entity.sort.mob;

import Entity.Entity;
import Entity.sort.Sort;

public class Rvie extends Sort {
    // ------------------------------- constructor -------------------------------
    public Rvie() {
        super("Régénération de vie", 20, 25);
    }

    // --------------------------------- methode ----------------------------------
    public boolean effect(Entity lanceur, Entity vise) {
        if (removeMana(lanceur)) {
            return (lanceur.heal((int) ((dommage / 100.) * lanceur.getMvie())) != -1);
        }
        return false;
    }
}
