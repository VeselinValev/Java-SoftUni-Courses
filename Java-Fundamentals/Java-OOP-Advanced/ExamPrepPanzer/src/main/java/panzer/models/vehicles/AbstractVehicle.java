package panzer.models.vehicles;

import panzer.contracts.Assembler;
import panzer.contracts.Part;
import panzer.contracts.Vehicle;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.List;

public abstract class AbstractVehicle implements Vehicle {

    private String model;
    private double weight;
    private BigDecimal price;
    private int attack;
    private int defense;
    private int hitPoints;
    private Assembler assembler;

    AbstractVehicle(String model, double weight, BigDecimal price, int attack, int defense, int hitPoints, Assembler assembler) {
        this.model = model;
        this.weight = weight;
        this.price = price;
        this.attack = attack;
        this.defense = defense;
        this.hitPoints = hitPoints;
        this.assembler = assembler;
    }

    @Override
    public double getTotalWeight() {

        return this.assembler.getTotalWeight() + this.weight;
    }

    @Override
    public BigDecimal getTotalPrice() {

        return this.assembler.getTotalPrice().add(this.price);
    }

    @Override
    public long getTotalAttack() {
        return this.assembler.getTotalAttackModification() + this.attack;
    }

    @Override
    public long getTotalDefense() {
        return this.assembler.getTotalDefenseModification() + this.defense;
    }

    @Override
    public long getTotalHitPoints() {
        return this.assembler.getTotalHitPointModification() + this.hitPoints;
    }

    @Override
    public void addArsenalPart(Part arsenalPart) {
        this.assembler.addArsenalPart(arsenalPart);
    }

    @Override
    public void addShellPart(Part shellPart) {
        this.assembler.addShellPart(shellPart);
    }

    @Override
    public void addEndurancePart(Part endurancePart) {
        this.assembler.addEndurancePart(endurancePart);
    }

    @Override
    public Iterable<Part> getParts() throws NoSuchFieldException, IllegalAccessException {
        Class<?> assemblerClass = this.assembler.getClass();
        Field allParts = assemblerClass.getDeclaredField("allParts");
        allParts.setAccessible(true);
        return (List<Part>) allParts.get(this.assembler);
    }

    @Override
    public String getModel() {

        return this.model;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("%s - %s%n", this.getClass().getSimpleName(), this.getModel()))
                .append(String.format("Total Weight: %.3f%n", this.getTotalWeight()))
                .append(String.format("Total Price: %.3f%n", this.getTotalPrice()))
                .append(String.format("Attack: %s%n", this.getTotalAttack()))
                .append(String.format("Defense: %s%n", this.getTotalDefense()))
                .append(String.format("HitPoints: %s%n", this.getTotalHitPoints()));
        sb.append("Parts: ");
        int counter = 0;
        try {
            for (Part part: this.getParts()){
                sb.append(part.getModel()).append(", ");
                counter++;
            }
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        if (counter == 0){
            sb.append("None");
        }else{
            sb.delete(sb.length() - 2, sb.length());
        }
        return sb.toString();
    }
}
