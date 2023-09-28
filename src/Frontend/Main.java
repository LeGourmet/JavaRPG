//DELON ARTHUR
package Frontend;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.image.Image;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileInputStream;


public class Main extends Application {
    private boolean debug = true;

    @Override
    public void start(Stage window) throws Exception {
        Font.loadFont(new FileInputStream(new File("src/Frontend/assets/fonts/JMHCthulhumbus-Regular.ttf")), 10);
        window.setResizable(false);
        window.getIcons().addAll(
                new Image(new FileInputStream(new File("src/Frontend/assets/images/UI/icons/icon16px.png"))),
                new Image(new FileInputStream(new File("src/Frontend/assets/images/UI/icons/icon32px.png"))),
                new Image(new FileInputStream(new File("src/Frontend/assets/images/UI/icons/icon48px.png"))),
                new Image(new FileInputStream(new File("src/Frontend/assets/images/UI/icons/icon64px.png"))),
                new Image(new FileInputStream(new File("src/Frontend/assets/images/UI/icons/icon96px.png"))),
                new Image(new FileInputStream(new File("src/Frontend/assets/images/UI/icons/icon256px.png")))
        );
        FXMLLoader loader = new FXMLLoader(getClass().getResource("scenes/MainMenu.fxml"));
        loader.load();
        MainMenuController mainMenu = loader.getController();
        mainMenu.setup(window);
        window.setTitle("J.A.V.A.A.");
        FXMLLoader.load(getClass().getResource("scenes/NewGame.fxml"));
        FXMLLoader.load(getClass().getResource("scenes/LoadGame.fxml"));
        FXMLLoader.load(getClass().getResource("scenes/Lobby.fxml"));
        FXMLLoader.load(getClass().getResource("scenes/SaveGame.fxml"));
        FXMLLoader.load(getClass().getResource("scenes/Inventory.fxml"));
        FXMLLoader.load(getClass().getResource("scenes/Shop.fxml"));
        FXMLLoader.load(getClass().getResource("scenes/Difficulty.fxml"));
        FXMLLoader.load(getClass().getResource("scenes/Fight.fxml"));
        FXMLLoader.load(getClass().getResource("scenes/NextStage.fxml"));
        window.show();

        if (debug) {
            Stage debug = new Stage();
            debug.setResizable(false);
            debug.setScene(FXMLLoader.load(getClass().getResource("debug.fxml")));
            debug.show();
        }
    }
}
