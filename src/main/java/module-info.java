module org.ez4drift.aes {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.ez4drift.aes to javafx.fxml;
    exports org.ez4drift.aes;
}