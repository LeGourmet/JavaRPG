// Blanchard Allan
package Entity.personnages.mage;

import Entity.Inventaire.Inventaire;
import Entity.personnages.Personnage;
import Entity.sort.Sort;
import Entity.sort.personnage.mage.AutoMage;
import Entity.sort.personnage.mage.Decharge;
import Frontend.AnimatedSprite;
import Frontend.Animation;
import Frontend.Constantes;
import Item.armes.Arme;

import java.util.HashMap;
import java.util.Map;

import static java.util.Map.entry;

public class Mage extends Personnage {
    // ------------------------------- attribute --------------------------------
    private boolean count;

    // ------------------------------- constructor -------------------------------
    public Mage(String nom) {
        super(nom, new AnimatedSprite(Constantes.MAGE,
                        new HashMap<>(Map.ofEntries(
                                entry("Idle", new Animation(9, 64, 64, 64, 9)),
                                entry("Attack0", new Animation(6, 128, 64, 64, 9)),
                                entry("Attack1", new Animation(7, 0, 64, 64, 9)),
                                entry("Death", new Animation(6, 192, 64, 64, 9))
                        )),
                        "Idle"),
                100, 100, 60, 60, 15, 1, 0,
                new Arme[]{}, new Inventaire(16),
                new Sort[]{new AutoMage(), new Decharge()}, 0, new boolean[]{false, false, false, false, false, false});
        this.count = false;
    }

    public Mage(String nom, int vie, int maxVie, int mana, int maxMana, int rmana, int niveau, int gold,
                Inventaire inventaire, int exp, boolean[] listChecks, boolean count) {
        super(nom, new AnimatedSprite(Constantes.MAGE,
                        new HashMap<>(Map.ofEntries(
                                entry("Idle", new Animation(9, 64, 64, 64, 9)),
                                entry("Attack0", new Animation(6, 128, 64, 64, 9)),
                                entry("Attack1", new Animation(7, 0, 64, 64, 9)),
                                entry("Death", new Animation(6, 192, 64, 64, 9))
                        )),
                        "Idle"),
                vie, maxVie, mana, maxMana, rmana, niveau, gold, new Arme[]{}, inventaire,
                new Sort[]{new AutoMage(), new Decharge()}, exp, listChecks);
        this.count = count;
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
            mvie += tmp * 12;
            vie = mvie;
            mmana += tmp * 2;
            for (int i = 0; i < tmp; i++) {
                count = !count;
                if (count) {
                    rmana += 1;
                }
            }
            mana = mmana;
        }
    }

    public boolean equiper(Arme arme) {
        return false;
    }

    protected String exportToString() {
        return " : " + nom + " mage de niveau " + niveau + "\n"
                + super.exportToString()
                + count;
    }
}
