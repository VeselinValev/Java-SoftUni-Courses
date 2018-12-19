package logger.layouts;

import logger.ReportThreshold;

public class SimpleLayout implements Layout {

    @Override
    public String returnFormattedMessage(String date, ReportThreshold threshold, String message) {
        return String.format("%s - %s - %s", date, threshold, message);
    }
}