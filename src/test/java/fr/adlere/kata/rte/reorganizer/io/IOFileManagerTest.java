package fr.adlere.kata.rte.reorganizer.io;

import fr.adlere.kata.rte.reorganizer.transformer.FileReorganizer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.util.StringUtils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

class IOFileManagerTest {

    private String outputFile;
    private IOFileManager ioFileManager;

    @BeforeEach
    void setUp() {
        String inputFile = getClass().getClassLoader().getResource("test0.txt").getFile();
        outputFile = "result.txt";
        assertTrue(StringUtils.isNotBlank(inputFile));
        ioFileManager = IOFileManager.createIOFileManager(inputFile, outputFile);
        assertNotNull(ioFileManager);
    }

    @Test
    void readAndReorganize_WhenInputFileExist_ReturnFileReorganizerObject() {
        FileReorganizer fileReorganizer = ioFileManager.readAndReorganize();
        assertNotNull(fileReorganizer);
        assertEquals(FileReorganizer.class, fileReorganizer.getClass());
    }

    @Test
    void readAndReorganize_WhenInputFileExist_ReturnFileReorganizerObjectWithOrganizedNumbersInTheArray() {
        FileReorganizer fileReorganizer = ioFileManager.readAndReorganize();
        StringBuilder[] resultArray = fileReorganizer.getResultArray();
        StringBuilder[] expectedResultArray = new StringBuilder[FileReorganizer.NB_ELEMENT];
        expectedResultArray[0] = new StringBuilder("900 60 ");
        expectedResultArray[1] = new StringBuilder("711 1 ");
        expectedResultArray[2] = new StringBuilder("302 ");
        expectedResultArray[3] = new StringBuilder("233 ");
        expectedResultArray[4] = new StringBuilder("624 ");
        expectedResultArray[5] = new StringBuilder("965 305 ");
        expectedResultArray[6] = new StringBuilder("826 ");
        expectedResultArray[7] = new StringBuilder("687 ");
        expectedResultArray[8] = new StringBuilder("978 918 ");
        expectedResultArray[9] = new StringBuilder("9 139 ");
        IntStream.range(0, expectedResultArray.length).forEach(index -> assertEquals(expectedResultArray[index].toString(), resultArray[index].toString()));
    }

    @Test
    void writeResult_WhenInputFileExist_WriteResultFileWithOrganizedNumbers() throws IOException {
        StringBuilder[] expectedResultArray = new StringBuilder[FileReorganizer.NB_ELEMENT];
        expectedResultArray[0] = new StringBuilder("900 60");
        expectedResultArray[1] = new StringBuilder("711 1");
        expectedResultArray[2] = new StringBuilder("302");
        expectedResultArray[3] = new StringBuilder("233");
        expectedResultArray[4] = new StringBuilder("624");
        expectedResultArray[5] = new StringBuilder("965 305");
        expectedResultArray[6] = new StringBuilder("826");
        expectedResultArray[7] = new StringBuilder("687");
        expectedResultArray[8] = new StringBuilder("978 918");
        expectedResultArray[9] = new StringBuilder("9 139");
        ioFileManager.writeResult(ioFileManager.readAndReorganize());
        BufferedReader bufferedReader = new BufferedReader(new FileReader(outputFile));
        int i = 0;
        while (bufferedReader.ready() && i < expectedResultArray.length) {
            assertEquals(expectedResultArray[i++].toString(), bufferedReader.readLine());
        }
    }
}