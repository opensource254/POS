package pos.app.pharmacy_app.billing.service;

import pos.app.pharmacy_app.billing.entity.dao.BillingAddressRequest;
import pos.app.pharmacy_app.billing.entity.dao.BillingAddressResponse;

public interface BillingAddressService {
    void createBillingAddress(BillingAddressRequest billingAddressRequest);
    BillingAddressResponse getBillingAddressResponse();
}
