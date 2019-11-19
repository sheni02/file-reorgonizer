package fr.adlere.kata.rte.reorganizer;

import fr.adlere.kata.rte.reorganizer.io.IOFileManager;
import fr.adlere.kata.rte.reorganizer.transformer.FileReorganizer;
import org.apache.log4j.Logger;

public class App {
    private static final Logger LOG = Logger.getLogger(App.class);

    public static void main( String[] args ) {
        if(args.length == 2) {
            IOFileManager ioFileManager = IOFileManager.createIOFileManager(args[0], args[1]);
            FileReorganizer fileReorganizer = ioFileManager.readAndReorganize();
            ioFileManager.writeResult(fileReorganizer);
        } else {
            LOG.error("Command line argument must contain in the first argument the input file and in the second argument the path to the result.txt file");
        }
    }
}
