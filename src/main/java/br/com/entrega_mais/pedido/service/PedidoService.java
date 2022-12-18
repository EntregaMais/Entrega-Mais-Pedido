package br.com.entrega_mais.pedido.service;

import br.com.entrega_mais.pedido.model.Pedido;
import br.com.entrega_mais.pedido.repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

	@Cacheable(cacheNames = "Pedido", key="#id")
    public Optional<Pedido> encontraPedidoPorId(Long id){
        return pedidoRepository.findById(id);
    }

	//@Cacheable(cacheNames = "Pedido", key="#idTransportadora")
    public List<Optional<Pedido>> encontraPedidosPorIdTransportadora(Long idTransportadora){
        return pedidoRepository.findByIdTransportadora(idTransportadora);
    }


    public List<Optional<Pedido>> encontraPedidosPorEstadoEIdTransportadora(String estado, Long idTransportadora){
        return pedidoRepository.findByEstadoAndIdTransportadora(estado,idTransportadora);
    }


    public List<Optional<Pedido>> encontraPedidosPorCidadeEIdTransportadora(String cidade, Long idTransportadora){
        return pedidoRepository.findByCidadeAndIdTransportadora(cidade, idTransportadora);
    }

    public List<Optional<Pedido>> encontraPedidosPorEstadoECidadeEIdTransportadora(String estado, String cidade, Long idTransportadora){
        return pedidoRepository.findByEstadoAndCidadeAndIdTransportadora(estado, cidade, idTransportadora);
    }

    public List<Optional<Pedido>> encontraPedidosPorStatusEIdTransportadora(String status, Long idTransportadora){
        return pedidoRepository.findByStatusAndIdTransportadora(status, idTransportadora);
    }

    public List<Optional<Pedido>> encontraPedidosPorFormaPagEIdTransportadora(String formaPag, Long idTransportadora){
        return pedidoRepository.findByFormaPagAndIdTransportadora(formaPag, idTransportadora);
    }

    public List<Optional<Pedido>> encontraPedidosPorIdVeiculoEIdTransportadora(Long idVeiculo, Long idTransportadora){
        return pedidoRepository.findByIdVeiculoAndIdTransportadora(idVeiculo, idTransportadora);
    }

    public List<Optional<Pedido>> encontraPedidosPorIdDespachanteEIdTransportadora(Long idDespachante, Long idTransportadora){
        return pedidoRepository.findByIdDespachanteAndIdTransportadora(idDespachante, idTransportadora);
    }


    @Transactional
	@CacheEvict(cacheNames = "Pedido", allEntries = true)
    public Pedido salvarPedido (Pedido pedido){
        return pedidoRepository.save(pedido);
    }

    @Transactional
	@CacheEvict(cacheNames = "Pedido", allEntries = true)
    public Pedido atualizarPedido(Long id, Pedido pedido_atualizado) {

        if (pedidoRepository.findById(id).isPresent()){
            Pedido pedidoExistente = pedidoRepository.findById(id).get();

            pedidoExistente.setFrete(pedido_atualizado.getFrete());
            pedidoExistente.setValorTotal(pedido_atualizado.getValorTotal());
            pedidoExistente.setEstado(pedido_atualizado.getEstado());
            pedidoExistente.setCidade(pedido_atualizado.getCidade());
            pedidoExistente.setFornPagouFrete(pedido_atualizado.getFornPagouFrete());
            pedidoExistente.setQuemPagaTaxa(pedido_atualizado.getQuemPagaTaxa());
            pedidoExistente.setNmCliente(pedido_atualizado.getNmCliente());
            pedidoExistente.setTelefoneCli(pedido_atualizado.getTelefoneCli());
            pedidoExistente.setEmailCli(pedido_atualizado.getEmailCli());
            pedidoExistente.setFormaPag(pedido_atualizado.getFormaPag());
            pedidoExistente.setStatus(pedido_atualizado.getStatus());
            pedidoExistente.setIdVeiculo(pedido_atualizado.getIdVeiculo());
            pedidoExistente.setIdDespachante(pedido_atualizado.getIdDespachante());

            Pedido pedidoAtualizado = pedidoRepository.save(pedidoExistente);

            return pedidoExistente;

        }else{
            return null;
        }
    }
}
