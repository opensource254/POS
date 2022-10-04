package pos.app.pharmacy_app.orders.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.hibernate.annotations.Filter;
import org.hibernate.annotations.FilterDef;
import org.hibernate.annotations.ParamDef;
import org.hibernate.annotations.SQLDelete;
import pos.app.pharmacy_app.products.entity.Products;
import pos.app.pharmacy_app.users.entity.Users;

import javax.persistence.*;
import javax.print.attribute.standard.MediaSize;
import java.math.BigDecimal;
import java.util.Date;

@Table(name = "ORDERS")
@Entity
@Data
@NoArgsConstructor
@SQLDelete(sql = "UPDATE ORDERS SET isDeleted = true WHERE orderID=?")
@FilterDef(name = "deletedOrdersFilter", parameters = @ParamDef(name = "isDeleted", type = "boolean"))
@Filter(name = "deletedOrdersFilter", condition = "deleted = :isDeleted")
public class Orders {
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ORDERS_SEQ")
    @SequenceGenerator(sequenceName = "\"orders_seq\"", allocationSize = 1, name = "ORDERS_SEQ")
    @Id
    @NonNull
    @Column(name = "ORDERS_ID")
    private Long orderID;
    @Column(name = "ORDER_CODE")
    private String orderCode;
    @Column(name = "PAYMENT_METHOD")
    private PaymentMethods paymentMethods;
    @JoinColumn(name = "PRODUCT_ID",referencedColumnName = "PRODUCT_ID")
    @ManyToOne
    private Products productID;
    @Column(name = "DISCOUNT")
    private Double discount;
    @Column(name = "CHANGE")
    private BigDecimal change;
    @Column(name = "PAID_AMOUNT")
    private BigDecimal paidAmount;
    @Column(name = "GRAND_TOTAL")
    private BigDecimal grandTotal;
    @Column(name = "ORDER_STATUS")
    private OrderStatus orderStatus;
    @Column(name = "ORDER_DATE")
    private Date orderDate;
    @JoinColumn(name = "USER_ID",referencedColumnName = "USER_ID")
    @ManyToOne
    private Users usersID;
    @Column(name = "PAYMENT_STATUS")
    private PaymentStatus paymentStatus;
    @Column(name = "VAT")
    private String vat;

    private BigDecimal subTotals;
    private boolean deleted=Boolean.FALSE;

    public Orders(String orderCode, PaymentMethods paymentMethods, Products productID,
                  Double discount, BigDecimal change, BigDecimal paidAmount,
                  BigDecimal grandTotal, OrderStatus orderStatus, Date orderDate, Users usersID,
                  PaymentStatus paymentStatus, String vat, BigDecimal subTotals)
    {
        this.orderCode = orderCode;
        this.paymentMethods = paymentMethods;
        this.productID = productID;
        this.discount = discount;
        this.change = change;
        this.paidAmount = paidAmount;
        this.grandTotal = grandTotal;
        this.orderStatus = orderStatus;
        this.orderDate = orderDate;
        this.usersID = usersID;
        this.paymentStatus = paymentStatus;
        this.vat = vat;
        this.subTotals = subTotals;
    }

}
