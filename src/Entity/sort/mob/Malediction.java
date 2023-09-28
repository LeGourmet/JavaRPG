// Blanchard Allan
package Entity.sort.mob;

import Entity.Entity;
import Entity.sort.Sort;

public class Malediction extends Sort {
    // ------------------------------- constructor -------------------------------
    public Malediction() {
        super("Malédiction", 20, 0);
    }

    // --------------------------------- methode ----------------------------------
    public boolean effect(Entity lanceur, Entity vise) {
        if (removeMana(lanceur)) {
            rollCrit();
            boolean reussite = (Math.random() < 0.7);
            lanceur.takeDammage(lanceur.getVie() / (critique ? (reussite ? 6 : 2) : 4));
            vise.takeDammage(reussite ? vise.getVie() / 3 : 0);
            return true;
        }
        return false;
    }
}
