package core;

import contracts.*;
import contracts.Runnable;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

public class Engine implements Runnable {

	private String[] data;
	private Repository repository;
	private UnitFactory unitFactory;
	private CommandInterpreter commandInterpreter;

	public Engine(Repository repository, UnitFactory unitFactory, CommandInterpreter commandInterpreter) {
		this.repository = repository;
		this.unitFactory = unitFactory;
		this.commandInterpreter = commandInterpreter;
	}

	@Override
	public void run() {
		BufferedReader reader = new BufferedReader(
				new InputStreamReader(System.in));
		while (true) {
			try {
				String input = reader.readLine();
				String[] tokens = input.split("\\s+");
				this.data = tokens;
				String commandName = tokens[0];
				Executable currentCommand = null;
				String result;
				try{
					currentCommand = this.commandInterpreter.interpretCommand(commandName);
				}catch (RuntimeException re){
					result = re.getMessage();
				}
				injectDependencies(currentCommand);
				result = currentCommand.execute();
				if (result.equals("fight")) {
					break;
				}
				System.out.println(result);
			} catch (RuntimeException e) {
				System.out.println(e.getMessage());
			} catch (IOException | InstantiationException | NoSuchMethodException | InvocationTargetException | ClassNotFoundException | IllegalAccessException e) {
				e.printStackTrace();
			}
		}
	}

	public void injectDependencies(Executable currentCommand) throws IllegalAccessException {
		Field[] engineFields = Engine.class.getDeclaredFields();
		Field[] commandFields = currentCommand.getClass().getSuperclass().getDeclaredFields();
		for (Field commandField: commandFields){
			if (commandField.isAnnotationPresent(Inject.class)){
				for (Field engineField: engineFields){
					if (engineField.getType().equals(commandField.getType())){
						commandField.setAccessible(true);
						commandField.set(currentCommand, engineField.get(this));
					}
				}
			}
		}
	}
}
