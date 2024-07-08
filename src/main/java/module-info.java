module org.example.quizlet {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires org.xerial.sqlitejdbc;


    opens org.example.quizlet to javafx.fxml;
    exports org.example.quizlet;
}