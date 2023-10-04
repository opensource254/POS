package pos.app.pharmacy_app.billing.service.impl;


import pos.app.pharmacy_app.billing.entity.BillingAddress;
import pos.app.pharmacy_app.billing.entity.dao.BillingAddressRequest;
import pos.app.pharmacy_app.billing.entity.dao.BillingAddressResponse;
import pos.app.pharmacy_app.billing.repository.BillingAddressRepository;
import pos.app.pharmacy_app.billing.service.BillingAddressService;

import java.util.Optional;

public class BillingAddressServiceImpl implements BillingAddressService {
    private BillingAddressRepository repository;

    public BillingAddressServiceImpl(BillingAddressRepository repository) {
        this.repository = repository;
    }

    @Override
    public void createBillingAddress(BillingAddressRequest billingAddressRequest) {
        //TODO  to implement the following.
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        String userName = (String) authentication.getPrincipal();
//
//        GetUserResponse userById = accountFeignClient.getUserByUserName(userName);
//
//        if (billingAddressRepository.existsByUserId(userById.getUserId())) {
//            throw new RuntimeException("Billing Address already exists for this User!");
//        }


        BillingAddress addressRequest=BillingAddress.builder()
                .addressLine1(billingAddressRequest.getAddressLine1())
                .addressLine2(billingAddressRequest.getAddressLine2())
                .billingAddressId(billingAddressRequest.getBillingAddressId())
                .phone(billingAddressRequest.getPhone())
                .state(billingAddressRequest.getState())
                .city(billingAddressRequest.getCity())
                .country(billingAddressRequest.getCountry())
                .userId(billingAddressRequest.getUserId())
                .postalCode(billingAddressRequest.getPostalCode()).build();
        repository.save(addressRequest);

    }

    @Override
  public BillingAddressResponse getBillingAddressResponse() {
        //TODO to implement geting userid using context user
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        String userName = (String) authentication.getPrincipal();
//
     //   GetUserResponse userById = accountFeignClient.getUserByUserName(userName);
//    Optional<BillingAddress> byUserId = repository
//                .findByUserId(userById.getUserId());
//
//        BillingAddress billingAddressDao = byUserId
//                .orElseThrow(() -> new RuntimeException("Billing Address does't exist for user!"));
//


//        return BillingAddressResponse.builder().billingAddressId(billingAddressDao.getBillingAddressId())
//                .addressLine1(billingAddressDao.getAddressLine1())
//                .addressLine2(billingAddressDao.getAddressLine2())
//                .city(billingAddressDao.getCity())
//                .country(billingAddressDao.getCountry())
//                .phone(billingAddressDao.getPhone())
//                .postalCode(billingAddressDao.getPostalCode())
//                .state(billingAddressDao.getState())
//                .userId(billingAddressDao.getUserId())
//                .build();
        return null;
    }
}
