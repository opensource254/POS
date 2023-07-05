package pos.app.pharmacy_app.orders.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.GenericGenerator;
import pos.app.pharmacy_app.customers.entity.CustomerDetails;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "CART")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Cart {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column(name = "CART_ID", updatable = false, nullable = false)
    private String cartId;
    @NotNull
    @Column(name = "placed_date", nullable = false)
    private Instant placedDate;
//
//    @NotEmpty
//    @NotNull
//    @Column(name = "USER_NAME", nullable = false)
//    private String userName;

    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<CartItem> cartItem;

    @Column(name = "TOTAL_PRICE", nullable = false)
    private BigDecimal totalPrice;
    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "payment_method", nullable = false)
    private PaymentMethod paymentMethod;

    @Column(name = "payment_reference")
    private String paymentReference;

    @Column(name = "payment_modification_reference")
    private String paymentModificationReference;
    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private OrderStatus status;

    @OneToMany(mappedBy = "cart")
    @org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Orders> orders = new HashSet<>();

    @ManyToOne(optional = false)
    @NotNull
    @JsonIgnoreProperties("carts")
    private CustomerDetails customerDetails;

    public Cart(Instant now, OrderStatus orderStatus, BigDecimal price, PaymentMethod paymentMethods, CustomerDetails customerDetails) {
        this.placedDate=now;
        this.status=orderStatus;
        this.totalPrice=price;
        this.paymentMethod=paymentMethods;
        this.customerDetails=customerDetails;
    }
    public void calculateTotalPrice() {
        if (null != this.orders) {
            this.setTotalPrice(this.orders.stream().map(Orders::getGrandTotal).reduce(BigDecimal.ZERO, BigDecimal::add));
        }
    }

    public Cart orders(Set<Orders> productOrders) {
        this.orders = productOrders;
        calculateTotalPrice();
        return this;
    }

    public Cart addOrder(Orders productOrder) {
        this.orders.add(productOrder);
        productOrder.setCart(this);
        calculateTotalPrice();
        return this;
    }

    public Cart removeOrder(Orders productOrder) {
        this.orders.remove(productOrder);
        productOrder.setCart(null);
        calculateTotalPrice();
        return this;
    }
    public void dismissChild(CartItem cartItem) {
        this.cartItem.remove(cartItem);
    }

    @PreRemove
    public void dismissChild() {
        this.cartItem.forEach(CartItem::dismissParent); // SYNCHRONIZING THE OTHER SIDE OF RELATIONSHIP
        this.cartItem.clear();
    }
}
