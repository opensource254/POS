package pos.app.pharmacy_app.suppliers.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Filter;
import org.hibernate.annotations.FilterDef;
import org.hibernate.annotations.ParamDef;
import org.hibernate.annotations.SQLDelete;

import javax.persistence.*;

@Table(name = "SUPPLIERS")
@Entity
@Data
@NoArgsConstructor
@SQLDelete(sql = "UPDATE SUPPLIERS SET isDeleted = true WHERE SUPPLIER_ID=?")
@FilterDef(name = "deletedOrdersFilter", parameters = @ParamDef(name = "isDeleted", type = "boolean"))
@Filter(name = "deletedOrdersFilter", condition = "isDeleted = :isDeleted")

public class Suppliers {
    @Id
    @Column(name = "SUPPLIER_ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SUPPLIERS_SEQ")
    @SequenceGenerator(sequenceName = "supplier_seq", allocationSize = 1, name = "SUPPLIERS_SEQ")
    private Long supplierId;
    @Column(name = "NAME")
    private String name;
    @Column(name = "PHONE")
    private String phone;
    @Column(name = "STATUS")
    private String status="Active";
    @
    Column(name = "ISDELETED")
    private Boolean isDeleted=Boolean.FALSE;

    public Suppliers(String name, String phone, String status, Boolean isDeleted) {
        this.name = name;
        this.phone = phone;
        this.status = status;
        this.isDeleted = isDeleted;
    }
}
