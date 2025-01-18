package br.ufv.tp1_poo.controller;

import br.ufv.tp1_poo.view.CarrinhoActivity;
import br.ufv.tp1_poo.view.PedidoFinalizadoActivity;

public class PedidoController {

    private final PedidoFinalizadoActivity view;
    private final CarrinhoActivity carrinho;

    private int idPedido;

    public PedidoController(PedidoFinalizadoActivity view, CarrinhoActivity carrinho) {
        this.view = view;
        this.carrinho = carrinho;
    }

    public void carregarNumeroPedido() {
        idPedido = (int) (Math.random() * 100000);
        String text = "#"+ idPedido;
        view.getNumeroPedido().setText(text);
    }
}
