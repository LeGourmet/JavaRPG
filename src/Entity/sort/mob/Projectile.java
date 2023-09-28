// Blanchard Allan
package Entity.sort.mob;

import Entity.Entity;
import Entity.sort.Sort;

public class Projectile extends Sort {
    // ------------------------------- constructor -------------------------------
    public Projectile() {
        super("Projectile", 20, 10);
    }

    // --------------------------------- methode ----------------------------------
    public boolean effect(Entity lanceur, Entity vise) {
        if (removeMana(lanceur)) {
            rollCrit();
            vise.takeDammage((int) (lanceur.getNiveau() * 2. * dommage / 5. * (critique ? 2. : 1.)));
            return true;
        }
        return false;
    }
}
