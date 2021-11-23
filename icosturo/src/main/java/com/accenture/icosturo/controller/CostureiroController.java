package com.accenture.icosturo.controller;

import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;
import java.util.Set;

import javax.validation.Valid;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.accenture.icosturo.entity.Costureiro;
import com.accenture.icosturo.entity.Modelo;
import com.accenture.icosturo.entity.Usuario;
import com.accenture.icosturo.repository.CostureiroRepository;
import com.accenture.icosturo.repository.ModeloRepository;
import com.accenture.icosturo.repository.UsuarioRepository;

@RestController
public class CostureiroController {

	@Autowired
	CostureiroRepository costureiroRepository;
	
	@Autowired
	UsuarioRepository usuarioRepository;
	
	@Autowired
	ModeloRepository modeloRepository;
	
	   @RequestMapping(value = "/costureiro", method = RequestMethod.GET)
	    public List<Costureiro> getAllCostureiro() {
	    	return costureiroRepository.findAll();
	    }
	    
	    @RequestMapping(value = "/costureiro/{costureiroId}", method = RequestMethod.GET)
	    public ResponseEntity<Costureiro> getCostureiroById(@PathVariable(value = "costureiroId") int costureiroId) {
	    	Optional<Costureiro> costureiro = costureiroRepository.findById(costureiroId);
	        if(costureiro.isPresent())
	            return new ResponseEntity<Costureiro>(costureiro.get(), HttpStatus.OK);
	        else
	            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	    
	    
	    @RequestMapping(value = "/modelo/{modeloId}/costureiros", method = RequestMethod.GET)
	    public Set<Costureiro> getCostureiroByModeloId(@PathVariable (value = "modeloId") int modeloId, Costureiro costureiro) {
	    	Modelo modelo = modeloRepository.getById(modeloId);
	    	return costureiroRepository.findByModelos(modelo);
	    }	    
	   
	    
	    @RequestMapping(value = "/costureiro", method =  RequestMethod.POST)
	    public Costureiro Post(@Valid @RequestBody Costureiro costureiro) {
	    	Optional<Usuario> usuario = usuarioRepository.findById(costureiro.getUsuario().getUserId());
	    	costureiro.setUsuario(usuario.get());
	    	return costureiroRepository.save(costureiro);
	    }
	    
	    @RequestMapping(value = "/costureirobyid", method =  RequestMethod.POST)
	    public Costureiro PostByLastId(@Valid @RequestBody Costureiro costureiro) {
	    	List<Usuario> lastUsuario = usuarioRepository.findAll();
	    	OptionalInt lastId;
	    	lastId = lastUsuario.stream().mapToInt(user -> user.getUserId()).max();
	    	Optional<Usuario> usuario = usuarioRepository.findById(lastId.getAsInt());
	    	costureiro.setUsuario(usuario.get());
	    	return costureiroRepository.save(costureiro);
	    }
	    
	    @RequestMapping(value = "/costureiro/{costureiroId}", method = RequestMethod.DELETE)
	    public ResponseEntity<Object> Delete(@PathVariable(value = "costureiroId") int costureiroId)
	    {
	        Optional<Costureiro> costureiro = costureiroRepository.findById(costureiroId);
	        if(costureiro.isPresent()){
	            costureiroRepository.delete(costureiro.get());
	            return new ResponseEntity<>(HttpStatus.OK);
	        }
	        else
	            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
		
	}
