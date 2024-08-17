package homework_7.paymentscore.repository;

import homework_7.paymentscore.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    @Override
    @NonNull
    @Query("SELECT new Product(products.id, products.account, products.balance, " +
            "products.productTypeId, products.userId, users.username, product_types.name) " +
            "FROM Product products " +
            "LEFT OUTER JOIN User users " +
            "ON products.userId = users.id " +
            "LEFT OUTER JOIN ProductType product_types " +
            "ON products.productTypeId = product_types.id")
    List<Product> findAll();

    @Override
    @NonNull
    @Query("SELECT new Product(products.id, products.account, products.balance, " +
            "products.productTypeId, products.userId, users.username, product_types.name) " +
            "FROM Product products " +
            "LEFT OUTER JOIN User users " +
            "ON products.userId = users.id " +
            "LEFT OUTER JOIN ProductType product_types " +
            "ON products.productTypeId = product_types.id " +
            "WHERE products.id=:id")
    Optional<Product> findById(@NonNull @Param("id") Long id);

    @NonNull
    @Query("SELECT new Product(products.id, products.account, products.balance, " +
            "products.productTypeId, products.userId, users.username, product_types.name) " +
            "FROM Product products " +
            "LEFT OUTER JOIN User users " +
            "ON products.userId = users.id " +
            "LEFT OUTER JOIN ProductType product_types " +
            "ON products.productTypeId = product_types.id " +
            "WHERE userId=:id")
    ArrayList<Product> getProductsByUserId(@NonNull @Param("id") Long id);

    @NonNull
    @Query("SELECT new Product(products.id, products.account, products.balance, " +
            "products.productTypeId, products.userId, users.username, product_types.name) " +
            "FROM Product products " +
            "LEFT OUTER JOIN User users " +
            "ON products.userId = users.id " +
            "LEFT OUTER JOIN ProductType product_types " +
            "ON products.productTypeId = product_types.id " +
            "WHERE products.id=:id AND userId=:userid")
    Product getProductsByIdAndUserId(@NonNull @Param("id") Long id, @NonNull @Param("userid") Long userid);

    @NonNull
    @Transactional
    @Modifying(clearAutomatically = true)
    @Query("UPDATE Product products " +
            "SET balance=:balance " +
            "WHERE id=:id AND userId=:userid")
    void setProductBalance(@NonNull @Param("id") Long id, @NonNull @Param("userid") Long userId, @NonNull @Param("balance") BigDecimal balance);
}
