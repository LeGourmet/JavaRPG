//DELON ARTHUR
package Frontend;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class LobbyController extends BaseController {

    public void update() {
    }

    @FXML
    public void save(ActionEvent actionEvent) {
        changeScene("SaveGame");
    }

    @FXML
    public void startGame(ActionEvent actionEvent) {
        changeScene("Difficulty");
    }

    @FXML
    public void returnMain(ActionEvent actionEvent) {
        changeScene("MainMenu");
    }

    @FXML
    public void shop(ActionEvent actionEvent) {
        changeScene("Shop");
    }

    @FXML
    public void inventory(ActionEvent actionEvent) {
        changeScene("Inventory");
    }
}
