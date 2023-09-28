// Blanchard Allan
package Entity.sort.mob;

import Entity.Entity;
import Entity.sort.Sort;

public class DrainM extends Sort {
    // ------------------------------- constructor -------------------------------
    public DrainM() {
        super("Drain de mana", 0, 0);
    }

    // --------------------------------- methode ----------------------------------
    public boolean effect(Entity lanceur, Entity vise) {
        int tmp = Math.min(lanceur.getMmana(), (int) (2. * vise.getMana() / (critique ? 2. : 3.)));
        if (vise.removeMana(tmp)) {
            rollCrit();
            lanceur.addMana(tmp);
            return true;
        }
        return false;
    }
}
