package pos.app.pharmacy_app.billing.service;

import pos.app.pharmacy_app.billing.entity.dao.ShippingAddressDao;
import pos.app.pharmacy_app.billing.entity.dao.ShippingAddressResponse;

public interface ShippingAddressService {
    void createShippingAddress(ShippingAddressDao shippingAddressDao);
    ShippingAddressResponse getShippingResponse();

}
