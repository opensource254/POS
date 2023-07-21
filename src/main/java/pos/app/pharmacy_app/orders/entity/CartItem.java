package pos.app.pharmacy_app.orders.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;



@Data
@Builder
@NoArgsConstructor
@Table(name = "CART_ITEM")
@Entity
@AllArgsConstructor
public class CartItem {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column(name = "CART_ITEM_ID", updatable = false, nullable = false)
    private String cartItemId;

    private int quantity;

    private double price;

    @Column(name = "PRODUCT_ID", nullable = false)
    private String productId;

    @ManyToOne
    @JoinColumn(name = "CART_ID")
    @JsonIgnore
    private Cart cart;

    @PreRemove
    public void dismissParent() {
        this.cart.dismissChild(this); //SYNCHRONIZING THE OTHER SIDE OF RELATIONSHIP
        this.cart = null;
    }

    @Override
    public String toString() {
        return "CartItem{" +
                "cartItemId='" + cartItemId + '\'' +
                ", quantity=" + quantity +
                ", price=" + price +
                ", productId='" + productId + '\'' +
                '}';
    }
}
