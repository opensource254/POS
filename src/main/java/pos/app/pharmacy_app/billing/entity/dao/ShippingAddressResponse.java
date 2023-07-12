package pos.app.pharmacy_app.billing.entity.dao;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ShippingAddressResponse {
    private String shippingAddressId;
    private String userId;
    private String addressLine1;
    private String addressLine2;
    private String city;
    private String state;
    private String postalCode;
    private String country;
    private String phone;
}
