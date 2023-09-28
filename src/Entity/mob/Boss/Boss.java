// Blanchard Allan
package Entity.mob.Boss;

import Entity.Inventaire.Inventaire;
import Entity.mob.Ennemie;
import Entity.sort.Sort;
import Frontend.AnimatedSprite;

public abstract class Boss extends Ennemie {
    // ------------------------------- attribute --------------------------------
    protected int armor;
    protected int resist;
    protected int rmana;
    protected int rvie;

    // ------------------------------- constructor -------------------------------
    public Boss(String nom, AnimatedSprite sprite, float vitesse, int vie, int maxVie, int mana, int maxMana,
                Sort[] sorts, Inventaire inventaire,
                int armor, int resist, int rmana, int rvie, int exp, int niveau, float loot, int gold) {
        super(nom, sprite, vitesse, vie, maxVie, mana, maxMana, sorts, inventaire, exp, niveau, loot, gold);
        this.armor = armor;
        this.resist = resist;
        this.rmana = rmana;
        this.rvie = rvie;
    }

    // --------------------------------- methode ----------------------------------
    public void regenMana() {
        mana = Math.min(mmana, mana + rmana);
    }
    public void regenVie() {
        vie = Math.min(mvie, vie + rvie);
    }

    public void takeDammage(int dmg) {
        vie = Math.max(vie - Math.max(Math.max(dmg - resist, 0) - armor, 0), 0);
        resist = Math.max(resist - dmg, 0);
    }

    public void endTurn() {
        if (!dead()) {
            regenMana();
            regenVie();
        }
    }

    public String afficher() {
        String boss = "Boss\n";
        String vit = "Attaque d'opportunité :\t" + (int) (speed * 100) + "%\n";
        String loot = "Chance de drop:\t\t" + (int) (chanceLoot * 100) + "%\n";
        String armure = "Armure :\t\t\t" + armor + "\n";
        String resistance = "Resistance :\t\t" + resist + "\n";
        String exp = "Experience :\t\t" + experience + "\n";
        String g = "Gold :\t\t\t\t" + gold + "\n";
        return boss + vit + loot + resistance + armure + exp + g;
    }
}
