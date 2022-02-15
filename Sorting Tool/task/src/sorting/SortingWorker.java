package sorting;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

class ValueThenKeyComparator<T extends Comparable<T>,
        V extends Comparable<V>>
        implements Comparator<Map.Entry<T, V>> {

    public int compare(Map.Entry<T, V> a, Map.Entry<T, V> b) {
        int cmp1 = a.getValue().compareTo(b.getValue());
        if (cmp1 != 0) {
            return cmp1;
        } else {
            return a.getKey().compareTo(b.getKey());
        }
    }
}

public abstract class SortingWorker<T extends Comparable<T>> {

    protected List<T> values = new ArrayList<>();
    private Map<T, Long> sortedByCountValues;
    private boolean naturalOrder = true;

    abstract void readInput(File inputFile) throws FileNotFoundException;

    public void sort(boolean order) {
        this.naturalOrder = order;
        if (naturalOrder) {
            sortNatural();
        } else {
            sortByCount();
        }
    }

    public void printSortedResults(File outputFile) {
        String result = getSortedResults();
        if (outputFile == null) {
            System.out.println(result);
        } else {
            try {
                FileWriter fileWriter = new FileWriter(outputFile);
                fileWriter.write(result);
                fileWriter.close();
            } catch (IOException e) {
                System.out.println("Can't create output file!");
            }
        }
    }

    protected long getCount() {
        return values.size();
    }

    protected long getPercentage(long value) {
        double percent = ((double) value / getCount()) * 100;
        return Math.round(percent);
    }

    private void sortNatural() {
        values.sort(Comparator.naturalOrder());
    }

    private void sortByCount() {
        Map<T, Long> counts = values.stream()
                .collect(Collectors.groupingBy(v -> v, Collectors.counting()));
        List<Map.Entry<T, Long>> list = new ArrayList<>(counts.entrySet());
        Collections.sort(list, new ValueThenKeyComparator<>());
        sortedByCountValues = list.stream()
                .collect(Collectors.toMap(Map.Entry::getKey,
                        Map.Entry::getValue,
                        (oldValue, newValue) -> oldValue, LinkedHashMap::new));
    }

    private String getSortedValues() {
        return "Sorted data: " + values.toString()
                    .replaceAll("[\\[\\],]", "");
    }

    private String getSortedByCountValues() {
        StringBuilder retString = new StringBuilder();
        for( Map.Entry<T, Long> entry : sortedByCountValues.entrySet()) {
            String str = entry.getKey() + ": "
                    + entry.getValue() + " time(s), "
                    + getPercentage(entry.getValue()) + "%\n";
            retString.append(str);
        }
        return retString.toString();
    }

    protected String getSortedResults() {
        return getCount() + "\n"
                + (naturalOrder ?
                getSortedValues() :
                getSortedByCountValues());
    }
}


