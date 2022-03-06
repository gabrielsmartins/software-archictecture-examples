package br.gasmartins.orders.orderservice.infra.persistence.mapper;

import br.gasmartins.orders.orderservice.domain.Order.OrderPaymentMethod;
import br.gasmartins.orders.orderservice.domain.enums.PaymentType;
import br.gasmartins.orders.orderservice.infra.persistence.entity.OrderEntity.OrderPaymentMethodEntity;
import br.gasmartins.orders.orderservice.infra.persistence.entity.OrderEntity.OrderPaymentMethodEntity.OrderPaymentMethodEntityId;
import br.gasmartins.orders.orderservice.infra.persistence.entity.enums.PaymentTypeData;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.stereotype.Component;

@Component
public class OrderPaymentMethodPersistenceMapper {

    public OrderPaymentMethod mapToDomain(OrderPaymentMethodEntity paymentMethodEntity){
        var mapper = new ModelMapper();
        mapper.addMappings(new PropertyMap<OrderPaymentMethodEntity, OrderPaymentMethod>() {
            @Override
            protected void configure() {
                using((Converter<OrderPaymentMethodEntityId, PaymentType>) context -> context.getSource().getPaymentType().getSource())
                      .map(this.source.getId(), this.destination.getPaymentType());
            }
        });
        return mapper.map(paymentMethodEntity, OrderPaymentMethod.class);
    }

    public OrderPaymentMethodEntity mapToEntity(OrderPaymentMethod paymentMethod){
        var mapper = new ModelMapper();
        mapper.addMappings(new PropertyMap<OrderPaymentMethod, OrderPaymentMethodEntity>() {
            @Override
            protected void configure() {
                using((Converter<PaymentType, OrderPaymentMethodEntityId>) context -> OrderPaymentMethodEntityId.builder()
                                                                                                                .withPaymentType(PaymentTypeData.fromSource(context.getSource()))
                                                                                                                .build())
                       .map(this.source.getPaymentType(), this.destination.getId());
            }
        });
        return mapper.map(paymentMethod, OrderPaymentMethodEntity.class);
    }

}
