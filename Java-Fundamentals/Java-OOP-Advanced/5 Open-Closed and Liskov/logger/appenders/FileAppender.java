package logger.appenders;

import logger.ReportThreshold;
import logger.layouts.Layout;

public class FileAppender extends BaseAppender {

    private int fileSize;

    public FileAppender(Layout layout) {
        super(layout);
    }

    @Override
    public void append(ReportThreshold reportThreshold, String date, String message) {
        if (reportThreshold.ordinal() >= super.getThreshold().ordinal()) {
            String formattedMessage = super.getLayout().returnFormattedMessage(date, reportThreshold, message);
            this.fileSize = this.fileSize + this.getSizeFromMessage(formattedMessage);
            super.increaseMessagesAppendedCount();
        }
    }

    private int getSizeFromMessage(String message) {
        int sum = 0;

        for (char c : message.toCharArray()) {
            if ((c >= 65 && c <= 90) || (c >= 97 && c <= 122)) {
                sum += c;
            }
        }

        return sum;
    }

    @Override
    public String toString() {
        return super.toString() + String.format(", File size: %d", this.fileSize);
    }
}