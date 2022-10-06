package pos.app.pharmacy_app.categories.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Table(name = "CATEGORIES")
@Entity
@Data
@NoArgsConstructor
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

   public Categories(String name, String description, String status, Boolean deleted) {
      this.name = name;
      this.description = description;
      this.status = status;
      this.deleted = deleted;
   }
}
