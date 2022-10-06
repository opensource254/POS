package pos.app.pharmacy_app.suppliers.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import pos.app.pharmacy_app.suppliers.entity.Suppliers;
import pos.app.pharmacy_app.suppliers.model.SupplierRequest;
import pos.app.pharmacy_app.suppliers.model.SupplierResponse;
import pos.app.pharmacy_app.suppliers.repository.SupplierRepository;
import pos.app.pharmacy_app.suppliers.service.SupplierService;

import java.util.List;
import java.util.Optional;

@RestController
public class SupplierController {
    @Autowired
    SupplierService service;
    @Autowired
    SupplierRepository supplierRepository;
    @PostMapping( "/createsuppervisor")
    public ResponseEntity<SupplierResponse>createSuppliers(@RequestBody SupplierRequest suppliers){
        try {
            SupplierResponse response = service.createService(suppliers);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @RequestMapping(path="/deleteSuppliers/{id}",method = RequestMethod.DELETE)
    public  ResponseEntity <SupplierResponse>deleteSupplier(@PathVariable Long id){
        try{
        SupplierResponse response=new SupplierResponse();
        Optional<Suppliers> suppliers=supplierRepository.findById(id);
        if(suppliers.isPresent()){
        service.deleteSuppliers(id);
            response.setMessage("Supplier deleted");
            response.setName(suppliers.get().getName());
        return new ResponseEntity<>(response,HttpStatus.OK);
        }else {
            response.setResponseCode("049");
            response.setMessage("The supplier Id does not exist");
            return new ResponseEntity<>(response,HttpStatus.CONFLICT);
        }

    }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/supplierList")
    public ResponseEntity<List<Suppliers>>getAllSuppliers(){
        try{
        List<Suppliers> suppliesList;

        suppliesList=service.getAllSuplliers();
        if (!suppliesList.isEmpty()){
        return new ResponseEntity<>(suppliesList,HttpStatus.OK);
        }else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PutMapping("/editSupliers/{Id}")
    public ResponseEntity<SupplierResponse>updateSuppliers(@RequestBody @Validated SupplierRequest request, @PathVariable Long Id)
    {
       try{
           Optional<Suppliers> suppliers= supplierRepository.findById(Id);
       if(suppliers.isPresent()){
           service.updateSuppliers(request,Id);
           return new ResponseEntity<>(HttpStatus.CREATED);
       }
       else {
           SupplierResponse response =new SupplierResponse();
           response.setResponseCode("049");
           response.setMessage("The Supplier Id "+Id+"does not exist");
           return new ResponseEntity<>(response,HttpStatus.CONFLICT);
       }
    }catch(Exception e){
           return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
       }
    }
    @GetMapping("/getSupplierByID/{id}")
    public ResponseEntity<Suppliers>getSupplierById(@PathVariable Long id){
        try{
        Optional<Suppliers>suppliers=supplierRepository.findById(id);
        if(suppliers.isPresent()){
            Suppliers supplier=  service.getSupplierById(id);
            return new ResponseEntity<>(supplier,HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}
