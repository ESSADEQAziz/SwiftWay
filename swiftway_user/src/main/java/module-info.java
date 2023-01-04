module swiftway_user {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.xml;
    requires java.sql.rowset;
    requires javafx.swing;

    opens swiftway_user to javafx.fxml;
    exports swiftway_user;
}
