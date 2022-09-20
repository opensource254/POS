package pos.app.pharmacy_app.sales.service;

import org.hibernate.Filter;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import pos.app.pharmacy_app.sales.entity.Sales;
import pos.app.pharmacy_app.sales.repository.SalesRepository;

import javax.persistence.EntityManager;

public class SalesService {
    @Autowired
    private SalesRepository salesRepository;
    @Autowired
    private EntityManager entityManager;


    public Iterable<Sales> findAll(boolean isDeleted){
        Session session = entityManager.unwrap(Session.class);
        Filter filter = session.enableFilter("deletedProductFilter");
        filter.setParameter("isDeleted", isDeleted);
        Iterable<Sales> products =  salesRepository.findAll();
        session.disableFilter("deletedProductFilter");
        return products;
    }

}
