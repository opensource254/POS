package pos.app.pharmacy_app.categories.controller;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import pos.app.pharmacy_app.categories.Data.CategoryRequest;
import pos.app.pharmacy_app.categories.Data.CategoryResponse;
import pos.app.pharmacy_app.categories.entity.Categories;
import pos.app.pharmacy_app.categories.repository.CategoryRepository;
import pos.app.pharmacy_app.categories.service.CategoryService;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@NoArgsConstructor
@AllArgsConstructor
@RestController
@RequestMapping("/categories")
public class CategoryController {
    @Autowired
    private  CategoryService categoryService;
    @Autowired
    private CategoryRepository repository;
    @GetMapping("/categoriesList")
    public ResponseEntity<List<Categories>>getCategoryList(@RequestParam Integer pageNo,@RequestParam Integer pageSize,
                                                           @RequestParam(defaultValue = "ASCE") String sortBy){

        List<Categories> allCategories;
        try{

            allCategories=  categoryService.getAllCategpories(pageNo,pageSize,sortBy);
            if(allCategories.isEmpty()){
            return new ResponseEntity<>(allCategories,new HttpHeaders(),HttpStatus.CONFLICT);
            }else {
                return new ResponseEntity<>(allCategories,new HttpHeaders(), HttpStatus.OK);
            }
           }
        catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);

        }

    }
    @PostMapping("/saveCaterories")
    public ResponseEntity<CategoryResponse>saveCategory(@RequestBody @Validated CategoryRequest categories) {
        try {
            CategoryResponse response = categoryService.saveCategory(categories);

            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (Exception e) {
    return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/updateCategories/{id}")
    public ResponseEntity<CategoryResponse>updateCategory(CategoryRequest categories,Long id){
        try {
           Optional<Categories>  category=repository.findById(id);

            if(category.isPresent()){
                CategoryResponse  response=categoryService.updateCategory(categories,id);
               return new ResponseEntity<>(response,HttpStatus.OK);
           }else {
                CategoryResponse response=new CategoryResponse();
                response.setMessage("No Data available");
                response.setResponseCode("409");
               return new ResponseEntity<>(response,HttpStatus.CONFLICT);
           }
        }
        catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

   @RequestMapping(path = "/getCategory/{id}",method = RequestMethod.GET)
    public ResponseEntity<Categories>getCategoryById(@PathVariable("id") Long id){
        try{
            Optional<Categories>getById=repository.findById(id);
            if(getById.isPresent())
            {
             Categories category= categoryService.getCategoryBYId(id);
               return new ResponseEntity<>(category,HttpStatus.OK);
           }
        else{ throw new NoSuchElementException("SUch Id Does not Exist");
           // return new ResponseEntity<>(HttpStatus.NO_CONTENT);
           }
        }
        catch (Exception e){
            e.getMessage();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
   }

   @RequestMapping(path = "/deleteCategoryById/{id}",method = RequestMethod.DELETE)
    public ResponseEntity<CategoryResponse>deleteById(@PathVariable("id") Long id){
        try{
            CategoryResponse response=new CategoryResponse();
            Optional<Categories> deleteCategory=repository.findById(id);
            if (deleteCategory.isPresent()){
             categoryService.deleteCategory(id);

                response.setMessage("deleted");
                response.setId(id);
                response.setResponseCode("00");

                return new ResponseEntity<>(response,HttpStatus.OK);

            }else {
                response.setMessage("The Id provided does not exist");
                response.setId(id);
                response.setResponseCode("409");
                return new ResponseEntity<>(response,HttpStatus.CONFLICT);
                 }

        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

   }
    

}
