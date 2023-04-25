package com.example.project;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.ArrayList;

public class PacjenciUtil {
    public void dodaj(Pacjenci pacjenci){
        Transaction transaction = null;
        try (Session session=HibernateUtil.openSession()){
            transaction=session.beginTransaction();
            session.persist(pacjenci);
            transaction.commit();
        }
        catch(Exception exception ){
            if(transaction!=null&&transaction.isActive()){
                System.out.println(exception.getMessage());
                transaction.rollback();
            }
        }
    }
    public Pacjenci szukajPoLoginie(String login){
        Transaction transaction = null;
        try (Session session=HibernateUtil.openSession()){
            transaction=session.beginTransaction();
            Query<Pacjenci> query = session.createQuery("select w from Pacjenci w where w.login=:login", Pacjenci.class);
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
    public Pacjenci szukajPoPeselu(String pesel){
        Transaction transaction = null;
        try (Session session=HibernateUtil.openSession()){
            transaction=session.beginTransaction();
            Query<Pacjenci> query = session.createQuery("select w from Pacjenci w where w.pesel=:pesel", Pacjenci.class);
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
    public ArrayList<Pacjenci> szukajPoImieniu(String name){
        Transaction transaction = null;
        try (Session session=HibernateUtil.openSession()){
            transaction=session.beginTransaction();
            Query<Pacjenci> query = session.createQuery("select w from Pacjenci w where w.name=:name", Pacjenci.class);
            query.setParameter("name",name);
            transaction.commit();
            return (ArrayList<Pacjenci>) query.getResultList();
        }
        catch(Exception exception ){
            if(transaction!=null&&transaction.isActive()){
                System.out.println(exception.getMessage());
                transaction.rollback();
            }
            return null;
        }
    }
    public ArrayList<Pacjenci> szukajPoNazwisku(String surname){
        Transaction transaction = null;
        try (Session session=HibernateUtil.openSession()){
            transaction=session.beginTransaction();
            Query<Pacjenci> query = session.createQuery("select w from Pacjenci w where w.surname=:surname", Pacjenci.class);
            query.setParameter("surname",surname);
            transaction.commit();
            return (ArrayList<Pacjenci>) query.getResultList();
        }
        catch(Exception exception ){
            if(transaction!=null&&transaction.isActive()){
                System.out.println(exception.getMessage());
                transaction.rollback();
            }
            return null;
        }
    }
    public ArrayList<Pacjenci> wszyscyPacjenci(){
        Transaction transaction = null;
        try (Session session=HibernateUtil.openSession()){
            transaction=session.beginTransaction();
            Query<Pacjenci> query = session.createQuery("select w from Pacjenci w", Pacjenci.class);
            transaction.commit();
            return (ArrayList<Pacjenci>) query.getResultList();
        }
        catch(Exception exception ){
            if(transaction!=null&&transaction.isActive()){
                System.out.println(exception.getMessage());
                transaction.rollback();
            }
            return null;
        }
    }
    public Pacjenci szukajPoImieniuINazwisku(String name,String surname){
        Transaction transaction = null;
        try (Session session=HibernateUtil.openSession()){
            transaction=session.beginTransaction();
            Query<Pacjenci> query = session.createQuery("select w from Pacjenci w where w.name=:name and w.surname=:surname", Pacjenci.class);
            query.setParameter("name",name);
            query.setParameter("surname",surname);
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
    public ArrayList<Pacjenci> szukajPoImieniuINazwiskuLista(String name,String surname){
        Transaction transaction = null;
        try (Session session=HibernateUtil.openSession()){
            transaction=session.beginTransaction();
            Query<Pacjenci> query = session.createQuery("select w from Pacjenci w where w.name=:name and w.surname=:surname", Pacjenci.class);
            query.setParameter("name",name);
            query.setParameter("surname",surname);
            transaction.commit();
            return (ArrayList<Pacjenci>) query.getResultList();
        }
        catch(Exception exception ){
            if(transaction!=null&&transaction.isActive()){
                System.out.println(exception.getMessage());
                transaction.rollback();
            }
            return null;
        }
    }
    public Pacjenci szukajPoImieniuINazwiskuIPeselu(String name,String surname,String pesel){
        Transaction transaction = null;
        try (Session session=HibernateUtil.openSession()){
            transaction=session.beginTransaction();
            Query<Pacjenci> query = session.createQuery("select w from Pacjenci w where w.name=:name and w.surname=:surname and w.pesel=:pesel", Pacjenci.class);
            query.setParameter("name",name);
            query.setParameter("surname",surname);
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
}
