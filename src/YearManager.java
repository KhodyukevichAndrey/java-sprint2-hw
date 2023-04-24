import java.util.ArrayList;
import java.util.HashMap;

public class YearManager {
    public ArrayList<ReportForYear> profitsAndLosesForYear = new ArrayList<>();

    public void loadFile(String path) {
        String reportYear = FileReader.readFileContentsOrNull(path);
        String[] lines = reportYear.split("\r?\n"); // "/r /n"
        for (int i = 1; i < lines.length; i++) {
            String line = lines[i];
            String[] parts = line.split(",");
            String month = parts[0];
            double amount = Double.parseDouble(parts[1]);
            boolean isExpense = Boolean.parseBoolean(parts[2]);

            ReportForYear reportForYear = new ReportForYear(month, amount, isExpense);
            profitsAndLosesForYear.add(reportForYear);
        }
    }

    public String getProfitForMonths() {
        HashMap<Integer, Double> profits = new HashMap<>();
        String report = "";
        for (ReportForYear profitForMonth : profitsAndLosesForYear) {
            if (!profitForMonth.isExpense) {
                profits.put(profitForMonth.month, profits.getOrDefault(profitForMonth.month, 0.0)
                        + profitForMonth.amount);
            } else {
                profits.put(profitForMonth.month, profits.getOrDefault(profitForMonth.month, 0.0)
                        - profitForMonth.amount);
            }
        }
        for (Integer month : profits.keySet()) {
            System.out.println("В " + month + " месяце ваша прибыль составила " + profits.get(month));
        }
        return report;
    }

    public Double avarageProfitSum() {
        double sum = 0;
        for (ReportForYear profits : profitsAndLosesForYear) {
            if (!profits.isExpense) {
                sum += profits.amount;
            }
        }
        return sum / 12;
    }

    public Double avarageLosesSum() {
        double sum = 0;
        for (ReportForYear loses : profitsAndLosesForYear) {
            if (loses.isExpense) {
                sum += loses.amount;
            }
        }
        return sum / 12;
    }
}