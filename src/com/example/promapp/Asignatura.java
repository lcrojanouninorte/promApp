package com.example.promapp;

import android.R.bool;

public class Asignatura {
	private String nombre = "";
	private int creditos;
	private float nota_real;
	private float nota_requerida;
	private float nota_simulada;
	private long semesterId;
	private long id;
	private String estado  ="";
	private int porcentaje = 0;
	private Evaluation[] evaluaciones;
	

	public String getNombre() {
		return nombre;
	}
	public boolean setNombre(String nombre) {
		if(!nombre.isEmpty() ){
			this.nombre = nombre;
		}else{
			return false;
		}
		return true;
	}
	public int getCreditos() {
		return this.creditos;
	}
	public boolean setCreditos(String cred) {
		if(!cred.isEmpty()){
			int creditos = Integer.parseInt(cred);
			if(creditos >=0){
				this.creditos = creditos;
			}else{
				return false;
			}
			return true;
		}else{
			return false;
		}
	}
		public boolean setNotaRequerida(String nota) {
		if(!nota.isEmpty()){
			float prom = Float.parseFloat(nota);
			if(prom>=0 && prom<=5){
				this.nota_requerida = prom;
				
			}else{
				return false;
			}
			return true;
		}else{
			return false;
		}
		
	}
		public void setSemestreID(long id) {
			this.semesterId = id;
			
		}
		public long getID() {
			// TODO Auto-generated method stub
			return this.id;
		}
		public void setID(long id) {
			// TODO Auto-generated method stub
			this.id =  id;
		}
		public String getEstado() {
			return this.estado;
		}
		
	
		public void setEstado(String estado) {

			this.estado = estado;
		}
		public float getNotaRequerida() {
			
			return this.nota_requerida;
		}
		public float getNotaReal() {
			// TODO Auto-generated method stub
			return this.nota_real;
		}
		public float getNotaSimulada() {
			// TODO Auto-generated method stub
			return this.nota_simulada;
		}
		public boolean setNota_simulada(String nota_simulada) {
			if(!nota_simulada.isEmpty()){
				float prom = Float.parseFloat(nota_simulada);
				if(prom>=0 && prom<=5){
					this.nota_simulada= prom;
				}else{
					return false;
				}
				return true;
			}else{
				return false;
			}
		}
		public int getPorcentaje() {
			
			return this.porcentaje;
		}
		public float getDiferencia() {
			// TODO Auto-generated method stub
			return this.nota_requerida - this.nota_simulada;
		}
		public boolean setNotaReal(String nota) {
			if(!nota.isEmpty()){
				float prom = Float.parseFloat(nota);
				if(prom>=0 && prom<=5){
					this.nota_real= prom;
				}else{
					return false;
				}
				return true;
			}else{
				return false;
			}
			
		}
		
		public void setPorcentaje() {
			//TODO:
			//calcular porcentaje basado en las evaluaciones
		}
public long getSemesterId() {
	return semesterId;
}
		
public void setEvaluaciones(Evaluation[] evaluaciones) {
	this.evaluaciones = evaluaciones;
}
public Evaluation[] getEvaluaciones() {
	return evaluaciones;
}

}
