package sorting;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class LongSortingWorker extends SortingWorker<Long> {

    @Override
    public void readInput(File inputFile) throws FileNotFoundException {
        Scanner scanner;
        if (inputFile == null) {
            scanner = new Scanner(System.in);
        } else {
            scanner = new Scanner(inputFile);
        }

        while (scanner.hasNext()) {
            String val = scanner.next();
            try {
                long longVal = Long.parseLong(val);
                values.add(longVal);
            } catch (NumberFormatException e) {
                System.out.println("\"" + val + "\" is not a long. It will be skipped.");
                continue;
            }
        }
        scanner.close();
    }

    @Override
    public String getSortedResults() {
        return "Total numbers: " + super.getSortedResults();
    }

}
