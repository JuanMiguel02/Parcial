module org.demo {
    requires javafx.controls;
    requires javafx.fxml;

    opens org.demo to javafx.fxml;
    opens org.demo.Controllers to javafx.fxml;
    opens org.demo.Models to javafx.base;

    exports org.demo;
    exports org.demo.Controllers;
}