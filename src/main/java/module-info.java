module me.project.sasha {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;
    requires jdk.jsobject;
    requires java.prefs;
    requires org.json;


    opens me.project.sasha to javafx.fxml;
    exports me.project.sasha;
}