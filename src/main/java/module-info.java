module org.itstep {
    requires javafx.controls;
    requires javafx.fxml;
    exports org.itstep.ui.controller;
    exports org.itstep.ui;
    exports org.itstep.blackjack;
    exports org.itstep;
    exports org.itstep.blackjack.card;
    exports org.itstep.blackjack.event;
    opens org.itstep.ui.controller;
}