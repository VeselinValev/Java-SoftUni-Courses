package core.factories;

import contracts.Unit;
import contracts.UnitFactory;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class UnitFactoryImpl implements UnitFactory {

	private static final String UNITS_PACKAGE_NAME =
			"units.";

	@Override
	public Unit createUnit(String unitType) throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
		Class<Unit> cl = (Class<Unit>) Class.forName(UNITS_PACKAGE_NAME + unitType);
		Constructor<Unit> constructor = cl.getDeclaredConstructor();
		constructor.setAccessible(true);
		return constructor.newInstance();
	}
}
