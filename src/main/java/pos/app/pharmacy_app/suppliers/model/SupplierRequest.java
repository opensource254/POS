package pos.app.pharmacy_app.suppliers.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SupplierRequest {
    private Long id;
    @NotBlank
    private String name;
    @NotBlank
    private String phone;

}
