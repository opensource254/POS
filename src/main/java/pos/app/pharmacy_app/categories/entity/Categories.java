package pos.app.pharmacy_app.categories.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;

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
   private Long categoryID;
   private String name;
   private String description;
   private int status;
   private Boolean deleted=Boolean.FALSE;

   public Categories(String name, String description, int status, Boolean deleted) {
      this.name = name;
      this.description = description;
      this.status = status;
      this.deleted = deleted;
   }
}
