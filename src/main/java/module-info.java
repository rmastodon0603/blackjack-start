module org.itstep {
    requires javafx.controls;
    requires javafx.fxml;
    exports org.itstep.blackjack.controller;
    exports org.itstep.blackjack.ui;
    exports org.itstep.blackjack;
    exports org.itstep;
    opens org.itstep.blackjack.controller;
}