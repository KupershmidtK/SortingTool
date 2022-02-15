package sorting;

public class SortingWorkerFactory {
    public SortingWorker createWorker(String type) {
        SortingWorker worker = new WordSortingWorker();
        switch (type) {
            case "long":
                worker = new LongSortingWorker();
                break;
            case "line":
                worker = new LineSortingWorker();
                break;
            case "word":
            default:
                break;
        }
        return worker;
    }
}
