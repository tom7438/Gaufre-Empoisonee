package Modele;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class MyLogger {
    private static final Logger LOGGER = Logger.getLogger("");
    private static final String LOG_FILE_PATH = "gaufre.log";

    static {
        try {
            FileHandler fileHandler = new FileHandler(LOG_FILE_PATH, true);
            SimpleFormatter formatter = new SimpleFormatter();
            fileHandler.setFormatter(formatter);
            LOGGER.addHandler(fileHandler);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Logger getLogger() {
        return LOGGER;
    }
}
