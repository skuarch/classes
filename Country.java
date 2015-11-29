package model.bean;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author skuarch
 */
@Entity
@Table(name = "country")
public class Country implements Serializable {

    private static final long serialVersionUID = 7461217241950251807L;
    
    @Id
    @Column(name = "country_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "country_name", nullable = false)
    private String name = null;

    //==========================================================================
    /**
     * constructor.
     */
    public Country() {
    }

    //==========================================================================
    /**
     * getter
     * @return id
     */
    public long getId() {
        return id;
    }

    //==========================================================================
    /**
     * setter
     * @param id long
     */
    public void setId(long id) {
        this.id = id;
    }

    //==========================================================================
    /**
     * getter
     * @return name
     */
    public String getName() {
        return name;
    }

    //==========================================================================
    public void setName(String name) {
        this.name = name;
    }

    //==========================================================================
    /**
     * toString
     * @return String
     */
    @Override
    public String toString() {
        return "id=" + id + " name=" + name;
    }

} // end class
