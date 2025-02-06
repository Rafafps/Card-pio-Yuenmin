# Cardapio YuenMin
![Licença](https://img.shields.io/badge/Licença-MIT-blue.svg)

## Índice

- [Sobre o Projeto](#sobre-o-projeto)
- [Funcionalidades](#funcionalidades)
- [Estrutura do Projeto](#estrutura-do-projeto)
- [Pré-requisitos](#pré-requisitos)
- [Como Executar](#como-executar)
- [Padrões de Código e Commits](#padrões-de-código-e-commits)
- [Autores](#autores)
- [Licença](#licença)

---

## Sobre o Projeto

Este projeto foi desenvolvido para a disciplina de **Programação Orientada a Objetos** e implementamos:

Um Cardápio Digital de Visualização criado para facilitar a consulta aos itens disponíveis no estabelecimento.
A ferramenta permite que os clientes visualizem o menu, conheçam as opções de produtos, preços e descrições detalhadas, mas não possibilita a realização de pedidos de delivery.

O projeto explorou conceitos como:

- Encapsulamento
- Herança
- Polimorfismo
- Abstração

## Funcionalidades

- **Visualizar produtos por categoria**: Os produtos são organizados em categorias como bebidas, congelados, veganos e vegetarianos.
- **Detalhamento de produtos**: Permite visualizar descrições detalhadas, incluindo preço e características.
- **Carrinho de compras**: Possibilidade de adicionar, remover produtos no carrinho e escolher a forma de pagamento.
- **Feedback de carrinho vazio**: Exibição de mensagem personalizada quando não há itens no carrinho.
- **Tela de Pedido Finalizado**: Tela que exibe o número do pedido.

## Estrutura do Projeto

O projeto segue o padrão **MVC (Model-View-Controller)**, com a seguinte organização:

### Controller

Contém as classes responsáveis pela lógica de controle do projeto:

- **CarrinhoAdapter.java**: Classe adaptadora para conectar os dados do carrinho com a interface do usuário, permitindo exibi-los de forma eficiente.
- **CarrinhoController.java**: Gerencia as operações relacionadas ao carrinho, como adicionar, remover e listar produtos.
- **PedidoController.java**: Controla a lógica dos pedidos, como finalização e cálculo de valores.
- **ProdutoController.java**: Gerencia as operações relacionadas aos produtos, como busca, filtragem e obtenção de detalhes.

### Model

Inclui as classes que representam os dados e a lógica de negócios:

- **Bebida.java**: Representa a categoria de produtos do tipo bebida, com atributos específicos como volume e sabor.
- **Carrinho.java**: Modela o carrinho de compras, armazenando produtos, adicionar produto, remover produto e finalizar compra.
- **Congelado.java**: Representa a categoria de produtos congelados, com atributos específicos como temperatura de armazenamento.
- **Produto.java**: Classe base que define os atributos gerais de um produto, como nome, preço e descrição.
- **Vegano.java**: Extensão de Produto para representar produtos veganos, adicionando informações específicas relacionadas à categoria.
- **Vegetariano.java**: Extensão de Produto para representar produtos vegetarianos, com atributos e lógica exclusivos.

### View

Agrupa as telas do aplicativo, responsáveis pela interação com o usuário:

- **CarrinhoActivity.java**: Tela para visualização do carrinho de compras.
- **CarrinhoNaoActivity.java**: Tela exibida quando o carrinho está vazio.
- **DetalhesProdutoActivity.java**: Tela com detalhes de um produto específico.
- **MainActivity.java**: Tela inicial do aplicativo e conexões das demais telas.
- **PedidoFinalizadoActivity.java**: Tela exibida após a finalização de um pedido.
- **SplashScreen.java**: Tela de carregamento inicial do aplicativo.

### Outros

- **Pasta `res`**: Contém os recursos do projeto, como layouts XML, imagens e ícones organizados em pastas específicas para diferentes densidades de tela.

## Pré-requisitos

Para executar este projeto, é necessário:

- [Java JDK 11 ou superior]
- [Android Studio - última versão estável]

## Como Executar

1. Clone este repositório:
   ```bash
   git clone https://github.com/seu-usuario/nome-do-repositorio.git
   ```
2. Abra o projeto no Android Studio.
3. Sincronize as dependências do Gradle.
4. Conecte um dispositivo Android ou configure um emulador.
5. Execute o aplicativo clicando no botão "Run" no Android Studio.

## Padrões de Código e Commits

Serão seguidos os seguintes padrões durante a implementação do projeto:

- **camelCase** para nomes de variáveis e métodos.
- **Identificadores para commits:**

  **`feat`**: Novo recurso ou funcionalidade.

  **`fix`**: Correção de bug.

  **`task`**: Tarefas que não implementam funcionalidade e nem correção de código.

  **`refactor`**: Refatoração de código.

  **`test`**: Adição ou correção de testes.

  Exemplo de mensagem de commit:
  - `feat #12: adiciona classe Salada`
  - `fix #5: alteracao do metodo getSalada`

## Autores

| Nome                             | Matrícula |
| -------------------------------- | --------- |
| Rafaella Ferreira Pinheiro Silva | 5363      |
| Daniel Martins De Abreu          | 5798      |
| Maria Eduarda O. G. Andrade      | 5365      |
| Raíssa Tayná Xavier dos Santos   | 5790      |
| Rafael Chang                     | 5783      |
| Manoel Augusto Alves             | 5379      |
| Ana Luísa Moreira Rodrigues      | 5389      |

## Licença

Este projeto está licenciado sob a Licença MIT - veja o arquivo [LICENSE](LICENSE) para mais detalhes.
