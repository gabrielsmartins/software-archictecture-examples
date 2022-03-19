package br.gasmartins.orders.domain.publisher;

import br.gasmartins.orders.domain.Order;

public interface OrderDispatcher {

    void dispatch(Order order);

}
