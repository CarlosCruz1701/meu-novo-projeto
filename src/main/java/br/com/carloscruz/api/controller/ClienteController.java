package br.com.carloscruz.api.controller;

import br.com.carloscruz.api.entity.Cliente;
import br.com.carloscruz.api.service.ClienteService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/c1")
@AllArgsConstructor
@Slf4j
public class ClienteController {

    ClienteService clienteService;

    @ApiOperation(value = "Cadastrando um novo cliente")
    @ApiResponses(value ={
            @ApiResponse(code = 201, message = "Cadastramento efetuado com sucesso"),
            @ApiResponse(code = 500, message = "Houve um erro ao cadastrar o cliente, verefique as informações")
    })

    @PostMapping("/clientes")
    @ResponseStatus(HttpStatus.CREATED)
    public Cliente createCliente(@RequestBody Cliente cliente){
        log.info("Cadastrando mais um cliente [{}]", cliente);
        return  clienteService.createCliente(cliente);
    }

    @ApiOperation(value = "Listando todos os cliente")
    @ApiResponses(value ={
            @ApiResponse(code = 200, message = "Clientes listado com sucesso"),
            @ApiResponse(code = 500, message = "Houve um erro ao listar os clientes")
    })

    @GetMapping("/clientes")
    @ResponseStatus(HttpStatus.OK)
    public List<Cliente> getCliente(){
        log.info("Listando todos os cliente");
        return clienteService.listAllClientes();
    }

    @ApiOperation(value = "Buscando um cliente pelo id")
    @ApiResponses(value ={
            @ApiResponse(code = 200, message = "Cliente encontrado com sucesso"),
            @ApiResponse(code = 404, message = "Não foi encontrado nenhum cliente com esse id")
    })

    @GetMapping("/clientes/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Cliente> getClienteById(@PathVariable (value = "id") Long id){
        log.info("Buscando cliente pelo id [{}]", id);
        return clienteService.findClienteById(id);
    }

    @ApiOperation(value = "Atualizando o cliente")
    @ApiResponses(value ={
            @ApiResponse(code = 200, message = "Cliente atualizado com sucesso"),
            @ApiResponse(code = 404, message = "Não foi possivel atualizar o cliente - cliente não encontrado")
    })

    @PutMapping("/clientes/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Cliente> updateClienteById(@PathVariable (value = "id") Long id, @RequestBody Cliente cliente){
        log.info("Atualizando um cliente pelo id [{}]",id, cliente);
        return clienteService.updateClienteById(cliente, id);
    }

    @ApiOperation(value = "Excluindo o cliente")
    @ApiResponses(value ={
            @ApiResponse(code = 204, message = "Cliente excluido com sucesso"),
            @ApiResponse(code = 404, message = "Não foi possivel excluir o cliente - cliente não encontrado")
    })

    @DeleteMapping("/clientes/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Object> deleteClienteById(@PathVariable (value = "id") Long id){
        log.info("Excluindo um cliente pelo id [{}]", id);
        return clienteService.deleteById(id);
    }
}
