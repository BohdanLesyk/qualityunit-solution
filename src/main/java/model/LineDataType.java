package model;

public enum LineDataType {
    C("Waiting timeline"), D("Query");

    private final String name;

    LineDataType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
