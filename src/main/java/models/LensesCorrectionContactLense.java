package models;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.util.ArrayList;
import java.util.List;

@Entity
public class LensesCorrectionContactLense {
    private int id;
    private LensesCorrection lensesCorrection;
    private ContactLense contactLense;

    private static List<LensesCorrectionContactLense> entity;

    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    /**
     * Required by Hibernate.
     */
    private LensesCorrectionContactLense() {
    }

    public LensesCorrectionContactLense(LensesCorrection lensesCorrection, ContactLense contactLense) {
        this.lensesCorrection = lensesCorrection;
        this.contactLense = contactLense;

    }

    private void addToEntity(LensesCorrectionContactLense element){
        if(entity==null){
            entity = new ArrayList<>();
        }
        entity.add(element);
    }
    @ManyToOne
    public ContactLense getContactLense() {
        return contactLense;
    }

    public void setContactLense(ContactLense contactLense) {
        this.contactLense = contactLense;
    }

    @ManyToOne
    public LensesCorrection getLensesCorrection() {
        return lensesCorrection;
    }

    public void setLensesCorrection(LensesCorrection lensesCorrection) {
        this.lensesCorrection = lensesCorrection;
    }
}
