package model;

public enum ServiceID {
    SERVICE_ID_1(1), SERVICE_ID_2(2), SERVICE_ID_3(3), SERVICE_ID_4(4), SERVICE_ID_5(5),
    SERVICE_ID_6(6), SERVICE_ID_7(7), SERVICE_ID_8(8), SERVICE_ID_9(9), SERVICE_ID_10(10);

    private final int ID;

    ServiceID(int ID) {
        this.ID = ID;
    }

    public int getID() {
        return ID;
    }
}
