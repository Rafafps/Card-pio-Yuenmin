package br.ufv.tp1_poo.view;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import br.ufv.tp1_poo.R;
import br.ufv.tp1_poo.controller.ProdutoController;
import br.ufv.tp1_poo.model.Produto;

public class DetalhesProdutoActivity extends AppCompatActivity {

    private ImageView imagemProduto;
    private TextView nomeProduto, descricaoProduto, quantidadeProduto, botaoVoltar, botaoAdicionar, botaoAumentar, botaoDiminuir;
    private EditText observacaoProduto;
    private RadioGroup radioGroupTamanhos;

    private ProdutoController produtoController;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhes_produto);

        // Inicializa os componentes da interface
        imagemProduto = findViewById(R.id.imagemProduto);
        nomeProduto = findViewById(R.id.nomeProduto);
        descricaoProduto = findViewById(R.id.descricaoProduto);
        quantidadeProduto = findViewById(R.id.quantidadeProduto);
        observacaoProduto = findViewById(R.id.observacaoProduto);
        botaoAdicionar = findViewById(R.id.botaoAdicionar);
        botaoAumentar = findViewById(R.id.botaoAumentar);
        botaoDiminuir = findViewById(R.id.botaoDiminuir);
        botaoVoltar = findViewById(R.id.botaoVoltar);
        radioGroupTamanhos = findViewById(R.id.radioGroupTamanhos);

        // Obter o produto passado via Intent
        Produto produto = (Produto) getIntent().getSerializableExtra("produto");

        if (produto != null) {
            produtoController = new ProdutoController(this, produto);
            produtoController.carregarProduto();
        } else {
            nomeProduto.setText("Produto não encontrado");
            botaoAdicionar.setEnabled(false);
        }

        atualizarEstadoBotaoDiminuir();

        // Configurar os listeners dos botões
        botaoAumentar.setOnClickListener(v -> produtoController.alterarQuantidade(1));
        botaoDiminuir.setOnClickListener(v -> produtoController.alterarQuantidade(-1));
        botaoVoltar.setOnClickListener(v -> finish());
        botaoAdicionar.setOnClickListener(v -> produtoController.adicionarProdutoCarrinho());
        radioGroupTamanhos.setOnCheckedChangeListener((group, checkedId) -> produtoController.atualizarValorAdicional());
    }

    public void atualizarEstadoBotaoDiminuir() {
        if (produtoController.getQuantidade() == 1) {
            botaoDiminuir.setEnabled(false);
            botaoDiminuir.setAlpha(0.5f);
        } else {
            botaoDiminuir.setEnabled(true);
            botaoDiminuir.setAlpha(1.0f);
        }
    }

    public ImageView getImagemProduto() {
        return imagemProduto;
    }

    public TextView getNomeProduto() {
        return nomeProduto;
    }

    public TextView getDescricaoProduto() {
        return descricaoProduto;
    }

    public TextView getQuantidadeProduto() {
        return quantidadeProduto;
    }

    public TextView getBotaoAdicionar() {
        return botaoAdicionar;
    }

    public RadioGroup getRadioGroupTamanhos() {
        return radioGroupTamanhos;
    }

    public EditText getObservacaoProduto() {
        return observacaoProduto;
    }
}