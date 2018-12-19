package logger.appenders;

import logger.ReportThreshold;

public interface Appender {

    void setThreshold(ReportThreshold threshold);

    void append(ReportThreshold reportThreshold, String date, String message);
}
