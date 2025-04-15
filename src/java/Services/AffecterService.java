package Services;

import Bean.Affecter;
import Helpclass.SessionFact;
import java.util.Date;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.Query; // Utilisation de l'ancienne API Query

public class AffecterService {

    private Session s;
    private Transaction tx;

    
        public boolean isAffecter(String code_emp , String codeLieu, Date dateAffectation) {
        boolean isAffecter = false;
        try {
            s = SessionFact.getCreater_session().openSession();
            tx = s.beginTransaction();
            // Utilisation de l'ancienne API Query avec createQuery(String)
            Query query = s.createQuery("FROM Affecter WHERE codeLieu = :codeLieu AND code_emp= :code_emp  AND dateAffectation = :dateAffectation");;
            query.setParameter("codeLieu", codeLieu);
            query.setParameter("code_emp", code_emp);
            query.setParameter("dateAffectation", dateAffectation);
            List<Affecter> result = query.list(); // list() est correct pour org.hibernate.Query
            tx.commit();
            isAffecter = !result.isEmpty();
        } catch (Exception e) {
            e.printStackTrace();
            if (tx != null) tx.rollback();
        } finally {
            if (s != null && s.isOpen()) s.close();
        }
        return isAffecter;
    }

    public boolean create(Affecter occ) {
        try {
            s = SessionFact.getCreater_session().openSession();
            tx = s.beginTransaction();
            s.save(occ);
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

   public boolean update(Affecter occ) {
        try {
            s = SessionFact.getCreater_session().openSession();
            tx = s.beginTransaction();
            s.update(occ); // Mettre à jour l'objet Affecter dans la base de données
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
            Affecter occ = (Affecter) s.get(Affecter.class, id);
            if (occ != null) {
                s.delete(occ);
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

    public Affecter recuper(long id) {
        Affecter resultat = null;
        try {
            s = SessionFact.getCreater_session().openSession();
            tx = s.beginTransaction();
            resultat = (Affecter) s.get(Affecter.class, id);
            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
            if (tx != null) tx.rollback();
        } finally {
            if (s != null && s.isOpen()) s.close();
        }
        return resultat;
    }

    public List<Affecter> recuper_all() {
        List<Affecter> listAffecter = null;
        try {
            s = SessionFact.getCreater_session().openSession();
            tx = s.beginTransaction();
            // Utilisation de l'ancienne API Query avec createQuery(String)
            Query query = s.createQuery("from Affecter");
            listAffecter = query.list(); // list() est correct pour org.hibernate.Query
            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
            if (tx != null) tx.rollback();
        } finally {
            if (s != null && s.isOpen()) s.close();
        }
        return listAffecter;
    }
}