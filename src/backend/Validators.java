package backend;

import backend.constants.Positions;

import java.io.IOException;
import java.time.LocalDateTime;

public class Validators {
     public static String lengthValidator(String toCheck, int requiredLength) throws Exception {
         toCheck = toCheck.strip();
         if (toCheck.length()>= requiredLength){
            return toCheck;
        }
        throw new IOException("The provided string is less than " + requiredLength);
    }
    public static String emailValidator(String email) throws Exception {
         email = email.strip();
         String[] beforeAndAfterAt = email.split("@");
         if (beforeAndAfterAt.length == 2){
             String[] beforeAndAfterDot = beforeAndAfterAt[1].split("\\.");
             if (beforeAndAfterDot.length >= 2){
                 return email;
             }
         }
         throw new IOException("Not a valid email");
    }
    public static String passwordValidator(String password) throws Exception{
         boolean upper = false, lower = false, numeric = false;
         int length = 0;
         for(char ch: password.toCharArray()){
             if (Character.isDigit(ch)) numeric = true;
             else if (Character.isUpperCase(ch)) upper = true;
             else if (Character.isLowerCase(ch)) lower = true;
             length ++;
         }
         if (length < 8) throw new IOException("Password must be a minimum of 8 characters");
         if (!numeric) throw new IOException("Password must contain at least one number");
         if (!upper) throw new IOException("Password must contain at least one upper case character");
         if (!lower) throw new IOException("Password must contain at least one lower case character");
         return password;
    }
    public static String attachmentValidator(String attachment) throws Exception{
         attachment = attachment.strip();
         String[] beforeAndAfterDot = attachment.split("\\.");
         if (beforeAndAfterDot.length >= 2){
             return attachment;
         }
         throw new IOException("Not a valid attachment");
    }
    public static LocalDateTime deadlineValidator(LocalDateTime deadline) throws Exception{
         if (deadline.isAfter(LocalDateTime.now())){
             return deadline;
         }
         throw new IOException("Are you living in the past?");
    }
    public static Positions positionValidator(Positions userPosition, Positions[] requiredPosition) throws Exception {
         for (Positions position: requiredPosition){
             if (userPosition == position){
                 return userPosition;
             }
         }
         throw new IOException("You do not have permission to do this");
    }
    public static double rangeValidator(double toValidate, double gt, double lt) throws Exception {
         if (toValidate>=gt && toValidate<=lt){
             return toValidate;
         }
        throw new IOException("This number should be between "+gt+" and "+lt);
    }
}
