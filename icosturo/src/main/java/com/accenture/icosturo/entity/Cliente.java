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
@Table(name = "Cliente")

public class Cliente {
	
	@Id
    @Column(name = "id_cliente")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int clienteId;
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "id_user", referencedColumnName = "id_user")
	private Usuario usuario;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy="cliente", cascade = CascadeType.ALL)
	@JsonIgnore
	private List<Compra> compraClienteList = new ArrayList<>();


	public int getClienteId() {
		return clienteId;
	}
	public void setClienteId(int clienteId) {
		this.clienteId = clienteId;
	}
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	public List<Compra> getCompraClienteList() {
		return compraClienteList;
	}
	public void setCompraClienteList(List<Compra> compraClienteList) {
		this.compraClienteList = compraClienteList;
	}
	
	

}