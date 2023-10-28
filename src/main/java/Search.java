import java.util.ArrayList;
import java.util.Scanner;

public class Search {
    private String q; // Переменная которая хранит строки поиска и замены

    /* Количество строк поиска и строк для рамены должны быть равны
       иначе программа завершиться аварийно с выводом кода ошибки 200
    */

    ArrayList<String> searchString = new ArrayList<>(10);

    ArrayList<String> searchStringCheng = new ArrayList<>(10);

    Scanner scanner = new Scanner(System.in);

    public ArrayList<String> putSearchString() {
        System.out.println("Введите строку поиска");
        System.out.println("по завершении всех строк поиска введите \"q\"");

        while (true) {
            q = scanner.nextLine();
            if (q.equals("q"))
                break;
            searchString.add(q);
            System.out.println("Введите следующую строку поиска");
        }
        return searchString;
    }

    public ArrayList<String> putSearchStringCheng() {
        System.out.println("Введите строку замены. Внимание !!! Количество строк поиска и замены должны быть одинаковыми !!!");
        System.out.println("по завершении всех строк поиска введите \"q\"");

        while (true) {
            q = scanner.nextLine();
            if (q.equals("q"))
                break;
            searchStringCheng.add(q);
            System.out.println("Введите следующую строку замены");
        }

        if (searchString.size() != searchStringCheng.size()) {
            System.out.println("Размеры массивов строк поиска и замены не совпадают");
            System.out.println("Программа завершилась аварийно");
            try {
                Thread.sleep(1000);
                System.exit(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return searchStringCheng;
    }
}



