public class Pet {
    private String name;
    private String kind;
    private int age;

    public Pet(String name, int age, String kind) {
        this.setName(name);
        this.setAge(age);
        this.setKind(kind);
    }

    public String getName() {
        return this.name;
    }

    private void setName(String name) {
        this.name = name;
    }

    public String getKind() {
        return this.kind;
    }

    private void setKind(String kind) {
        this.kind = kind;
    }

    public int getAge() {
        return this.age;
    }

    private void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return String.format("%s %s %s", this.getName(), this.getAge(), this.getKind());
    }
}
