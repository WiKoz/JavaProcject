package com.example.project;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

public class LekarzeUtil {
    public void dodaj( Lekarze lekarze){
        Transaction transaction = null;
        try (Session session=HibernateUtil.openSession()){
            transaction=session.beginTransaction();
            session.persist(lekarze);
            transaction.commit();
        }
        catch(Exception exception ){
            if(transaction!=null&&transaction.isActive()){
                System.out.println(exception.getMessage());
                transaction.rollback();
            }
        }
    }
    public Lekarze szukaj(String imie){
        Transaction transaction = null;
        try (Session session=HibernateUtil.openSession()){
            transaction=session.beginTransaction();
            Query<Lekarze> query = session.createQuery("select w from Lekarze w where w.name=:imie", Lekarze.class);
            query.setParameter("imie",imie);
            transaction.commit();
            return query.getSingleResult();
        }
        catch(Exception exception ){
            if(transaction!=null&&transaction.isActive()){
                System.out.println(exception.getMessage());
                transaction.rollback();
            }
            return null;
        }
    }
    public Lekarze szukajPoPeselu(String pesel){
        Transaction transaction = null;
        try (Session session=HibernateUtil.openSession()){
            transaction=session.beginTransaction();
            Query<Lekarze> query = session.createQuery("select w from Lekarze w where w.pesel=:pesel", Lekarze.class);
            query.setParameter("pesel",pesel);
            transaction.commit();
            return query.getSingleResult();
        }
        catch(Exception exception ){
            if(transaction!=null&&transaction.isActive()){
                System.out.println(exception.getMessage());
                transaction.rollback();
            }
            return null;
        }
    }
    public Lekarze szukaj1(String login){
        Transaction transaction = null;
        try (Session session=HibernateUtil.openSession()){
            transaction=session.beginTransaction();
            Query<Lekarze> query = session.createQuery("select w from Lekarze w where w.login=:login", Lekarze.class);
            query.setParameter("login",login);
            transaction.commit();
            return query.getSingleResult();
        }
        catch(Exception exception ){
            if(transaction!=null&&transaction.isActive()){
                System.out.println(exception.getMessage());
                transaction.rollback();
            }
            return null;
        }
    }
}
