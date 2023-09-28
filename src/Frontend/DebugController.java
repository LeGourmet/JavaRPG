//DELON ARTHUR
package Frontend;

import javafx.fxml.FXML;

public class DebugController extends BaseController {

    public void update() {
    }

    @FXML
    public void healPlayer() {
        controllers.get(currentDisplayedScene).update();
        joueur.heal(joueur.getMvie());
    }

    @FXML
    public void regenMana() {
        controllers.get(currentDisplayedScene).update();
        joueur.addMana(joueur.getMmana());
    }

    @FXML
    public void addLvl() {
        controllers.get(currentDisplayedScene).update();
        joueur.addExp((int) Math.round(0.35 * joueur.getNiveau() * joueur.getNiveau() + 2 * joueur.getNiveau() + 17.5));
    }

    @FXML
    public void addGold() {
        controllers.get(currentDisplayedScene).update();
        joueur.addMoney(1000);
    }

    @FXML
    public void skip() {
        ((FightController) controllers.get(currentDisplayedScene)).skip();
    }
}
