package br.gasmartins.orders.orderservice.infra.persistence.adapter;

import br.gasmartins.orders.orderservice.infra.persistence.mapper.OrderItemPersistenceMapper;
import br.gasmartins.orders.orderservice.infra.persistence.mapper.OrderLogPersistenceMapper;
import br.gasmartins.orders.orderservice.infra.persistence.mapper.OrderPaymentMethodPersistenceMapper;
import br.gasmartins.orders.orderservice.infra.persistence.mapper.OrderPersistenceMapper;
import br.gasmartins.orders.orderservice.infra.persistence.service.IOrderPersistenceService;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static br.gasmartins.orders.orderservice.domain.support.OrderSupport.defaultOrder;
import static br.gasmartins.orders.orderservice.infra.persistence.support.OrderEntitySupport.defaultOrderEntity;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@Import({OrderRepositoryPersistenceAdapter.class,
        OrderPersistenceMapper.class,
        OrderItemPersistenceMapper.class,
        OrderLogPersistenceMapper.class,
        OrderPaymentMethodPersistenceMapper.class})
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class OrderRepositoryPersistenceAdapterTest {

    @InjectMocks
    private final OrderRepositoryPersistenceAdapter adapter;

    @MockBean
    public IOrderPersistenceService service;

    @Test
    @DisplayName("Given Order Id When Exists Then Return Order")
    public void givenOrderIdWhenExistsThenReturnOrder(){
        var order = defaultOrderEntity().build();
        when(this.service.findById(anyLong())).thenReturn(Optional.of(order));
        var optionalOrder = this.adapter.findById(1L);
        assertThat(optionalOrder).isPresent();
    }

    @Test
    @DisplayName("Given Order When Save Then Return Saved Order")
    public void givenOrderWhenSaveThenReturnSavedOrder(){
        var orderEntity = defaultOrderEntity().build();
        when(this.service.save(orderEntity)).thenAnswer(invocation -> invocation.getArgument(0));

        var order = defaultOrder().build();
        var savedOrder = this.adapter.save(order);
        assertThat(savedOrder).isNotNull();
    }




}
