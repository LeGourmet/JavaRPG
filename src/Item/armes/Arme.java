// Blanchard Allan
package Item.armes;

import Frontend.AnimatedSprite;
import Item.Item;

public abstract class Arme extends Item {
    // ------------------------------- attribute --------------------------------
    protected int dommage;
    protected int levelMin;

    // ------------------------------- constructor -------------------------------
    public Arme(int dammage, int value, String nom, int levelMin, AnimatedSprite sprite) {
        super(value, nom, sprite);
        this.dommage = dammage;
        this.levelMin = levelMin;
    }

    // --------------------------------- getter ----------------------------------
    public int getDommage() {
        return this.dommage;
    }
    public int getLevelMin() {
        return this.levelMin;
    }
}
