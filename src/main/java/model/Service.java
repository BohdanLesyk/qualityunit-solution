package model;

public class Service {
    private ServiceID serviceID;
    private ServiceVariation serviceVariation;

    public Service(ServiceID serviceID) {
        this.serviceID = serviceID;
    }

    public ServiceID getServiceID() {
        return serviceID;
    }

    public ServiceVariation getServiceVariation() {
        return serviceVariation;
    }

    public void setServiceVariation(ServiceVariation serviceVariation) {
        this.serviceVariation = serviceVariation;
    }

    @Override
    public String toString() {
        return "Service{" +
                "serviceID=" + serviceID +
                ", serviceVariation=" + serviceVariation +
                '}';
    }
}
