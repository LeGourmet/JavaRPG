// Blanchard Allan
package Entity.mob.Monstre;

import Entity.Entity;
import Entity.Inventaire.Inventaire;
import Entity.personnages.Personnage;
import Entity.sort.Sort;
import Entity.sort.personnage.guerrier.AutoGuerrier;
import Entity.sort.personnage.guerrier.Mandales;
import Frontend.AnimatedSprite;
import Frontend.Animation;
import Frontend.Constantes;
import Item.armes.Epee;

import java.util.HashMap;
import java.util.Map;

import static java.util.Map.entry;

public class BarbareS extends Monstre {
    // ------------------------------- constructor -------------------------------
    public BarbareS() {
        super("Barbare squelette", new AnimatedSprite(Constantes.BARBARES,
                        new HashMap<>(Map.ofEntries(
                                entry("Idle", new Animation(9, 0, 64, 64, 9))
                        )),
                        "Idle"),
                0, 150, 150, 40, 40,
                new Sort[]{new AutoGuerrier(), new Mandales()},
                new Inventaire(1), 0, 0, 10, 10, 0.2f, 5
        );
        inventaire.add(new Epee(5, 0, "Epee en mousse(King jouet)", 0));
    }

    // --------------------------------- methode ----------------------------------
    public String playTurn(Entity adversaire) {
        if (!dead()) {
            if (Math.random() > chanceMiss) {
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
        mvie += 13 * lv;
        gold += 8 * lv;
        vie = mvie;
        experience += 2 * lv;
    }
}
