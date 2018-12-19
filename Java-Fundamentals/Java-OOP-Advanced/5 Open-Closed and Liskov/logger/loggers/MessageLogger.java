package logger.loggers;

import logger.ReportThreshold;
import logger.appenders.Appender;

public class MessageLogger implements Logger {

    Appender[] appenders;

    public MessageLogger(Appender... appenders) {
        this.appenders = appenders;
    }

    @Override
    public void logMessage(ReportThreshold threshold, String date, String message) {
        for (Appender appender : this.appenders) {
            appender.append(threshold, date, message);
        }
    }

    @Override
    public String toString() {
        StringBuilder out = new StringBuilder();
        out.append("Logger info\n");

        for (Appender appender : this.appenders) {
            out.append(appender.toString()).append("\n");
        }

        return out.toString();
    }
}