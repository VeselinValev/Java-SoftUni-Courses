package cresla.factories;

import cresla.entities.modules.CooldownSystem;
import cresla.entities.modules.CryogenRod;
import cresla.entities.modules.HeatProcessor;
import cresla.entities.reactors.CryoReactor;
import cresla.interfaces.*;
import cresla.interfaces.Module;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

public class ModuleFactory {

    public static EnergyModule createEnergyModule(int id, List<String> params){
        return new CryogenRod(id, Integer.parseInt(params.get(3)));
    }
    public static AbsorbingModule createEnergyAbsorberModule(int id, String type, List<String> params){
        if(type.equals("HeatProcessor")){
            return new HeatProcessor(id, Integer.parseInt(params.get(3)));
        }
        return new CooldownSystem(id, Integer.parseInt(params.get(3)));
    }
    public static Module createModule(int id, String type, List<String> params){
        if (type.equals("CryogenRod")){
            return new CryogenRod(id, Integer.parseInt(params.get(3)));
        }else if(type.equals("HeatProcessor")){
            return new HeatProcessor(id, Integer.parseInt(params.get(3)));
        }else{
            return new CooldownSystem(id, Integer.parseInt(params.get(3)));
        }
    }
}
