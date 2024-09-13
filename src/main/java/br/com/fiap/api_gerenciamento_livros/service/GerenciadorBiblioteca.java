package br.com.fiap.api_gerenciamento_livros.service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import br.com.fiap.api_gerenciamento_livros.exception.LivroNaoEncontradoException;
import br.com.fiap.api_gerenciamento_livros.model.Livro;
import br.com.fiap.api_gerenciamento_livros.model.LivroDTO;

public class GerenciadorBiblioteca implements GerenciadorBibliotecaInterface {
    private List<Livro> livros = new ArrayList<Livro>();

    @Override
    public Livro getLivroPorISBN(long isbn){
        Livro livroEncontrado = this.livros.stream()
            .filter(livro -> (livro.getIsbn() == isbn))
            .findFirst().orElseThrow(() -> new LivroNaoEncontradoException("Livro com isbn " + isbn + " não encontrado"));
        return livroEncontrado;
    }

    @Override
    public List<LivroDTO> criarLivros(List<LivroDTO> livrosDTOCriados) {
        List<LivroDTO> livrosAdicionados = new ArrayList<LivroDTO>();
        for(LivroDTO livroDTO:livrosDTOCriados) {
            livrosAdicionados.add(livroDTO);
            this.livros.add(convertToEntity(livroDTO));
        }
        return livrosAdicionados;
    }

    @Override
    public void excluirLivro(long isbn) {
        livros.remove(getLivroPorISBN(isbn));
    }
/*
    @Override
    public List<Livro> listarLivros(Optional<String> ordenacao, Optional<String> categoria) {
        List<Livro> livrosResultado = getLivros();

        if(categoria.isPresent()) {
            livrosResultado.stream()
            .filter(livro -> (livro.getCategoria().equals(categoria.toString())))
            .collect(Collectors.toList());
        }

        if(ordenacao.isPresent()) {
            if (ordenacao.toString().equals("titulo")) {
                y
            } else if (ordenacao.toString().equals("autor")) {
                livrosResultado.stream()
                .sorted(Comparator.comparing(Livro::getAutor))
                .collect(Collectors.toList());
            }
            
        }
        return livrosResultado;
    }
*/
    @Override
    public List<LivroDTO> listarLivros() {
        return getLivros();
    }

    @Override
    public List<LivroDTO> listarLivrosOrdenadosPorPropriedade(String propriedade) {
        List<LivroDTO> livrosDTO = convertoToDTOList();
        if (propriedade.equals("autor")) {
            return livrosDTO.stream()
                .sorted(Comparator.comparing(LivroDTO::getAutor))
                .collect(Collectors.toList());
        } else if (propriedade.equals("titulo")) {
            return livrosDTO.stream()
                .sorted(Comparator.comparing(LivroDTO::getTitulo))
                .collect(Collectors.toList());
        } else {
            return null;
        }  

    }
    
    @Override 
    public List<LivroDTO> listarLivrosFiltradosPorCategoria(String categoria) {
        List<LivroDTO> livrosDTO = convertoToDTOList();
        return livrosDTO.stream()
            .filter(livro -> (livro.getCategoria().equals(categoria)))
            .collect(Collectors.toList());
    }

    @Override
    public boolean reservarLivro(long isbn, long userID) {
        return getLivroPorISBN(isbn).fazReserva(userID);
    }

    @Override
    public boolean cancelarReserva(long isbn, long userID) {
        return getLivroPorISBN(isbn).cancelaReserva(userID);
    }

    @Override
    public List<Long> listarReservas(long isbn) {
        return getLivroPorISBN(isbn).getReservas();
    }

    public List<LivroDTO> getLivros() {
        List<LivroDTO> livrosDTO = new ArrayList<>();
        for(Livro livro:livros) {
            livrosDTO.add(convertToDTO(livro));
        }
        return livrosDTO;
    }

    public void setLivros(List<Livro> livros) {
        this.livros = livros;
    }

    private LivroDTO convertToDTO(Livro livro) {
        LivroDTO livroDTO = new LivroDTO();
        livroDTO.setIsbn(livro.getIsbn());
        livroDTO.setTitulo(livro.getTitulo());
        livroDTO.setAutor(livro.getAutor());
        livroDTO.setCategoria(livro.getCategoria());
        livroDTO.setReservas(livro.getReservas());
        return livroDTO;
    }

    private List<LivroDTO> convertoToDTOList() {
        return livros.stream() 
            .map(livro -> convertToDTO(livro))
            .collect(Collectors.toList());
    }


    private Livro convertToEntity(LivroDTO livroDTO) {
        Livro livro = new Livro();
        livro.setIsbn(livroDTO.getIsbn());
        livro.setTitulo(livroDTO.getTitulo());
        livro.setAutor(livroDTO.getAutor());
        livro.setCategoria(livroDTO.getCategoria());
        return livro;
    }
    
}
