// Blanchard Allan
package Entity.sort.mob;

import Entity.Entity;
import Entity.sort.Sort;

public class Explosion extends Sort {
    // ------------------------------- constructor -------------------------------
    public Explosion() {
        super("Suicide", 100, 0);
    }

    // --------------------------------- methode ----------------------------------
    public boolean effect(Entity lanceur, Entity vise) {
        if (removeMana(lanceur)) {
            lanceur.takeDammage(lanceur.getMvie());
            vise.takeDammage(lanceur.getMvie());
            return true;
        }
        return false;
    }
}
