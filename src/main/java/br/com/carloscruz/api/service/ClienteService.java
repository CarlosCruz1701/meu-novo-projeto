package br.com.carloscruz.api.service;

import br.com.carloscruz.api.entity.Cliente;
import br.com.carloscruz.api.repository.ClienteRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ClienteService {


    private ClienteRepository clienteRepository;

    public Cliente createCliente(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    public List<Cliente> listAllClientes() {
        return clienteRepository.findAll();
    }

    public ResponseEntity<Cliente> findClienteById(long id) {
        return clienteRepository.findById(id)
                .map(cliente -> ResponseEntity.ok().body(cliente))
                .orElse(ResponseEntity.notFound().build());
    }

    public ResponseEntity<Cliente> updateClienteById(Cliente cliente, Long id) {
        return clienteRepository.findById(id)
                .map(clienteToUpdate -> {
                    clienteToUpdate.setName(cliente.getName());
                    clienteToUpdate.setEmail(cliente.getEmail());
                    Cliente update = clienteRepository.save(clienteToUpdate);
                    return ResponseEntity.ok().body(update);
                }).orElse(ResponseEntity.notFound().build());
    }

    public ResponseEntity<Object> deleteById (Long id){
        return clienteRepository.findById(id)
                .map(clienteToDelete ->{
                   clienteRepository.deleteById(id);
                   return ResponseEntity.noContent().build();
                }).orElse(ResponseEntity.notFound().build());
    }
}
