import java.util.ArrayList;
import java.util.HashMap;

public class MonthManager {
    public ArrayList<ReportForMonth> profitsAndLosesForMonth = new ArrayList<>();

    public void loadFile(int month, String path) {
        String reportMonth = FileReader.readFileContentsOrNull(path);
        String[] lines = reportMonth.split("\r?\n");
        for (int i = 1; i < lines.length; i++) {
            String line = lines[i];
            String[] parts = line.split(",");
            String itemName = parts[0];
            boolean isExpense = Boolean.parseBoolean(parts[1]);
            int quantity = Integer.parseInt(parts[2]);
            double sumOfOne = Double.parseDouble(parts[3]);

            ReportForMonth reportForMonth = new ReportForMonth(itemName, isExpense, quantity, sumOfOne, month);
            profitsAndLosesForMonth.add(reportForMonth);
        }
    }

    public String getTopProfitItemOfEachMonth() {
        HashMap<Integer, HashMap<String, Double>> bestItemsForMonth = new HashMap<>();
        String bestItem = null;
        double maxValueOfBestItem = 0.0;

        for (ReportForMonth reportForMonth : profitsAndLosesForMonth) {
            if (reportForMonth.isExpense) {
                continue;
            }
            if (!bestItemsForMonth.containsKey(reportForMonth.month)) {
                bestItemsForMonth.put(reportForMonth.month, new HashMap<>());
            }
            HashMap<String, Double> itemsByAmount = bestItemsForMonth.get(reportForMonth.month);
            itemsByAmount.put(reportForMonth.itemName,
                    itemsByAmount.getOrDefault(reportForMonth.itemName, 0.0)
                            + (reportForMonth.quantity * reportForMonth.sumOfOne));
        }

        for (Integer month : bestItemsForMonth.keySet()) {
            HashMap<String, Double> itemsByAmount = bestItemsForMonth.get(month);
            for (String Item : itemsByAmount.keySet()) {
                double itemNameAmount = itemsByAmount.get(Item);
                if (itemNameAmount > maxValueOfBestItem) {
                    bestItem = Item;
                    maxValueOfBestItem = itemNameAmount;
                }
            }
                    System.out.println("Самая прибыльная позиция в " + month + " месяце это - " + bestItem
                            + ". Доход по ней составил: " + maxValueOfBestItem);
            bestItem = null;
            maxValueOfBestItem = 0.0;
        }
        return null;
    }


    public String getTopLoseItemOfMonth() {
        HashMap<Integer, HashMap<String, Double>> worstItemsForMonth = new HashMap<>();
        String worstItem = null;
        double maxValueOfWorstItem = 0.0;

        for (ReportForMonth reportForMonth : profitsAndLosesForMonth) {
            if (!reportForMonth.isExpense) {
                continue;
            }
            if (!worstItemsForMonth.containsKey(reportForMonth.month)) {
                worstItemsForMonth.put(reportForMonth.month, new HashMap<>());
            }
            HashMap<String, Double> itemsByAmount = worstItemsForMonth.get(reportForMonth.month);
            itemsByAmount.put(reportForMonth.itemName,
                    itemsByAmount.getOrDefault(reportForMonth.itemName, 0.0)
                            + (reportForMonth.quantity * reportForMonth.sumOfOne));
        }

        for (Integer month : worstItemsForMonth.keySet()) {
            HashMap<String, Double> itemsByAmount = worstItemsForMonth.get(month);
            for (String Item : itemsByAmount.keySet()) {
                double itemNameAmount = itemsByAmount.get(Item);
                if (itemNameAmount > maxValueOfWorstItem) {
                    worstItem = Item;
                    maxValueOfWorstItem = itemNameAmount;
                }
            }
            System.out.println("Самая затратная позиция в " + month + " месяце это - " + worstItem
                    + ". Расходы по ней составили: " + maxValueOfWorstItem);
            worstItem = null;
            maxValueOfWorstItem = 0.0;
        }
        return null;
    }
}
