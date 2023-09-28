// Blanchard Allan
package Entity.personnages.chasseur;

import Entity.Inventaire.Inventaire;
import Entity.personnages.Personnage;
import Entity.sort.Sort;
import Entity.sort.personnage.chasseur.AutoChasseur;
import Entity.sort.personnage.chasseur.Recharge;
import Frontend.AnimatedSprite;
import Frontend.Animation;
import Frontend.Constantes;
import Item.armes.Arc;
import Item.armes.Arme;

import java.util.HashMap;
import java.util.Map;

import static java.util.Map.entry;

public class Chasseur extends Personnage {
    // ------------------------------- constructor -------------------------------
    public Chasseur(String nom) {
        super(nom, new AnimatedSprite(Constantes.CHASSEUR,
                        new HashMap<>(Map.ofEntries(
                                entry("Idle", new Animation(9, 64, 64, 64, 9)),
                                entry("Attack0", new Animation(11, 128, 64, 64, 9)),
                                entry("Attack1", new Animation(7, 0, 64, 64, 9)),
                                entry("Death", new Animation(6, 192, 64, 64, 9))
                        )),
                        "Idle"),
                120, 120, 30, 30, 10, 1, 0,
                new Arme[]{new Arc()}, new Inventaire(16),
                new Sort[]{new AutoChasseur(), new Recharge()}, 0, new boolean[]{false, false, false, false, false, false});
    }

    public Chasseur(String nom, int vie, int maxVie, int mana, int maxMana, int rmana, int niveau, int gold,
                    Arme[] armes, Inventaire inventaire, int exp, boolean[] listChecks) {
        super(nom, new AnimatedSprite(Constantes.CHASSEUR,
                        new HashMap<>(Map.ofEntries(
                                entry("Idle", new Animation(9, 64, 64, 64, 9)),
                                entry("Attack0", new Animation(11, 128, 64, 64, 9)),
                                entry("Attack1", new Animation(7, 0, 64, 64, 9)),
                                entry("Death", new Animation(6, 192, 64, 64, 9))
                        )),
                        "Idle"),
                vie, maxVie, mana, maxMana, rmana, niveau, gold, armes, inventaire, new Sort[]{new AutoChasseur(), new Recharge()}, exp, listChecks);
    }

    // --------------------------------- getter ----------------------------------
    public int getNbArrow() {
        if (listArmes.length == 1 && listArmes[0] instanceof Arc) {
            return ((Arc) listArmes[0]).getNbFleche();
        }
        return -1;
    }

    // --------------------------------- methode ----------------------------------
    public void takeDammage(int d) {
        vie = Math.max(vie - d, 0);
    }

    public void levelUp(int nb) {
        int tmp = nb;
        if (nb + niveau > lvMax) {
            tmp = lvMax - niveau;
        }
        if (tmp != 0) {
            niveau += tmp;
            mvie += 15 * tmp;
            vie = mvie;
            mana = mmana;
        }
    }

    public boolean equiper(Arme arme) {
        if (arme instanceof Arc && niveau >= arme.getLevelMin()) {
            inventaire.swapItem(arme, listArmes[0]);
            listArmes[0] = arme;
            return true;
        }
        return false;
    }

    protected String exportToString() {
        return " : " + nom + " chasseur de niveau " + niveau + "\n"
                + super.exportToString();
    }
}

