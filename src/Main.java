import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;


public class Main {
    public static String PATH_FILE_TEMP;
    public static void main(String[] args) {
        LocalDateTime currentDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");
        String formattedDateTime = currentDateTime.format(formatter);

        createFile(formattedDateTime, new StringBuilder());

        GameProgress gameProgress1 = new GameProgress(100, 100, 10, 10_000);
        //GameProgress gameProgress2 = new GameProgress(20, 50, 1, 500);
        //GameProgress gameProgress3 = new GameProgress(1, 10, 3, 1000);

        saveGame("./Games/savegames/save1.dat", gameProgress1, new StringBuilder());
        zipFiles("./Games/savegames/zip1.zip", new File("./Games/savegames"), new StringBuilder());
        deleteFile(new File("./Games/savegames"), new StringBuilder());
        openZip("./Games/savegames/zip1.zip", "./Games/savegames/", new StringBuilder());
        openProgress("./Games/savegames/save1.dat");

    }

    public static void saveLog(StringBuilder stringBuilder){
        try (FileWriter writer = new FileWriter(PATH_FILE_TEMP, true)) {
            writer.write(stringBuilder.toString());
            writer.flush();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public static void createFile(String formattedDateTime, StringBuilder stringBuilder) {
        File src = new File("./Games/src");
        if (src.mkdir()) {
            stringBuilder.append("Директория " + src.getName() + " успешно создана в " + " (" + formattedDateTime + ")\n");
        } else {
            stringBuilder.append("Директория " + src.getName() + " не создана или уже создана\n");
        }

        File res = new File("./Games/res");
        if (res.mkdir()) {
            stringBuilder.append("Директория " + res.getName() + " успешно создана в " + " (" + formattedDateTime + ")\n");
        } else {
            stringBuilder.append("Директория " + res.getName() + " не создана или уже создана\n");
        }

        File savegames = new File("./Games/savegames");
        if (savegames.mkdir()) {
            stringBuilder.append("Директория " + savegames.getName() + " успешно создана в " + " (" + formattedDateTime + ")\n");
        } else {
            stringBuilder.append("Директория " + savegames.getName() + " не создана или уже создана\n");
        }

        File temp = new File("./Games/temp");
        if (temp.mkdir()) {
            stringBuilder.append("Директория " + temp.getName() + " успешно создана в " + " (" + formattedDateTime + "\n");
        } else {
            stringBuilder.append("Директория " + temp.getName() + " не создана или уже создана\n");
        }

        File mainSrc = new File(src, "main");
        if (mainSrc.mkdir()) {
            stringBuilder.append("Директория " + mainSrc.getName() + " успешно создана в " + " (" + formattedDateTime + ")\n");
        } else {
            stringBuilder.append("Директория " + mainSrc.getName() + " не создана или уже создана\n");
        }

        File testSrc = new File(src, "test");
        if (testSrc.mkdir()) {
            stringBuilder.append("Директория " + testSrc.getName() + " успешно создана в " + " (" + formattedDateTime + ")\n");
        } else {
            stringBuilder.append("Директория " + testSrc.getName() + " не создана или уже создана\n");
        }

        File newFileMain = new File(mainSrc, "Main.java");
        try {
            if (newFileMain.createNewFile()) {
                stringBuilder.append("Файл " + newFileMain.getName() + " успешно создана в " + " (" + formattedDateTime + ")\n");
            }
        } catch (IOException e) {
            stringBuilder.append("Файл " + newFileMain.getName() + " не создана или уже создана\n");
        }

        File newFileUtils = new File(mainSrc, "Utils.java");
        try {
            if (newFileUtils.createNewFile()) {
                stringBuilder.append("Файл " + newFileUtils.getName() + " успешно создана в " + " (" + formattedDateTime + ")\n");
            }
        } catch (IOException e) {
            stringBuilder.append("Файл " + newFileUtils.getName() + " не создана или уже создана\n");
        }

        File drawablesRes = new File(res, "drawables");
        if (drawablesRes.mkdir()) {
            stringBuilder.append("Директория " + drawablesRes.getName() + " успешно создана в " + " (" + formattedDateTime + ")\n");
        } else {
            stringBuilder.append("Директория " + drawablesRes.getName() + " не создана или уже создана\n");
        }

        File vectorsRes = new File(res, "vectors");
        if (vectorsRes.mkdir()) {
            stringBuilder.append("Директория " + vectorsRes.getName() + " успешно создана в " + " (" + formattedDateTime + ")\n");
        } else {
            stringBuilder.append("Директория " + vectorsRes.getName() + " не создана или уже создана\n");
        }

        File iconsRes = new File(res, "icons");
        if (iconsRes.mkdir()) {
            stringBuilder.append("Директория " + iconsRes.getName() + " успешно создана в " + " (" + formattedDateTime + ")\n");
        } else {
            stringBuilder.append("Директория " + iconsRes.getName() + " не создана или уже создана\n");
        }

        File newFileTemp = new File(temp, "temp.txt");
        try {
            if (newFileTemp.createNewFile()) {
                stringBuilder.append("Файл " + newFileTemp.getName() + " успешно создана в " + " (" + formattedDateTime + ")\n");
                PATH_FILE_TEMP = newFileTemp.getPath();
            }
        } catch (IOException e) {
            stringBuilder.append("Файл " + newFileTemp.getName() + " не создана или уже создана\n");
        }

        saveLog(stringBuilder);
    }

    public static void saveGame(String path, GameProgress gameProgress, StringBuilder stringBuilder) {
        try (FileOutputStream fileOut = new FileOutputStream(path);
             ObjectOutputStream objectOut = new ObjectOutputStream(fileOut)) {
            objectOut.writeObject(gameProgress);
            stringBuilder.append("Игра успешно сохранена\n");
        } catch (IOException e) {
            e.printStackTrace();
            stringBuilder.append("Игра не сохранена\n");
        }

        saveLog(stringBuilder);
    }

    public static void zipFiles(String path, File listFiles, StringBuilder stringBuilder) {
        try (FileOutputStream fileOut = new FileOutputStream(path);
             ZipOutputStream zipOut = new ZipOutputStream(fileOut)) {

            for (File filePath : listFiles.listFiles()) {
                File fileToZip = new File(filePath.getPath());
                FileInputStream fileIn = new FileInputStream(fileToZip);
                ZipEntry zipEntry = new ZipEntry(fileToZip.getName());

                zipOut.putNextEntry(zipEntry);

                byte[] bytes = new byte[fileIn.available()];
                int length;
                while ((length = fileIn.read(bytes)) >= 0) {
                    zipOut.write(bytes, 0, length);
                }
                fileIn.close();
                zipOut.closeEntry();
            }
            stringBuilder.append("Архив успешно создан\n");
        } catch (IOException e) {
            e.printStackTrace();
            stringBuilder.append("Архив не создан\n");
        }

        saveLog(stringBuilder);
    }

    public static void deleteFile(File listFiles, StringBuilder stringBuilder) {
        for (File filePath : listFiles.listFiles()) {
            if (getFileExtension(filePath).equals("dat")) {
                File fileToDelete = new File(filePath.getPath());
                if (fileToDelete.delete()) {
                    stringBuilder.append("Файл успешно удален.\n");
                } else {
                    stringBuilder.append("Не удалось удалить файл.\n");
                }
            }
        }
        saveLog(stringBuilder);
    }

    private static String getFileExtension(File file) {
        String fileName = file.getName();
        if (fileName.lastIndexOf(".") != -1 && fileName.lastIndexOf(".") != 0)
            return fileName.substring(fileName.lastIndexOf(".") + 1);
        else return "";
    }

    public static void openZip(String pathZipFile, String pathOutFile, StringBuilder stringBuilder) {
        try (ZipInputStream zin = new ZipInputStream(new
                FileInputStream(pathZipFile))) {
            ZipEntry entry;
            String name;
            while ((entry = zin.getNextEntry()) != null) {
                name = entry.getName();
                FileOutputStream fout = new FileOutputStream(pathOutFile + name);
                for (int c = zin.read(); c != -1; c = zin.read()) {
                    fout.write(c);
                }
                fout.flush();
                zin.closeEntry();
                fout.close();
            }

            stringBuilder.append("Архив успешно разархивирован.\n");
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            stringBuilder.append("Архив не разархивирован.\n");
        }

        saveLog(stringBuilder);
    }

    public static GameProgress openProgress(String pathSaveGame){
        GameProgress gameProgress = null;

        try (FileInputStream fileInputStream = new FileInputStream(pathSaveGame);
             ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream)) {

            gameProgress = (GameProgress) objectInputStream.readObject();
            System.out.println(gameProgress.toString());
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return gameProgress;
    }
}














