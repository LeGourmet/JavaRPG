//DELON ARTHUR
package Frontend;

import Entity.mob.Ennemie;
import Entity.personnages.Personnage;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.HashMap;

public abstract class BaseController {
    static protected Stage window;
    static protected String lastScene;
    static protected String currentDisplayedScene;
    static protected HashMap<String, Scene> scenes;
    static protected HashMap<String, BaseController> controllers;
    static protected boolean canBePausedCurrently = false;
    static protected Personnage joueur;
    static protected ArrayList<Ennemie> moblist;
    static protected int dungeonSize;
    static boolean gamePaused = false;
    @FXML
    protected Scene scene;
    @FXML
    protected String sceneName;
    @FXML
    private Boolean canBePaused = false;

    @FXML
    public void initialize() {
        if (scenes != null)
            scenes.put(sceneName, scene);
        if (controllers != null)
            controllers.put(sceneName, this);
    }

    public void setup(Stage stage) { // setup le stage (1 seul appel)
        window = stage;
        scenes = new HashMap<>();
        controllers = new HashMap<>();
        scenes.put(sceneName, scene);
        controllers.put(sceneName, this);
        currentDisplayedScene = sceneName;
        window.setScene(scene);
    }

    @FXML
    public void previousScene() {
        controllers.get(lastScene).load();
    }

    protected void changeScene(String scenename) {
        if (scenes.containsKey(scenename)) {
            controllers.get(scenename).load();

        }
    }

    @FXML
    public void exit() {
        Platform.exit();
    }

    @FXML
    public void handleKeyPressed(KeyEvent keyEvent) {
    }

    public void load() {
        window.setScene(scene);
        lastScene = currentDisplayedScene;
        currentDisplayedScene = sceneName;
        canBePausedCurrently = canBePaused;
        controllers.get(currentDisplayedScene).update();
    }

    public abstract void update();
}