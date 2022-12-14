module com.example.sudoku {
    requires javafx.controls;
    requires javafx.fxml;

    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;

    opens com.example.sudoku to javafx.fxml;
    exports com.example.sudoku;
}