import Animals.Animal;
import Animals.Cat;
import Animals.Dog;
import Centers.AdoptionCenter;
import Centers.CastrationCenter;
import Centers.Center;
import Centers.CleansingCenter;

import java.util.*;
import java.util.stream.Collectors;

public class AnimalCenterManager {
    private Map<String, Center> cleansingCenters;
    private Map<String, Center> adoptionCenters;
    private Map<String, Center> castrationCenters;
    private List<Animal> castrated;
    private List<Animal> cleansed;
    private List<Animal> adopted;

    public AnimalCenterManager() {
        this.setCleansingCenters(new LinkedHashMap<>());
        this.setAdoptionCenters(new LinkedHashMap<>());
        this.setCastrationCenters(new LinkedHashMap<>());
        this.setAdopted(new ArrayList<>());
        this.setCleansed(new ArrayList<>());
        this.setCastrated(new ArrayList<>());
    }

    public Map<String, Center> getCastrationCenters() {
        return this.castrationCenters;
    }

    private void setCastrationCenters(Map<String, Center> castrationCenters) {
        this.castrationCenters = castrationCenters;
    }

    public List<Animal> getCastrated() {
        return this.castrated;
    }

    private void setCastrated(List<Animal> castrated) {
        this.castrated = castrated;
    }

    public List<Animal> getCleansed() {
        return this.cleansed;
    }

    private void setCleansed(List<Animal> cleansed) {
        this.cleansed = cleansed;
    }

    public List<Animal> getAdopted() {
        return this.adopted;
    }

    private void setAdopted(List<Animal> adopted) {
        this.adopted = adopted;
    }

    public Map<String, Center> getCleansingCenters() {
        return this.cleansingCenters;
    }

    private void setCleansingCenters(Map<String, Center> cleansingCenters) {
        this.cleansingCenters = cleansingCenters;
    }

    public Map<String, Center> getAdoptionCenters() {
        return this.adoptionCenters;
    }

    private void setAdoptionCenters(Map<String, Center> adoptionCenters) {
        this.adoptionCenters = adoptionCenters;
    }

    public void registerCleansingCenter(String name) {
        this.getCleansingCenters().putIfAbsent(name, new CleansingCenter(name));
    }

    public void registerAdoptionCenter(String name) {
        this.getAdoptionCenters().putIfAbsent(name, new AdoptionCenter(name));
    }

    public void registerCastrationCenter(String name) {
        this.getCastrationCenters().putIfAbsent(name, new CastrationCenter(name));
    }

    public void registerDog(String name, int age, int learnedCommands, String adoptionCenterName) {
        this.getAdoptionCenters().get(adoptionCenterName).getAnimals().add(new Dog(name, age, learnedCommands, adoptionCenterName));
    }

    public void registerCat(String name, int age, int intelligenceCoefficient, String adoptionCenterName) {
        this.getAdoptionCenters().get(adoptionCenterName).getAnimals().add(new Cat(name, age, intelligenceCoefficient, adoptionCenterName));
    }

    public void sendForCastration(String adoptionCenterName, String castrationCenterName) {
        this.getCastrationCenters().get(castrationCenterName).getAnimals()
                .addAll(this.getAdoptionCenters().get(adoptionCenterName).getAnimals().stream()
                        .filter(x -> !x.isCleansingStatus()).collect(Collectors.toList()));
        this.getAdoptionCenters().get(adoptionCenterName).getAnimals().removeIf(x -> !x.isCastratedStatus());
    }

    public void sendForCleansing(String adoptionCenterName, String cleansingCenterName) {
        this.getCleansingCenters().get(cleansingCenterName).getAnimals()
                .addAll(this.getAdoptionCenters().get(adoptionCenterName).getAnimals().stream()
                        .filter(x -> !x.isCleansingStatus()).collect(Collectors.toList()));
        this.getAdoptionCenters().get(adoptionCenterName).getAnimals().removeIf(x -> !x.isCleansingStatus());
    }

    public void cleanse(String cleansingCenterName) {
        Center currentCenter = this.getCleansingCenters().get(cleansingCenterName);
        for (Animal animal : currentCenter.getAnimals()) {
            animal.cleanAnimal();
            this.getAdoptionCenters().get(animal.getAdoptionCenter()).getAnimals().add(animal);
            this.getCleansed().add(animal);
        }
        currentCenter.getAnimals().clear();
    }

    public void castrate(String castrationCenterName) {
        Center currentCenter = this.getCastrationCenters().get(castrationCenterName);
        for (Animal animal : currentCenter.getAnimals()) {
            animal.castrateAnimal();
            this.getAdoptionCenters().get(animal.getAdoptionCenter()).getAnimals().add(animal);
            this.getCastrated().add(animal);
        }
        currentCenter.getAnimals().clear();
    }

    public void adopt(String adoptionCenterName) {
        Center currentCenter = this.getAdoptionCenters().get(adoptionCenterName);
        this.getAdopted().addAll(currentCenter.getAnimals().stream()
                .filter(Animal::isCleansingStatus).collect(Collectors.toList()));
        currentCenter.getAnimals().removeIf(Animal::isCleansingStatus);
    }

    public String printCastrationStatistics() {
        StringBuilder sb = new StringBuilder();
        sb.append("Paw Inc. Regular Castration Statistics\n");
        sb.append(String.format("Castration Centers: %s%n", this.getCastrationCenters().size()));
        sb.append(statisticsAdoptedOrCleansed("Castrated", this.getCastrated()));
        sb.deleteCharAt(sb.length() - 1);
        return sb.toString();
    }

    public void printStatistics() {
        StringBuilder sb = new StringBuilder();
        sb.append("Paw Incorporative Regular Statistics\n");
        sb.append(String.format("Adoption Centers: %s%n", this.getAdoptionCenters().size()));
        sb.append(String.format("Cleansing Centers: %s%n", this.getCleansingCenters().size()));
        sb.append(statisticsAdoptedOrCleansed("Adopted", this.getAdopted()));
        sb.append(statisticsAdoptedOrCleansed("Cleansed", this.getCleansed()));
        int awaitingAdoption = 0;
        for (Center center : this.getAdoptionCenters().values()) {
            center.getAnimals().removeIf(x -> !x.isCleansingStatus());
            awaitingAdoption += center.getAnimals().size();
        }
        sb.append(String.format("Animals Awaiting Adoption: %s%n", awaitingAdoption));
        int awaitingCleansing = 0;
        for (Center center : this.getCleansingCenters().values()) {
            awaitingCleansing += center.getAnimals().size();
        }
        sb.append(String.format("Animals Awaiting Cleansing: %s%n", awaitingCleansing));
        System.out.print(sb);
    }

    public String statisticsAdoptedOrCleansed(String centerType, List<Animal> center) {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("%s Animals: ", centerType));
        if (center.isEmpty()) {
            sb.append("None\n");
        } else {
            List<Animal> sortedAnimals = center.stream().sorted(Comparator.comparing(Animal::getName)).collect(Collectors.toList());
            for (Animal animal: sortedAnimals){
                sb.append(animal.getName() + ", ");
            }
            sb.delete(sb.length() - 2, sb.length()).append("\n");
        }
        return sb.toString();
    }
}
