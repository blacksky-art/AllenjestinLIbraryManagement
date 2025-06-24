package com.example.library.dao;

import com.example.library.entity.Member;
import com.example.library.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class MemberDAO {

    public void saveMember(Member member) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(member);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        }
    }
}
