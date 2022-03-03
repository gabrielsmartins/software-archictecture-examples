package br.gasmartins.orders.orderservice.infra.persistence.mapper;

import br.gasmartins.orders.orderservice.domain.Order.OrderPaymentMethod;
import br.gasmartins.orders.orderservice.infra.persistence.entity.OrderEntity.OrderPaymentMethodEntity;
import br.gasmartins.orders.orderservice.infra.persistence.entity.enums.PaymentTypeData;
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
                this.destination.setPaymentType(this.source.getId().getPaymentType().getSource());
            }
        });
        return mapper.map(paymentMethodEntity, OrderPaymentMethod.class);
    }

    public OrderPaymentMethodEntity mapToEntity(OrderPaymentMethod paymentMethod){
        var mapper = new ModelMapper();
        mapper.addMappings(new PropertyMap<OrderPaymentMethod, OrderPaymentMethodEntity>() {
            @Override
            protected void configure() {
                this.destination.getId().setPaymentType(PaymentTypeData.fromSource(this.source.getPaymentType()));
            }
        });
        return mapper.map(paymentMethod, OrderPaymentMethodEntity.class);
    }

}
