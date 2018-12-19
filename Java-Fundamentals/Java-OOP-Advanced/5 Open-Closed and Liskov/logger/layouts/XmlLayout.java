package logger.layouts;

import logger.ReportThreshold;

public class XmlLayout implements Layout {

    @Override
    public String returnFormattedMessage(String date, ReportThreshold threshold, String message) {
        return String.format("<log>\n\t<date>%s</date>\n\t<level>%s</level>\n\t<message>%s</message>\n</log>",
                date, threshold, message);
    }
}