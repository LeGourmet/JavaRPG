// Blanchard Allan
package Entity.mob.Monstre;

import Entity.Inventaire.Inventaire;
import Entity.mob.Ennemie;
import Entity.sort.Sort;
import Frontend.AnimatedSprite;

public abstract class Monstre extends Ennemie {
    // ------------------------------- attribute --------------------------------
    protected float chanceMiss;
    protected float chanceEsquive;

    // ------------------------------- constructor -------------------------------
    public Monstre(String nom, AnimatedSprite sprite, float vitesse, int vie, int maxVie, int mana, int maxMana,
                   Sort[] sorts, Inventaire inventaire, float chanceMiss, float chanceEsquive, int exp, int niveau, float loot, int gold) {
        super(nom, sprite, vitesse, vie, maxVie, mana, maxMana, sorts, inventaire, exp, niveau, loot, gold);
        this.chanceMiss = chanceMiss;
        this.chanceEsquive = chanceEsquive;
    }

    // ------------------------------- getter ------------------------------------
    public float getChanceEsquive() {
        return chanceEsquive;
    }
    public float getChanceMiss() {
        return chanceMiss;
    }

    // --------------------------------- methode ----------------------------------
    public void takeDammage(int dmg) {
        if (Math.random() > chanceEsquive) {
            vie = Math.max(vie - dmg, 0);
        }
    }

    public void endTurn(){}

    public String afficher() {
        String boss = "Monstre\n";
        String vit = "Attaque d'opportunité :\t" + (int) (speed * 100) + "%\n";
        String loot = "Chance de drop:\t\t" + (int) (chanceLoot * 100) + "%\n";
        String ChanceM = "Chance miss:\t\t" + (int) (chanceMiss * 100) + "%\n";
        String ChanceE = "Chance esquive:\t\t" + (int) (chanceEsquive * 100) + "%\n";
        String exp = "Experience :\t\t" + experience + "\n";
        String g = "Gold :\t\t\t\t" + gold + "\n";
        return boss + vit + loot + ChanceM + ChanceE + exp + g;
    }

    public void lessMiss(float m) {
        if (chanceMiss - m > 0.f) {
            chanceMiss -= m;
        } else {
            chanceMiss = 0.f;
        }
    }

    public void lessEsq(float e) {
        if (chanceEsquive - e > 0.f) {
            chanceEsquive -= e;
        } else {
            chanceEsquive = 0.f;
        }
    }
}
