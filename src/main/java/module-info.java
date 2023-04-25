module com.example.project {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.hibernate.orm.core;
    requires java.naming;
    requires java.sql;
    requires jakarta.persistence;


    opens com.example.project to javafx.fxml, org.hibernate.orm.core;
    exports com.example.project;
}