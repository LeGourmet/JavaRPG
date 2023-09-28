// Blanchard Allan
package Entity.sort.personnage;

import Entity.personnages.Personnage;
import Entity.sort.Sort;
import Frontend.AnimatedSprite;

public abstract class SortPerso extends Sort {
    // ------------------------------- attribute --------------------------------
    protected AnimatedSprite sprite;

    // ------------------------------- constructor -------------------------------
    public SortPerso(String nom, int cout, int dammage, AnimatedSprite sprite) {
        super(nom, cout, dammage);
        this.sprite = sprite;
    }

    // ---------------------------- abstract methode ------------------------------
    public abstract String calcDammage(Personnage personnage);
    public abstract String calcCout(Personnage personnage);
    public AnimatedSprite getSprite() {
        return sprite;
    }
}
