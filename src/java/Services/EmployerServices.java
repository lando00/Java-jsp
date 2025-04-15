package Services;

import Bean.Employeur;
import Helpclass.SessionFact;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class EmployerServices {
    private Session s;
    private Transaction tx;

public boolean create(Employeur pf) {
    try {
        s = SessionFact.getCreater_session().openSession();
        tx = s.beginTransaction();
        s.save(pf); // Pour un nouvel enregistrement
        tx.commit();
        return true;
    } catch (Exception e) {
        e.printStackTrace();
        if (tx != null) tx.rollback();
        return false;
    } finally {
        if (s != null && s.isOpen()) s.close();
    }
}

    public boolean update(Employeur pf) {
        try {
            s = SessionFact.getCreater_session().openSession();
            tx = s.beginTransaction();
            s.update(pf); // Pour mettre à jour un enregistrement existant
            tx.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            if (tx != null) tx.rollback();
            return false;
        } finally {
            if (s != null && s.isOpen()) s.close();
        }
    }

    public Employeur recuper(Long id) {
        Employeur resultat = null;
        try {
            s = SessionFact.getCreater_session().openSession();
            tx = s.beginTransaction();
            resultat = (Employeur) s.get(Employeur.class, id);
            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
            if (tx != null) tx.rollback();
        } finally {
            if (s != null && s.isOpen()) s.close();
        }
        return resultat;
    }

    public List<Employeur> recuper_all() {
        List<Employeur> list_employees = null;
        try {
            s = SessionFact.getCreater_session().openSession();
            tx = s.beginTransaction();
            list_employees = s.createQuery("from Employeur").list();
            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
            if (tx != null) tx.rollback();
        } finally {
            if (s != null && s.isOpen()) s.close();
        }
        return list_employees;
    }  

    public boolean delete(Long id) {
        try {
            s = SessionFact.getCreater_session().openSession();
            tx = s.beginTransaction();
            Employeur professeur = (Employeur) s.get(Employeur.class, id);
            if (professeur != null) {
                s.delete(professeur);
                tx.commit();
                return true;
            } else {
                return false; // Professor not found
            }
        } catch (Exception e) {
            e.printStackTrace();
            if (tx != null) tx.rollback();
            return false;
        } finally {
            if (s != null && s.isOpen()) s.close();
        }
    } //To change body of generated methods, choose Tools | Templates.
    
}