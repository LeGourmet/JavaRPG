//DELON ARTHUR
package Frontend;

import javafx.beans.binding.BooleanBinding;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class SaveGameController extends BaseController {
    @FXML
    private ToggleGroup Saves;
    @FXML
    private Button play;

    @FXML
    public void initialize() {
        super.initialize();
        BooleanBinding isFormFilled = new BooleanBinding() {
            {
                super.bind(Saves.selectedToggleProperty());
            }

            @Override
            protected boolean computeValue() {
                return Saves.getSelectedToggle() == null;
            }
        };
        play.disableProperty().bind(isFormFilled);
    }

    public void update() {
        File saveDir = new File(Constantes.SAVEPATH);
        if (saveDir.exists()) {
            List<String> saves = Arrays.asList(saveDir.list());
            for (Toggle slot : Saves.getToggles()) {
                if (saves.contains(((RadioButton) slot).getId() + ".sav")) {
                    String path = Constantes.SAVEPATH + "/" + ((RadioButton) slot).getId() + ".sav";
                    slot.setUserData(((RadioButton) slot).getId() + ".sav");
                    Scanner scanner = null;
                    try {
                        scanner = new Scanner(new File(path));
                        ((RadioButton) slot).setText(scanner.nextLine());
                        scanner.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else {
                    ((RadioButton) slot).setText(((Node) slot).getId());
                }
            }
        }
    }

    @FXML
    @Override
    public void handleKeyPressed(KeyEvent keyEvent) {
        if (keyEvent.getCode().equals(KeyCode.ESCAPE)) {
            previousScene();
        }
    }

    @FXML
    public void handleSave(ActionEvent event) {
        if (Saves.getSelectedToggle().getUserData() != null) {
            joueur.save(((String) Saves.getSelectedToggle().getUserData()).split(".sav")[0] + ".sav");
        } else {
            joueur.save(((RadioButton) Saves.getSelectedToggle()).getText() + ".sav");
        }
        update();
    }

    @FXML
    public void handleFDelete(ActionEvent event) {
        ((RadioButton) ((Button) event.getSource()).getParent()).setSelected(false);
        File file = new File(Constantes.SAVEPATH + "/" + ((Node) event.getSource()).getId() + ".sav");
        if (file.exists()) {
            file.delete();
        }
        update();
    }
}
