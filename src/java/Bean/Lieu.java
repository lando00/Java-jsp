package Bean;

import javax.persistence.*;

@Entity
@Table(name = "lieu") // Nom de la table dans la base de données
public class Lieu {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "code_lieu")
    private String codeLieu;

    @Column(name = "designation")
    private String designation;

    @Column(name = "province")
    private String province;

    public Lieu() {}

    public Lieu(String codeLieu, String designation, String province) {
        this.codeLieu = codeLieu;
        this.designation = designation;
        this.province = province;
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

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }
}