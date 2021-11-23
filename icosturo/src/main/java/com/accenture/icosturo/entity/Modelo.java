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
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
@Table(name = "Modelo")
public class Modelo {
	
	@Id
    @Column(name = "id_modelo")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int modeloId;
	private String tipo;
	private String cor;
	private double valor;
	@Column(name = "image_filepath_1")
	private String imageUm;
	@Column(name = "image_filepath_2")
	private String imageDois;
	@Column(name = "image_filepath_3")
	private String imageTres;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_designer", referencedColumnName = "id_designer")
	private Designer designer;
	
    @ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "modelo_costureiro",
			joinColumns = {
					@JoinColumn(name = "id_modelo", referencedColumnName = "id_modelo")
			},
			inverseJoinColumns = {
					@JoinColumn(name = "id_costureiro", referencedColumnName = "id_costureiro")
			}
			)
    private Set<Costureiro> costureiros = new HashSet<Costureiro>();
    
    @OneToMany(fetch = FetchType.LAZY, mappedBy="modelo", cascade = CascadeType.ALL)
	@JsonIgnore
    private List<Compra> compraModeloList = new ArrayList<>();

	
	public int getModeloId() {
		return modeloId;
	}
	public void setModeloId(int modeloId) {
		this.modeloId = modeloId;
	}
	
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public String getCor() {
		return cor;
	}
	public void setCor(String cor) {
		this.cor = cor;
	}
	public String getImageUm() {
		return imageUm;
	}
	public void setImageUm(String imageUm) {
		this.imageUm = imageUm;
	}
	public String getImageDois() {
		return imageDois;
	}
	public void setImageDois(String imageDois) {
		this.imageDois = imageDois;
	}
	public String getImageTres() {
		return imageTres;
	}
	public void setImageTres(String imageTres) {
		this.imageTres = imageTres;
	}
	public Designer getDesigner() {
		return designer;
	}
	public void setDesigner(Designer designer) {
		this.designer = designer;
	}

	public Set<Costureiro> getCostureiros() {
		return costureiros;
	}
	public void setCostureiros(Set<Costureiro> costureiros) {
		this.costureiros = costureiros;
	}
	public List<Compra> getCompraModeloList() {
		return compraModeloList;
	}
	public void setCompraModeloList(List<Compra> compraModeloList) {
		this.compraModeloList = compraModeloList;
	}
	public double getValor() {
		return valor;
	}
	public void setValor(double valor) {
		this.valor = valor;
	}

}