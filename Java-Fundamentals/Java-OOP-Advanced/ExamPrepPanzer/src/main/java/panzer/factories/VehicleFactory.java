package panzer.factories;

import panzer.contracts.Assembler;
import panzer.contracts.Vehicle;
import panzer.models.vehicles.Revenger;
import panzer.models.vehicles.Vanguard;

import java.math.BigDecimal;
import java.util.List;

public class VehicleFactory {

    public static Vehicle createVehicle(List<String> data){
        Assembler assembler = AssemblerFactory.createAssembler();
        if (data.get(1).equals("Vanguard")){
            return new Vanguard(data.get(2), Double.parseDouble(data.get(3)), BigDecimal.valueOf(Long.parseLong(data.get(4))),
                    Integer.parseInt(data.get(5)), Integer.parseInt(data.get(6)), Integer.parseInt(data.get(7)), assembler);
        }else{
            return new Revenger(data.get(2), Double.parseDouble(data.get(3)), BigDecimal.valueOf(Long.parseLong(data.get(4))),
                    Integer.parseInt(data.get(5)), Integer.parseInt(data.get(6)), Integer.parseInt(data.get(7)), assembler);
        }
    }
}
