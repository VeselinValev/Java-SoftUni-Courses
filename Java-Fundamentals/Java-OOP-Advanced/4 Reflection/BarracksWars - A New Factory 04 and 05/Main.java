import contracts.CommandInterpreter;
import contracts.Repository;
import contracts.Runnable;
import contracts.UnitFactory;
import core.Engine;
import core.factories.UnitFactoryImpl;
import data.UnitRepository;
import interpreters.CommandInterpreterImpl;

public class Main {

	public static void main(String[] args) {
		Repository repository = new UnitRepository();
		UnitFactory unitFactory = new UnitFactoryImpl();
		CommandInterpreter commandInterpreter = new CommandInterpreterImpl();
		Runnable engine = new Engine(repository, unitFactory, commandInterpreter);
		engine.run();
	}
}
