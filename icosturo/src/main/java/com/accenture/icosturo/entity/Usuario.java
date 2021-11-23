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
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
@Table(name = "Usuario")

public class Usuario {

	@Id
    @Column(name = "id_user")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int userId;
	private String email;
	private String password;
	private String telefone;
	private String nome;
	private String sobrenome;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy="usuario", cascade = CascadeType.ALL)
	@JsonIgnore
    private List<Designer> designerList = new ArrayList<>();
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy="usuario", cascade = CascadeType.ALL)
	@JsonIgnore
	private List<Costureiro> costureiroList = new ArrayList<>();
	@OneToMany(fetch = FetchType.LAZY, mappedBy="usuario", cascade = CascadeType.ALL)
	@JsonIgnore
	private List<Cliente> clienteList = new ArrayList<>();
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getSobrenome() {
		return sobrenome;
	}
	public void setSobrenome(String sobrenome) {
		this.sobrenome = sobrenome;
	}
	public List<Designer> getDesignerList() {
		return designerList;
	}
	public void setDesignerList(List<Designer> designerList) {
		this.designerList = designerList;
	}
	public List<Costureiro> getCostureiroList() {
		return costureiroList;
	}
	public void setCostureiroList(List<Costureiro> costureiroList) {
		this.costureiroList = costureiroList;
	}
	public List<Cliente> getClienteList() {
		return clienteList;
	}
	public void setClienteList(List<Cliente> clienteList) {
		this.clienteList = clienteList;
	}

	
	

}