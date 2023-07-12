package pos.app.pharmacy_app.orders.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.apache.commons.logging.LogFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pos.app.pharmacy_app.customers.entity.CustomerDetails;
import pos.app.pharmacy_app.customers.repository.CustomerRepository;
import pos.app.pharmacy_app.orders.entity.*;
import pos.app.pharmacy_app.orders.repositories.CartRepositories;
import pos.app.pharmacy_app.products.entity.Products;
import pos.app.pharmacy_app.products.service.ProductService;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
@Transactional
public class CartService {
   private  final Logger log = LoggerFactory.getLogger(CartService.class);
  private final  CartRepositories shoppingCartRepository;
  private final CustomerRepository customerRepository;
  private final ProductService productService;
  private final OrdersService productOrderService;
    public Cart save(Cart shoppingCart) {
        log.debug("Request to save ShoppingCart : {}", shoppingCart);
        return shoppingCartRepository.save(shoppingCart);
    }
    @Transactional(readOnly = true)
    public List<Cart> findAll() {
        log.debug("Request to get all ShoppingCarts");
        return shoppingCartRepository.findAll();
    }
    @Transactional(readOnly = true)
    public Optional<Cart> findOne(Long id) {
        log.debug("Request to get ShoppingCart : {}", id);
        return shoppingCartRepository.findById(id);
    }

    public void delete(Long id) {
        log.debug("Request to delete ShoppingCart : {}", id);
        shoppingCartRepository.deleteById(id);
    }
    public Cart findActiveCartByUser(String user) {
        Optional<Cart> oCart = shoppingCartRepository.findFirstByCustomerDetailsUserLoginAndStatusOrderByCartIdAsc(user, OrderStatus.OPEN);
        Cart activeCart = oCart.orElseGet(() -> {
            Optional<CustomerDetails> customer = customerRepository.findOneByUserLogin(user);
            return shoppingCartRepository.save(new Cart(
                    Instant.now(), OrderStatus.OPEN, BigDecimal.ZERO, PaymentMethod.CREDIT_CARD, customer.get()
            ));
        });
        // also serves as lazy init of orders
        log.info("Cart for user {} has {} orders", user, activeCart.getOrders().size());
        return activeCart;
    }
    @Transactional(readOnly = true)
    public List<Cart> findCartsByUser(String user) {
        return shoppingCartRepository.findAllByCustomerDetailsUserLoginAndStatusNot(user, OrderStatus.OPEN);
    }

    public Cart addProductForUser(Long id, String user) throws EntityNotFoundException {
        Cart activeCart = findActiveCartByUser(user);
        Products product = productService.getProductById(id);
        Orders order;
        List<Orders> orders = activeCart.getOrders().stream().filter(productOrder -> productOrder.getProductID().getProductId().equals(id)).collect(Collectors.toList());
        if (orders.isEmpty()) {
            order = new Orders();
            order.setQuantity(1);
            order.setGrandTotal(product.getPrice());
            order.setProductID(product);
            order.setCart(activeCart);
            activeCart.addOrder(order);
        } else {
            order = orders.get(0);
            order.setQuantity(order.getQuantity() + 1);
            order.setGrandTotal(product.getPrice().multiply(new BigDecimal(order.getQuantity())));
        }
        activeCart.calculateTotalPrice();
        productOrderService.save(order);
        return shoppingCartRepository.save(activeCart);
    }
    public Cart removeProductOrderForUser(final Long id, final String user) {
        Cart activeCart = findActiveCartByUser(user);
        List<Orders> orders = activeCart.getOrders().stream().filter(productOrder -> productOrder.getProductID().equals(id)).collect(Collectors.toList());
        if (orders.isEmpty()) {
            throw new EntityNotFoundException("Product order not found in cart");
        } else {
            Orders order = orders.get(0);
            activeCart.removeOrder(order);
            productOrderService.delete(order.getOrderID());
        }
        return shoppingCartRepository.save(activeCart);
    }
    public Cart updateCartForUser(final String user, final String paymentType, final String paymentRef, OrderStatus status) {
        Cart activeCart = findActiveCartByUser(user);
        activeCart.setStatus(status);
        activeCart.setPaymentReference(paymentRef);
        activeCart.setPaymentMethod(PaymentMethod.fromLabel(paymentType));
        return shoppingCartRepository.save(activeCart);
    }
    public Optional<Cart> findOneByPaymentModificationReference(final String paymentRef) {
        return shoppingCartRepository.findOneByPaymentModificationReference(paymentRef);
    }

    public Optional<Cart> findOneByPaymentReference(final String paymentRef) {
        return shoppingCartRepository.findOneByPaymentReference(paymentRef);
    }
}
