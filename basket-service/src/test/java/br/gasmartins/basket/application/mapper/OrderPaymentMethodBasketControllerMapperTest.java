package br.gasmartins.basket.application.mapper;

import br.gasmartins.basket.application.web.mapper.OrderPaymentMethodBasketControllerMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static br.gasmartins.basket.application.support.OrderDtoSupport.defaultOrderPaymentMethodDto;
import static br.gasmartins.basket.domain.support.OrderSupport.defaultOrderPaymentMethod;
import static org.assertj.core.api.Assertions.assertThat;

public class OrderPaymentMethodBasketControllerMapperTest {

    private OrderPaymentMethodBasketControllerMapper mapper;

    @BeforeEach
    public void setup(){
        this.mapper = new OrderPaymentMethodBasketControllerMapper();
    }

    @Test
    @DisplayName("Given Order Payment Method When Map Then Return Order Payment Method Dto")
    public void givenOrderPaymentMethodWhenMapThenReturnOrderPaymentMethodDto(){

        var orderPaymentMethod = defaultOrderPaymentMethod().build();

        var orderPaymentMethodDto = this.mapper.mapToDto(orderPaymentMethod);

        assertThat(orderPaymentMethodDto).hasNoNullFieldsOrProperties();
        assertThat(orderPaymentMethodDto.getAmount()).isEqualTo(orderPaymentMethod.getAmount());
        assertThat(orderPaymentMethodDto.getPaymentType()).isEqualTo(orderPaymentMethod.getPaymentType().getDescription());
    }

    @Test
    @DisplayName("Given Order Payment Method Dto When Map Then Return Order Payment Method")
    public void givenOrderPaymentMethodDtoWhenMapThenReturnOrderPaymentMethod(){

        var orderPaymentMethodDto = defaultOrderPaymentMethodDto().build();

        var orderPaymentMethod = this.mapper.mapToDomain(orderPaymentMethodDto);

        assertThat(orderPaymentMethod).hasNoNullFieldsOrPropertiesExcept("order");
        assertThat(orderPaymentMethod.getAmount()).isEqualTo(orderPaymentMethodDto.getAmount());
        assertThat(orderPaymentMethod.getPaymentType().getDescription()).isEqualTo(orderPaymentMethodDto.getPaymentType());
    }
}
