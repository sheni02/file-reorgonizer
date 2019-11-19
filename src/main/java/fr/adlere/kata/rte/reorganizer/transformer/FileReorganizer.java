package fr.adlere.kata.rte.reorganizer.transformer;

import java.util.Arrays;
import java.util.OptionalInt;
import java.util.stream.IntStream;

/**
 * Class that orgnize the numbers read from the input file into an array containing in each cell
 * a space separated numbers having their units matches to the cell index.
 */
public class FileReorganizer {

    private StringBuilder[] resultArray;
    public static final int NB_ELEMENT = 10;

    /**
     * Create an instance of the calss and init the resultArray attribute with empty StringBuilder objects.
     * @return FileReorganizer initialized instance
     */
    public static FileReorganizer createFileReorganizer() {
        FileReorganizer fileReorganizer = new FileReorganizer();
        fileReorganizer.setResultArray(new StringBuilder[NB_ELEMENT]);
        fileReorganizer.initArray();
        return fileReorganizer;
    }


    /**
     * Find the right position where the number param should be added in the resultArray attribute
     * @param number to add (classify) in the resultArray
     * @return OptionalInt of the position where the number should be added
     */
    private OptionalInt findPosition(String number) {
        return IntStream.range(0, NB_ELEMENT).filter(i -> number.endsWith(String.valueOf(i))).findAny();
    }

    private void initArray() {
        this.resultArray = Arrays.stream(resultArray)
                .map(e -> new StringBuilder()).toArray(StringBuilder[]::new);
    }

    private void addToStringBuilder(String element, int positionInResultArray) {
        this.resultArray[positionInResultArray].append(element).append(" ");
    }

    /**
     * Search the right position and then add the element to the resultArray
     * @param element to add to the resultArray
     */
    public void addToStringBuilder(String element) {
        OptionalInt positionInResultArray = findPosition(element);
        int position = positionInResultArray.orElseThrow(() -> new IllegalArgumentException("Couldn't get the element position"));
        addToStringBuilder(element, position);
    }

    public StringBuilder[] getResultArray() {
        return resultArray;
    }

    public void setResultArray(StringBuilder[] resultArray) {
        this.resultArray = resultArray;
    }
}
