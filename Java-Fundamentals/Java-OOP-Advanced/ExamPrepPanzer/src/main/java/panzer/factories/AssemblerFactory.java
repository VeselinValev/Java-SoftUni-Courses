package panzer.factories;

import panzer.contracts.Assembler;
import panzer.models.miscellaneous.VehicleAssembler;

public class AssemblerFactory {

    public static Assembler createAssembler(){
        return new VehicleAssembler();
    }
}
