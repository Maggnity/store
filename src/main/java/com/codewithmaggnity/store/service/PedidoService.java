package com.codewithmaggnity.store.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.codewithmaggnity.store.model.Pedido;
import com.codewithmaggnity.store.model.Prato;

@Service
public class PedidoService {

        private List<Prato> cardapio = new ArrayList<>();
        private List<Prato> pedidos = new ArrayList<>();

        public PedidoService() {
                cardapio.add(new Prato(1, "Prato 1", 20.99, List.of("arroz", "feijão", "bisteca", "salada")));
                cardapio.add(new Prato(2, "Prato 2", 25.50, List.of("arroz", "frango", "batata", "salada")));
                cardapio.add(new Prato(3, "Prato 3", 18.00, List.of("macarrão", "molho", "queijo")));
        }

        public List<Prato> listarPratos() {
                return cardapio;
        }

        public void adicionarPedido(Prato prato) {
                pedidos.add(prato);
        }

        public Pedido finalizarPedido() {

                if (pedidos.isEmpty())
                        return null;

                double total = pedidos.stream().mapToDouble(Prato::getPreco).sum();
                Pedido pedidoFinal = new Pedido();
                pedidoFinal.setItens(new ArrayList<>(pedidos));
                pedidoFinal.setTotal(total);
                pedidos.clear();
                return pedidoFinal;
        };

        public boolean isPedidoVazio() {
                return pedidos.isEmpty();
        }
}
