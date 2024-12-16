package me.project.sasha;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import netscape.javascript.JSObject;

import java.util.Objects;

public class HelloApplication extends Application {

    public static WebEngine webEngine;

    private final Service service = Service.getInstance();

    @Override
    public void start(Stage stage) {
        WebView webView = new WebView();
        webEngine = webView.getEngine();

        webEngine.load(Objects.requireNonNull(getClass().getResource("/html/index.html")).toExternalForm());

        webEngine.documentProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                JSObject window = (JSObject) webEngine.executeScript("window");
                window.setMember("java", new JavaBridge());
            }
        });

        Scene scene = new Scene(webView, 210, 220);
        stage.setScene(scene);
        stage.setTitle("JavaFX-HTML Communication");
        stage.show();

    }

    public static void main(String[] args) {
        launch();
    }
}