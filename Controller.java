import appenders.Appender;
import appenders.ConsoleAppender;
import appenders.FileAppender;
import appenders.XmlAppender;
import layouts.Layout;
import layouts.SimpleLayout;
import layouts.XmlLayout;
import loggers.Logger;
import loggers.MessageLogger;
import utilities.File;
import utilities.LogFile;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class Controller {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = Integer.parseInt(scanner.nextLine());

        Map<Appender, MessageLogger.LogLevel> appenderLogLevelMap = new LinkedHashMap<>();

        for (int i = 0; i < n; i++) {
            String[] tokens = scanner.nextLine().split(" ");

            String appenderType = tokens[0];
            String layoutType = tokens[1];
            MessageLogger.LogLevel logLevel = tokens.length == 3
                    ? MessageLogger.LogLevel.valueOf(tokens[2])
                    : MessageLogger.LogLevel.INFO;

            Layout layout = createLayout(layoutType);
            Appender appender = createAppender(appenderType, layout);
            appenderLogLevelMap.put(appender, logLevel);

        }

        Logger logger = new MessageLogger(appenderLogLevelMap);

        String line = scanner.nextLine();

        while (!line.equals("END")) {
            String[] tokens = line.split("\\|");

            MessageLogger.LogLevel logLevel = MessageLogger.LogLevel.valueOf(tokens[0]);

            String timeStamp = tokens[1];
            String message = tokens[2];

            switch (logLevel) {
                case INFO:
                    logger.logInfo(timeStamp, message);
                    break;
                case WARNING:
                    logger.logWarning(timeStamp, message);
                    break;
                case ERROR:
                    logger.logError(timeStamp, message);
                    break;
                case CRITICAL:
                    logger.logCritical(timeStamp, message);
                    break;
                case FATAL:
                    logger.logFatal(timeStamp, message);
                    break;
            }

            line = scanner.nextLine();
        }

        System.out.println(logger);
    }

    private static Appender createAppender(String appenderType, Layout layout) {
        return switch (appenderType) {
            case "ConsoleAppender" -> new ConsoleAppender(layout);
            case "FileAppender" -> new FileAppender(layout, new LogFile());
            case "XmlAppender" -> new XmlAppender(layout);
            default -> throw new IllegalStateException("Unexpected value: " + appenderType);
        };
    }

    private static Layout createLayout(String layoutType) {
        return layoutType.equals("SimpleLayout") ? new SimpleLayout() : new XmlLayout();
    }
}
