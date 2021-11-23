package com.accenture.icosturo.controller;

import java.util.List;
import java.util.Optional;
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

import com.accenture.icosturo.entity.Modelo;
import com.accenture.icosturo.entity.Costureiro;
import com.accenture.icosturo.entity.Designer;
import com.accenture.icosturo.repository.ModeloRepository;
import com.accenture.icosturo.repository.CostureiroRepository;
import com.accenture.icosturo.repository.DesignerRepository;


@RestController
public class ModeloController {
	
	@Autowired
	ModeloRepository modeloRepository;
	
	@Autowired
	DesignerRepository designerRepository;
	
	@Autowired
	CostureiroRepository costureiroRepository;
	
    @RequestMapping(value = "/modelo", method = RequestMethod.GET)
    public List<Modelo> getAllModelo() {
    	return modeloRepository.findAll();
    }
    
    @RequestMapping(value = "/modelo/{modeloId}", method = RequestMethod.GET)
    public ResponseEntity<Modelo> getModeloById(@PathVariable(value = "modeloId") int modeloId) {
    	Optional<Modelo> modelo = modeloRepository.findById(modeloId);
    	if (modelo.isPresent()) {
    		 return new ResponseEntity<Modelo>(modelo.get(), HttpStatus.OK);
    	} else {
    		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    	}
    }
    
    @RequestMapping(value = "/designer/{designerId}/modelos/", method = RequestMethod.GET)
    public List<Modelo> getAllModelosByDesigner(@PathVariable (value = "designerId") int designerId) {
    	Designer designer = designerRepository.getById(designerId);
    	return modeloRepository.findByDesigner(designer);
    	
    }
    
    @RequestMapping(value = "/costureiro/{costureiroId}/modelos/", method = RequestMethod.GET)
    public Set<Modelo> getAllModelosByCostureiro(@PathVariable (value = "costureiroId")int costureiroId) {
    	Costureiro costureiros = costureiroRepository.getById(costureiroId);
    	return modeloRepository.findByCostureiros(costureiros);
    }
    
    @RequestMapping(value = "/modelo", method =  RequestMethod.POST)
    public Modelo Post(@Valid @RequestBody Modelo modelo) {
    	Optional<Designer> designer = designerRepository.findById(modelo.getDesigner().getDesignerId());
    	modelo.setDesigner(designer.get());    	
    	return modeloRepository.save(modelo);
    }
    
    @RequestMapping(value = "/modelo/{modeloId}", method =  RequestMethod.PUT)
    public ResponseEntity<Modelo> Put(@PathVariable(value = "modeloId") int modeloId, @Valid @RequestBody Modelo newModelo)
    {
        Optional<Modelo> oldModelo = modeloRepository.findById(modeloId);
        if(oldModelo.isPresent()){
            Modelo modelo = oldModelo.get();
            modelo.setTipo(newModelo.getTipo());
            modelo.setCor(newModelo.getCor());
            modelo.setValor(newModelo.getValor());
            modelo.setImageUm(newModelo.getImageUm());
            modelo.setImageDois(newModelo.getImageDois());
            modelo.setImageTres(newModelo.getImageTres());
            modelo.setCostureiros(newModelo.getCostureiros());
            modeloRepository.save(modelo);
            return new ResponseEntity<Modelo>(modelo, HttpStatus.OK);
        }
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    
    @RequestMapping(value = "/modelo/{modeloId}", method = RequestMethod.DELETE)
    public ResponseEntity<Modelo> delete(@PathVariable(value = "modeloId") int modeloId) {
    	Optional<Modelo> modelo = modeloRepository.findById(modeloId);
    	if (modelo.isPresent()) {
    		modeloRepository.delete(modelo.get());
            return new ResponseEntity<>(HttpStatus.OK);
    	} else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);	
    	}
    }


}
