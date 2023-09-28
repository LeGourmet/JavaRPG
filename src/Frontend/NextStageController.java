//DELON ARTHUR
package Frontend;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;


public class NextStageController extends BaseController {

    @FXML
    private Label text;
    @FXML
    private String sceneType;
    private int levelid;

    public void update() {
    }

    public void setup(String message, String sceneType, int levelid) {
        text.setText(message);
        this.sceneType = sceneType;
        this.levelid = levelid;
    }

    @FXML
    public void handleKeyPressed(KeyEvent keyEvent) {
        if (sceneType.equals("NextStage")) {
            ((FightController) controllers.get("Fight")).start(moblist.remove(0));
            changeScene("Fight");
        } else {
            if (sceneType.equals("GameOver")) {
                joueur.reset();
                moblist.clear();
            } else {
                joueur.check(levelid);
            }
            changeScene("Lobby");
        }
    }
}
