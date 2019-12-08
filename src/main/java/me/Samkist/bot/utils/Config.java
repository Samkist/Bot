package me.Samkist.bot.utils;

import me.Samkist.bot.main.Launcher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class Config {

    private static ArrayList<String> list;
    private static Path filePath = null;
    private static File file = null;

    private Config() {

    }

    static  {
        Logger logger = LoggerFactory.getLogger(Config.class);
        try {
            File f = new File("config.txt");
            if(f.createNewFile()) {
                throw new IOException("Config is empty");
            }
            list = (ArrayList<String>) Files.readAllLines(Paths.get("config.txt"));
            filePath = Paths.get("config.txt");
            file = filePath.toFile();
        } catch (IOException e) {

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

    public static String getGiphyAPIKey() throws IndexOutOfBoundsException {
        return list.get(3);
    }

    public static Path getFilePath() {
        return filePath;
    }

    public static File getConfig() {
        return file;
    }
}
