package br.com.senai.comercio094.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.senai.comercio094.entity.Produto;
import br.com.senai.comercio094.entity.Venda;
import br.com.senai.comercio094.repository.ProdutoRepository;
import br.com.senai.comercio094.repository.VendaRepository;

@Service
public class VendaService {

    @Autowired
    private VendaRepository vendaRepository;
    @Autowired
    private ProdutoRepository produtoRepository;

    public Venda realizarVenda(Venda venda){
        // Define a data de hoje como a data da venda
        venda.setDataVenda(LocalDate.now());

        // Reduz a quantidade de produto no estoque
        Produto produto = produtoRepository.findById(venda.getProdutoId()).orElse(null);
        if (produto == null){
            return null;
        }else{
            int quantidadeVendida = venda.getQuantidade();
            int quantidadeAtualizada = produto.getQuantidade() - quantidadeVendida;
            produto.setQuantidade(quantidadeAtualizada);
            produtoRepository.save(produto);

            // Incluir preço do produto no valor da venda
            venda.setPrecoUnitario(produto.getPreco());

            // Salva a venda no banco de dados
            return vendaRepository.save(venda);
        }
    }
    public List<Venda> findAll(){
        return vendaRepository.findAll();
    }
}
