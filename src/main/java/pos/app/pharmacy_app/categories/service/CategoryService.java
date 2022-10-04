package pos.app.pharmacy_app.categories.service;

import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import pos.app.pharmacy_app.categories.Data.CategoryRequest;
import pos.app.pharmacy_app.categories.Data.CategoryResponse;
import pos.app.pharmacy_app.categories.entity.Categories;
import pos.app.pharmacy_app.categories.repository.CategoryRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryService {
private CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public CategoryResponse saveCategory(@Validated CategoryRequest categoriess){


        Categories request=new Categories();
        request.setName(categoriess.getName());
        request.setDescription(categoriess.getDescription());
        request.setStatus(categoriess.getStatus());
        categoryRepository.save(request);

        CategoryResponse response=new CategoryResponse();
        response.setId(request.getCategoryID());
        response.setDescription(request.getDescription());
        response.setName(request.getName());
        response.setResponseCode("00");
        response.setMessage("Category returned data");
        return response;



 }
 public Categories getGategory(Long id)
 {
     return categoryRepository.findById(id).get();
 }
 public List<Categories>getAllCategpories(){

     List<Categories>categoryList=new ArrayList<>();
     // using different methods to iterate over elements
     //using method refference
      categoryRepository.findAll().forEach(categoryList::add);
     // using for loop instead of foreach method.
     //     for(Categories categories : categoryRepository.findAll()) {
     //       categoryList.add(categories);
     //     }
     // using bulk Collection.add()
     //   categoryList.addAll(categoryRepository.findAll());
    //categoryRepository.findAll().forEach(e -> categoryList.add(e));
     return categoryList;
 }
 public void deleteCategory(Long id){
     categoryRepository.deleteById(id);
 }
 public CategoryResponse updateCategory(CategoryRequest categoryDetails,Long id){
     Categories category=categoryRepository.findById(id).get();
        category.setCategoryID(id);
        category.setName(category.getName());
        category.setStatus(categoryDetails.getStatus());
        category.setDescription(categoryDetails.getDescription());
        categoryRepository.save(category);
     CategoryResponse response=new CategoryResponse();
     response.setResponseCode("00");
     response.setMessage("Data changed");
     response.setName(category.getName());
     response.setDescription(category.getDescription());
     return response;

 }

}
