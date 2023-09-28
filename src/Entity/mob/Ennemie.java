// Blanchard Allan
package Entity.mob;

import Entity.Entity;
import Entity.Inventaire.Inventaire;
import Entity.personnages.Personnage;
import Entity.sort.Sort;
import Frontend.AnimatedSprite;

public abstract class Ennemie extends Entity {
    // ------------------------------- attribute --------------------------------
    protected final float speed;
    protected float chanceLoot;

    // ------------------------------- constructor -------------------------------
    public Ennemie(String nom, AnimatedSprite sprite, float vitesse, int vie, int maxVie, int mana, int maxMana,
                   Sort[] sorts, Inventaire inventaire, int exp, int niveau, float loot, int gold) {
        super(nom, sprite, vie, maxVie, mana, maxMana, gold, exp, niveau, inventaire, sorts);
        this.speed = vitesse;
        this.chanceLoot = loot;
    }

    // --------------------------------- getter ----------------------------------
    public float getSpeed() {
        return this.speed;
    }

    // ---------------------------- abstract methode ------------------------------
    public abstract void adjust(Personnage adversaire);
    public abstract void takeDammage(int dmg);
    public abstract String playTurn(Entity adversaire);
    public abstract void endTurn();

    // ----------------------------- methode --------------------------------------
    public boolean lootObj(Personnage personnage) {
        int size = this.inventaire.getItems().size();
        if (chanceLoot < Math.random() && size != 0) {
            return personnage.getInventaire().add(this.inventaire.getItem((int) (Math.random() * size)));
        }
        return false;
    }
}
