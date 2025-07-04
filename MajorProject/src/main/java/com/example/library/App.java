
package com.example.library;

import com.example.library.dao.BookDAO;
import com.example.library.dao.MemberDAO;
import com.example.library.entity.Book;
import com.example.library.entity.Member;

import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        MemberDAO memberDAO = new MemberDAO();

        System.out.println("Welcome to the Library System");
        System.out.println("1. Register");
        System.out.println("2. Login");
        System.out.print("Choose an option (1 or 2): ");
        int choice = scanner.nextInt();
        scanner.nextLine(); // consume newline

        Member currentUser = null;

        if (choice == 1) {
            System.out.println("=== Registration ===");
            System.out.print("Enter your name: ");
            String name = scanner.nextLine();
            System.out.print("Enter your email: ");
            String email = scanner.nextLine();

            if (memberDAO.getMemberByEmail(email) != null) {
                System.out.println("User already exists. Try logging in.");
            } else {
                currentUser = new Member(name, email);
                memberDAO.saveMember(currentUser);
                System.out.println("Registration successful.");
            }
        } else if (choice == 2) {
            System.out.println("=== Login ===");
            System.out.print("Enter your email: ");
            String email = scanner.nextLine();

            currentUser = memberDAO.getMemberByEmail(email);

            if (currentUser != null) {
                System.out.println("Welcome back, " + currentUser.getName() + "!");
            } else {
                System.out.println("User not found. Please register first.");
            }
        } else {
            System.out.println("Invalid choice.");
        }

        if (currentUser != null) {
            BookDAO bookDAO = new BookDAO();

            while (true) {
                System.out.println("\n--- Book Menu ---");
                System.out.println("1. Add a Book");
                System.out.println("2. View All Books");
                System.out.println("3. Delete Book by ID");
                System.out.println("4. View Profile");
                System.out.println("5. Logout");
                System.out.print("Choose an action: ");
                int action = scanner.nextInt();
                scanner.nextLine();

                switch (action) {
                    case 1:
                        System.out.print("Enter book title: ");
                        String title = scanner.nextLine();
                        System.out.print("Enter book author: ");
                        String author = scanner.nextLine();
                        System.out.print("Enter ISBN: ");
                        String isbn = scanner.nextLine();

                        Book book = new Book(title, author, isbn);
                        bookDAO.saveBook(book);
                        System.out.println("Book saved successfully.");
                        break;

                    case 2:
                        System.out.println("\n--- All Books ---");
                        for (Book b : bookDAO.getAllBooks()) {
                            System.out.println("ID: " + b.getId() + ", Title: " + b.getTitle() + ", Author: " + b.getAuthor());
                        }
                        break;

                    case 3:
                        System.out.print("Enter Book ID to delete: ");
                        int id = scanner.nextInt();
                        scanner.nextLine();
                        bookDAO.deleteBookById(id);
                        System.out.println("Book deleted (if exists).");
                        break;

                    case 4:
                        System.out.println("Your Profile:");
                        System.out.println("Name: " + currentUser.getName());
                        System.out.println("Email: " + currentUser.getEmail());
                        break;

                    case 5:
                        System.out.println("Logged out. Goodbye!");
                        scanner.close();
                        return;

                    default:
                        System.out.println("Invalid action.");
                }
            }
        }

        scanner.close(); // moved outside while + if blocks
    }
}
