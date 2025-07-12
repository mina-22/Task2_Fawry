import java.util.Scanner;

public class Validation {
    public static int ValidNumber(String choice){
        int choicenum ;
        Scanner in = new Scanner(System.in);
        while (true) {
            try {
                choicenum = Integer.parseInt(choice);
                break;
            } catch (Exception ex) {
                System.out.println("Enter Valid number");
                choice = in.nextLine();
            }
        }
        return choicenum;
    }
    public static int chooseInLimitedSize(int size) {
        Scanner in = new Scanner(System.in) ;

        String choice = in.nextLine();
        int choicenum = ValidNumber(choice);

        while (choicenum <1 || choicenum > size) {
            System.out.println("Your choice out of restricted bounds");
            choice = in.nextLine();
            choicenum = ValidNumber(choice);
        }
        return  choicenum;
    }
    public static String emailValidation( String email){
        Scanner  input=new Scanner(System.in);
        while(!(email.contains("@gmail.com")&&email.endsWith("com"))||(email.startsWith("@"))||email.contains(" ")){
            System.out.println("\u001B[31mInvalid Email try again\u001B[0m");
            email=input.nextLine();
        }
        return email;
    }
}
