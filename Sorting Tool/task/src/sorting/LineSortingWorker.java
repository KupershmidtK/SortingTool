package sorting;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class LineSortingWorker extends SortingWorker<String> {

    @Override
    public void readInput(File inputFile) throws FileNotFoundException {
        Scanner scanner;
        if (inputFile == null) {
            scanner = new Scanner(System.in);
        } else {
            scanner = new Scanner(inputFile);
        }

        while (scanner.hasNextLine()) {
            values.add(scanner.nextLine());
        }
    }

    @Override
    public String getSortedResults() {
        return "Total lines: " + super.getSortedResults();
    }
}
