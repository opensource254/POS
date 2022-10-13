package pos.app.pharmacy_app.products.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.hibernate.annotations.Filter;
import org.hibernate.annotations.FilterDef;
import org.hibernate.annotations.ParamDef;
import org.hibernate.annotations.SQLDelete;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;
@Table(name = "PRODUCTS")
@Entity
@Data
@NoArgsConstructor
@SQLDelete(sql = "UPDATE ORDERS SET deleted = true WHERE productId=?")
@FilterDef(name = "deletedProductsFilter", parameters = @ParamDef(name = "isDeleted", type = "boolean"))
@Filter(name = "deletedProductsFilter", condition = "deleted = :isDeleted")
public class Products {
   @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "PRODUCT_SEQ")
   @SequenceGenerator(name = "PRODUCT_SEQ", sequenceName = "\"Product_Seq\"",allocationSize = 1)
   @Id
   @Column(name = "PRODUCT_ID")
   @NonNull
   private  Long productId;
   @Column(name = "PRODUCT_NAME")
   private  String productName;
   @Column(name = "BBRAND")
   private  String brand;
   @Column(name = "DESCRIPTION")
   private  String description;
   @Column(name = "WEIGHT")
   private  String weight;
   @
   Column(name = "PRODUCT_CODE")
   private String productCode;
   @Column(name = "PRICE")
   private  BigDecimal price;
   @Column(name = "STATUS")
   private  String status="Active";
   private  Boolean deleted=Boolean.FALSE;

   public Products(String productName, String brand, String description, String weight,
                   BigDecimal price, String status, Boolean deleted) {
      this.productName = productName;
      this.brand = brand;
      this.description = description;
      this.weight = weight;
      this.price = price;
      this.status = status;
      this.deleted = deleted;
   }
}
