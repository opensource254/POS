package pos.app.pharmacy_app.sales.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Filter;
import org.hibernate.annotations.FilterDef;
import org.hibernate.annotations.ParamDef;
import org.hibernate.annotations.SQLDelete;
import pos.app.pharmacy_app.orders.entity.Orders;
import pos.app.pharmacy_app.products.entity.Products;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@Table(name = "SALES")
@SQLDelete(sql = "UPDATE SALES SET isDeleted = true WHERE SalesID=?")
@FilterDef(name = "deletedSalesFilter", parameters = @ParamDef(name = "isDeleted", type = "boolean"))
@Filter(name = "deletedSalesFilter", condition = "deleted = :isDeleted")
public class Sales {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "SALES_SEQ")
    @SequenceGenerator(sequenceName = "sales_seq",allocationSize = 1,name = "SALES_SEQ")
    @Column(name = "SALES_ID")
    private Long SalesID;
    @JoinColumn(name = "ORDERS_ID",referencedColumnName = "ORDERS_ID")
    @ManyToOne
    private Orders ordersID;
    @JoinColumn(name = "PRODUCTS_ID",referencedColumnName = "PRODUCT_ID")
    @ManyToOne
    private Products productsID;
    @Column(name = "QUANTITY")
    private String quantity;
    @Column(name = "RECEIPT_NO")
    private String receiptNo;
    @Column(name = "TOTALS")
    private String totals;
    @Column(name = "SALES_DATE")
    private Date salesDate;
    @Column(name = "IS_DELETED")
    private boolean isDeleted=Boolean.FALSE;

    public Sales(Orders ordersID, Products productsID, String quantity,
                 String receiptNo, String totals, Date salesDate, boolean isDeleted) {
        this.ordersID = ordersID;
        this.productsID = productsID;
        this.quantity = quantity;
        this.receiptNo = receiptNo;
        this.totals = totals;
        this.salesDate = salesDate;
        this.isDeleted = isDeleted;
    }
}
