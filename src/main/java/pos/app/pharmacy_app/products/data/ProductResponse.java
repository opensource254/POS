package pos.app.pharmacy_app.products.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductResponse {
    private Long id;
    private String message;
    private String responseCode;
    private String name;
    private String brand;
    private String weight;
}
