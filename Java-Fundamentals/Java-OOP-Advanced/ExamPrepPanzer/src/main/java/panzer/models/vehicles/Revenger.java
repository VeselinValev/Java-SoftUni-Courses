package panzer.models.vehicles;

import panzer.contracts.Assembler;

import java.math.BigDecimal;

public class Revenger extends AbstractVehicle {
    public Revenger(String model, double weight, BigDecimal price, int attack, int defense, int hitPoints, Assembler assembler) {
        super(model, weight, price.multiply(BigDecimal.valueOf(1.5)), (int) (attack * 2.5), (int) (defense * 0.5), (int) (hitPoints * 0.5), assembler);
    }
}
