package db;

import java.util.Scanner;

public class ConsoleService {

    static BookRepository repository = new BookRepository();
    public static void choiceInsert() {
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter book name: ");
        String newBookName = sc.nextLine();
        if(repository.whetherBookExists(newBookName)) {
            System.out.println("The entered book exists in the database");
        }
        else {
            System.out.println("Enter author name: ");
            String newAuthorName = sc.nextLine();
            System.out.println("Enter genre: ");
            String genre = sc.nextLine();
            System.out.println("Enter language: ");
            String language = sc.nextLine();

            repository.insertBook(newBookName, genre, language);
            repository.insertAuthor(newAuthorName);
        }
    }
    public static void choiceDelete() {
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter the ID of the book you want to delete: ");
        int id = sc.nextInt();
        sc.nextLine();

        repository.deleteRecord(id);
    }
    public static void choiceUpdate() {
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter the ID of the book you want to update: ");
        int id = sc.nextInt();
        sc.nextLine();
        System.out.println("Enter the new Title of the book: ");
        String title = sc.nextLine();
        repository.updateRecord(id, title);
    }
}
