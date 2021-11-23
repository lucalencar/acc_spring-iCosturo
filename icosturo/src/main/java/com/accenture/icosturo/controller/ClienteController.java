package com.accenture.icosturo.controller;

import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.accenture.icosturo.entity.Cliente;
import com.accenture.icosturo.entity.Usuario;
import com.accenture.icosturo.repository.ClienteRepository;
import com.accenture.icosturo.repository.UsuarioRepository;

@RestController
public class ClienteController {
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
    @RequestMapping(value = "/cliente", method = RequestMethod.GET)
    public List<Cliente> getAllCliente() {
    	return clienteRepository.findAll();
    }
    
    @RequestMapping(value = "/cliente/{clienteId}", method = RequestMethod.GET)
    public ResponseEntity<Cliente> getClienteById(@PathVariable(value = "clienteId") int clienteId) {
    	Optional<Cliente> cliente = clienteRepository.findById(clienteId);
        if(cliente.isPresent())
            return new ResponseEntity<Cliente>(cliente.get(), HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    
    @RequestMapping(value = "/cliente", method =  RequestMethod.POST)
    public Cliente Post(@Valid @RequestBody Cliente cliente) {
    	Optional<Usuario> usuario = usuarioRepository.findById(cliente.getUsuario().getUserId());
    	cliente.setUsuario(usuario.get());    	
    	return clienteRepository.save(cliente);
    
    }
    
    @RequestMapping(value = "/clientebyid", method =  RequestMethod.POST)
    public Cliente PostByLastId(@Valid @RequestBody Cliente cliente) {
    	List<Usuario> lastUsuario = usuarioRepository.findAll();
    	OptionalInt lastId;
    	lastId = lastUsuario.stream().mapToInt(user -> user.getUserId()).max();
    	Optional<Usuario> usuario = usuarioRepository.findById(lastId.getAsInt());
    	cliente.setUsuario(usuario.get());
    	return clienteRepository.save(cliente);
    }
    
    @RequestMapping(value = "/cliente/{clienteId}", method = RequestMethod.DELETE)
    public ResponseEntity<Object> Delete(@PathVariable(value = "clienteId") int clienteId)
    {
        Optional<Cliente> cliente = clienteRepository.findById(clienteId);
        if(cliente.isPresent()){
            clienteRepository.delete(cliente.get());
            return new ResponseEntity<>(HttpStatus.OK);
        }
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
	
}
