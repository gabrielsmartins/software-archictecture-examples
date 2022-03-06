package br.gasmartins.orders.orderservice.infra.persistence.repository;

import br.gasmartins.orders.orderservice.PersistenceConfig;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static br.gasmartins.orders.orderservice.infra.persistence.support.OrderEntitySupport.*;
import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@ContextConfiguration(classes= PersistenceConfig.class)
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class OrderEntityRepositoryTest {

    private final OrderEntityRepository repository;

    @Test
    @DisplayName("Given Order Id When Exists Then Return Order")
    public void givenOrderIdWhenExistsThenReturnOrder(){
        var order = defaultOrderEntity().build();
        this.repository.save(order);
        var optionalOrder = this.repository.findById(1L);
        assertThat(optionalOrder).isPresent();
    }

    @Test
    @DisplayName("Given Order When Save Then Return Saved Order")
    public void givenOrderWhenSaveThenReturnSavedOrder(){
        var order = defaultOrderEntity().build();
        var orderLog = defaultOrderLogEntity().build();
        order.addLog(orderLog);

        var orderItem = defaultOrderItemEntity().build();
        order.addItem(orderItem);

        var paymentMethod = defaultOrderPaymentMethodEntity().build();
        order.addPaymentMethod(paymentMethod);

        var savedOrder = this.repository.save(order);
        assertThat(savedOrder).isNotNull();
    }
}
