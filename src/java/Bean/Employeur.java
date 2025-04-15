package Bean;

import javax.persistence.*;

@Entity
@Table(name = "employeur")  // Ajoute explicitement le nom de la table ici
public class Employeur {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "code_emp")  // Utilisation explicite du nom de la colonne
    private String code_emp;

    private String nom;
    private String prenom;
    private String poste;

    // Constructeurs
    public Employeur() {}

    public Employeur(String code_emp, String nom, String prenom, String poste) {
        this.code_emp = code_emp;
        this.nom = nom;
        this.prenom = prenom;
        this.poste = poste;
    }

    // Getters et Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode_emp() {
        return code_emp;
    }

    public void setCode_emp(String code_emp) {
        this.code_emp = code_emp;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getPoste() {
        return poste;
    }

    public void setPoste(String poste) {
        this.poste = poste;
    }
}
