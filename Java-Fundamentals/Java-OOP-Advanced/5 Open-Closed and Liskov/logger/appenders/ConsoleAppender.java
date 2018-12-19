package logger.appenders;

import logger.ReportThreshold;
import logger.layouts.Layout;

public class ConsoleAppender extends BaseAppender {

    public ConsoleAppender(Layout layout) {
        super(layout);
    }

    @Override
    public void append(ReportThreshold reportThreshold, String date, String message) {
        if (super.getThreshold().ordinal() <= reportThreshold.ordinal()) {
            System.out.println(super.getLayout().returnFormattedMessage(date, reportThreshold, message));
            super.increaseMessagesAppendedCount();
        }
    }
}