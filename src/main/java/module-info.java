module swiftway {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires poi;
    requires poi.ooxml;
    requires poi.ooxml.schemas;
    requires org.apache.xmlbeans;
    requires javafx.swing;
    requires java.mail;
    
   
    
    

    
    

    
    
    opens swiftway to javafx.fxml;
    opens  swiftway.Models;
    exports swiftway;
}
