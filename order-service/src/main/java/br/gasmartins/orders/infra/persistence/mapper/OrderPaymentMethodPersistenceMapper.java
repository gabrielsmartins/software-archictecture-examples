package br.gasmartins.orders.infra.persistence.mapper;

import br.gasmartins.orders.domain.enums.PaymentType;
import br.gasmartins.orders.infra.persistence.entity.OrderEntity;
import br.gasmartins.orders.infra.persistence.entity.enums.PaymentTypeData;
import br.gasmartins.orders.domain.Order.OrderPaymentMethod;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.stereotype.Component;

@Component
public class OrderPaymentMethodPersistenceMapper {

    public OrderPaymentMethod mapToDomain(OrderEntity.OrderPaymentMethodEntity paymentMethodEntity){
        var mapper = new ModelMapper();
        mapper.addMappings(new PropertyMap<OrderEntity.OrderPaymentMethodEntity, OrderPaymentMethod>() {
            @Override
            protected void configure() {
                using((Converter<OrderEntity.OrderPaymentMethodEntity.OrderPaymentMethodEntityId, PaymentType>) context -> context.getSource().getPaymentType().getSource())
                      .map(this.source.getId(), this.destination.getPaymentType());
            }
        });
        return mapper.map(paymentMethodEntity, OrderPaymentMethod.class);
    }

    public OrderEntity.OrderPaymentMethodEntity mapToEntity(OrderPaymentMethod paymentMethod){
        var mapper = new ModelMapper();
        mapper.addMappings(new PropertyMap<OrderPaymentMethod, OrderEntity.OrderPaymentMethodEntity>() {
            @Override
            protected void configure() {
                using((Converter<PaymentType, OrderEntity.OrderPaymentMethodEntity.OrderPaymentMethodEntityId>) context -> OrderEntity.OrderPaymentMethodEntity.OrderPaymentMethodEntityId.builder()
                                                                                                                .withPaymentType(PaymentTypeData.fromSource(context.getSource()))
                                                                                                                .build())
                       .map(this.source.getPaymentType(), this.destination.getId());
            }
        });
        return mapper.map(paymentMethod, OrderEntity.OrderPaymentMethodEntity.class);
    }

}
