package pos.app.pharmacy_app.stock.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.*;
import org.hibernate.sql.Update;
import pos.app.pharmacy_app.products.entity.Products;
import pos.app.pharmacy_app.suppliers.entity.Suppliers;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.Date;

@Table(name = "STOCKS")
@Entity
@Data
@NoArgsConstructor
@SQLDelete(sql = "Update STOCKS SET isDeleted=TRUE WHERE STOCK_ID=?")
@FilterDef(name = "deletedStockFilter", parameters = @ParamDef(name = "isDeleted", type = "boolean"))
@Filter(name = "deletedStockFilter", condition = "isDeleted = :isDeleted")
@Where(clause = "isDeleted=false")
public class Stocks {
    @Id
    @Column(name = "STOCK_ID")
    private Long stockID;
    @
    Column(name = "STK_QUANTITY")
    private Long quantity;
    @Column(name = "ENTRY_DATE")
    private Date entryDate;
    @Column(name = "EXPIRY_DATE")
    private Date expiryDate;
    @Column(name = "SALES_PRICE")
    private BigDecimal salePrice;
    @Column(name = "PURCHASE_PRICE")
    private BigDecimal purchasePrice;
    @JoinColumn(name = "PRODUCT_ID",referencedColumnName = "PRODUCT_ID")
    @ManyToOne
    private Products productsId;
    @JoinColumn(name = "SUPPLIER_ID",referencedColumnName = "SUPPLIER_ID")
    @ManyToOne
    private Suppliers suppliersId;
    @Column(name = "IS_DELETED")
    private boolean isDeleted;


    public Stocks(Long quantity, Date entryDate, Date expiryDate, BigDecimal salePrice,
                  BigDecimal purchasePrice, Products productsId, Suppliers suppliersId, boolean isDeleted) {
        this.quantity = quantity;
        this.entryDate = entryDate;
        this.expiryDate = expiryDate;
        this.salePrice = salePrice;
        this.purchasePrice = purchasePrice;
        this.productsId = productsId;
        this.suppliersId = suppliersId;
        this.isDeleted = isDeleted;
    }
}
