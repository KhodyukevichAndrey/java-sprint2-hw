public class ReportForYear {
    public int month;
    public double amount;
    public boolean isExpense;

    public ReportForYear(String month, double amount, boolean isExpense) {
        this.month = Integer.parseInt(month);
        this.amount = amount;
        this.isExpense = isExpense;
    }
}
