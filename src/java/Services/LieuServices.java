package Services;

import Bean.Lieu;
import Helpclass.SessionFact;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.Query;

public class LieuServices {

    private Session s;
    private Transaction tx;

    public boolean create(Lieu lieu) {
        try {
            s = SessionFact.getCreater_session().openSession();
            tx = s.beginTransaction();
            s.save(lieu);
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

    public boolean update(Lieu lieu) {
        try {
            s = SessionFact.getCreater_session().openSession();
            tx = s.beginTransaction();
            s.update(lieu);
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

    public boolean delete(long id) {
        try {
            s = SessionFact.getCreater_session().openSession();
            tx = s.beginTransaction();
            Lieu lieu = (Lieu) s.get(Lieu.class, id);
            if (lieu != null) {
                s.delete(lieu);
                tx.commit();
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            if (tx != null) tx.rollback();
            return false;
        } finally {
            if (s != null && s.isOpen()) s.close();
        }
    }

    public List<Lieu> recuper_all() {
        List<Lieu> list_Lieu = null;
        try {
            s = SessionFact.getCreater_session().openSession();
            tx = s.beginTransaction();
            Query query = s.createQuery("from Lieu"); // Correction : "Salles" au lieu de "Salle"
            list_Lieu = query.list();
            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
            if (tx != null) tx.rollback();
        } finally {
            if (s != null && s.isOpen()) s.close();
        }
        return list_Lieu;
    }
}