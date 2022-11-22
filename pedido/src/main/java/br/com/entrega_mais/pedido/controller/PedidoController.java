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
    public ResponseEntity<Pedido> GetById (@PathVariable(value = "id") Long id)
    {
        Optional<Pedido> pedido = pedidoService.encontraPedidoPorId(id);
        if(pedido.isPresent())
            return new ResponseEntity<Pedido>(pedido.get(), HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


//    @RequestMapping(value = "/pedidosPorIdTransportadora/{id}", method = RequestMethod.GET)
//    public ResponseEntity<List<Pedido>> GetByIdTransportadora (@PathVariable(value = "id") Long id)
//    {
//        List<Optional<Pedido>> pedidos = pedidoService.encontraPedidoPorIdTransportadora(id);
//
//        if(!pedidos.isEmpty())
//            return new ResponseEntity<List<Pedido>>(pedidos.stream().toList(), HttpStatus.OK);
//        else
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//    }
//
//    @RequestMapping(value = "/pedidosPorEstadoEIdTransportadora/{id}", method = RequestMethod.GET)
//    public ResponseEntity<List<Pedido>> GetByEstadoAndIdTransportadora (@PathVariable(value = "id") Long id)
//    {
//        Optional<Pedido> pedido = pedidoService.encontraPedidoPorId(id);
//        if(pedido.isPresent())
//            return new ResponseEntity<Pedido>(pedido.get(), HttpStatus.OK);
//        else
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//    }
//
//    @RequestMapping(value = "/pedidosPorCidadeEIdTransportadora/{id}", method = RequestMethod.GET)
//    public ResponseEntity<Pedido> GetByCidadeAndIdTransportadora (@PathVariable(value = "id") Long id)
//    {
//        Optional<Pedido> pedido = pedidoService.encontraPedidoPorId(id);
//        if(pedido.isPresent())
//            return new ResponseEntity<Pedido>(pedido.get(), HttpStatus.OK);
//        else
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//    }
//
//    @RequestMapping(value = "/pedidoPorEstadoECidadeEIdTransportadora/{id}", method = RequestMethod.GET)
//    public ResponseEntity<Pedido> GetByEstadoAndCidadeAndIdTransportadora (@PathVariable(value = "id") Long id)
//    {
//        Optional<Pedido> pedido = pedidoService.encontraPedidoPorId(id);
//        if(pedido.isPresent())
//            return new ResponseEntity<Pedido>(pedido.get(), HttpStatus.OK);
//        else
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//    }


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
