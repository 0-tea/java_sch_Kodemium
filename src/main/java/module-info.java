module com.example.abrakadyabra123 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.abrakadyabra123 to javafx.fxml;
    exports com.example.abrakadyabra123;
}