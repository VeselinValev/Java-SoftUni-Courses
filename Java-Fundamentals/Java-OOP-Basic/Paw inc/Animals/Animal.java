package Animals;

public abstract class Animal {
    private String name;
    private int age;
    private boolean cleansingStatus;
    private boolean castratedStatus;
    private String adoptionCenter;

    public Animal(String name, int age) {
        this.setName(name);
        this.setAge(age);
        this.setCleansingStatus(false);
        this.setCastratedStatus(false);
    }

    public boolean isCastratedStatus() {
        return this.castratedStatus;
    }

    private void setCastratedStatus(boolean castratedStatus) {
        this.castratedStatus = castratedStatus;
    }

    public String getAdoptionCenter() {
        return this.adoptionCenter;
    }

    private void setAdoptionCenter(String adoptionCenter){
        this.adoptionCenter = adoptionCenter;
    };

    public void changeAdoptionCenter(String adoptionCenter){
        this.setAdoptionCenter(adoptionCenter);
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

    public boolean isCleansingStatus() {
        return this.cleansingStatus;
    }

    private void setCleansingStatus(boolean cleansingStatus) {
        this.cleansingStatus = cleansingStatus;
    }

    public void cleanAnimal(){
        this.setCleansingStatus(true);
    }

    public void castrateAnimal(){
        this.setCastratedStatus(true);
    }
}
