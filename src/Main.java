import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class Main {
    public static void main(String[] args) {
        String json = "[{\"фамилия\":\"Иванов\",\"оценка\":\"5\",\"предмет\":\"Математика\"}," +
                "{\"фамилия\":\"Петрова\",\"оценка\":\"4\",\"предмет\":\"Информатика\"}," +
                "{\"фамилия\":\"Краснов\",\"оценка\":\"5\",\"предмет\":\"Физика\"}]";

        json = json.substring(3, json.length() - 3);

        String[] students = json.split("},");


        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < students.length; i++) {
            String[] students2 = students[i].split(",");
            String[] students3 = students[i].split(":");
            String[] students4 = students[i].split("\",\"");
            for (int j = 0; j < students4.length; j++) {
                String[] students5 = {students4[j].replace("\":\"", " ")};
                for (int k = 0; k < students5.length; k++) {
                    String[] students6 = {students5[k].replace("{\"", "")};
                    for (int l = 0; l < students6.length; l++) {
                        String[] students7 = {students6[l].replace("\"", "")};
                        for (int m = 0; m < students7.length; m++) {
                            String[] students8 = {students7[m].replace("фамилия", "\nСтудент")};
                            String[] students9 = {students8[m].replace("оценка", " получил")};
                            String[] students10 = {students9[m].replace("предмет", " по предмету")};
                            for (int n = 0; n < students10.length; n += 3) {
                                sb.append(students10[n]);
                            }
                        }
                    }
                }
            }
        }
        String s = sb.toString();
        System.out.println(s);

        Logger lg = Logger.getAnonymousLogger();
        lg.log(Level.INFO, "Всё хорошо");
        SimpleFormatter formatter = new SimpleFormatter();
        FileHandler fh = null;
        try {
            fh = new FileHandler("log.txt");
            fh.setFormatter(formatter);
        } catch (IOException e) {
            e.printStackTrace();
        }
        lg.addHandler(fh);

        try (FileWriter fw = new FileWriter("file.txt", false);) {
            fw.write(s);
        } catch (IOException e) {
            lg.log(Level.WARNING, e.getMessage());
            e.printStackTrace();
        } catch (Exception ex) {
            lg.log(Level.WARNING, ex.getMessage());
            ex.printStackTrace();
        }
    }
}


