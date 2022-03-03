package br.gasmartins.orders.orderservice.infra.persistence.entity.enums.converter;

import br.gasmartins.orders.orderservice.infra.persistence.entity.enums.PaymentTypeData;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.Optional;

@Converter(autoApply = true)
public class PaymentTypeDataConverter implements AttributeConverter<PaymentTypeData, Integer> {

    @Override
    public Integer convertToDatabaseColumn(PaymentTypeData paymentTypeData) {
        return Optional.ofNullable(paymentTypeData)
                        .map(PaymentTypeData::getCode)
                        .orElse(null);
    }

    @Override
    public PaymentTypeData convertToEntityAttribute(Integer code) {
        return PaymentTypeData.fromCode(code);
    }

}
