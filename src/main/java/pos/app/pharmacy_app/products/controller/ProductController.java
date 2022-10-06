package pos.app.pharmacy_app.products.controller;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import pos.app.pharmacy_app.products.data.ProductRequest;
import pos.app.pharmacy_app.products.data.ProductResponse;
import pos.app.pharmacy_app.products.entity.Products;
import pos.app.pharmacy_app.products.repository.ProductRepository;
import pos.app.pharmacy_app.products.service.ProductService;
import java.util.List;
import java.util.Optional;


@NoArgsConstructor
@AllArgsConstructor
@RestController


public class ProductController {
    @Autowired
    ProductRepository repository;
    @Autowired
    ProductService service;
   @PostMapping("createProduct")
    public ResponseEntity<ProductResponse>saveProducts(@RequestBody @Validated ProductRequest productRequest){
      try{
          ProductResponse response=  service.createProducts(productRequest);
              return new ResponseEntity<>(response, HttpStatus.OK);

//          else
//          {
//              response.setMessage("Failed to create");
//              response.setResponseCode("409");
//              response.setBrand(productRequest.getBrand());
//              response.setName(productRequest.getName());
//              response.setWeight(productRequest.getWeight());
//              return new ResponseEntity<>(response,HttpStatus.CONFLICT);
//          }
      } catch (Exception e){
          return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
      }

    }
    @GetMapping("/productList")
    public ResponseEntity<List<Products>>getProducts(){
       try{

       List<Products> productList;
       productList=service.getAllProducts();
       if(!productList.isEmpty()){
       return new ResponseEntity<>(productList,HttpStatus.OK);
       }else {
           return new ResponseEntity<>(HttpStatus.NO_CONTENT);
       }
       }
       catch (Exception e)
       {
           return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
       }

    }
    @PutMapping("/updateProducts")
    public ResponseEntity<ProductResponse>updateProducts(@RequestBody @Validated ProductRequest request, Long id){
       try{

       if (repository.findById(id).isPresent())
           { ProductResponse response;
                response= service.updateProducts(request,id);
               return new ResponseEntity<>(response,HttpStatus.OK);
           }
       else {
           ProductResponse response=new ProductResponse();
           response.setMessage("Update failed, No such IDs");
           response.setResponseCode("049");
           return new ResponseEntity<>(response,HttpStatus.CONFLICT);
       }
       }catch (Exception e){
           return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
       }
    }
    @RequestMapping(path = "/getProductById/{id}",method = RequestMethod.GET)
    public ResponseEntity<Products>getProductById(@PathVariable Long id){
       try{
       Optional<Products> products=repository.findById(id);
       if(products.isPresent()){
        Products products1= service.getProductById(id);
        return new ResponseEntity<>(products1,HttpStatus.OK);
       }else {
           return new ResponseEntity<>(HttpStatus.NO_CONTENT);
       }
       }catch (Exception e){
           return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
       }
    }
    @RequestMapping(value = "/deleteProduct/{id}")
    public ResponseEntity<ProductResponse>deleProductById(@PathVariable  Long id){
      try{ Optional<Products>products=repository.findById(id);
        ProductResponse response=new ProductResponse();
       if(products.isPresent()){
          service.deleteById(id);

          response.setName(products.get().getProductName());
          response.setResponseCode("00");
          response.setMessage("Produtct deleted");
          return new ResponseEntity<>(response,HttpStatus.OK);
       }else
        response.setId(id);
        response.setResponseCode("049");
        response.setMessage("Produtct does not exist");
        return new ResponseEntity<>(response,HttpStatus.CONFLICT);
      }
      catch (Exception e){
          return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
      }
    }
}
