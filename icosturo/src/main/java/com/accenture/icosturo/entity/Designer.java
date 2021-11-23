package com.accenture.icosturo.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
@Table(name = "Designer")

public class Designer {
	
	@Id
    @Column(name = "id_designer")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int designerId;
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "id_user", referencedColumnName = "id_user")
    private Usuario usuario;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy="designer", cascade = CascadeType.ALL)
	@JsonIgnore
	private List<Modelo> modeloList = new ArrayList<>();
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy="designer", cascade = CascadeType.ALL)
	@JsonIgnore
	private List<Compra> compraDesignerList = new ArrayList<>();
	
	public int getDesignerId() {
		return designerId;
	}
	public void setDesignerId(int designerId) {
		this.designerId = designerId;
	}

	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	public List<Modelo> getModeloList() {
		return modeloList;
	}
	public void setModeloList(List<Modelo> modeloList) {
		this.modeloList = modeloList;
	}
	public List<Compra> getCompraDesignerList() {
		return compraDesignerList;
	}
	public void setCompraDesignerList(List<Compra> compraDesignerList) {
		this.compraDesignerList = compraDesignerList;
	}

	
	
	

}