package br.com.entrega_mais.pedido.repository;

import br.com.entrega_mais.pedido.model.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long> {

    Optional<Pedido> findById(Long id);
    List<Optional<Pedido>> findByIdTransportadora(Long idTransportadora);
    List<Optional<Pedido>> findByEstadoAndIdTransportadora(String estado, Long idTransportadora);
    List<Optional<Pedido>> findByCidadeAndIdTransportadora(String cidade, Long idTransportadora);
    List<Optional<Pedido>> findByEstadoAndCidadeAndIdTransportadora(String estado, String cidade, Long idTransportadora);
    List<Optional<Pedido>> findByStatusAndIdTransportadora(String status, Long idTransportadora);
    List<Optional<Pedido>> findByFormaPagAndIdTransportadora(String formaPag, Long idTransportadora);
    List<Optional<Pedido>> findByIdVeiculoAndIdTransportadora(Long idVeiculo, Long idTransportadora);
    List<Optional<Pedido>> findByIdDespachanteAndIdTransportadora(Long idDespachante, Long idTransportadora);
}
