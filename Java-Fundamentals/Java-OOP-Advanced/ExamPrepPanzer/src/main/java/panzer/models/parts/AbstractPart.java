package panzer.models.parts;

import panzer.contracts.Part;

import java.math.BigDecimal;

public abstract class AbstractPart implements Part {
    private String Model;
    private double Weight;
    private BigDecimal Price;

    AbstractPart(String model, double weight, BigDecimal price) {
        Model = model;
        Weight = weight;
        Price = price;
    }

    @Override
    public String getModel() {
        return this.Model;
    }

    @Override
    public double getWeight() {
        return this.Weight;
    }

    @Override
    public BigDecimal getPrice() {
        return this.Price;
    }

    @Override
    public String toString() {
        return String.format("%s Part - %s", this.getClass().getSimpleName(), this.getModel());
    }
}
