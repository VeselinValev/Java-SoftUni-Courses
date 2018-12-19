public class Person implements Comparable<Person>{
    private String name;
    private int age;

    public Person(String name, int age) {
        this.setName(name);
        this.setAge(age);
    }

    public String getName() {
        return this.name;
    }

    private void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return this.age;
    }

    private void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return String.format("%s %s", this.getName(), this.getAge());
    }

    @Override
    public int compareTo(Person o) {
        int compare = this.getName().compareTo(o.getName());
        if (compare == 0){
            compare = Integer.compare(this.getAge(), o.getAge());
        }
        return compare;
    }

    @Override
    public int hashCode() {
        int prime = 5;
        int result = 1;
        result = prime * result + this.getAge();
        result = prime * result + this.getName().hashCode();
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) return false;
        Person temp = (Person) obj;
        if(!temp.getName().equals(this.getName()) || temp.getAge() != temp.getAge()){
            return false;
        }
        return true;
    }
}
