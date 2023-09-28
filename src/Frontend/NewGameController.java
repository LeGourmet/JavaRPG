//DELON ARTHUR
package Frontend;


import Entity.personnages.chasseur.Chasseur;
import Entity.personnages.guerrier.Guerrier_Epee;
import Entity.personnages.guerrier.Guerrier_Epee_Bouclier;
import Entity.personnages.mage.Mage;
import javafx.beans.binding.BooleanBinding;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class NewGameController extends BaseController {
    @FXML
    private ToggleGroup ClassSelector;
    @FXML
    private TextField name;
    @FXML
    private Button play;

    @FXML
    public void initialize() {
        super.initialize();
        BooleanBinding isFormFilled = new BooleanBinding() {
            {
                super.bind(ClassSelector.selectedToggleProperty(), name.textProperty());
            }

            @Override
            protected boolean computeValue() {
                return ClassSelector.getSelectedToggle() == null || name.getText().isBlank();
            }
        };
        play.disableProperty().bind(isFormFilled);
    }

    public void update() {
    }

    @FXML
    @Override
    public void handleKeyPressed(KeyEvent keyEvent) {
        if (keyEvent.getCode().equals(KeyCode.ESCAPE)) {
            previousScene();
        }
    }

    @FXML
    public void createPlayer(ActionEvent e) {
        String playername = name.getText();
        switch (((RadioButton) ClassSelector.getSelectedToggle()).getId()) {
            case "warriorClass":
                joueur = new Guerrier_Epee(playername);
                break;
            case "shieldWarClass":
                joueur = new Guerrier_Epee_Bouclier(playername);
                break;
            case "hunterClass":
                joueur = new Chasseur(playername);
                break;
            case "mageClass":
                joueur = new Mage(playername);
                break;
        }
        changeScene("Lobby");
    }
}
