package callofduty.interfaces;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

public interface Runnable {
    void run() throws IOException, IllegalAccessException, NoSuchFieldException, NoSuchMethodException, InvocationTargetException, InstantiationException;
}
