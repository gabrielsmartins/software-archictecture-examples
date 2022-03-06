package br.gasmartins.orders.infra.persistence.repository;

import br.gasmartins.orders.infra.persistence.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderEntityRepository extends JpaRepository<OrderEntity, Long> {
}
