import java.util.HashMap;

public class Checker {
    public YearManager yearManager;
    public MonthManager monthManager;

    public Checker(YearManager yearManager, MonthManager monthManager) {
        this.yearManager = yearManager;
        this.monthManager = monthManager;
    }

    public boolean checkBetweenReports() {

        boolean check = true;
        HashMap<Integer, Double> amountOfProfitForYear = new HashMap<>();
        HashMap<Integer, Double> amountOfLosesForYear = new HashMap<>();
        for (ReportForYear reportForYear : yearManager.profitsAndLosesForYear) {
            if (reportForYear.isExpense) {
                amountOfLosesForYear.put(reportForYear.month, reportForYear.amount);
            } else {
                amountOfProfitForYear.put(reportForYear.month, reportForYear.amount);
            }
        }

        HashMap<Integer, Double> amountOfProfitForMonth = new HashMap<>();
        HashMap<Integer, Double> amountOfLosesForMonth = new HashMap<>();
        for (ReportForMonth reportForMonth : monthManager.profitsAndLosesForMonth) {
            if (reportForMonth.isExpense) {
                amountOfLosesForMonth.put(reportForMonth.month,
                        amountOfLosesForMonth.getOrDefault(reportForMonth.month, 0.0) +
                                (reportForMonth.sumOfOne * reportForMonth.quantity));
            } else {
                amountOfProfitForMonth.put(reportForMonth.month,
                        amountOfProfitForMonth.getOrDefault(reportForMonth.month, 0.0)
                                + (reportForMonth.sumOfOne * reportForMonth.quantity));
            }
        }

        for (Integer month : amountOfProfitForYear.keySet()) {
            double amountByYearReport = amountOfProfitForYear.get(month);
            double amountByMonthReport = amountOfProfitForMonth.get(month);
            if (amountByYearReport != amountByMonthReport) {
                System.out.println("Сумма в " + month + " месяце годового отчета, " +
                        "не соответствует сумме затрат в месячном отчете");
                check = false;
            }
        }
        if(check) {
            System.out.println("Данные в отчетах совпадают, можно расслабится :)");
        }
        return check;
    }
}
