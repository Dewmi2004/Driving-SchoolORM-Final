package lk.ijse.drivingschoolormfinal;
import lk.ijse.drivingschoolormfinal.config.FactoryConfiguration;
import org.hibernate.Session;

public class App {
    public static void main(String[] args) {
        Session session = FactoryConfiguration.getInstance().getSession();
        System.out.println("Session opened: " + session.isOpen());
        session.close();
    }
}