package com.example.promapp;

import android.R.integer;

public class Semester {
	private float promRequerido; //Promedio Root
	private String semestre;
	private String estado;
	private int creditosSemestre;
	private long student_id;
	private float promReal;
	private long mId;
	private float promSimulado;
	private float diferencia = 0;
	
	

	public Semester(String semestre, long student_id, float promRequerido) {
		// TODO Auto-generated constructor stub
		this.promRequerido = 0;
		this.semestre = semestre;
		this.estado = "Vacio";
		this.creditosSemestre = 0;
		this.student_id = student_id;
		this.promReal = 0;
		this.promRequerido = promRequerido;

	}

	public Semester() {
		this.promRequerido = 0;
		this.semestre = "";
		this.estado = "Vacio";
		this.creditosSemestre = 0;
		this.student_id = 0;
		this.promReal = 0;
		this.promRequerido = 0;
	}

	public float getPromRequerido() {
		return this.promRequerido;
	}
	public boolean setPromRequerido(float promRequerido) {
		if(promRequerido>0 && promRequerido<5.0){
			this.promRequerido = promRequerido;
		}else{
			return false;
		}
		return true;
		
	}
	public long getStudent_id() {
		return student_id;
	}
	public String getSemestre() {
		return semestre;
	}
	public long getID() {
		return mId;
	}
	public void setID(long mId) {
		this.mId = mId;
	}
	public String getName() {

		return semestre;
	}

	public String getEstado() {
		// TODO Auto-generated method stub
		return estado;
	}
	public void setEstado(String estado) {

		this.estado = estado;
	}
	

	public int getCreditos() {
		
		return creditosSemestre;
	}

	public float getPromReal() {

		return promReal;
	}

	public void setNombre(String sem) {
		this.semestre = sem;
		
	}

	public void setCreditos(int creditos) {
			this.creditosSemestre = creditos;
	}

	public void setPromReal(float prom) {
		this.promReal = prom;
		
	}

	public void setStudentId(long student) {
		this.student_id = student;
	}

	public float getPromSimulado() {
		
		return this.promSimulado;
	}
	public void setPromSimulado(float promSimulado) {
		this.promSimulado = promSimulado;
	}

	public void setDiferencia(float dif) {
	
		 this.diferencia = dif;
	}
	public float getDiferencia() {
		return diferencia;
	}
	
}