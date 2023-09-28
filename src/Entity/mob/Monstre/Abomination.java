// Blanchard Allan
package Entity.mob.Monstre;

import Entity.Entity;
import Entity.Inventaire.Inventaire;
import Entity.personnages.Personnage;
import Entity.sort.Sort;
import Entity.sort.mob.MalPBien;
import Entity.sort.mob.Rvie;
import Frontend.AnimatedSprite;
import Frontend.Animation;
import Frontend.Constantes;
import Item.popo.PopoVie;

import java.util.HashMap;
import java.util.Map;

import static java.util.Map.entry;

public class Abomination extends Monstre {
    // ------------------------------- constructor -------------------------------
    public Abomination() {
        super("Abomination", new AnimatedSprite(Constantes.ABOMINATION,
                        new HashMap<>(Map.ofEntries(
                                entry("Idle", new Animation(3, 0, 64, 64, 9))
                        )),
                        "Idle"), 0, 200, 200, 60, 60,
                new Sort[]{new MalPBien(), new Rvie()},
                new Inventaire(1), 0, 0.1f, 30, 20, 0.5f, 20
        );
        inventaire.add(new PopoVie(0.3f, niveau));
    }

    // --------------------------------- methode ----------------------------------
    public String playTurn(Entity adversaire) {
        if (!dead()) {
            if (Math.random() > chanceMiss) {
                if (vie > mvie / 2 && listSort[0].effect(this, adversaire)) {
                    return "Utilise : " + listSort[0].getNom();
                }
                if (listSort[1].effect(this, adversaire)) {
                    return "Utilise : " + listSort[1].getNom();
                }
                if (listSort[0].effect(this, adversaire)) {
                    return "Utilise : " + listSort[0].getNom();
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
        mvie += 17 * lv;
        gold += 12 * lv;
        vie = mvie;
        experience += 4 * lv;
        inventaire.sell(0);
        inventaire.add(new PopoVie(0.3f, niveau));
    }
}
