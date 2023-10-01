import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class Main {
    public static void main(String[] args) {
        StringBuilder stringBuilder = new StringBuilder();

        LocalDateTime currentDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");
        String formattedDateTime = currentDateTime.format(formatter);

        File src = new File("./Games/src");
        if (src.mkdir()){
            stringBuilder.append("Директория " + src.getName() + " успешно создана в " + " (" + formattedDateTime + ")\n");
        }else {
            stringBuilder.append("Директория " + src.getName() + " не создана или уже создана\n");
        }

        File res = new File("./Games/res");
        if (res.mkdir()){
            stringBuilder.append("Директория " + res.getName() + " успешно создана в " + " (" + formattedDateTime + ")\n");
        }else {
            stringBuilder.append("Директория " + res.getName() + " не создана или уже создана\n");
        }

        File savegames = new File("./Games/savegames");
        if (savegames.mkdir()){
            stringBuilder.append("Директория " + savegames.getName() + " успешно создана в " + " (" + formattedDateTime + ")\n");
        }else {
            stringBuilder.append("Директория " + savegames.getName() + " не создана или уже создана\n");
        }

        File temp = new File("./Games/temp");
        if (temp.mkdir()){
            stringBuilder.append("Директория " + temp.getName() + " успешно создана в " + " (" + formattedDateTime + "\n");
        }else {
            stringBuilder.append("Директория " + temp.getName() + " не создана или уже создана\n");
        }

        File mainSrc = new File(src, "main");
        if (mainSrc.mkdir()){
            stringBuilder.append("Директория " + mainSrc.getName() + " успешно создана в " + " (" + formattedDateTime + ")\n");
        }else {
            stringBuilder.append("Директория " + mainSrc.getName() + " не создана или уже создана\n");
        }

        File testSrc = new File(src, "test");
        if (testSrc.mkdir()){
            stringBuilder.append("Директория " + testSrc.getName() + " успешно создана в " + " (" + formattedDateTime + ")\n");
        }else {
            stringBuilder.append("Директория " + testSrc.getName() + " не создана или уже создана\n");
        }

        File newFileMain = new File(mainSrc, "Main.java");
        try {
            if (newFileMain.createNewFile()){
                stringBuilder.append("Файл " + newFileMain.getName() + " успешно создана в " + " (" + formattedDateTime + ")\n");
            }
        } catch (IOException e) {
            stringBuilder.append("Файл " + newFileMain.getName() + " не создана или уже создана\n");
        }

        File newFileUtils = new File(mainSrc, "Utils.java");
        try {
            if (newFileUtils.createNewFile()){
                stringBuilder.append("Файл " + newFileUtils.getName() + " успешно создана в " + " (" + formattedDateTime + ")\n");
            }
        } catch (IOException e) {
            stringBuilder.append("Файл " + newFileUtils.getName() + " не создана или уже создана\n");
        }

        File drawablesRes = new File(res, "drawables");
        if (drawablesRes.mkdir()){
            stringBuilder.append("Директория " + drawablesRes.getName() + " успешно создана в " + " (" + formattedDateTime + ")\n");
        }else {
            stringBuilder.append("Директория " + drawablesRes.getName() + " не создана или уже создана\n");
        }

        File vectorsRes = new File(res, "vectors");
        if (vectorsRes.mkdir()){
            stringBuilder.append("Директория " + vectorsRes.getName() + " успешно создана в " + " (" + formattedDateTime + ")\n");
        }else {
            stringBuilder.append("Директория " + vectorsRes.getName() + " не создана или уже создана\n");
        }

        File iconsRes = new File(res, "icons");
        if (iconsRes.mkdir()){
            stringBuilder.append("Директория " + iconsRes.getName() + " успешно создана в " + " (" + formattedDateTime + ")\n");
        }else {
            stringBuilder.append("Директория " + iconsRes.getName() + " не создана или уже создана\n");
        }

        File newFileTemp = new File(temp, "temp.txt");
        try {
            if (newFileTemp.createNewFile()){
                stringBuilder.append("Файл " + newFileTemp.getName() + " успешно создана в " + " (" + formattedDateTime + ")\n");
            }
        } catch (IOException e) {
            stringBuilder.append("Файл " + newFileTemp.getName() + " не создана или уже создана\n");
        }

        try (FileWriter writer = new FileWriter(newFileTemp, true)) {
            writer.write(stringBuilder.toString());
            writer.flush();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

    }
}














