package application.calcpro.database;

import java.io.*;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import application.calcpro.models.JobData;

public class DatabaseOperations {
    // Logger
    private static final Logger logger = Logger.getLogger(DatabaseOperations.class.getName());

    private static final String APP_DIR = ".calcpro";
    private static final String FILE_NAME = "jobData.dat";

    private static Path getDataFilePath() {
        String userHome = System.getProperty("user.home");
        Path appDir = Paths.get(userHome, APP_DIR);
        try {
            Files.createDirectories(appDir);
        } catch (IOException e) {
            logger.severe("Failed to create application directory: " + e.getMessage());
        }
        return appDir.resolve(FILE_NAME);
    }

    public static void saveData(List<JobData> jobDataList) {
        Path filePath = getDataFilePath();
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filePath.toFile()))) {
            oos.writeObject(jobDataList);
        } catch (IOException e) {
            logger.severe("An error occurred while saving data to the database: " + e.getMessage());
        }
    }

    public static List<JobData> loadData() {
        Path filePath = getDataFilePath();
        if (!Files.exists(filePath)) {
            return new ArrayList<>();
        }
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filePath.toFile()))) {
            return (List<JobData>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            logger.severe("An error occurred while loading data from the database: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    public static void addJobData(JobData jobData) {
        List<JobData> jobDataList = loadData();
        jobDataList.add(jobData);
        saveData(jobDataList);
    }
}