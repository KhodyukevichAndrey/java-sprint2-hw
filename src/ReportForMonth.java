public class ReportForMonth {
    public String itemName;
    public boolean isExpense;
    public int quantity;
    public double sumOfOne;
    public int month;

    public ReportForMonth(String itemName, boolean isExpense, int quantity, double sumOfOne, int month) {
        this.itemName = itemName;
        this.isExpense = isExpense;
        this.quantity = quantity;
        this.sumOfOne = sumOfOne;
        this.month = month;
    }
}
