package pos.app.pharmacy_app.products.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductRequest {
    private  Long id;
    @NotBlank
    private String name;
    @NotBlank
    private String description;
    @NotBlank
    private String brand;
    @NotBlank
    private String weight;
    @NonNull
    private BigDecimal price;
}
