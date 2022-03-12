package layouts;

import layouts.Layout;
import loggers.MessageLogger;

public class ComplexLayout implements Layout {

    @Override
    public String format(String timeStamp, MessageLogger.LogLevel logLevel, String message) {
        return String.format("%s <=> %s <=> %s",timeStamp, logLevel, message);
    }
}
