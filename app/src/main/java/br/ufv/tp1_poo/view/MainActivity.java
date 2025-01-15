package br.ufv.tp1_poo.view;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import br.ufv.tp1_poo.R;
import br.ufv.tp1_poo.controller.CarrinhoController;
import br.ufv.tp1_poo.controller.ProdutoAdapter;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import br.ufv.tp1_poo.model.Bebida;
import br.ufv.tp1_poo.model.Carrinho;
import br.ufv.tp1_poo.model.Congelado;
import br.ufv.tp1_poo.model.Produto;
import br.ufv.tp1_poo.model.Vegano;
import br.ufv.tp1_poo.model.Vegetariano;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;
import br.ufv.tp1_poo.R;
import br.ufv.tp1_poo.controller.CarrinhoController;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerViewProdutos;

    private CarrinhoController carrinhoController;

    // Elementos de UI para as abas
    private TextView tabVegetariano, tabVegano, tabCongelado, tabBebida;
    private View barraVegetariano, barraVegano, barraCongelado, barraBebida;

    private static Carrinho carrinhos;

    // Lista completa de produtos
    private List<Produto> listaCompletaProdutos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        carrinhoController = new CarrinhoController(this);

        // Configuração inicial

        configurarCliqueCarrinho();
        configurarRecyclerView();
        inicializarTabs();
        configurarTabs();

    }

    // Configura o RecyclerView para exibir a lista de produtos.
    private void configurarRecyclerView() {
        recyclerViewProdutos = findViewById(R.id.recyclerViewProdutos);
        recyclerViewProdutos.setLayoutManager(new LinearLayoutManager(this));

        listaCompletaProdutos = carregarProdutos();

        if (listaCompletaProdutos != null && !listaCompletaProdutos.isEmpty()) {
            ProdutoAdapter produtoAdapter = new ProdutoAdapter(listaCompletaProdutos, produto -> {
                // Clique no item: abre a tela de detalhes do produto
                Intent intent = new Intent(MainActivity.this, DetalhesProdutoActivity.class);
                intent.putExtra("produto", produto); // Passa o objeto Produto
                startActivity(intent);
            });
            recyclerViewProdutos.setAdapter(produtoAdapter);
        } else {
            Log.e("MainActivity", "Lista de produtos está vazia ou não carregou.");
        }
    }

    // Inicializa os componentes das abas e barras.
    private void inicializarTabs() {
        tabVegetariano = findViewById(R.id.tabVegetariano);
        tabVegano = findViewById(R.id.tabVegano);
        tabCongelado = findViewById(R.id.tabCongelado);
        tabBebida = findViewById(R.id.tabBebida);

        barraVegetariano = findViewById(R.id.barraVegetariano);
        barraVegano = findViewById(R.id.barraVegano);
        barraCongelado = findViewById(R.id.barraCongelado);
        barraBebida = findViewById(R.id.barraBebida);

        // Define "Vegetariano" como a aba padrão selecionada
        selecionarAba(tabVegetariano, barraVegetariano);
    }

    // Configura os listeners de clique para alternar entre as abas.
    private void configurarTabs() {
        tabVegetariano.setOnClickListener(v -> selecionarAba(tabVegetariano, barraVegetariano));
        tabVegano.setOnClickListener(v -> selecionarAba(tabVegano, barraVegano));
        tabCongelado.setOnClickListener(v -> selecionarAba(tabCongelado, barraCongelado));
        tabBebida.setOnClickListener(v -> selecionarAba(tabBebida, barraBebida));
    }

    // Altera o estilo da aba selecionada e redefine as outras.
    private void selecionarAba(TextView tabSelecionada, View barraSelecionada) {
        // Redefine o estilo das abas
        resetarEstilos();

        // Estilo para a aba selecionada
        tabSelecionada.setTextColor(getResources().getColor(R.color.laranja));
        barraSelecionada.setVisibility(View.VISIBLE);

        // Filtra os produtos com base na aba selecionada
        String categoria = tabSelecionada.getText().toString();
        List<Produto> produtosFiltrados = filtrarProdutosPorCategoria(categoria);

        // Atualiza o RecyclerView com os produtos filtrados
        ProdutoAdapter produtoAdapter = new ProdutoAdapter(produtosFiltrados, produto -> {
            // Clique no item: abre a tela de detalhes do produto
            Intent intent = new Intent(MainActivity.this, DetalhesProdutoActivity.class);
            intent.putExtra("produto", produto); // Passa o objeto Produto
            startActivity(intent);
        });
        recyclerViewProdutos.setAdapter(produtoAdapter);
    }

    // Filtra os produtos com base na categoria
    private List<Produto> filtrarProdutosPorCategoria(String categoria) {
        List<Produto> produtosFiltrados = new ArrayList<>();

        for (Produto produto : listaCompletaProdutos) {
            if (categoria.equalsIgnoreCase("Vegetariano") && produto instanceof Vegetariano) {
                produtosFiltrados.add(produto);
            } else if (categoria.equalsIgnoreCase("Vegano") && produto instanceof Vegano) {
                produtosFiltrados.add(produto);
            } else if (categoria.equalsIgnoreCase("Congelado") && produto instanceof Congelado) {
                produtosFiltrados.add(produto);
            } else if (categoria.equalsIgnoreCase("Bebida") && produto instanceof Bebida) {
                produtosFiltrados.add(produto);
            }
        }

        return produtosFiltrados;
    }



    // Redefine o estilo de todas as abas para o estado padrão.
    private void resetarEstilos() {
        tabVegetariano.setTextColor(getResources().getColor(R.color.cinza));
        tabVegano.setTextColor(getResources().getColor(R.color.cinza));
        tabCongelado.setTextColor(getResources().getColor(R.color.cinza));
        tabBebida.setTextColor(getResources().getColor(R.color.cinza));

        barraVegetariano.setVisibility(View.GONE);
        barraVegano.setVisibility(View.GONE);
        barraCongelado.setVisibility(View.GONE);
        barraBebida.setVisibility(View.GONE);
    }

    // Carrega a lista de produtos do arquivo JSON
    private List<Produto> carregarProdutos() {
        try (InputStream inputStream = getResources().openRawResource(R.raw.produtos)) {
            // Cria uma instância do ObjectMapper
            ObjectMapper objectMapper = new ObjectMapper();

            // Converte o JSON para a lista de objetos Produto
            return objectMapper.readValue(inputStream, new TypeReference<List<Produto>>() {});
        } catch (Exception e) {
            Log.e("MainActivity", "Erro ao carregar JSON do raw: " + e.getMessage(), e);
            return new ArrayList<>();
        }
    }
    private void configurarCliqueCarrinho() {
        ImageView imagemCarrinho = findViewById(R.id.carrinho);
        imagemCarrinho.setOnClickListener(v -> carrinhoController.verificarEstadoCarrinho(this));
    }
    public void abrirCarrinhoVazio() {
        Intent intent = new Intent(MainActivity.this, CarrinhoVazioActivity.class);
        startActivity(intent);
    }
    public void abrirCarrinhoCheio() {
        Intent intent = new Intent(MainActivity.this, CarrinhoActivity.class);
        startActivity(intent);
    }
}