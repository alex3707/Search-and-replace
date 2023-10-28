import java.io.File;
import java.util.ArrayList;

public class ProcessFiles {

    ArrayList<String> path = new ArrayList<>(100);

    /* результат работы этого метода - массив с абсолютными путями к файлам, которые нужно передать в метод
    (вложенный метод) по очереди в цикле */

    public void processFilesFromFolder(File folder){

        File[] folderEntries = folder.listFiles();
        assert folderEntries != null;
        for (File entry : folderEntries)
        {
            if (entry.isDirectory())
            {
                processFilesFromFolder(entry);
                continue;
            }
            path.add(entry.getAbsolutePath()); // добавляем адресс файла в arreyList
            // иначе вам попался файл, обрабатывайте его!
        }
        for (var pt : path) {       // выводим в консоль имена всех файлов
            System.out.println(pt);
        }
    }
}
