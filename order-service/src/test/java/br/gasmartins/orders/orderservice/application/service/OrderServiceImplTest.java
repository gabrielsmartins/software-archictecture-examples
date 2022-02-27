package br.gasmartins.orders.orderservice.application.service;

import br.gasmartins.orders.orderservice.application.OrderService;
import br.gasmartins.orders.orderservice.domain.repository.OrderRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static br.gasmartins.orders.orderservice.domain.support.OrderSupport.defaultOrder;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

public class OrderServiceImplTest {

    private OrderService service;
    private OrderRepository repository;

    @BeforeEach
    public void setUp() {
        this.repository = mock(OrderRepository.class);
        this.service = new OrderServiceImpl(this.repository);
    }

    @Test
    @DisplayName("Given Order When Create Then Return Created Order")
    public void givenOrderWhenCreateThenReturnCreatedOrder(){
        var order = defaultOrder().build();
        var createdOrder = this.service.create(order);
        assertThat(createdOrder).isNotNull();
    }


}