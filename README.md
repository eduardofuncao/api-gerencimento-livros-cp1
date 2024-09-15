# api-gerencimento-livros-cp1
> Eduardo Felipe Nunes Função  
> RM553362  
> Link Github: https://github.com/eduardofuncao/api-gerencimento-livros-cp1  

## Projeto
API REST para o gerenciamento de livros em uma biblioteca, incluido funcionalidade de fila de reservas para cada livro.

## Considerações para a entrega
1. É considerado que a biblioteca pode ter somente um livro para cada isbn;
2. A lista de reservas é uma fila para cada livro, contendo os códigos de usuário. O primeiro código da lista representa o usuário que possui o livro no momento. Foi considerado também que, quando o usuário que está com o livro realiza a devolução para a biblioteca, o livro passa automáticamente para o próximo usuário da fila;

## Testes
Os testes da API implementada podem ser feitos através da collection bruno, no arquivo collection-bruno-apiTest.json, na raíz do diretório.
