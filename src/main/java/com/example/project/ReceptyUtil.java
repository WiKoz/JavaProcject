package com.example.project;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;

public class ReceptyUtil {
    public void dodaj( Recepy recepy){
        Transaction transaction = null;
        try (Session session=HibernateUtil.openSession()){
            transaction=session.beginTransaction();
            session.persist(recepy);
            transaction.commit();
        }
        catch(Exception exception ){
            if(transaction!=null&&transaction.isActive()){
                System.out.println(exception.getMessage());
                transaction.rollback();
            }
        }
    }
    public List<Recepy>szukajPoID(Long idL_id) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.openSession()) {
            transaction = session.beginTransaction();
            Query<Recepy>query = session.createQuery("select w from Recepy w where w.idL.Id=:idL_id", Recepy.class);
            query.setParameter("idL_id", idL_id);
            transaction.commit();
            return query.getResultList();
        } catch (Exception exception) {
            if (transaction != null && transaction.isActive()) {
                System.out.println(exception.getMessage());
                transaction.rollback();
            }
            return null;
        }
    }


    public void usun(Recepy recepa) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.openSession()) {
            transaction = session.beginTransaction();
            session.remove(recepa);
            transaction.commit();
        } catch (Exception exception) {
            if (transaction != null && transaction.isActive()) {
                System.out.println(exception.getMessage());
                transaction.rollback();
            }
        }
    }
    public void zmien(Recepy recepa) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.openSession()) {
            transaction = session.beginTransaction();
            session.merge(recepa);
            transaction.commit();
        } catch (Exception exception) {
            if (transaction != null && transaction.isActive()) {
                System.out.println(exception.getMessage());
                transaction.rollback();
            }
        }
    }
    public List<Recepy>szukajPoIDP(Long idP_id) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.openSession()) {
            transaction = session.beginTransaction();
            Query<Recepy>query = session.createQuery("select w from Recepy w where w.idP.Id=:idP_id", Recepy.class);
            query.setParameter("idP_id", idP_id);
            transaction.commit();
            return query.getResultList();
        } catch (Exception exception) {
            if (transaction != null && transaction.isActive()) {
                System.out.println(exception.getMessage());
                transaction.rollback();
            }
            return null;
        }
    }
    public List<Recepy>szukajPoNazwiskuPIIdLekarza(String nazwisko,Long idL) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.openSession()) {
            transaction = session.beginTransaction();
            Query<Recepy>query = session.createQuery("select w from Recepy w where w.idP.surname=:nazwisko and w.idL.Id=:idL", Recepy.class);
            query.setParameter("nazwisko", nazwisko);
            query.setParameter("idL", idL);
            transaction.commit();
            return query.getResultList();
        } catch (Exception exception) {
            if (transaction != null && transaction.isActive()) {
                System.out.println(exception.getMessage());
                transaction.rollback();
            }
            return null;
        }
    }
    public Recepy szukajPoNazwiskuPITresci(String nazwisko,String tresc) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.openSession()) {
            transaction = session.beginTransaction();
            Query<Recepy>query = session.createQuery("select w from Recepy w where w.idP.surname=:nazwisko and w.lek=:tresc", Recepy.class);
            query.setParameter("nazwisko", nazwisko);
            query.setParameter("tresc", tresc);
            transaction.commit();
            return query.getSingleResult();
        } catch (Exception exception) {
            if (transaction != null && transaction.isActive()) {
                System.out.println(exception.getMessage());
                transaction.rollback();
            }
            return null;
        }
    }
    public Recepy szukajPoIDPITresi(Long idP_id, String tresc) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.openSession()) {
            transaction = session.beginTransaction();
            Query<Recepy>query = session.createQuery("select w from Recepy w where w.idP.Id=:idP_id and w.lek=:tresc", Recepy.class);
            query.setParameter("idP_id", idP_id);
            query.setParameter("tresc", tresc);
            transaction.commit();
            return query.getSingleResult();
        } catch (Exception exception) {
            if (transaction != null && transaction.isActive()) {
                System.out.println(exception.getMessage());
                transaction.rollback();
            }
            return null;
        }
    }
}
