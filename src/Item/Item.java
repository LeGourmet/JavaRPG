// Blanchard Allan
package Item;

import Frontend.AnimatedSprite;

public abstract class Item {
    // ------------------------------- attribute --------------------------------
    protected int valeurGold;
    protected String nom;
    protected AnimatedSprite sprite;

    // ------------------------------- constructor -------------------------------
    public Item(int valeurGold, String nom, AnimatedSprite sprite) {
        this.valeurGold = valeurGold;
        this.nom = nom;
        this.sprite = sprite;
    }

    // --------------------------------- getter -----------------------------------
    public int getValeurGold() {
        return this.valeurGold;
    }
    public String getNom() {
        return this.nom;
    }
    public AnimatedSprite getSprite() {
        return this.sprite;
    }
    public abstract String afficher();
}
