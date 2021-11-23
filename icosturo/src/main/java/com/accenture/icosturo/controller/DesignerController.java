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

import com.accenture.icosturo.entity.Designer;
import com.accenture.icosturo.entity.Modelo;
import com.accenture.icosturo.entity.Usuario;
import com.accenture.icosturo.repository.DesignerRepository;
import com.accenture.icosturo.repository.ModeloRepository;
import com.accenture.icosturo.repository.UsuarioRepository;

@RestController
public class DesignerController {
	
	@Autowired
	private DesignerRepository designerRepository;
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private ModeloRepository modeloRepository;
	
    @RequestMapping(value = "/designer", method = RequestMethod.GET)
    public List<Designer> getAllDesigner() {
    	return designerRepository.findAll();
    }
    
    @RequestMapping(value = "/designer/{designerId}", method = RequestMethod.GET)
    public ResponseEntity<Designer> getDesignerById(@PathVariable(value = "designerId") int designerId) {
    	Optional<Designer> designer = designerRepository.findById(designerId);
        if(designer.isPresent())
            return new ResponseEntity<Designer>(designer.get(), HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    
    @RequestMapping(value = "/modelo/{modeloId}/designer", method = RequestMethod.GET)
    public ResponseEntity<Designer> getDesignerByModeloId(@PathVariable (value = "modeloId") int modeloId) {
    	Modelo modelo = modeloRepository.getById(modeloId);
    	Optional<Designer> designer=  designerRepository.findByModeloList(modelo);
        if(designer.isPresent())
            return new ResponseEntity<Designer>(designer.get(), HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);    }
   

    
    @RequestMapping(value = "/designer", method =  RequestMethod.POST)
    public Designer Post(@Valid @RequestBody Designer designer) {
    	Optional<Usuario> usuario = usuarioRepository.findById(designer.getUsuario().getUserId());
    	designer.setUsuario(usuario.get());    	
    	return designerRepository.save(designer);
    }
    
    
    @RequestMapping(value = "/designerbyid", method =  RequestMethod.POST)
    public Designer PostByLastId(@Valid @RequestBody Designer designer) {
    	List<Usuario> lastUsuario = usuarioRepository.findAll();
    	OptionalInt lastId;
    	lastId = lastUsuario.stream().mapToInt(user -> user.getUserId()).max();
    	Optional<Usuario> usuario = usuarioRepository.findById(lastId.getAsInt());
    	designer.setUsuario(usuario.get());
    	return designerRepository.save(designer);
    }
   
    
    @RequestMapping(value = "/designer/{designerId}", method = RequestMethod.DELETE)
    public ResponseEntity<Object> Delete(@PathVariable(value = "designerId") int designerId)
    {
        Optional<Designer> designer = designerRepository.findById(designerId);
        if(designer.isPresent()){
            designerRepository.delete(designer.get());
            return new ResponseEntity<>(HttpStatus.OK);
        }
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    	
}
