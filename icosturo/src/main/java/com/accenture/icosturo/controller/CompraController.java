package com.accenture.icosturo.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.accenture.icosturo.entity.Compra;
import com.accenture.icosturo.entity.Designer;
import com.accenture.icosturo.entity.Costureiro;
import com.accenture.icosturo.entity.Cliente;
import com.accenture.icosturo.entity.Modelo;
import com.accenture.icosturo.repository.CompraRepository;
import com.accenture.icosturo.repository.DesignerRepository;
import com.accenture.icosturo.repository.CostureiroRepository;
import com.accenture.icosturo.repository.ClienteRepository;
import com.accenture.icosturo.repository.ModeloRepository;


@RestController
public class CompraController {
	
	@Autowired
	CompraRepository compraRepository;
	@Autowired
	DesignerRepository designerRepository;
	@Autowired
	CostureiroRepository costureiroRepository;
	@Autowired
	ModeloRepository modeloRepository;
	@Autowired
	ClienteRepository clienteRepository;
	
    @RequestMapping(value = "/compra", method = RequestMethod.GET)
    public List<Compra> getAllCompra() {
    	return compraRepository.findAll();
    }
    
    @RequestMapping(value = "/compra/{compraId}", method = RequestMethod.GET)
    public ResponseEntity<Compra> getCompraById(@PathVariable(value = "compraId") int compraId) {
    	Optional<Compra> compra = compraRepository.findById(compraId);
    	if (compra.isPresent()) {
    		return new ResponseEntity<Compra>(compra.get(), HttpStatus.OK);
    	} else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    	}
    }
    
    @RequestMapping(value = "/compra", method =  RequestMethod.POST)
    public ResponseEntity<Compra> Post(@Valid @RequestBody Compra compra) {
    	Optional<Cliente> cliente = clienteRepository.findById(compra.getCliente().getClienteId());
    	Optional<Designer> designer = designerRepository.findById(compra.getDesigner().getDesignerId());
    	Optional<Costureiro> costureiro = costureiroRepository.findById(compra.getCostureiro().getCostureiroId());
    	Optional<Modelo> modelo = modeloRepository.findById(compra.getModelo().getModeloId());
    	compra.setCliente(cliente.get());
    	compra.setDesigner(designer.get());
    	compra.setCostureiro(costureiro.get());
    	compra.setModelo(modelo.get());
    	compra = compraRepository.save(compra);
    	
    	return new ResponseEntity<>(compra, HttpStatus.OK);
    }
    
    @RequestMapping(value = "/compra/{compraId}", method = RequestMethod.DELETE)
    public ResponseEntity<Object> Delete(@PathVariable(value = "compraId") int compraId) {
    	Optional<Compra> compra = compraRepository.findById(compraId);
    	if (compra.isPresent()) {
    		compraRepository.delete(compra.get());
            return new ResponseEntity<>(HttpStatus.OK);
    	} else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    	}
    }



}
