package com.usuarios.model;

import java.util.Date;

public class covid {

	private Date fecha;
	private int dia;
	private int mes;
	private int casos;
	private int muertes;
	private String pais;
	private String codpais;
	private String codpais2;
	private String pop;
	
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public int getDia() {
		return dia;
	}
	public void setDia(int dia) {
		this.dia = dia;
	}
	public int getMes() {
		return mes;
	}
	public void setMes(int mes) {
		this.mes = mes;
	}
	public int getCasos() {
		return casos;
	}
	public void setCasos(int casos) {
		this.casos = casos;
	}
	public int getMuertes() {
		return muertes;
	}
	public void setMuertes(int muertes) {
		this.muertes = muertes;
	}
	public String getPais() {
		return pais;
	}
	public void setPais(String pais) {
		this.pais = pais;
	}
	public String getCodpais() {
		return codpais;
	}
	public void setCodpais(String codpais) {
		this.codpais = codpais;
	}
	public String getCodpais2() {
		return codpais2;
	}
	public void setCodpais2(String codpais2) {
		this.codpais2 = codpais2;
	}
	public String getPop() {
		return pop;
	}
	public void setPop(String pop) {
		this.pop = pop;
	}
	
	
	
}