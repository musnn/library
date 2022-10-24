package db;

import java.util.Scanner;

public class Main {


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        BookRepository repository = new BookRepository();
        ConsoleService service = new ConsoleService();
        System.out.println("Choose one: (1) Insert, (2) Print out, (3) Update, (4) Delete");
        int choice = sc.nextInt();
        sc.nextLine();
        switch (choice) {
            case 1 -> service.choiceInsert();
            case 2 -> repository.printInfo();
            case 3 -> service.choiceUpdate();
            case 4 -> service.choiceDelete();
        }
    }
}
