package logger.loggers;

import logger.ReportThreshold;

public interface Logger {

    void logMessage(ReportThreshold threshold, String date, String message);
}