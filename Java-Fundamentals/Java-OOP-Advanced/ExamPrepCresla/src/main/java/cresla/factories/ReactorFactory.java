package cresla.factories;

import cresla.entities.reactors.CryoReactor;
import cresla.entities.reactors.HeatReactor;
import cresla.interfaces.Container;
import cresla.interfaces.Reactor;

import java.util.List;

public class ReactorFactory {

    public static Reactor createCryoReactor(int id, Container container, List<String> params){
        return new CryoReactor(id, container, Integer.parseInt(params.get(2)));
    }
    public static Reactor createHeatReactor(int id, Container container, List<String> params){
        return new HeatReactor(id, container, Integer.parseInt(params.get(2)));
    }
}
