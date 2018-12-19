package cresla;

import cresla.factories.ContainerFactory;
import cresla.factories.ModuleFactory;
import cresla.factories.ReactorFactory;
import cresla.interfaces.Container;
import cresla.interfaces.Manager;
import cresla.interfaces.Module;
import cresla.interfaces.Reactor;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class ManagerImpl implements Manager {
    private Map<Integer, Reactor> allReactors;
    private Map<Integer, Module> allModules;
    private int cryoReactorsCount;
    private int heatReactorsCount;
    private int absorbingModuleCount;
    private int energyModuleCount;
    private int id;

    ManagerImpl() {
        this.allReactors = new LinkedHashMap<>();
        this.allModules = new LinkedHashMap<>();
        this.id = 1;
        this.cryoReactorsCount = 0;
        this.heatReactorsCount = 0;
        this.absorbingModuleCount = 0;
        this.energyModuleCount = 0;
    }

    @Override
    public String reactorCommand(List<String> arguments) {
        Container container = ContainerFactory.createContainer(Integer.parseInt(arguments.get(3)));
        String reactorType = arguments.get(1);
        if (reactorType.equals("Cryo")){
            allReactors.putIfAbsent(this.id, ReactorFactory.createCryoReactor(this.id, container, arguments));
            this.cryoReactorsCount++;
        }else{
            allReactors.putIfAbsent(this.id, ReactorFactory.createHeatReactor(this.id, container, arguments));
            this.heatReactorsCount++;
        }
        return String.format("Created %s Reactor - %s", reactorType, this.id++);
    }

    @Override
    public String moduleCommand(List<String> arguments) {
        String moduleType = arguments.get(2);
        Reactor reactor = this.allReactors.get(Integer.parseInt(arguments.get(1)));
        if (moduleType.equals("CryogenRod")){
                   reactor.addEnergyModule(ModuleFactory.createEnergyModule(this.id, arguments));
                   this.energyModuleCount++;
        }else{
            reactor.addAbsorbingModule(ModuleFactory.createEnergyAbsorberModule(this.id, moduleType, arguments));
            this.absorbingModuleCount++;
        }
        this.allModules.putIfAbsent(id, ModuleFactory.createModule(this.id, moduleType, arguments));
        return String.format("Added %s - %s to Reactor - %s", moduleType, this.id++, reactor.getId());
    }

    @Override
    public String reportCommand(List<String> arguments) {
        int id = Integer.parseInt(arguments.get(1));
        if (this.allReactors.containsKey(id)){
            return this.allReactors.get(id).toString();
        }else{
            return this.allModules.get(id).toString();
        }
    }

    @Override
    public String exitCommand(List<String> arguments) {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("Cryo Reactors: %s%n", this.cryoReactorsCount));
        sb.append(String.format("Heat Reactors: %s%n", this.heatReactorsCount));
        sb.append(String.format("Energy Modules: %s%n", this.energyModuleCount));
        sb.append(String.format("Absorbing Modules: %s%n", this.absorbingModuleCount));
        sb.append(String.format("Total Energy Output: %s%n", this.getAllReactorsEnergyOutput()));
        sb.append(String.format("Total Heat Absorbing: %s", this.getAllReactorsHeatAbsorbing()));
        return sb.toString();
    }

    private long getAllReactorsEnergyOutput(){
        long result = 0L;
        for (Reactor reactor: allReactors.values()){
            result+= reactor.getTotalEnergyOutput();
        }
        return result;
    }

    private long getAllReactorsHeatAbsorbing(){
        long result = 0L;
        for (Reactor reactor: allReactors.values()){
            result+= reactor.getTotalHeatAbsorbing();
        }
        return result;
    }
}
