import java.util.Scanner;

public class Book {
    public    String ISBN ;
    public    String Title ;
    public   int PublishedYear ;



    public static void addBook()
    {
        Scanner in = new Scanner(System.in);
        System.out.println("Enter ISBN");
        String Ispn = in.nextLine();
        System.out.println("Enter Title");
        String title = in.nextLine();
        System.out.println("Enter Publish Year");
        int year = Validation.chooseInLimitedSize(2025);

        Book type = chooseType();
        type.ISBN = Ispn;
        type.PublishedYear  = year ;
        type.Title = title;
        AddBookbytype(type);
    }
    public static Book chooseType()
    {
        Scanner in = new Scanner(System.in);

        System.out.println("1-Press 1 for Paper Book");
        System.out.println("2-Press 2 for E-Book");
        System.out.println("3-Press 3 for Demo");


        int num = Validation.chooseInLimitedSize(3);

            if (num == 1) return new PaperBook();
            else if (num ==2) return new EBook();
            else return new Demo();
    }
    public static void AddBookbytype(Book book)
    {
        if(book instanceof PaperBook) AddPaperBook(book);
        if(book instanceof EBook) AddEBook(book);
        if(book instanceof Demo) AddDemo(book);
    }
    public static void AddPaperBook(Book book)
    {
        Scanner in = new Scanner(System.in);
        System.out.println("Add Quantity");

        int quantity = Validation.chooseInLimitedSize(Integer.MAX_VALUE -1);
        System.out.println("Enter Price");
        double price= in.nextDouble();

        PaperBook PB = new PaperBook();
        PB.Title = book.Title ;
        PB.PublishedYear = book.PublishedYear;
        PB.ISBN = book.ISBN;
        PB.setPrice(price);
        PB.setQuantity(quantity);
        Main.Books.add(PB);
        System.out.println("Book is Added");
    }
    public  static  void AddEBook(Book book){
        Scanner in = new Scanner(System.in);
        System.out.println("Enter Type Of file");
        String type = in.nextLine();
        System.out.println("Enter Price");
        double price= in.nextDouble();

        EBook EB = new EBook();
        EB.ISBN = book.ISBN;
        EB.PublishedYear = book.PublishedYear;
        EB.Title = book.Title;
        EB.setPrice(price);
        EB.FileType = type;
        Main.Books.add(EB);
        System.out.println("Book is Added");
    }
    public  static  void AddDemo(Book book){
        Scanner in = new Scanner(System.in);
        System.out.println("Enter Type Of file");
        String type = in.nextLine();
        Demo demo = new Demo();
        demo.ISBN = book.ISBN;
        demo.PublishedYear = book.PublishedYear;
        demo.Title = book.Title;
        demo.FileType = type;
        Main.Books.add(demo);
        System.out.println("Book is Added");
    }
    public static int ChooseBookByISBN()
    {
        if (Main.Books.size() == 0) {
            System.out.println("NO Books is Found");
            return 0;
        }
        Scanner in = new Scanner(System.in);
        System.out.println("Enter ISBN you want to delete");
        for(Book book : Main.Books){
            if( book instanceof Demo){
                continue;
            }
            System.out.println("ISBN: " + book.ISBN + " - Book Name: " + book.Title);
        }
        String Isbn = in.nextLine();
        boolean exist =false;
        int idx = 0;
        while (true)
        {
            idx =0;
            for(Book book : Main.Books){
                if(book.ISBN.equals(Isbn))
                {
                    exist =true;

                    break;
                }
                idx++;
            }
            if(exist) break;
            System.out.println("This ISBN Not found please Enter Another");
            Isbn = in.nextLine();

        }
        return idx;
    }

    public static void DeleteBook(){
        System.out.println("Enter num of years that make book outdate");
       int years = Validation.chooseInLimitedSize(2025);
       for(Book book:Main.Books)
       {
           if(book.PublishedYear <= 2025 - years)
           {
               Main.Books.remove(book);
           }
       }
        System.out.println("Books is Removed");

    }
    public static IServices  BuyBook()
    {
        Scanner in = new Scanner(System.in);
        int idx = ChooseBookByISBN();
        Book book = Main.Books.get(idx);

        if(book instanceof EBook)
        {
            System.out.println("Enter your  Email");
            String email = in.nextLine();
             Validation.emailValidation(email);
            MailService ms = new MailService();
            ms.book = book;
            ms.Email = email;
            System.out.println("\n---------------\n\n");
            System.out.println("The paid amount is " + ((EBook)book).getPrice() );
            System.out.println("\n---------------\n\n");
            ms.MakeServices();
            return ms;
        }
        else {
            System.out.println("Enter address");
            String add = in.nextLine();
            System.out.println("Enter Quantity");
            System.out.println("Available is : " + ((PaperBook)book).getQuantity() );
            int q = Validation.chooseInLimitedSize(((PaperBook)book).getQuantity());
            ShippingService shippingService = new ShippingService();
            shippingService.address =add ;
            ((PaperBook) book).setQuantity(((PaperBook) book).getQuantity()  - q);
            System.out.println("\n---------------\n\n");
            System.out.println("The paid amount is " + (((PaperBook)book).getPrice() * q + (5 * q))   );
            System.out.println("subtotal : " + ((PaperBook)book).getPrice() * q);
            System.out.println("Shipping is 5 pounds for each book : " + 5*q);
            System.out.println("\n---------------\n\n");
            shippingService.MakeServices();
            return  shippingService;
        }
    }
}
