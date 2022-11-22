package br.com.entrega_mais.pedido.service;

import br.com.entrega_mais.pedido.model.Pedido;
import br.com.entrega_mais.pedido.repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Cacheable("pedidoatual")
    public Optional<Pedido> encontraPedidoPorId(Long id){
        return pedidoRepository.findById(id);
    }

    @Cacheable("pedidoatual")
    public List<Optional<Pedido>> encontraPedidoPorIdTransportadora(Long idTransportadora){
        return pedidoRepository.findByIdTransportadora(idTransportadora);
    }

    @Cacheable("pedidoatual")
    public List<Optional<Pedido>> encontraPedidoPorEstadoEIdTransportadora(String estado, Long idTransportadora){
        return pedidoRepository.findByEstadoAndIdTransportadora(estado,idTransportadora);
    }

    @Cacheable("pedidoatual")
    public List<Optional<Pedido>> encontraPedidoPorCidadeEIdTransportadora(String cidade, Long idTransportadora){
        return pedidoRepository.findByCidadeAndIdTransportadora(cidade, idTransportadora);
    }

    @Cacheable("pedidoatual")
    public List<Optional<Pedido>> encontraPedidoPorEstadoECidadeEIdTransportadora(String estado, String cidade, Long idTransportadora){
        return pedidoRepository.findByEstadoAndCidadeAndIdTransportadora(estado, cidade, idTransportadora);
    }

    @CacheEvict("pedidoatual")
    @Transactional
    public Pedido salvarPedido (Pedido pedido){
        return pedidoRepository.save(pedido);
    }

    @Transactional
    @CacheEvict("pedidoatual")
    public Pedido atualizarPedido(Long id, Pedido pedido_atualizado) {

        if (pedidoRepository.findById(id).isPresent()){
            Pedido pedidoExistente = pedidoRepository.findById(id).get();

            pedidoExistente.setFrete(pedido_atualizado.getFrete());
            pedidoExistente.setValorTotal(pedido_atualizado.getValorTotal());
            pedidoExistente.setNmCliente(pedido_atualizado.getNmCliente());
            pedidoExistente.setEstado(pedido_atualizado.getEstado());
            pedidoExistente.setCidade(pedido_atualizado.getCidade());
            pedidoExistente.setObservacao(pedido_atualizado.getObservacao());
            pedidoExistente.setFornPagouFrete(pedido_atualizado.getFornPagouFrete());
            pedidoExistente.setQuemPagaTaxa(pedido_atualizado.getQuemPagaTaxa());

            Pedido pedidoAtualizado = pedidoRepository.save(pedidoExistente);

            return pedidoExistente;

        }else{
            return null;
        }
    }
}
