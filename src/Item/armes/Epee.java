// Blanchard Allan
package Item.armes;

import Frontend.AnimatedSprite;
import Frontend.Animation;
import Frontend.Constantes;

import java.util.HashMap;
import java.util.Map;

import static java.util.Map.entry;

public class Epee extends Arme {
    // ------------------------------- constructor -------------------------------
    public Epee() {
        super(10, 1, "Epee d'entrainement", 1, new AnimatedSprite(Constantes.EPEE, new HashMap<>(Map.ofEntries(entry("Idle", new Animation(1, 0, 32, 32, 9)))), "Idle"));
    }

    public Epee(int dammage, int value, String nom, int levelMin) {
        super(dammage, value, nom, levelMin, new AnimatedSprite(Constantes.EPEE, new HashMap<>(Map.ofEntries(entry("Idle", new Animation(1, 0, 32, 32, 9)))), "Idle"));
    }

    // --------------------------------- methode ----------------------------------
    public String afficher() {
        String n = nom + "\n";
        String dmg = "Dégâts : " + dommage + "\n";
        String lmin = "Niveau minimum : " + levelMin + "\n";
        String valeur = "Prix : " + valeurGold + " gold\n";
        return n + dmg + lmin + valeur;
    }
}
