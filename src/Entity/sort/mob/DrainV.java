// Blanchard Allan
package Entity.sort.mob;

import Entity.Entity;
import Entity.sort.Sort;

public class DrainV extends Sort {
    // ------------------------------- constructor -------------------------------
    public DrainV() {
        super("Drain de vie", 50, 15);
    }

    // --------------------------------- methode ----------------------------------
    public boolean effect(Entity lanceur, Entity vise) {
        if (removeMana(lanceur)) {
            rollCrit();
            int tmp = (int) (2. * (lanceur.getNiveau() + dommage) * (critique ? 2. : 1.));
            lanceur.heal((int) (2. * tmp / (critique ? 2. : 3.)));
            vise.takeDammage(tmp);
            return true;
        }
        return false;
    }
}
