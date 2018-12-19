package cresla.factories;

import cresla.entities.containers.ModuleContainer;
import cresla.interfaces.Container;

public class ContainerFactory {

    public static Container createContainer(int capacity){
        return new ModuleContainer(capacity);
    }
}
