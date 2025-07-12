import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static ArrayList<Book> Books = new ArrayList<Book>();
    public static void main(String[] args)
    {
        Scanner in = new Scanner(System.in);
        String choice;
        do{
            System.out.println("1-Press 1 for Add Book");
            System.out.println("2-Press 2 for delete Book");
            System.out.println("3-Press 3 for buying Book");
            System.out.println("4-Press any key for exit");
             choice = in.nextLine();
            switch (choice){
                case "1": Book.addBook();break;
                case "2": Book.DeleteBook();break;
                case "3": Book.BuyBook(); break;
                default: {
                    System.out.println("Thank you for using our system");

                    return;
                }
            }
        }
        while (choice.equals("1") || choice.equals("2") || choice.equals("3"));
    }
}