// Blanchard Allan
package Entity.personnages.guerrier;

import Entity.Inventaire.Inventaire;
import Frontend.AnimatedSprite;
import Frontend.Animation;
import Frontend.Constantes;
import Item.armes.Arme;
import Item.armes.Bouclier;
import Item.armes.Epee;

import java.util.HashMap;
import java.util.Map;

import static java.util.Map.entry;

public class Guerrier_Epee_Bouclier extends Guerrier {
    // ------------------------------- constructor -------------------------------
    public Guerrier_Epee_Bouclier(String nom) {
        super(nom, new AnimatedSprite(Constantes.CHEVALIER,
                        new HashMap<>(Map.ofEntries(
                                entry("Idle", new Animation(9, 64, 64, 64, 9)),
                                entry("Attack0", new Animation(6, 128, 64, 64, 9)),
                                entry("Attack1", new Animation(8, 0, 64, 64, 9)),
                                entry("Death", new Animation(6, 192, 64, 64, 9))
                        )),
                        "Idle"),
                new Arme[]{new Epee(), new Bouclier()}, 170);
    }

    public Guerrier_Epee_Bouclier(String nom, int vie, int maxVie, int mana, int maxMana, int rmana, int niveau, int gold,
                                  Arme[] a, Inventaire i, int exp, boolean[] listChecks) {
        super(nom, new AnimatedSprite(Constantes.CHEVALIER,
                        new HashMap<>(Map.ofEntries(
                                entry("Idle", new Animation(9, 64, 64, 64, 9)),
                                entry("Attack0", new Animation(6, 128, 64, 64, 9)),
                                entry("Attack1", new Animation(8, 0, 64, 64, 9)),
                                entry("Death", new Animation(6, 192, 64, 64, 9))
                        )),
                        "Idle"),
                vie, maxVie, mana, maxMana, rmana, niveau, gold, a, i, exp, listChecks);
    }

    // --------------------------------- getter ----------------------------------
    public int getArmor() {
        if (listArmes.length == 2 && listArmes[1] instanceof Bouclier) {
            return ((Bouclier) listArmes[1]).getArmure();
        }
        return -1;
    }

    // --------------------------------- methode ----------------------------------
    public void takeDammage(int d) {
        vie = Math.max(vie - Math.max(d - Math.max(getArmor(), 0), 0), 0);
    }

    public void levelUp(int nb) {
        int tmp = nb;
        if (nb + niveau > lvMax) {
            tmp = lvMax - niveau;
        }
        if (tmp != 0) {
            niveau += tmp;
            mvie += 22 * tmp;
            mana = mmana;
            vie = mvie;
        }
    }

    public boolean equiper(Arme arme) {
        if (arme instanceof Epee && niveau >= arme.getLevelMin()) {
            inventaire.swapItem(arme, listArmes[0]);
            listArmes[0] = arme;
            return true;
        }
        if (arme instanceof Bouclier && niveau >= arme.getLevelMin()) {
            inventaire.swapItem(arme, listArmes[1]);
            listArmes[1] = arme;
            return true;
        }
        return false;
    }

    protected String exportToString() {
        return " : " + nom + " chevalier de niveau " + niveau + "\n"
                + super.exportToString();
    }
}
