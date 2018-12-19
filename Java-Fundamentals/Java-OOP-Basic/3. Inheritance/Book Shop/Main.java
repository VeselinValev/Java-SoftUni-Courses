package exerciseOOP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.instrument.IllegalClassFormatException;
import java.util.*;
import java.util.stream.Collectors;
import java.lang.reflect.Method;

public class Main {
    public static void main(String[] args) throws IllegalClassFormatException {
        try {
            Scanner scanner = new Scanner(System.in);
            String author = scanner.nextLine();
            String title = scanner.nextLine();
            double price = Double.valueOf(scanner.nextLine());

            Book book = new Book(author, title, price);

            GoldenEditionBook goldenEditionBook = new GoldenEditionBook(author, title, price);

            Method[] bookDeclaredMethods = Book.class.getDeclaredMethods();
            Method[] goldenBookDeclaredMethods = GoldenEditionBook.class.getDeclaredMethods();

            if (goldenBookDeclaredMethods.length > 1) {
                throw new IllegalClassFormatException(
                        "Code duplication in GoldenEditionBook!");
            }

            System.out.println(book.toString());
            System.out.println(goldenEditionBook.toString());

        } catch (IllegalArgumentException error) {
            System.out.println(error.getMessage());
        }
    }

}
