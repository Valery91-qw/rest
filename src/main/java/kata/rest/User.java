package kata.rest;

public class User {
    private Long id;
    private String name;
    private String lastName;
    private Byte age;

    public User(int id, String name, String lastName, int age) {
        this.id = (long) id;
        this.name = name;
        this.lastName = lastName;
        this.age = (byte) age;
    }

    @Override
    public String toString() {
        return "{ \"id\": " + this.id + ",\"name\": \"" + this.name + "\",\"lastName\": \"" + this.lastName
                + "\",\"age\": "
                + this.age + "}";
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
