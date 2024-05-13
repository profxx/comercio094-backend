package br.com.senai.comercio094.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.senai.comercio094.entity.Venda;
import br.com.senai.comercio094.service.VendaService;

@RestController
@RequestMapping("/venda")
public class VendaController {

    @Autowired
    private VendaService vendaService;

    @PostMapping("/nova")
    public ResponseEntity<Venda> realizarVenda(@RequestBody Venda venda){
        Venda vendaRealizada = vendaService.realizarVenda(venda);
        return ResponseEntity.ok().body(vendaRealizada);
    }
    @GetMapping("/listar")
    public ResponseEntity<List<Venda>> findAll(){
        List<Venda> vendas = vendaService.findAll();
        return ResponseEntity.ok().body(vendas);
    }

}
