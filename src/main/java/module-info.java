module com.example.abrakadyabra123 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires jdk.compiler;


    opens com.example.abrakadyabra123 to javafx.fxml;
    exports com.example.abrakadyabra123;
}