package data;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * Created by ajo on 04.06.17.
 */
@Entity
@Table(name = "customer_order", schema = "IGT", catalog = "")
public class CustomerOrderEntity {
    private int idorder;
    private BigDecimal price;

    @Id
    @Column(name = "idorder")
    public int getIdorder() {
        return idorder;
    }

    public void setIdorder(int idorder) {
        this.idorder = idorder;
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

        CustomerOrderEntity that = (CustomerOrderEntity) o;

        if (idorder != that.idorder) return false;
        if (price != null ? !price.equals(that.price) : that.price != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idorder;
        result = 31 * result + (price != null ? price.hashCode() : 0);
        return result;
    }
}
