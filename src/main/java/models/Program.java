package models;

public class Program {

    private String number;
    private String name;
    private String type;
    private String tape;
    private String registry;

    public Program(String number, String name, String type, String tape, String registry){
        this.number = number;
        this.name = name;
        this.type = type;
        this.tape = tape;
        this.registry = registry;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
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

    public String getRegistry() {
        return registry;
    }

    public void setRegistry(String registry) {
        this.registry = registry;
    }

    public String serialize(){
        return String.format("{\"number\":\"%s\",\"name\":\"%s\",\"type\":\"%s\",\"tape\":\"%s\"," +
                "\"register\":\"%s\"}",
            this.number, this.name, this.type, this.tape, this.registry);
    }
}
