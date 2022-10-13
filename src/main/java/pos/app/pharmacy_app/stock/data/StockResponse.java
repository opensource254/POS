package pos.app.pharmacy_app.stock.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StockResponse {
    private String responseCode;
    private String message;
    private String supplierName;
    private String productCode;
}
