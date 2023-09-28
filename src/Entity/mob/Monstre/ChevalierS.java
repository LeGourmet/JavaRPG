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
import Item.armes.Bouclier;

import java.util.HashMap;
import java.util.Map;

import static java.util.Map.entry;

public class ChevalierS extends Monstre {
    // ------------------------------- constructor -------------------------------
    public ChevalierS() {
        super("Chevalier squelette", new AnimatedSprite(Constantes.CHEVALIERS,
                        new HashMap<>(Map.ofEntries(
                                entry("Idle", new Animation(9, 0, 64, 64, 9))
                        )),
                        "Idle"),
                0, 170, 170, 40, 40,
                new Sort[]{new AutoGuerrier(), new Mandales()},
                new Inventaire(1), 0, 0, 10, 10, 0.2f, 5
        );
        inventaire.add(new Bouclier(3, 0, "Une porte", 0));
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
        mvie += 16 * lv;
        gold += 8 * lv;
        vie = mvie;
        experience += 2 * lv;
    }
}
