package fr.houseofcode.dap.server.msa.data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * @author msa
 *
 */
@Entity
public class AppUser {
    @Id
    @GeneratedValue
    //TODO MSA by Djer |Audit Code| (Checkstyle) Commentaire JavaDoc manquant
    private Integer id;

    /**
     * @return the id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    //TODO MSA by Djer |Audit Code| (Checkstyle) LE paramètre devrait être final
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    //TODO MSA by Djer |Audit Code| (Checkstyle) LE paramètre devrait être final
    public void setName(String name) {
        this.name = name;
    }

    //TODO MSA by Djer |Audit Code| (Checkstyle) Commentaire JavaDoc manquant
    //TODO MSA by Djer |POO| Place les attributs vers le début de la classe. Ordre attendu : Constantes, attributs, intialisateurs statiques, constructeurs, méthodes métier, méthodes utilitaires (toString, Equals,...), getter/setters
    private String name;

}
