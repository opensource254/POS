package pos.app.pharmacy_app.suppliers.service;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pos.app.pharmacy_app.suppliers.entity.Suppliers;
import pos.app.pharmacy_app.suppliers.model.SupplierRequest;
import pos.app.pharmacy_app.suppliers.model.SupplierResponse;
import pos.app.pharmacy_app.suppliers.repository.SupplierRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@NoArgsConstructor
public class SupplierService {
    @Autowired
    private SupplierRepository supplierRepository;
    public SupplierResponse createService(SupplierRequest request){
        Suppliers suppliers=new Suppliers();
        suppliers.setName(request.getName());
        suppliers.setPhone(request.getPhone());
        supplierRepository.save(suppliers);
        SupplierResponse response=new SupplierResponse();
        response.setId(suppliers.getSupplierId());
        response.setResponseCode("00");
        response.setMessage("supplier details created");
        response.setName(suppliers.getName());
        return response;
    }
    public List<Suppliers> getAllSuplliers(){
        List<Suppliers> suppliersList=new ArrayList<>();
        supplierRepository.findAll().forEach(suppliers ->suppliersList.add(suppliers) );
        return suppliersList;
    }
    public void deleteSuppliers(Long id){

            supplierRepository.deleteById(id);

    }
    public Suppliers getSupplierById(Long id){
        return
                supplierRepository.findById(id).get();
    }
    public SupplierResponse updateSuppliers(SupplierRequest request, Long id){
        Suppliers supplier=new Suppliers();
        SupplierResponse response=new SupplierResponse();
            supplier.setSupplierId(id);
            supplier.setPhone(request.getPhone());
            supplier.setName(request.getName());
            supplierRepository.save(supplier);

            response.setResponseCode("00");
            response.setMessage("Supplier Updated");
            response.setName(supplier.getName());
            return response;
        }
}
