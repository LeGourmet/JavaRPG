// Blanchard Allan
package Entity.personnages.guerrier;

import Entity.Inventaire.Inventaire;
import Frontend.AnimatedSprite;
import Frontend.Animation;
import Frontend.Constantes;
import Item.armes.Arme;
import Item.armes.Epee;

import java.util.HashMap;
import java.util.Map;

import static java.util.Map.entry;

public class Guerrier_Epee extends Guerrier {
    // ------------------------------- constructor -------------------------------
    public Guerrier_Epee(String nom) {
        super(nom, new AnimatedSprite(Constantes.BARBARE,
                        new HashMap<>(Map.ofEntries(
                                entry("Idle", new Animation(9, 0, 64, 64, 9)),
                                entry("Attack0", new Animation(6, 192, 192, 128, 9)),
                                entry("Attack1", new Animation(6, 64, 64, 64, 9)),
                                entry("Death", new Animation(6, 128, 64, 64, 9))
                        )),
                        "Idle"),
                new Arme[]{new Epee()}, 150);
    }

    public Guerrier_Epee(String nom, int vie, int maxVie, int mana, int maxMana, int rmana, int niveau, int gold,
                         Arme[] armes, Inventaire inventaire, int exp, boolean[] listChecks) {
        super(nom, new AnimatedSprite(Constantes.BARBARE,
                        new HashMap<>(Map.ofEntries(
                                entry("Idle", new Animation(9, 0, 64, 64, 9)),
                                entry("Attack0", new Animation(6, 192, 192, 128, 9)),
                                entry("Attack1", new Animation(6, 64, 64, 64, 9)),
                                entry("Death", new Animation(6, 128, 64, 64, 9))
                        )),
                        "Idle"),
                vie, maxVie, mana, maxMana, rmana, niveau, gold, armes, inventaire, exp, listChecks);
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
            mvie += 18 * tmp;
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
        return false;
    }

    protected String exportToString() {
        return " : " + nom + " barbare de niveau " + niveau + "\n"
                + super.exportToString();
    }
}
