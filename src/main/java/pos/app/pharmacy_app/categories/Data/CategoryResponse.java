package pos.app.pharmacy_app.categories.Data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CategoryResponse {
    private Long id;
    private String message;
    private String responseCode;
    private String name;
    private String description;
}
