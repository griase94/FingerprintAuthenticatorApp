package de.lmu.ifi.fingerprintauthenticator;

/**
 * Created by andyg on 23.01.2018.
 */

public class RegistrationInfo {
    String service_name;
    String user_id;

    public RegistrationInfo() {
    }

    public String getService_name() {
        return service_name;
    }

    public void setService_name(String service_name) {
        this.service_name = service_name;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }
}
