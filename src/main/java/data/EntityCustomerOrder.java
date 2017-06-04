package data;

import org.hibernate.search.annotations.Indexed;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * Created by ajo on 04.06.17.
 */
@Entity
@Indexed
@Table(name = "customer_order", schema = "IGT", catalog = "")
public class EntityCustomerOrder {
    private int idorder;
    private int fKCustomer;
    private BigDecimal price;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "idorder")
    public int getIdorder() {
        return idorder;
    }

    public void setIdorder(int idorder) {
        this.idorder = idorder;
    }

    @Basic
    @Column(name = "fK_customer")
    public int getfKCustomer() {
        return fKCustomer;
    }

    public void setfKCustomer(int fKCustomer) {
        this.fKCustomer = fKCustomer;
    }

    @Basic
    @Column(name = "price")
    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EntityCustomerOrder that = (EntityCustomerOrder) o;

        if (idorder != that.idorder) return false;
        if (fKCustomer != that.fKCustomer) return false;
        if (price != null ? !price.equals(that.price) : that.price != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idorder;
        result = 31 * result + fKCustomer;
        result = 31 * result + (price != null ? price.hashCode() : 0);
        return result;
    }
}
