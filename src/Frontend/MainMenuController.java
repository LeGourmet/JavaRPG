//DELON ARTHUR
package Frontend;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class MainMenuController extends BaseController {
    public void update() {
    }

    @FXML
    public void NewGame(ActionEvent e) {
        changeScene("NewGame");
    }

    @FXML
    public void LoadGame(ActionEvent e) {
        changeScene("LoadGame");
    }
}
