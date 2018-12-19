package engine;

import annotations.Inject;
import contracts.*;

import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

public class Engine implements Runnable {

    private String[] params;
    private Repository<Weapon> allWeapons;
    private ConsoleWriter writer;
    private ConsoleReader reader;

    public Engine(Repository<Weapon> allWeapons, ConsoleWriter writer, ConsoleReader reader) {
        this.allWeapons = allWeapons;
        this.writer = writer;
        this.reader = reader;
    }

    @Override
    public void run() {
        try {
            for (String input = reader.readLine(); !input.equals("END"); input = reader.readLine()) {
                this.params = input.split(";");
                try{
                    String result = dispatchCommand(this.params[0]);
                    if (result != null) {
                        System.out.println(result);
                    }
                }catch (IndexOutOfBoundsException iobe){

                }
            }
        } catch (IOException | InstantiationException | InvocationTargetException | IllegalAccessException | NoSuchMethodException | ClassNotFoundException e) {
            e.printStackTrace();

        }
    }

    @SuppressWarnings("unchecked")
    private String dispatchCommand(String command) throws ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        Class<Executable> commandClass = (Class<Executable>) Class.forName(String.format("commands.%sCommand", command));
        Constructor<Executable> declaredConstructor = commandClass.getDeclaredConstructor();
        Executable executable = declaredConstructor.newInstance();
        injectDependencies(executable);
        return executable.execute();
    }

    private void injectDependencies(Executable executable) throws IllegalAccessException {
        Field[] fields = executable.getClass().getSuperclass().getDeclaredFields();
        Field[] currentFields = this.getClass().getDeclaredFields();
        for (Field field : fields) {
            if (field.isAnnotationPresent(Inject.class)) {
                for (Field currentField : currentFields) {
                    if (field.getType().equals(currentField.getType())) {
                        field.setAccessible(true);
                        field.set(executable, currentField.get(this));
                    }
                }
            }
        }
    }
}
