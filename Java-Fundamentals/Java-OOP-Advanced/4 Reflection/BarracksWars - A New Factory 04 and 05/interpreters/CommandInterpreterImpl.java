package interpreters;

import contracts.Executable;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class CommandInterpreterImpl implements contracts.CommandInterpreter {

    private final String PACKAGE_NAME = "core.commands.";
    private final String SUFFIX = "Command";


    @Override
    public Executable interpretCommand(String commandName) throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        String correctName = String.valueOf(commandName.charAt(0)).toUpperCase() + commandName.substring(1);
        Class<?> cl;
        try{
             cl = Class.forName(PACKAGE_NAME + correctName + SUFFIX);
        }catch (ClassNotFoundException cnf){
            throw new RuntimeException("Invalid command!");
        }
        Constructor<?> constructor = cl.getConstructor();
        return (Executable) constructor.newInstance();
    }
}
