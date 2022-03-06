package br.gasmartins.orders.orderservice.infra.persistence.service;

import br.gasmartins.orders.orderservice.infra.persistence.repository.OrderEntityRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static br.gasmartins.orders.orderservice.infra.persistence.support.OrderEntitySupport.defaultOrderEntity;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class OrderPersistenceServiceTest {

    private OrderPersistenceService service;
    private OrderEntityRepository repository;

    @BeforeEach
    public void setup(){
        this.repository = mock(OrderEntityRepository.class);
        this.service = new OrderPersistenceService(this.repository);
    }

    @Test
    @DisplayName("Given Order Id When Exists Then Return Order")
    public void givenOrderIdWhenExistsThenReturnOrder(){
        var order = defaultOrderEntity().build();
        when(this.repository.findById(anyLong())).thenReturn(Optional.of(order));
        var optionalOrder = this.service.findById(1L);
        assertThat(optionalOrder).isPresent();
    }

    @Test
    @DisplayName("Given Order When Save Then Return Saved Order")
    public void givenOrderWhenSaveThenReturnSavedOrder(){
        var order = defaultOrderEntity().build();
        when(this.repository.saveAndFlush(order)).thenAnswer(invocation -> invocation.getArgument(0));
        var savedOrder = this.service.save(order);
        assertThat(savedOrder).isNotNull();
    }
}
