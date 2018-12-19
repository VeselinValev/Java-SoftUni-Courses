package logger;

import logger.appenders.Appender;
import logger.layouts.Layout;
import logger.loggers.Logger;
import logger.loggers.MessageLogger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class Main {
    public static void main(String[] args) throws IOException, ClassNotFoundException,
            NoSuchMethodException, IllegalAccessException, InstantiationException, InvocationTargetException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(reader.readLine());

        Appender[] appenders = new Appender[n];

        for (int i = 0; i < n; i++) {
            String[] tokens = reader.readLine().split(" ");

            String layoutName = tokens[1];
            String appenderName = tokens[0];

            Layout layout = getLayoutFromClassName(layoutName);
            Appender appender = getAppenderFromClassName(appenderName, layout);

            if (tokens.length == 3) {
                appender.setThreshold(ReportThreshold.valueOf(tokens[2]));
            }

            appenders[i] = appender;
        }

        Logger logger = new MessageLogger(appenders);

        String command = reader.readLine();

        while (!command.equals("END")) {
            String[] tokens = command.split("\\|");

            ReportThreshold messageThreshold = ReportThreshold.valueOf(tokens[0]);
            String date = tokens[1];
            String message = tokens[2];

            logger.logMessage(messageThreshold, date, message);

            command = reader.readLine();
        }

        System.out.println(logger);
    }

    @SuppressWarnings("unchecked")
    private static Layout getLayoutFromClassName(String layoutName) throws ClassNotFoundException, NoSuchMethodException,
            IllegalAccessException, InvocationTargetException, InstantiationException {
        Class<Layout> layoutClass = (Class<Layout>) Class.forName("logger.layouts." + layoutName);
        Constructor<Layout> constructor = layoutClass.getConstructor();

        return constructor.newInstance();
    }

    @SuppressWarnings("unchecked")
    private static Appender getAppenderFromClassName(String appenderName, Layout layout) throws ClassNotFoundException, NoSuchMethodException,
            IllegalAccessException, InvocationTargetException, InstantiationException {
        Class<Appender> appenderClass = (Class<Appender>) Class.forName("logger.appenders." + appenderName);
        Constructor<Appender> constructor = appenderClass.getDeclaredConstructor(Layout.class);

        return constructor.newInstance(layout);
    }
}