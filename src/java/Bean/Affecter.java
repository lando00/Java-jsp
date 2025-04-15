package Bean;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "affecter")
public class Affecter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "code_lieu")
    private String codeLieu;

    @Column(name = "code_emp")
    private String code_emp;

    @Column(name = "date_Affectation")
    @Temporal(TemporalType.DATE)
    private Date dateAffectation;

    public Affecter() {}

    public Affecter(String codeLieu, String code_emp, Date dateAffectation) {
        this.codeLieu = codeLieu;
        this.code_emp = code_emp;
        this.dateAffectation = dateAffectation;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCodeLieu() {
        return codeLieu;
    }

    public void setCodeLieu(String codeLieu) {
        this.codeLieu = codeLieu;
    }

    public String getCode_emp() {
        return code_emp;
    }

    public void setCode_emp(String code_emp) {
        this.code_emp = code_emp;
    }

    public Date getDateAffectation() {
        return dateAffectation;
    }

    public void getDateAffectation(Date dateOccupation) {
        this.dateAffectation = dateAffectation;
    }
}