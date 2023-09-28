// Blanchard Allan
package Item.armes;

import Frontend.AnimatedSprite;
import Frontend.Animation;
import Frontend.Constantes;

import java.util.HashMap;
import java.util.Map;

import static java.util.Map.entry;

public class Bouclier extends Arme {
    // ------------------------------- attribute --------------------------------
    private final int armure;

    // ------------------------------- constructor -------------------------------
    public Bouclier() {
        super(0, 1, "Couvercle de tonneau", 1, new AnimatedSprite(Constantes.BOUCLIER, new HashMap<>(Map.ofEntries(entry("Idle", new Animation(1, 0, 32, 32, 9)))), "Idle"));
        this.armure = 5;
    }

    public Bouclier(int armure, int value, String nom, int levelMin) {
        super(0, value, nom, levelMin, new AnimatedSprite(Constantes.BOUCLIER, new HashMap<>(Map.ofEntries(entry("Idle", new Animation(1, 0, 32, 32, 9)))), "Idle"));
        this.armure = armure;
    }

    // --------------------------------- getter -----------------------------------
    public int getArmure() {
        return this.armure;
    }

    // --------------------------------- methode ----------------------------------
    public String afficher() {
        String n = nom + "\n";
        String arm = "Protection : " + armure + "\n";
        String lmin = "Niveau minimum : " + levelMin + "\n";
        String valeur = "Prix : " + valeurGold + " gold\n";
        return n + arm + lmin + valeur;
    }
}
