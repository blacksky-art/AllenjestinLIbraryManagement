package com.example.library.dao;

import com.example.library.entity.Book;
import com.example.library.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class BookDAO {

    public void saveBook(Book book) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(book);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        }
    }
}
