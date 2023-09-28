// Blanchard Allan
package Entity.personnages.guerrier;

import Entity.Inventaire.Inventaire;
import Entity.personnages.Personnage;
import Entity.sort.Sort;
import Entity.sort.personnage.guerrier.AutoGuerrier;
import Entity.sort.personnage.guerrier.Mandales;
import Frontend.AnimatedSprite;
import Item.armes.Arme;

public abstract class Guerrier extends Personnage {
    // ------------------------------- constructor -------------------------------
    public Guerrier(String nom, AnimatedSprite sprite, Arme[] armes, int maxVie) {
        super(nom, sprite, maxVie, maxVie, 40, 40, 10, 1, 0,
                armes, new Inventaire(16),
                new Sort[]{new AutoGuerrier(), new Mandales()}, 0, new boolean[]{false, false, false, false, false, false});
    }

    public Guerrier(String nom, AnimatedSprite sprite, int vie, int maxVie, int mana, int maxMana, int rmana, int niveau, int gold,
                    Arme[] armes, Inventaire inventaire, int exp, boolean[] listChecks) {
        super(nom, sprite, vie, maxVie, mana, maxMana, rmana, niveau, gold, armes, inventaire,
                new Sort[]{new AutoGuerrier(), new Mandales()}, exp, listChecks);
    }
}
