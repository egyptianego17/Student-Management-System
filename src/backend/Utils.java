package backend;

import org.springframework.security.crypto.bcrypt.BCrypt;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Utils {
    public static void print(Object toPrint){
        System.out.println(toPrint);
    }
    public static void PrintArray(Object[] array){
        for (Object item: array){
            System.out.println(item);
        }
    }
    public static String hash(String toHash){
        return BCrypt.hashpw(toHash, BCrypt.gensalt());
    }
    public static Boolean checkHash(String toCompare, String storedHash){
        return BCrypt.checkpw(toCompare, storedHash);
    }
    public static String dateToString(LocalDateTime date){
        return date.format(Config.dateFormat);
    }
    public static LocalDateTime stringToDate(String string){
        return LocalDateTime.parse(string, Config.dateFormat);
    }
}
