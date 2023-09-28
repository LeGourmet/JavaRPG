// Blanchard Allan
package Entity.sort.mob;

import Entity.Entity;
import Entity.sort.Sort;

public class Rmana extends Sort {
    // ------------------------------- constructor -------------------------------
    public Rmana() {
        super("Régénération de mana", 0, 20);
    }

    // --------------------------------- methode ----------------------------------
    public boolean effect(Entity lanceur, Entity vise) {
        return (lanceur.addMana(dommage) == -1);
    }
}
