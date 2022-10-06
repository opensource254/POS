package pos.app.pharmacy_app.products.service;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pos.app.pharmacy_app.products.data.ProductRequest;
import pos.app.pharmacy_app.products.data.ProductResponse;
import pos.app.pharmacy_app.products.entity.Products;
import pos.app.pharmacy_app.products.repository.ProductRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
@AllArgsConstructor
@NoArgsConstructor
public class ProductService {
    @Autowired
   private ProductRepository productRepository;
public ProductResponse createProducts(ProductRequest request){
    Products productsRequest=new Products();
    productsRequest.setProductName(request.getName());
    productsRequest.setProductName(request.getDescription());
    productsRequest.setPrice(request.getPrice());
    productsRequest.setBrand(request.getBrand());
    productsRequest.setWeight(request.getWeight());
    productRepository.save(productsRequest);
    ProductResponse response=new ProductResponse();
    response.setResponseCode("00");
    response.setBrand(productsRequest.getBrand());
    response.setMessage("created product with code "+ generateRandom());
    response.setName(productsRequest.getProductName());

    return response;
}
  public List<Products> getAllProducts()
  {
      List<Products> productList=new ArrayList<>();
      productRepository.findAll().forEach(products -> productList.add(products));
      return productList;
  }
  public Products getProductById(Long productId){
    return productRepository.findById(productId).get();
  }
  public ProductResponse updateProducts(ProductRequest request, Long productId){
    Products products=productRepository.findById(productId).get();
    products.setProductId(productId);
    products.setDescription(request.getDescription());
    products.setProductName(request.getName());
    products.setWeight(request.getWeight());
    products.setBrand(request.getBrand());
    products.setPrice(request.getPrice());
    productRepository.save(products);
      ProductResponse response=new ProductResponse();
      response.setWeight(products.getWeight());
      response.setResponseCode("00");
      response.setWeight(products.getWeight());
      response.setBrand(products.getBrand());
      response.setMessage("updated with code "+ generateRandom());

      return response;
  }
  public void deleteById(Long productId)
  {
      productRepository.deleteById(productId);
  }

public String generateRandom(){
    Random rand = new Random();

    String str = rand.ints(48, 123)
            .filter(num -> (num<58 || num>64) && (num<91 || num>96))
            .limit(15)
            .mapToObj(c -> (char)c).collect(StringBuffer::new, StringBuffer::append, StringBuffer::append)
            .toString();
    return str;
}
}
