// Blanchard Allan
package Entity.sort.mob;

import Entity.Entity;
import Entity.mob.Ennemie;
import Entity.sort.Sort;

public class AutoCavalier extends Sort {
    // ------------------------------- constructor -------------------------------
    public AutoCavalier(int position) {
        super("????", 20, position + 1);
    }

    // --------------------------------- methode ----------------------------------
    public boolean effect(Entity lanceur, Entity vise) {
        if (lanceur instanceof Ennemie && removeMana(lanceur)) {
            vise.takeDammage((int) ((lanceur.getNiveau() / 2.) * dommage * (critique ? 2 : 1)));
            return true;
        }
        return false;
    }
}
