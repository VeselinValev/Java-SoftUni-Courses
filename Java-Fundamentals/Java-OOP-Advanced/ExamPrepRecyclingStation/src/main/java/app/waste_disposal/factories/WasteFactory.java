package app.waste_disposal.factories;

import app.waste_disposal.contracts.Waste;
import app.waste_disposal.models.garbage.BurnableGarbage;
import app.waste_disposal.models.garbage.RecyclableGarbage;
import app.waste_disposal.models.garbage.StorableGarbage;

public class WasteFactory {
    public static Waste createWaste(String name, double volumePerKg, double weight, String type){
        Waste waste;
        if (type.equals("Recyclable")){
            waste = new RecyclableGarbage(name,  weight, volumePerKg);
        }else if (type.equals("Burnable")){
            waste = new BurnableGarbage(name,  weight, volumePerKg);
        }else{
            waste = new StorableGarbage(name,  weight, volumePerKg);
        }
        return waste;
    }
}
