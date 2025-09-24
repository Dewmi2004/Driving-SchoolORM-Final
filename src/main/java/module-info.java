module lk.ijse.drivingschoolormfinal {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires static lombok;

    requires jakarta.persistence;
    requires org.hibernate.orm.core;
    requires java.naming;
//    requires ehcache;
    requires jakarta.xml.bind;
    requires jakarta.activation;
    requires jbcrypt;

    opens lk.ijse.drivingschoolormfinal.controller to javafx.fxml;
    opens lk.ijse.drivingschoolormfinal.view.tdm to javafx.base;
    opens lk.ijse.drivingschoolormfinal.config to jakarta.persistence;
    opens lk.ijse.drivingschoolormfinal.entity to org.hibernate.orm.core;


    exports lk.ijse.drivingschoolormfinal;
}
