package com.codewithmaggnity.store.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.codewithmaggnity.store.model.Pedido;
import com.codewithmaggnity.store.model.Prato;
import com.codewithmaggnity.store.repository.PedidoRepository;
import com.codewithmaggnity.store.repository.PratoRepository;

@Service
public class PedidoService {

        private final PedidoRepository pedidoRepository;
        private final PratoRepository pratoRepository;

        public PedidoService(PedidoRepository pedidoRepository, PratoRepository pratoRepository) {
                this.pedidoRepository = pedidoRepository;
                this.pratoRepository = pratoRepository;
        }

        public Pedido adicionarPrato(Long pedidoId, Long pratoId) {

                Optional<Pedido> pedidoOpt = pedidoRepository.findById(pedidoId);
                Optional<Prato> pratoOpt = pratoRepository.findById((pratoId));

                if (pratoOpt.isEmpty()) {
                        throw new IllegalArgumentException("Prato não encontrado!");
                }

                Pedido pedido = pedidoOpt.orElse(new Pedido());

                pedido.getItens().add(pratoOpt.get());

                double total = pedido.getItens()
                                .stream()
                                .mapToDouble(Prato::getPreco)
                                .sum();

                pedido.setTotal(total);

                return pedidoRepository.save(pedido);
        };

        public Pedido finalizarPedido(Long pedidoId) {

                Pedido pedido = pedidoRepository.findById(pedidoId)
                                .orElseThrow(() -> new IllegalArgumentException("Pedido não encontrado!"));

                if (pedido.getItens().isEmpty()) {

                        throw new IllegalStateException("Não há itens no pedido para finalizar!");
                }

                pedido.setStatus("finalizado");

                return pedidoRepository.save(pedido);
        };

        public List<Pedido> listarPedidos() {

                return pedidoRepository.findAll();

        };
}
