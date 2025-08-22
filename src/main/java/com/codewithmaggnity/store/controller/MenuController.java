package com.codewithmaggnity.store.controller;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codewithmaggnity.store.model.Prato;
import com.codewithmaggnity.store.service.PedidoService;

@RestController
@RequestMapping("/api")
public class MenuController {

    private final PedidoService pedidoService;

    public MenuController(PedidoService pedidoService) {
        this.pedidoService = pedidoService;
    }

    @GetMapping("/pratos")
    public List<Prato> listarPratos() {
        return pedidoService.listarPratos();
    }

    @PostMapping("/pedido")
    public Map<String, Object> adicionarPedido(@RequestBody Prato prato) {
        pedidoService.adicionarPedido(prato);
        return Map.of("status", "ok", "mensagem", "Prato adicionado");
    }

    @PostMapping("/pedido/finalizar")
    public ResponseEntity<?> finalizarPedido() {
        if (pedidoService.isPedidoVazio()) {
            return ResponseEntity.badRequest().body(Map.of("status", "erro", "mensagem", "Não há itens no pedido"));
        }
        return ResponseEntity.ok(pedidoService.finalizarPedido());
    }


}