module org.example.lab2 {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.example.lab2 to javafx.fxml;
    exports org.example.lab2;
}