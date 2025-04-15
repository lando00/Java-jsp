package Helpclass;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class SessionFact {
    private static SessionFactory creater_session;

    public static SessionFactory getCreater_session() {
        if (creater_session == null) {
            try {
                // Charger explicitement le fichier de configuration depuis le classpath
                creater_session = new Configuration()
                        .configure("/util/hibernate.cfg.xml") // Vérifie que ce chemin est correct
                        .buildSessionFactory();
                System.out.println("SessionFactory créée avec succès pour Hibernate 5.2.12");
            } catch (Exception e) {
                System.err.println("Erreur lors de la création de la SessionFactory : " + e.getMessage());
                e.printStackTrace();
                throw new IllegalStateException("Impossible d'initialiser la SessionFactory", e); // Lever une exception explicite
            }
        }
        // Vérification finale pour éviter de retourner null
        if (creater_session == null) {
            throw new IllegalStateException("SessionFactory n'a pas été initialisée correctement");
        }
        return creater_session;
    }
}