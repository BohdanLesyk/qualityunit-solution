package model;

public enum ResponseType {
    P("First answer"), N("Next Answer");

    private final String name;

    ResponseType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
