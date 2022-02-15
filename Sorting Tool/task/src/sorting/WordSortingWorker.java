package sorting;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class WordSortingWorker extends SortingWorker<String> {

    @Override
    public void readInput(File inputFile) throws FileNotFoundException {
        Scanner scanner;
        if (inputFile == null) {
            scanner = new Scanner(System.in);
        } else {
            scanner = new Scanner(inputFile);
        }

        while (scanner.hasNext()) {
            values.add(scanner.next());
        }
    }

    @Override
    public String getSortedResults() {
        return "Total words: " + super.getSortedResults();
    }
}
