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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.accenture.icosturo.entity.Cliente;
import com.accenture.icosturo.entity.Costureiro;
import com.accenture.icosturo.entity.Designer;
import com.accenture.icosturo.entity.Usuario;
import com.accenture.icosturo.repository.ClienteRepository;
import com.accenture.icosturo.repository.CostureiroRepository;
import com.accenture.icosturo.repository.DesignerRepository;
import com.accenture.icosturo.repository.UsuarioRepository;

@RestController
public class UsuarioController {

	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private DesignerRepository designerRepository;
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private CostureiroRepository costureiroRepository;

	
    @RequestMapping(value = "/usuario", method = RequestMethod.GET)
    public List<Usuario> getAllUser() {
    	return usuarioRepository.findAll();
    }
    
    @RequestMapping(value = "/usuario/lastId", method = RequestMethod.GET)
    @ResponseBody
    public Usuario getLastId() {
    	List<Usuario> lastUsuario = usuarioRepository.findAll();
    	OptionalInt lastId;
    	lastId = lastUsuario.stream().mapToInt(user -> user.getUserId()).max();
    	int id = lastId.getAsInt();
    	return usuarioRepository.getById(id);
    }
    
    
    @RequestMapping(value = "/usuario/{userId}", method = RequestMethod.GET)
    public ResponseEntity<Usuario> getUserById(@PathVariable(value = "userId") int userId) {
    	Optional<Usuario> usuario = usuarioRepository.findById(userId);
        if(usuario.isPresent())
            return new ResponseEntity<Usuario>(usuario.get(), HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    

    @RequestMapping(value = "/designer/{designerId}/usuario", method = RequestMethod.GET)
    public ResponseEntity<Usuario> getByDesignerId(@PathVariable(value = "designerId") int designerId) {
    	Designer designer = designerRepository.getById(designerId);
    	Optional<Usuario> usuario = usuarioRepository.findByDesignerList(designer);
    	if (usuario.isPresent()) 
            return new ResponseEntity<Usuario>(usuario.get(), HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    
    public ResponseEntity<Usuario> getByClienteId(@PathVariable(value = "clienteId") int clienteId) {
    	Cliente cliente = clienteRepository.getById(clienteId);
    	Optional<Usuario> usuario = usuarioRepository.findByClienteList(cliente);
    	if (usuario.isPresent()) 
            return new ResponseEntity<Usuario>(usuario.get(), HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    
    public ResponseEntity<Usuario> getByCostureiroId(@PathVariable(value = "costureiroId") int costureiroId) {
    	Costureiro costureiro = costureiroRepository.getById(costureiroId);
    	Optional<Usuario> usuario = usuarioRepository.findByCostureiroList(costureiro);
    	if (usuario.isPresent()) 
            return new ResponseEntity<Usuario>(usuario.get(), HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
	
   
    @RequestMapping(value = "/usuario", method =  RequestMethod.POST)
    public Usuario saveUsuario(@Valid @RequestBody Usuario usuario) {
    	return usuarioRepository.save(usuario);
    }
    
    @RequestMapping(value = "/usuario/{userId}", method =  RequestMethod.PUT)
    public ResponseEntity<Usuario> updateUsuario(@PathVariable(value = "userId") int userId, @Valid @RequestBody Usuario newUsuario)
    {
        Optional<Usuario> oldUsuario = usuarioRepository.findById(userId);
        if(oldUsuario.isPresent()){
            Usuario usuario = oldUsuario.get();
            usuario.setNome(newUsuario.getNome());
            usuario.setSobrenome(newUsuario.getSobrenome());
            usuario.setTelefone(newUsuario.getTelefone());
            usuario.setEmail(newUsuario.getEmail());
            usuario.setPassword(newUsuario.getPassword());
            usuarioRepository.save(usuario);
            return new ResponseEntity<Usuario>(usuario, HttpStatus.OK);
        }
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    
    @RequestMapping(value = "/usuario/{userId}", method = RequestMethod.DELETE)
    public ResponseEntity<Object> deleteUsuario(@PathVariable(value = "userId") int userId)
    {
        Optional<Usuario> usuario = usuarioRepository.findById(userId);
        if(usuario.isPresent()){
            usuarioRepository.delete(usuario.get());
            return new ResponseEntity<>(HttpStatus.OK);
        }
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }    
}
