// Blanchard Allan
package Entity.mob.Monstre;

import Entity.Entity;
import Entity.Inventaire.Inventaire;
import Entity.personnages.Personnage;
import Entity.sort.Sort;
import Entity.sort.personnage.chasseur.AutoChasseur;
import Entity.sort.personnage.chasseur.Recharge;
import Frontend.AnimatedSprite;
import Frontend.Animation;
import Frontend.Constantes;
import Item.armes.Arc;

import java.util.HashMap;
import java.util.Map;

import static java.util.Map.entry;

public class ArcherS extends Monstre {
    // ------------------------------- attribute --------------------------------
    private int nbFleche;
    private final int maxFleche;

    // ------------------------------- constructor -------------------------------
    public ArcherS() {
        super("Archer squelette", new AnimatedSprite(Constantes.ARCHERS,
                        new HashMap<>(Map.ofEntries(
                                entry("Idle", new Animation(9, 0, 64, 64, 9))
                        )),
                        "Idle"), 0, 120, 120, 30, 30,
                new Sort[]{new AutoChasseur(), new Recharge()},
                new Inventaire(1), 0, 0, 10, 10, 0.2f, 5
        );
        inventaire.add(new Arc(20, 1, 0, "Lance pierre", 0, true));
        this.nbFleche = 3;
        this.maxFleche = 10;
    }

    // --------------------------------- methode ----------------------------------
    public boolean useArrow() {
        if (nbFleche > 0) {
            nbFleche--;
            return true;
        }
        return false;
    }

    public boolean recharge(int nb) {
        if (nbFleche < maxFleche) {
            nbFleche = Math.min(maxFleche, nbFleche + nb);
            return true;
        }
        return false;
    }

    public String playTurn(Entity adversaire) {
        if (!dead()) {
            if (Math.random() > chanceMiss) {
                if (listSort[0].effect(this, adversaire)) {
                    return "Utilise : " + listSort[0].getNom();
                }
                if (listSort[1].effect(this, adversaire)) {
                    return "Utilise : " + listSort[1].getNom();
                }
                return "Passe son tour";
            }
            return "Trébuche!";
        }
        return "";
    }

    public void adjust(Personnage adversaire) {
        int lv = Math.max(adversaire.getNiveau() - this.niveau, 0);
        niveau += lv;
        mvie += 11 * lv;
        gold += 8 * lv;
        vie = mvie;
        experience += 2 * lv;
    }
}
