package lk.ijse.drivingschoolormfinal.util;

import lk.ijse.drivingschoolormfinal.entity.User;

public class SessionManager {
    private static User loggedUser;

    public static void setLoggedUser(User user) {
        loggedUser = user;
    }

    public static User getLoggedUser() {
        return loggedUser;
    }

    public static String getUserRole() {
        return loggedUser != null ? loggedUser.getUserRole() : null;
    }

    public static void clearSession() {
        loggedUser = null;
    }
}
