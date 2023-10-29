import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.xwpf.usermodel.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class OpenDok {
    Scanner scanner = new Scanner(System.in);
    String[] path; // переменная в которой  будут храниться пути до файлов
    ProcessFiles processFiles = new ProcessFiles(); // экз класса метода с путями до файлов

    Search search = new Search();

    String[] mySearch = search.putSearchString().toArray(new String[0]);

    String[] put = search.putSearchStringCheng().toArray(new String[0]);

    public void setMySearch(String pathName, String pathSave) throws InvalidFormatException, IOException {
        for (int i = 0; i < mySearch.length; i++) {
            System.out.print("Вы меняете фразу: " + mySearch[i] + " на фразу " + put[i]);
            System.out.println();
        }
        System.out.println("Если все верно введите \"y\" для продолжения, если не верно - введите \"n\"");
        while (true) {

            String answer;
            answer = scanner.nextLine();

            if (answer.equals("y") || answer.equals("н"))  // на случай если пользовател не переключил алфавит
                break;
            else if (answer.equals("n") || answer.equals("т")) // на случай если пользовател не переключил алфавит
                System.exit(200);
            else
                System.out.println("Повторите ввод n или y");
        }

        processFiles.processFilesFromFolder(new File(pathName)); // обращаемся к каталогу где лежат файлы

        this.path = processFiles.path.toArray(new String[0]); // записываем в переменную все пути

        for (String pt : path) { // перебераем все пути

            File file = new File(pt); // сюда все файлы из папки по очереди
            XWPFDocument doc = new XWPFDocument(OPCPackage.open(file));

            for (int i = 0; i < mySearch.length; i++) {
                for (XWPFParagraph p : doc.getParagraphs()) {
                    List<XWPFRun> runs = p.getRuns();
                    if (runs != null) {
                        for (XWPFRun r : runs) {
                            String text = r.getText(0);
                            if (text != null && text.contains(mySearch[i])) { // какой текст ищем
                                text = text.replace(mySearch[i], put[i]); // ту же строку какую ищем , на что меняем
                                r.setText(text, 0);
                            }
                        }
                    }
                }
                for (XWPFTable tbl : doc.getTables()) {
                    for (XWPFTableRow row : tbl.getRows()) {
                        for (XWPFTableCell cell : row.getTableCells()) {
                            for (XWPFParagraph p : cell.getParagraphs()) {
                                for (XWPFRun r : p.getRuns()) {
                                    String text = r.getText(0);
                                    if (text != null && text.contains(mySearch[i])) { // сюда теже строки что выше
                                        text = text.replace(mySearch[i], put[i]);
                                        r.setText(text, 0);
                                    }
                                }
                            }
                        }
                    }
                }
            }

            try {
                doc.write(new FileOutputStream(pathSave + file.getName().substring(0, file.getName().length() - 5) + ".docx")); // путь куда будем сохранять
            } catch (FileNotFoundException exception) {
                System.out.println("Такой каталог отсутствует");
            }

        }

    }

}
