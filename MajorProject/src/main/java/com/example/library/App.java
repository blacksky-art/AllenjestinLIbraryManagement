package com.example.library;

import com.example.library.dao.BookDAO;
import com.example.library.dao.MemberDAO;
import com.example.library.entity.Book;
import com.example.library.entity.Member;

public class App {
    public static void main(String[] args) {

        Book book = new Book("Origin", "Dan Brown", "9780134685991");
        BookDAO bookDAO = new BookDAO();
        bookDAO.saveBook(book);

        Member member = new Member("John Doe", "john@example.com");
        MemberDAO memberDAO = new MemberDAO();
        memberDAO.saveMember(member);

        System.out.println("Data saved successfully!");
    }
}
