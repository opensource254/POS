package pos.app.pharmacy_app.billing.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;


@Data
@Table(name = "BIILING_ADDRESS")
@Entity
@Builder
@NoArgsConstructor(force = true)
@AllArgsConstructor
public class BillingAddress {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column(name = "BILLING_ADDRESS_ID", updatable = false, nullable = false)
    private String billingAddressId;

    @Column(name = "USER_ID", nullable = false)
    private String userId;

    @Column(name = "ADDRESS_LINE1", nullable = false)
    private String addressLine1;

    @Column(name = "ADDRESS_LINE2")
    private String addressLine2;

    @Column(name = "CITY", nullable = false)
    private String city;

    private String state;

    @Column(name = "POSTAL_CODE")
    private String postalCode;

    @Pattern(regexp = "[A-Z]{2}", message = "2-letter ISO country code required")
    @NonNull
    private String country;

    private String phone;

}
