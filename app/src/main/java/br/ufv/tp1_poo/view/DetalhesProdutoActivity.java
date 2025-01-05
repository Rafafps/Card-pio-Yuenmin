package br.ufv.tp1_poo.view;

import android.os.Bundle;
import android.text.Html;
import android.text.Spanned;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

import br.ufv.tp1_poo.R;
import br.ufv.tp1_poo.model.Produto;

public class DetalhesProdutoActivity extends AppCompatActivity {

    private ImageView imagemProduto;
    private TextView nomeProduto, descricaoProduto, quantidadeProduto, botaoVoltar, botaoAdicionar, botaoAumentar, botaoDiminuir;
    private EditText observacaoProduto;
    private RadioGroup radioGroupTamanhos;

    private Produto produto;
    private int quantidade = 1;
    private int valorAdicional = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhes_produto);

        // Inicialização das Views
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
        produto = (Produto) getIntent().getSerializableExtra("produto");

        if (produto != null) {
            carregarProduto();
        } else {
            nomeProduto.setText("Produto não encontrado");
            botaoAdicionar.setEnabled(false);
        }

        atualizarEstadoBotaoDiminuir();

        // Configurar os listeners dos botões
        botaoAumentar.setOnClickListener(v -> alterarQuantidade(1));
        botaoDiminuir.setOnClickListener(v -> alterarQuantidade(-1));
        botaoVoltar.setOnClickListener(v -> finish());
        botaoAdicionar.setOnClickListener(v -> adicionarProdutoCarrinho());

        // Listener para os tamanhos
        radioGroupTamanhos.setOnCheckedChangeListener((group, checkedId) -> atualizarValorAdicional());
    }

    private void carregarProduto() {
        try {
            // Carregar imagem do produto
            int resourceId = getResources().getIdentifier(produto.getImagem(), "drawable", getPackageName());
            if (resourceId != 0) {
                Glide.with(this).load(resourceId).into(imagemProduto);
            } else {
                Glide.with(this).load(R.drawable.imagem_carregando).into(imagemProduto); // Imagem padrão
            }
            // Configurar texto dos campos
            nomeProduto.setText(produto.getNome());
            descricaoProduto.setText(produto.getDescricao());

            configurarOpcoesTamanhos();

            atualizarPreco();
        } catch (Exception e) {
            e.printStackTrace();
            Glide.with(this).load(R.drawable.imagem_carregando).into(imagemProduto);
        }
    }

    private void configurarOpcoesTamanhos() {
        // Remove todas as opções previamente criadas
        radioGroupTamanhos.removeAllViews();

        // Define as opções com base na categoria do produto
        if ("Bebida".equalsIgnoreCase(produto.getCategoria())) {
            adicionarOpcaoTamanho("300ml", 0);
            adicionarOpcaoTamanho("500ml", 2);
            adicionarOpcaoTamanho("700ml", 4);
        } else {
            adicionarOpcaoTamanho("P", 0);
            adicionarOpcaoTamanho("M", 2);
            adicionarOpcaoTamanho("G", 4);
        }
    }

    private void adicionarOpcaoTamanho(String label, int adicional) {
        RadioButton opcao = new RadioButton(this);
        opcao.setText(label);
        opcao.setTag(adicional);
        radioGroupTamanhos.addView(opcao);
    }

    private void alterarQuantidade(int delta) {
        quantidade = Math.max(1, quantidade + delta);
        quantidadeProduto.setText(String.valueOf(quantidade));
        atualizarPreco();
        atualizarEstadoBotaoDiminuir();
    }

    private void atualizarValorAdicional() {
        // Obtém o adicional da opção selecionada
        int checkedId = radioGroupTamanhos.getCheckedRadioButtonId();
        if (checkedId != -1) {
            RadioButton selecionado = findViewById(checkedId);
            valorAdicional = (int) selecionado.getTag();
        } else {
            valorAdicional = 0;
        }
        atualizarPreco();
    }

    private void atualizarPreco() {
        double precoBase = produto.getPreco() / 100.0;
        double precoTotal = (precoBase + valorAdicional) * quantidade;
        String precoFormatado = String.format("%.2f", precoTotal);
        botaoAdicionar.setText("Adicionar R$ " + precoFormatado);
    }

    private void atualizarEstadoBotaoDiminuir() {
        if (quantidade == 1) {
            botaoDiminuir.setEnabled(false);
            botaoDiminuir.setAlpha(0.5f);
        } else {
            botaoDiminuir.setEnabled(true);
            botaoDiminuir.setAlpha(1.0f);
        }
    }

    private void adicionarProdutoCarrinho() {
        if (produto != null) {
            produto.setQuantidade(quantidade);
            produto.setObservacao(observacaoProduto.getText().toString());
            // Atualiza o tamanho selecionado e o valor adicional
            int checkedId = radioGroupTamanhos.getCheckedRadioButtonId();
            if (checkedId != -1) {
                RadioButton selecionado = findViewById(checkedId);
                produto.setTamanho(selecionado.getText().toString());
            }
            // Lógica para adicionar o produto ao carrinho pode ser implementada aqui
        }
    }
}
