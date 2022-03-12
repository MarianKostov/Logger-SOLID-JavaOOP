package appenders;

import layouts.Layout;
import loggers.MessageLogger;

public abstract class BaseAppender implements Appender{
    private Layout layout;
    protected int count;

    protected BaseAppender(Layout layout) {
        this.layout = layout;
    }

    public Layout getLayout() {
        return layout;
    }

    public int getCount() {
        return count;
    }

    @Override
    public int getMessageCount() {
        return count;
    }

//    @Override
//    public void append(String timeStamp, MessageLogger.LogLevel logLevel, String message) {
//        count++;
//    }
}
