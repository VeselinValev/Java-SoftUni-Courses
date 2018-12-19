package logger.layouts;

import logger.ReportThreshold;

public interface Layout {

    String returnFormattedMessage(String date, ReportThreshold threshold, String message);
}