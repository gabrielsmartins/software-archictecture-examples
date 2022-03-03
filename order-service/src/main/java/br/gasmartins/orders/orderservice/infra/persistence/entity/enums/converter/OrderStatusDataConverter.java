package br.gasmartins.orders.orderservice.infra.persistence.entity.enums.converter;

import br.gasmartins.orders.orderservice.infra.persistence.entity.enums.OrderStatusData;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.Optional;

@Converter(autoApply = true)
public class OrderStatusDataConverter implements AttributeConverter<OrderStatusData, Integer> {

    @Override
    public Integer convertToDatabaseColumn(OrderStatusData orderStatusData) {
        return Optional.ofNullable(orderStatusData)
                       .map(OrderStatusData::getCode)
                       .orElse(null);
    }

    @Override
    public OrderStatusData convertToEntityAttribute(Integer code) {
        return OrderStatusData.fromCode(code);
    }
}
