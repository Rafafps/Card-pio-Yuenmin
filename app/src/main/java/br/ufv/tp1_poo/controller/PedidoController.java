package br.ufv.tp1_poo.controller;

import br.ufv.tp1_poo.view.CarrinhoActivity;
import br.ufv.tp1_poo.view.PedidoFinalizadoActivity;

public class PedidoController {

    private final PedidoFinalizadoActivity view;

    private int idPedido;

    public PedidoController(PedidoFinalizadoActivity view) {
        this.view = view;
    }

    public void carregarNumeroPedido() {
        idPedido = (int) (Math.random() * 100000);
        String text = "#"+ idPedido;
        view.getNumeroPedido().setText(text);
    }
}
