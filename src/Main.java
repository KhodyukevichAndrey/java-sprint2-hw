import java.util.Scanner;
public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        YearManager yearManager = new YearManager("resources/y.2021.csv");
        MonthManager monthManager = new MonthManager();

        Checker checker = new Checker(yearManager, monthManager);


        while (true) {
            printMenu();
            String command = scanner.nextLine();
            switch (command) {
                case "1":
                    for (int i = 1; i <= 3; i++) {
                        monthManager.loadFile(i, "resources/m.20210" + i + ".csv");
                    }
                    System.out.println("Отчеты успешно считаны!");
                    break;
                case "2":
                    yearManager.readFileContentsOrNull("resources/y.2021.csv");
                    System.out.println("Отчет успешно считан!");
                    break;
                case "3":
                    if (!monthManager.profitsAndLosesForMonth.isEmpty()) {
                        System.out.println("Ваш отчет: " + checker.checkBetweenReports());
                    } else {
                        System.out.println("Операция не может быть выполнена. " +
                                "Сначала необходимо сканировать файлы");
                    }
                    break;
                case "4":
                    if (!monthManager.profitsAndLosesForMonth.isEmpty()) {
                        monthManager.getTopProfitItemOfEachMonth();
                        monthManager.getTopLoseItemOfMonth();
                    } else {
                        System.out.println("Операция не может быть выполнена. " +
                                "Сначала необходимо сканировать файлы");
                    }
                    break;
                case "5":
                    System.out.println(yearManager.getProfitForMonths());
                    System.out.println("Средний ежемесячный доход в году составляет: "
                            + yearManager.avarageProfitSum());
                    System.out.println("Средний ежемесячный расход в году составляет: "
                            + yearManager.avarageLosesSum());
                    break;
                case "Хочу выйти":
                    return;
                default:
                    System.out.println("Извините, такой команды пока нет.");
                    break;
            }
        }
    }

    private static void printMenu() {
        System.out.println("Добрый день! Что бы вы хотели узнать?");
        System.out.println("1 - Считать все месячные отчеты");
        System.out.println("2 - Считать отчет за год");
        System.out.println("3 - Произвести сверку отчетов");
        System.out.println("4 - Вывести информацию о всех месячных отчетах");
        System.out.println("5 - Вывести информацию о годовом отчете");
        System.out.println("Чтобы выйти из программы напишите \"Хочу выйти\"");
    }
}
