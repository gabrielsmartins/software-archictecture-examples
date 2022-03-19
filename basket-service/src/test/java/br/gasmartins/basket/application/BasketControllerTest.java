package br.gasmartins.basket.application;

import br.gasmartins.basket.application.web.BasketController;
import br.gasmartins.basket.application.web.mapper.OrderBasketControllerMapper;
import br.gasmartins.basket.application.web.mapper.OrderItemBasketControllerMapper;
import br.gasmartins.basket.application.web.mapper.OrderPaymentMethodBasketControllerMapper;
import br.gasmartins.basket.domain.Order;
import br.gasmartins.basket.domain.service.IBasketService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;
import reactor.core.publisher.Mono;

import static br.gasmartins.basket.application.support.OrderDtoSupport.*;
import static br.gasmartins.basket.domain.support.OrderSupport.defaultOrder;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
@WebFluxTest(controllers = BasketController.class)
@Import({OrderBasketControllerMapper.class,
        OrderItemBasketControllerMapper.class,
        OrderPaymentMethodBasketControllerMapper.class})
public class BasketControllerTest {

    @MockBean
    private IBasketService service;

    @Autowired
    private WebTestClient webClient;

    private ObjectMapper mapper;

    @BeforeEach
    public void setup(){
        this.mapper = new ObjectMapper();
        this.mapper.registerModule(new JavaTimeModule());
    }

    @Test
    @DisplayName("Given Order When Create Then Return Created Order")
    public void givenOrderWhenCreateThenReturnCreatedOrder() throws JsonProcessingException {

        var orderDto = defaultOrderDto().build();
        var itemDto = defaultOrderItemDto().build();
        var paymentMethodDto = defaultOrderPaymentMethodDto().build();

        orderDto.addItem(itemDto);
        orderDto.addPaymentMethod(paymentMethodDto);

        String body = mapper.writeValueAsString(orderDto);

        var order = defaultOrder().build();
        when(service.create(any(Order.class))).thenAnswer(invocation -> Mono.just(order));

        webClient.post()
                .uri("/orders/v1")
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(body))
                .exchange()
                .expectStatus().isCreated()
                .expectBody()
                .jsonPath("id").isNotEmpty();

        verify(this.service, times(1)).create(any(Order.class));
    }


}
