package br.gasmartins.basket.application.web.mapper;

import br.gasmartins.basket.application.web.dto.OrderDto.OrderPaymentMethodDto;
import br.gasmartins.basket.domain.Order.OrderPaymentMethod;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.stereotype.Component;

@Component
public class OrderPaymentMethodBasketControllerMapper {

    public OrderPaymentMethodDto mapToDto(OrderPaymentMethod paymentMethod){
        var mapper = new ModelMapper();
        return mapper.map(paymentMethod, OrderPaymentMethodDto.class);
    }

    public OrderPaymentMethod mapToDomain(OrderPaymentMethodDto paymentMethodDto){
        var mapper = new ModelMapper();
        mapper.addMappings(new PropertyMap<OrderPaymentMethodDto, OrderPaymentMethod>() {
            @Override
            protected void configure() {
                skip(this.destination.getOrder());
            }
        });
        return mapper.map(paymentMethodDto, OrderPaymentMethod.class);
    }
}
