package pos.app.pharmacy_app.categories.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;



@Table(name = "CATEGORIES")
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@SQLDelete(sql = "UPDATE CATEGORIES SET deleted = true WHERE categoryID=?")
@Where(clause = "deleted=false")
public class Categories {
   @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CATEGORY_SQE")
   @SequenceGenerator(sequenceName = "category_seq",allocationSize = 1,name = "CATEGORY_SQEc")
   @Id
   @NotNull
   private Long categoryID;
   @NotBlank
   private String name;
   @NotBlank
   private String description;
   @NotBlank
   private String status="Active";
   private Boolean deleted=Boolean.FALSE;

   public Long getCategoryID() {
      return categoryID;
   }

   public void setCategoryID(Long categoryID) {
      this.categoryID = categoryID;
   }

   public String getName() {
      return name;
   }

   public void setName(String name) {
      this.name = name;
   }

   public String getDescription() {
      return description;
   }

   public void setDescription(String description) {
      this.description = description;
   }

   public String getStatus() {
      return status;
   }

   public void setStatus(String status) {
      this.status = status;
   }

   public Boolean getDeleted() {
      return deleted;
   }

   public void setDeleted(Boolean deleted) {
      this.deleted = deleted;
   }
}
