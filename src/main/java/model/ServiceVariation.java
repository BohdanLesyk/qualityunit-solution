package model;

public enum ServiceVariation {
    SERVICE_VARIATION_1(1), SERVICE_VARIATION_2(2), SERVICE_VARIATION_3(3);

    private final int ID;

    ServiceVariation(int ID) {
        this.ID = ID;
    }

    public int getID() {
        return ID;
    }
}
