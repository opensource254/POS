package pos.app.pharmacy_app.stock.data;

import lombok.Data;
import pos.app.pharmacy_app.products.entity.Products;
import pos.app.pharmacy_app.suppliers.entity.Suppliers;
import java.math.BigDecimal;
import java.util.Date;
@Data
public class StockRequest {

    private Long stockID;
    private Long quantity;
    private Date entryDate;
    private Date expiryDate;
    private BigDecimal salePrice;
    private BigDecimal purchasePrice;
    private Products productsId;
    private Suppliers suppliersId;
}
