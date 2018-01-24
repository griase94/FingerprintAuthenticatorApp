package de.lmu.ifi.fingerprintauthenticator;

/**
 * Created by andyg on 23.01.2018.
 */

public class Service {
    String name;
    ServiceStatus status;

    public Service() {
    }

    public Service(String name, ServiceStatus status) {
        this.name = name;
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ServiceStatus getStatus() {
        return status;
    }

    public void setStatus(ServiceStatus status) {
        this.status = status;
    }
}
