package br.gasmartins.orders.infra.messaging.mapper;

import br.gasmartins.orders.domain.Order;
import br.gasmartins.orders.domain.enums.OrderStatus;
import org.apache.avro.specific.SpecificRecord;

public interface OrderProducerMapper<T extends SpecificRecord>{

    SpecificRecord mapToMessage(Order order);

    OrderStatus getStatus();

}
