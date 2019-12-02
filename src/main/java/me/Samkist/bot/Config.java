package me.Samkist.bot;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class Config {

    private static List<String> list;

    private Config() {

    }

    static  {
        Logger logger = LoggerFactory.getLogger(Config.class);
        try {
            list = Files.readAllLines(Paths.get("config.txt"));
        } catch (IOException e) {
            logger.warn("Could not find config.txt, attempting to create");
            Path dir = Paths.get(System.getProperty("user.dir"));
            Path fileToCreatePath = dir.resolve("config.txt");
            Path newFilePath = null;
            try {
                newFilePath = Files.createFile(fileToCreatePath);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            assert newFilePath != null;
            if(!Files.exists(newFilePath))
                logger.warn("Failed to create txt");
        }

    }

    public static String getToken() throws IndexOutOfBoundsException {
        return list.get(0);
    }

    public static String getOwnerId() throws IndexOutOfBoundsException {
        return list.get(1);
    }

    public static String getPrefix() throws IndexOutOfBoundsException {
        return list.get(2);
    }
}
