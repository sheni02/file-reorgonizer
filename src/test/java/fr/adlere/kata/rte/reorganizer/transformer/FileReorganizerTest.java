package fr.adlere.kata.rte.reorganizer.transformer;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.TreeSet;

import static org.junit.jupiter.api.Assertions.*;

class FileReorganizerTest {

    private FileReorganizer fileReorganizer;

    @BeforeEach
    void setUp() {
        fileReorganizer = FileReorganizer.createFileReorganizer();
    }
    @Test
    void createFileReorganizer_ShouldReturnNonNullObject() {
        assertNotNull(fileReorganizer);
    }

    @Test
    void createFileReorganizer_When10IsPassed_ShouldReturnAfileOrganizerInstanceContainingAnArrayOf10StringBuilders() {
        FileReorganizer fileReorganizer = FileReorganizer.createFileReorganizer();
        assertEquals(10, fileReorganizer.getResultArray().length);
    }

    @Test
    void createFileReorganizer_When10IsPassed_ShouldReturnAfileOrganizerInstanceContainingAnArrayOf10NonNullStringBuilder() {
        FileReorganizer fileReorganizer = FileReorganizer.createFileReorganizer();
        assertNotNull(fileReorganizer.getResultArray()[0]);
        assertNotNull(fileReorganizer.getResultArray()[1]);
        assertNotNull(fileReorganizer.getResultArray()[2]);
        assertNotNull(fileReorganizer.getResultArray()[3]);
        assertNotNull(fileReorganizer.getResultArray()[4]);
        assertNotNull(fileReorganizer.getResultArray()[5]);
        assertNotNull(fileReorganizer.getResultArray()[6]);
        assertNotNull(fileReorganizer.getResultArray()[7]);
        assertNotNull(fileReorganizer.getResultArray()[8]);
        assertNotNull(fileReorganizer.getResultArray()[9]);
    }

    @Test
    void addToSortedSet_WhenAnIntegerIsAdded_IntegerShouldBeRetrieved() {
        fileReorganizer.addToStringBuilder("100");
        fileReorganizer.addToStringBuilder((" 0"));
        fileReorganizer.addToStringBuilder(("11"));
        fileReorganizer.addToStringBuilder(("80002"));
        assertTrue(fileReorganizer.getResultArray()[0].indexOf("100") >= 0);
        assertTrue(fileReorganizer.getResultArray()[0].indexOf("0") >= 0);
        assertTrue(fileReorganizer.getResultArray()[1].indexOf("11") >= 0);
        assertTrue(fileReorganizer.getResultArray()[2].indexOf("80002") >= 0);
    }
}