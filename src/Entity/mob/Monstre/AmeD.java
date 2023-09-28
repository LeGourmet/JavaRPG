// Blanchard Allan
package Entity.mob.Monstre;

import Entity.Entity;
import Entity.Inventaire.Inventaire;
import Entity.personnages.Personnage;
import Entity.sort.Sort;
import Entity.sort.mob.Buff;
import Entity.sort.mob.Projectile;
import Frontend.AnimatedSprite;
import Frontend.Animation;
import Frontend.Constantes;

import java.util.HashMap;
import java.util.Map;

import static java.util.Map.entry;

public class AmeD extends Monstre {
    // ------------------------------- constructor -------------------------------
    public AmeD() {
        super("Ame damné", new AnimatedSprite(Constantes.AMED,
                        new HashMap<>(Map.ofEntries(
                                entry("Idle", new Animation(3, 0, 32, 32, 9))
                        )),
                        "Idle"), 0, 90, 90, 100, 100,
                new Sort[]{new Projectile(), new Buff()},
                new Inventaire(0), 0.5f, 0.7f, 5, 5, 0, 2
        );
    }

    // --------------------------------- methode ----------------------------------
    public String playTurn(Entity adversaire) {
        if (!dead()) {
            if (Math.random() > chanceMiss) {
                if (chanceMiss > 0.3f && listSort[1].effect(this, adversaire)) {
                    return "Utilise : " + listSort[1].getNom();
                }
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
        mvie += 8 * lv;
        gold += lv * 5;
        vie = mvie;
        experience += lv;
    }

}

