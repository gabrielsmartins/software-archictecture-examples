package br.gasmartins.basket.application;

import br.gasmartins.basket.application.web.BasketController;
import br.gasmartins.basket.application.web.mapper.OrderBasketControllerMapper;
import br.gasmartins.basket.application.web.mapper.OrderItemBasketControllerMapper;
import br.gasmartins.basket.application.web.mapper.OrderPaymentMethodBasketControllerMapper;
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

import static br.gasmartins.basket.application.support.OrderDtoSupport.defaultOrderDto;

@ExtendWith(SpringExtension.class)
@WebFluxTest(controllers = BasketController.class)
@Import({OrderBasketControllerMapper.class,
        OrderItemBasketControllerMapper.class,
        OrderPaymentMethodBasketControllerMapper.class})
public class ExceptionHandlerControllerTest {

    @MockBean
    private IBasketService service;

    @Autowired
    private WebTestClient webClient;

    private ObjectMapper mapper;

    @BeforeEach
    public void setup() {
        this.mapper = new ObjectMapper();
        this.mapper.registerModule(new JavaTimeModule());
    }

    @Test
    @DisplayName("Given Order When Is Invalid Then Return Bad Request")
    public void givenOrderWhenIsInvalidThenReturnBadRequest() throws JsonProcessingException {

        var orderDto = defaultOrderDto().build();

        String body = mapper.writeValueAsString(orderDto);

        webClient.post()
                .uri("/orders/v1")
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(body))
                .exchange()
                .expectStatus().isBadRequest()
                .expectBody()
                .jsonPath("fields").isNotEmpty();
    }
}
