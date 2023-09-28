module Frontend {
    requires javafx.controls;
    requires javafx.fxml;

    requires com.almasb.fxgl.all;

    opens Frontend to javafx.fxml;
    exports Frontend;
}