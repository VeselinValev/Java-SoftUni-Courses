package logger.appenders;

import logger.ReportThreshold;
import logger.layouts.Layout;

public abstract class BaseAppender implements Appender {

    private ReportThreshold threshold;
    private Layout layout;
    private int messagesAppended;

    public BaseAppender(Layout layout) {
        this.threshold = ReportThreshold.INFO;
        this.layout = layout;
    }

    public ReportThreshold getThreshold() {
        return this.threshold;
    }

    public Layout getLayout() {
        return this.layout;
    }

    public int getMessagesAppended() {
        return this.messagesAppended;
    }

    public void increaseMessagesAppendedCount() {
        this.messagesAppended++;
    }

    @Override
    public void setThreshold(ReportThreshold threshold) {
        this.threshold = threshold;
    }

    @Override
    public String toString() {
        return String.format("Appender type: %s, Layout type: %s, Report level: %s, Messages appended: %d",
                this.getClass().getSimpleName(),
                this.getLayout().getClass().getSimpleName(),
                this.getThreshold(),
                this.getMessagesAppended());
    }
}