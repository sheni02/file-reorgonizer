package fr.adlere.kata.rte.reorganizer.io;

import fr.adlere.kata.rte.reorganizer.transformer.FileReorganizer;
import org.apache.log4j.Logger;

import java.io.*;


public class IOFileManager {

    private static final Logger LOG = Logger.getLogger(IOFileManager.class);

    private String inputFileUrl;
    private String outputFileUrl;


    public static IOFileManager createIOFileManager(String inputFileUrl, String resultFile) {
        IOFileManager ioFileManager = new IOFileManager();
        ioFileManager.setInputFileUrl(inputFileUrl);
        ioFileManager.setOutputFileUrl(resultFile);
        return ioFileManager;
    }

    /**
     * Read teh values line by line from the input file. Create an instance of FileReorganizer.
     * Organize the read numbers in the array of the FileReorganizer instance.
     * @return FileReorganizer
     */
    public FileReorganizer readAndReorganize() {
        FileReorganizer fileReorganizer = FileReorganizer.createFileReorganizer();

        try (BufferedReader reader = new BufferedReader(new FileReader(inputFileUrl))) {
            while (reader.ready()) {
                fileReorganizer.addToStringBuilder(reader.readLine());
            }
        } catch (FileNotFoundException e) {
            LOG.error("Couldn't find the input file", e);
        } catch (IOException e) {
            LOG.error("Error reading input file", e);
        }
        return fileReorganizer;
    }

    /**
     * Write in the result file, the array passed in the FileReorganizer instance.
     * The result file will receive one array cell per line.
     * @param fileReorganizer
     */
    public void writeResult(FileReorganizer fileReorganizer) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFileUrl))) {
            StringBuilder[] resultArray = fileReorganizer.getResultArray();
            for (int i = 0; i < resultArray.length; i++) {
                StringBuilder element = resultArray[i];
                writer.write(element.toString().trim());
                if(i != resultArray.length - 1) {
                    writer.newLine();
                }
            }
        } catch (IOException e) {
            LOG.error("Error Writing into result file", e);
        }
    }

    public void setInputFileUrl(String inputFileUrl) {
        this.inputFileUrl = inputFileUrl;
    }

    public void setOutputFileUrl(String outputFileUrl) {
        this.outputFileUrl = outputFileUrl;
    }
}
