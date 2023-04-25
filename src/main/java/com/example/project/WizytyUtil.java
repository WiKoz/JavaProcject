package com.example.project;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.hibernate.query.SelectionQuery;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class WizytyUtil {
    public void dodaj(Wizyty wizyty) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.openSession()) {
            transaction = session.beginTransaction();
            session.persist(wizyty);
            transaction.commit();
        } catch (Exception exception) {
            if (transaction != null && transaction.isActive()) {
                System.out.println(exception.getMessage());
                transaction.rollback();
            }
        }
    }

    public List<Wizyty>szukajPoID(Long idL_id) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.openSession()) {
            transaction = session.beginTransaction();
            Query<Wizyty>query = session.createQuery("select w from Wizyty w where w.idL.Id=:idL_id", Wizyty.class);
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
    public ArrayList<Wizyty> szukajPoNazwiskuPacjenta(String nazwisko) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.openSession()) {
            transaction = session.beginTransaction();
            Query<Wizyty>query = session.createQuery("select w from Wizyty w where w.idP.surname=:nazwisko", Wizyty.class);
            query.setParameter("nazwisko", nazwisko);
            transaction.commit();
            return (ArrayList<Wizyty>) query.getResultList();
        } catch (Exception exception) {
            if (transaction != null && transaction.isActive()) {
                System.out.println(exception.getMessage());
                transaction.rollback();
            }
            return null;
        }
    }
    public ArrayList<Wizyty> szukajPoNazwiskuPacjentaIIdLekarza(String nazwisko, Long id) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.openSession()) {
            transaction = session.beginTransaction();
            Query<Wizyty>query = session.createQuery("select w from Wizyty w where w.idP.surname=:nazwisko and w.idL.Id=:id", Wizyty.class);
            query.setParameter("nazwisko", nazwisko);
            query.setParameter("id", id);
            transaction.commit();
            return (ArrayList<Wizyty>) query.getResultList();
        } catch (Exception exception) {
            if (transaction != null && transaction.isActive()) {
                System.out.println(exception.getMessage());
                transaction.rollback();
            }
            return null;
        }
    }
    public ArrayList<Wizyty> szukajPoDacie(Date date) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.openSession()) {
            transaction = session.beginTransaction();
            Query<Wizyty>query = session.createQuery("select w from Wizyty w where w.date=:date", Wizyty.class);
            query.setParameter("date", date);
            transaction.commit();
            return (ArrayList<Wizyty>) query.getResultList();
        } catch (Exception exception) {
            if (transaction != null && transaction.isActive()) {
                System.out.println(exception.getMessage());
                transaction.rollback();
            }
            return null;
        }
    }

    public ArrayList<Wizyty> szukajPoDacieIIdLekarza(Date date,Long id) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.openSession()) {
            transaction = session.beginTransaction();
            Query<Wizyty>query = session.createQuery("select w from Wizyty w where w.date=:date and w.idL.Id=:id", Wizyty.class);
            query.setParameter("date", date);
            query.setParameter("id", id);
            transaction.commit();
            return (ArrayList<Wizyty>) query.getResultList();
        } catch (Exception exception) {
            if (transaction != null && transaction.isActive()) {
                System.out.println(exception.getMessage());
                transaction.rollback();
            }
            return null;
        }
    }
    public ArrayList<Wizyty> szukajPoDacieINazwisku(String nazwisko,Date date) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.openSession()) {
            transaction = session.beginTransaction();
            Query<Wizyty>query = session.createQuery("select w from Wizyty w where  w.idP.surname=:nazwisko and w.date=:date", Wizyty.class);
            query.setParameter("date", date);
            query.setParameter("nazwisko", nazwisko);
            transaction.commit();
            return (ArrayList<Wizyty>) query.getResultList();
        } catch (Exception exception) {
            if (transaction != null && transaction.isActive()) {
                System.out.println(exception.getMessage());
                transaction.rollback();
            }
            return null;
        }
    }
    public ArrayList<Wizyty> szukajPoDacieINazwiskuIIdLekarza(String nazwisko,Date date,Long id) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.openSession()) {
            transaction = session.beginTransaction();
            Query<Wizyty>query = session.createQuery("select w from Wizyty w where  w.idP.surname=:nazwisko and w.date=:date and w.idL.Id=:id", Wizyty.class);
            query.setParameter("date", date);
            query.setParameter("id", id);
            query.setParameter("nazwisko", nazwisko);
            transaction.commit();
            return (ArrayList<Wizyty>) query.getResultList();
        } catch (Exception exception) {
            if (transaction != null && transaction.isActive()) {
                System.out.println(exception.getMessage());
                transaction.rollback();
            }
            return null;
        }
    }
    public List<Wizyty>szukajPoIDP(Long idP_id) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.openSession()) {
            transaction = session.beginTransaction();
            Query<Wizyty>query = session.createQuery("select w from Wizyty w where w.idP.Id=:idP_id", Wizyty.class);
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
    public List<Wizyty> wyswietl() {
        Transaction transaction = null;
        try (Session session = HibernateUtil.openSession()) {
            transaction = session.beginTransaction();
            Query<Wizyty> query = session.createQuery("select w from Wizyty w" , Wizyty.class);
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
    public void usun(Wizyty wizyty) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.openSession()) {
            transaction = session.beginTransaction();
            session.remove(wizyty);
            transaction.commit();
        } catch (Exception exception) {
            if (transaction != null && transaction.isActive()) {
                System.out.println(exception.getMessage());
                transaction.rollback();
            }
        }
    }

    public void zmien(Wizyty wizyty) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.openSession()) {
            transaction = session.beginTransaction();
            session.merge(wizyty);
            transaction.commit();
        } catch (Exception exception) {
            if (transaction != null && transaction.isActive()) {
                System.out.println(exception.getMessage());
                transaction.rollback();
            }
        }
    }
    public Wizyty szukajDoUsun(Long idP_id, Date date) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.openSession()) {
            transaction = session.beginTransaction();
            Query<Wizyty>query = session.createQuery("select w from Wizyty w where w.idP.Id=:idP_id and w.date=:date", Wizyty.class);
            query.setParameter("idP_id", idP_id);
            query.setParameter("date", date);
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
