package models;

public class GameVO {
    private int id;
    private String name;
    private String type;
    private String tape;

    public GameVO(int id, String type, String tape, String name) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.tape = tape;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTape() {
        return tape;
    }

    public void setTape(String tape) {
        this.tape = tape;
    }
}

