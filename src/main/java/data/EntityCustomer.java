package data;

import org.hibernate.search.annotations.Indexed;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by ajo on 04.06.17.
 */
@Entity
@Indexed
public class EntityCustomer {
    private int idcustomer;
    private Date birthdate;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "idcustomer")
    public int getIdcustomer() {
        return idcustomer;
    }

    public void setIdcustomer(int idcustomer) {
        this.idcustomer = idcustomer;
    }

    @Basic
    @Column(name = "birthdate")
    public Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EntityCustomer that = (EntityCustomer) o;

        if (idcustomer != that.idcustomer) return false;
        if (birthdate != null ? !birthdate.equals(that.birthdate) : that.birthdate != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idcustomer;
        result = 31 * result + (birthdate != null ? birthdate.hashCode() : 0);
        return result;
    }
}
