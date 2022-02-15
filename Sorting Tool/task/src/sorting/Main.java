package sorting;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(final String[] args) {
        App app = new App(args);
        app.run();
    }
}

class App {
    private boolean naturalSortingType = true;
    private SortingWorker worker = new WordSortingWorker();
    private final Map<String, String> params = new HashMap<>();
    private File inputFile = null;
    private File outputFile = null;

    public App(String[] args) {
        if (args != null)
            initialization(args);
    }

    public void run() {
        try {
            worker.readInput(inputFile);
            worker.sort(naturalSortingType);
            worker.printSortedResults(outputFile);
        } catch (FileNotFoundException e) {
            System.out.println("Input file not found!");
        }
    }

    private void initialization(String[] args) {
        if (args.length > 0) {
            parseArgs(args);

            for (Map.Entry<String, String> entry : params.entrySet()) {
                String key = entry.getKey();
                String value = entry.getValue();
                switch (key) {
                    case "-sortingType":
                        setSortingType(value);
                        break;
                    case "-dataType":
                        setDataType(value);
                        break;
                    case "-inputFile":
                        setInputFile(value);
                        break;
                    case "-outputFile":
                        setOutputFile(value);
                        break;
                    default:
                        System.out.println("\"" + key
                                + "\" is not a valid parameter. It will be skipped.");
                        break;
                }
            }
        }
    }

    private void parseArgs(String[] args) {
        String arg;
        String val;

        for (int i = 0; i < args.length; i++) {
            if (args[i].matches("-.+")) {
                arg = args[i];
                if (i == args.length - 1) {
                    val = "";
                } else if (args[i + 1].matches("-.+")) {
                    val = "";
                } else {
                    val = args[i + 1];
                }
                params.put(arg, val);
            }
        }
    }

    private void setSortingType(String value) {
        if (value.isEmpty()) {
            System.out.println("No sorting type defined!");
        } else {
            naturalSortingType = "natural".equals(value);
        }
    }

    private void setDataType(String value) {
        if (value.isEmpty()) {
            System.out.println("No data type defined!");
        } else {
            worker = new SortingWorkerFactory().createWorker(value);
        }
    }

    private void setInputFile(String path) {
        if (path.isEmpty())
            return;
        inputFile = new File(path);
    }

    private void setOutputFile(String path) {
        if (path.isEmpty())
            return;
        outputFile = new File(path);
    }
}