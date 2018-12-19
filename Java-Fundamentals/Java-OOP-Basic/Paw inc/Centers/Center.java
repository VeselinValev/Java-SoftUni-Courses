package Centers;

import Animals.Animal;

import java.util.ArrayList;
import java.util.List;

public abstract class Center {
    private String name;
    private List<Animal> animals;

    public Center(String name) {
        this.setName(name);
        this.setAnimals(new ArrayList<>());
    }

    public String getName() {
        return this.name;
    }

    private void setName(String name) {
        this.name = name;
    }

    public List<Animal> getAnimals() {
        return this.animals;
    }

    private void setAnimals(List<Animal> animals) {
        this.animals = animals;
    }
}
