package com.codewithmaggnity.store.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codewithmaggnity.store.model.Pedido;
import com.codewithmaggnity.store.model.Prato;
import com.codewithmaggnity.store.repository.PedidoRepository;
import com.codewithmaggnity.store.repository.PratoRepository;
import com.codewithmaggnity.store.service.PedidoService;

@RestController
@RequestMapping("/api")
public class MenuController {

    @Autowired
    private PratoRepository pratoRepo;
    @Autowired
    private PedidoRepository pedidoRepo;

    private final PedidoService pedidoService;

    public MenuController(PedidoService pedidoService) {
        this.pedidoService = pedidoService;
    }

    @GetMapping("/pratos")
    public List<Prato> listarPratos() {

        return pratoRepo.findAll();

        // return pedidoService.listarPratos();
    }

    @PostMapping("/pedido")
    public ResponseEntity<?> adicionarPedido() {

        Pedido pedido = new Pedido();

        pedido.setStatus("aberto");
        pedidoRepo.save((pedido));

        return ResponseEntity.ok(pedido);

    }

    @PostMapping("/pedido/{pedidoId}/add/{pratoId}")
    public ResponseEntity<Pedido> adicionarPrato(@PathVariable Long pedidoId, @PathVariable Long pratoId) {

        return ResponseEntity.ok(pedidoService.adicionarPrato(pedidoId, pratoId));

    };

    @PostMapping("/pedido/{pedidoId}/finalizar")
    public ResponseEntity<?> finalizarPedido(@PathVariable Long pedidoId) {

        return ResponseEntity.ok(pedidoService.finalizarPedido(pedidoId));

    }
}