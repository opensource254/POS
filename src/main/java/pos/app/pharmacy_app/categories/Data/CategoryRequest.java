package pos.app.pharmacy_app.categories.Data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CategoryRequest {
    private  Long id;
    @NotBlank
    private String name;
    @NotBlank
    private String description;
    @NotBlank
    private String status;
}
