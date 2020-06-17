package fr.houseofcode.dap.server.msa.data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

//TODO MSA by Djer |JavaDoc| Description de la classe  manquants.
/**
 *
 * @author msa
 */
@Entity
public class AppUser {

    //TODO MSA by Djer |JavaDoc| On met en générale ces commentaires JavaDoc sur une seul ligne (je te l'ai modifié pour l'exemple)
    /** Id. */
    @Id
    @GeneratedValue
    private Integer id;

    /**
     * Name.
     */
    private String name;

    /**
     * Get the Id.
     *
     * @return the id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param identifiant the id to set
     */
    public void setId(final Integer identifiant) {
        this.id = identifiant;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param nom the name to set
     */
    public void setName(final String nom) {
        this.name = nom;
    }

}
