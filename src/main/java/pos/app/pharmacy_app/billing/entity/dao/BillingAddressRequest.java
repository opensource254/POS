package pos.app.pharmacy_app.billing.entity.dao;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NonNull;



@Data
@AllArgsConstructor
@Builder
public class BillingAddressRequest {
    private String billingAddressId;

    @NonNull
    private String userId;

    @NonNull
    private String addressLine1;
    private String addressLine2;

    @Column(name = "CITY", nullable = false)
    private String city;

    private String state;

    private String postalCode;

    @Pattern(regexp = "[A-Z]{2}", message = "2-letter ISO country code required")
    @NonNull
    private String country;

    private String phone;

}
