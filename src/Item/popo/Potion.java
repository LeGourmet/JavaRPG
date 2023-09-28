// Blanchard Allan
package Item.popo;

import Entity.Entity;
import Frontend.AnimatedSprite;
import Item.Item;

public abstract class Potion extends Item {
    // ------------------------------- attribute --------------------------------
    protected final float power;
    protected final int niveau;

    // ------------------------------- constructor -------------------------------
    public Potion(int value, String nom, float power, int niveau, AnimatedSprite sprite) {
        super(value, nom, sprite);
        this.power = power;
        this.niveau = niveau;
    }

    // --------------------------------- getter -----------------------------------
    public float getPower() {
        return this.power;
    }
    public int getNiveau() {
        return niveau;
    }

    // ---------------------------- abstract methode ------------------------------
    public abstract boolean effect(Entity lanceur);

    // --------------------------------- methode ----------------------------------
    public String afficher() {
        String nom = this.nom + "\n";
        String valeur = "Prix : " + valeurGold + " gold\n";
        String effet = "Effet : Régénère " + (int) (power * 100.) + "%" + (this instanceof PopoVie ? " vie" : " mana") + "\n";
        return nom + valeur + effet;
    }
}
