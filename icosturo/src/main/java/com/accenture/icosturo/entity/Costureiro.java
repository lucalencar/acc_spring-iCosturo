package com.accenture.icosturo.entity;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "Costureiro")

public class Costureiro {
	
	@Id
    @Column(name = "id_costureiro")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int costureiroId;
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "id_user", referencedColumnName = "id_user")
	private Usuario usuario;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy="costureiro", cascade = CascadeType.ALL)
	@JsonIgnore
	private List<Compra> compraCostureiroList = new ArrayList<>();	
	
    @ManyToMany(mappedBy = "costureiros", fetch = FetchType.EAGER)
	@JsonIgnore
    private Set<Modelo> modelos = new HashSet<Modelo>();
    
	public int getCostureiroId() {
		return costureiroId;
	}
	public void setCostureiroId(int costureiroId) {
		this.costureiroId = costureiroId;
	}
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	public List<Compra> getCompraCostureiroList() {
		return compraCostureiroList;
	}
	public void setCompraCostureiroList(List<Compra> compraCostureiroList) {
		this.compraCostureiroList = compraCostureiroList;
	}
	public Set<Modelo> getModelos() {
		return modelos;
	}
	public void setModelos(Set<Modelo> modelos) {
		this.modelos = modelos;
	}
	
	
}