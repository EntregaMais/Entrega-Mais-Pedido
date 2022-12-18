package br.com.entrega_mais.pedido.controller;

import br.com.entrega_mais.pedido.model.Pedido;
import br.com.entrega_mais.pedido.service.PedidoService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.google.gson.Gson;
import lombok.SneakyThrows;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;


import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/api/pedido")
public class PedidoController {

    @GetMapping("/ping")
    public String ping() {
        return "Pong";
    }
    @Autowired
    private PedidoService pedidoService;

    @PostMapping("/salvar")
    @SneakyThrows
    public ResponseEntity<Pedido> salvar (@RequestBody Pedido pedido){
        return ResponseEntity.ok(pedidoService.salvarPedido(pedido));
    }

    @RequestMapping(value = "/pedidoPorId/{id}", method = RequestMethod.GET)
    public ResponseEntity<Pedido> GetById (@PathVariable(value = "id") Long id) {
        Optional<Pedido> pedido = pedidoService.encontraPedidoPorId(id);
        return pedido.map(value -> new ResponseEntity<>(value, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @RequestMapping(value = "/pedidosPorIdTransportadora/{id}", method = RequestMethod.GET)
    public ResponseEntity<List<Pedido>> getByIdTransportadora (@PathVariable(value = "id") Long idTransportadora) {
        
        List<Optional<Pedido>> pedidos = pedidoService.encontraPedidosPorIdTransportadora(idTransportadora);
        List<Pedido> pedidosReal = pedidos.stream().map(Optional::get).collect(Collectors.toList());

        if(!pedidos.isEmpty())
            return new ResponseEntity<>(pedidosReal, HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value = "/pedidosPorEstadoEIdTransportadora/{estado}/{id}", method = RequestMethod.GET)
    public ResponseEntity<List<Pedido>> GetByIdTransportadoraAndEstado(@PathVariable(value = "id") Long idTransportadora, @PathVariable(value = "estado") String estado)
    {
        List<Optional<Pedido>> pedidos = pedidoService.encontraPedidosPorEstadoEIdTransportadora(estado, idTransportadora);
        List<Pedido> pedidosReal = pedidos.stream().map(Optional::get).collect(Collectors.toList());

        if(!pedidos.isEmpty())
            return new ResponseEntity<>(pedidosReal, HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value = "/pedidosPorCidadeEIdTransportadora/{cidade}/{id}", method = RequestMethod.GET)
    public ResponseEntity<List<Pedido>> GetByCidadeAndIdTransportadora (@PathVariable(value = "id") Long idTransportadora, @PathVariable(value = "cidade") String cidade)
    {
        List<Optional<Pedido>> pedidos = pedidoService.encontraPedidosPorCidadeEIdTransportadora(cidade, idTransportadora);
        List<Pedido> pedidosReal = pedidos.stream().map(Optional::get).collect(Collectors.toList());

        if(!pedidos.isEmpty())
            return new ResponseEntity<>(pedidosReal, HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value = "/pedidosPorEstadoECidadeEIdTransportadora/{estado}/{cidade}/{id}", method = RequestMethod.GET)
    public ResponseEntity<List<Pedido>> GetByEstadoAndCidadeAndIdTransportadora (@PathVariable(value = "id") Long idTransportadora, @PathVariable(value = "cidade") String cidade, @PathVariable(value="estado") String estado)
    {
        List<Optional<Pedido>> pedidos = pedidoService.encontraPedidosPorEstadoECidadeEIdTransportadora(estado, cidade, idTransportadora);
        List<Pedido> pedidosReal = pedidos.stream().map(Optional::get).collect(Collectors.toList());

        if(!pedidos.isEmpty())
            return new ResponseEntity<>(pedidosReal, HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value = "/pedidosPorStatusEIdTransportadora/{status}/{id}", method = RequestMethod.GET)
    public ResponseEntity<List<Pedido>> GetByStatusAndIdTransportadora (@PathVariable(value = "status") String status, @PathVariable(value = "id") Long idTransportadora)
    {
        List<Optional<Pedido>> pedidos = pedidoService.encontraPedidosPorStatusEIdTransportadora(status, idTransportadora);
        List<Pedido> pedidosReal = pedidos.stream().map(Optional::get).collect(Collectors.toList());

        if(!pedidos.isEmpty())
            return new ResponseEntity<>(pedidosReal, HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value = "/pedidosPorFormaPagEIdTransportadora/{formapag}/{transportadora}", method = RequestMethod.GET)
    public ResponseEntity<List<Pedido>> GetByFormaPagAndIdTransportadora ( @PathVariable(value = "formapag") String formapag, @PathVariable(value = "id") Long idTransportadora)
    {
        List<Optional<Pedido>> pedidos = pedidoService.encontraPedidosPorFormaPagEIdTransportadora(formapag, idTransportadora);
        List<Pedido> pedidosReal = pedidos.stream().map(Optional::get).collect(Collectors.toList());

        if(!pedidos.isEmpty())
            return new ResponseEntity<>(pedidosReal, HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value = "/pedidosPorIdVeiculoEIdTransportadora/{veiculo}/{transportadora}", method = RequestMethod.GET)
    public ResponseEntity<List<Pedido>> GetByIdVeiculoAndIdTransportadora (@PathVariable(value = "veiculo") Long idVeiculo, @PathVariable(value = "transportadora") Long  idTransportadora)
    {
        List<Optional<Pedido>> pedidos = pedidoService.encontraPedidosPorIdVeiculoEIdTransportadora(idVeiculo, idTransportadora);
        List<Pedido> pedidosReal = pedidos.stream().map(Optional::get).collect(Collectors.toList());

        if(!pedidos.isEmpty())
            return new ResponseEntity<>(pedidosReal, HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value = "/pedidosPorIdDespachanteEIdTransportadora/{despachante}/{transportadora}", method = RequestMethod.GET)
    public ResponseEntity<List<Pedido>> GetByDespachanteAndIdTransportadora (@PathVariable(value = "despachante") Long idDespachante, @PathVariable(value = "transportadora") Long idTransportadora)
    {
        List<Optional<Pedido>> pedidos = pedidoService.encontraPedidosPorIdDespachanteEIdTransportadora(idDespachante, idTransportadora);
        List<Pedido> pedidosReal = pedidos.stream().map(Optional::get).collect(Collectors.toList());

        if(!pedidos.isEmpty())
            return new ResponseEntity<>(pedidosReal, HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/pedidoEdicao/{id}/")
    public ResponseEntity<Pedido> editar (@PathVariable(value = "id") Long id, @RequestBody Pedido pedido){

        Pedido pedidoAtualizada = pedidoService.atualizarPedido(id, pedido);

        if(pedido != null) {
            return new ResponseEntity<Pedido>(pedidoAtualizada, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

    @GetMapping("/ok")
    public ResponseEntity<String> testandoAPi() {
        return ResponseEntity.ok("ok");
    }


}
