package pos.app.pharmacy_app.categories.service;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import pos.app.pharmacy_app.categories.Data.CategoryRequest;
import pos.app.pharmacy_app.categories.Data.CategoryResponse;
import pos.app.pharmacy_app.categories.entity.Categories;
import pos.app.pharmacy_app.categories.repository.CategoryRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CategoryService {
private CategoryRepository categoryRepository;


    public CategoryResponse saveCategory(@Validated CategoryRequest categoriess){


        Categories request=new Categories();
        request.setName(categoriess.getCategoryName());
        request.setDescription(categoriess.getDescription());
        categoryRepository.save(request);

        CategoryResponse response=new CategoryResponse();
        response.setResponseCode("00");

        return response;



 }
 public Categories getCategoryBYId(Long id)
 {
     Optional<Categories>categories=categoryRepository.findById(id);
     if (categories.isPresent()){
         return categories.get();
     }
     else  throw new NoSuchElementException("User with that Id does not exist");

 }
 public List<Categories>getAllCategpories(Integer pageNo, Integer pageSize, String sort){
     Pageable pageable=PageRequest.of(pageNo, pageSize, Sort.by(sort));

     List<Categories>categoryList=new ArrayList<>();
     Page<Categories>pageSults=categoryRepository.findAll(pageable);
     if(pageSults.hasContent()){
         return pageSults.getContent();
     }else return  new ArrayList<Categories>();
     // using different methods to iterate over elements
     //using method refference
    //  categoryRepository.findAll().forEach(categoryList::add);
     // using for loop instead of foreach method.
     //     for(Categories categories : categoryRepository.findAll()) {
     //       categoryList.add(categories);
     //     }
     // using bulk Collection.add()
     //   categoryList.addAll(categoryRepository.findAll());
    //categoryRepository.findAll().forEach(e -> categoryList.add(e));

 }
 public void deleteCategory(Long id){
     categoryRepository.deleteById(id);
 }
 public CategoryResponse updateCategory(CategoryRequest categoryDetails,Long id){
     Categories category=categoryRepository.findById(id).get();
        category.setCategoryID(id);
        category.setName(category.getName());

        category.setDescription(categoryDetails.getDescription());
        categoryRepository.save(category);
     CategoryResponse response=new CategoryResponse();
     response.setResponseCode("00");

     return response;

 }

}
