module miranda.c482retry {
    requires javafx.controls;
    requires javafx.fxml;


    opens miranda.c482retry to javafx.fxml, javafx.graphics;
    exports miranda.c482retry;
}