module swiftway {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    opens swiftway to javafx.fxml;
    exports swiftway;
}
