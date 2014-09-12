package com.example.promapp;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;


public class FirstTimeFragment extends DialogFragment{
	 private View vi;
	 Toast toast;
	 @Override   
	 public void onCreate(Bundle savedInstanceState) {       
		 super.onCreate(savedInstanceState);    
		 
		 
	 }
	
	 @Override  
	 public Dialog onCreateDialog(Bundle savedInstanceState) { 
		 vi = getActivity().getLayoutInflater().inflate(R.layout.fragment_firsttime, null); 
		 AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
		 builder.setView(vi);
		 builder.setTitle(R.string.welcome_title);
		 builder.setPositiveButton(android.R.string.ok, 
				 new DialogInterface.OnClickListener() {
				 	@Override
		            public void onClick(DialogInterface dialog, int which)
		            {
		                //Do nothing here solo para compatibilidad
		            }
		         });
		    

		 return builder.create();
		
	}  
	@Override
	 public void onStart()
	 {
	     super.onStart();    //super.onStart() is where dialog.show() is actually called on the underlying dialog, so we have to do it after this point
	     AlertDialog d = (AlertDialog)getDialog();
	     toast = Toast.makeText(getActivity().getApplicationContext(),
	    		 "Datos agregados Correctamente", 
	    		 Toast.LENGTH_SHORT);
	     if(d != null)
	     {
	         Button positiveButton = (Button) d.getButton(Dialog.BUTTON_POSITIVE);
	         positiveButton.setOnClickListener(
	        		 new View.OnClickListener() {
	                     @Override
	                     public void onClick(View v)
	                     {
	                    	 if(!insertStudentFirstTime(vi)){    
	                    		 
	                    	 }else{
		                         toast.setText("Datos Ingresados Correctamente");
		                       	 toast.show();

		                       	 dismiss();
		                         
		                       	 //recargar layout menuprincipal
		                       
		                       	((MainActivity)getActivity()).reloadFragment("menu_principal");
	                        }
	                     }
	                 });
	     }
	 }
		private boolean insertStudentFirstTime(View v){
			 //obtener los views y sus datos
			//Estudiante
			 boolean go = true;
			 Student e = new Student();
		   	 EditText edit = (EditText)v.findViewById(R.id.editTextNombre);
		   	 String nombre = edit.getText().toString();
		   	 if(!e.setNombre(nombre)){
		   		 edit.setError("Olvidaste colocar tu nombre!");
		   		 go =false;
		   	 }
		   	 edit = (EditText)v.findViewById(R.id.editTextPromAcum);
		   	 String promAcum = edit.getText().toString();
		   	 if(!e.setPromAcum(promAcum)){
		   		 edit.setError("Ingresa un promedio entre 0 y 5");
		   		 go =false;
		   	 }
		   	 edit = (EditText)v.findViewById(R.id.editTextCredCursados);
		   	 String credCursados = edit.getText().toString();
		   	 if(!e.setCredCursados(credCursados)){
		   		 edit.setError("Estos creditos no son validos");
		   		go =false;
		   	 }
		   	 e.setPromAcumDeseado(e.getPromAcum()); //el prom deseado por defecto es el acumulado real
		   	 if(go){
			   	 //semestre
			    Spinner spinner = (Spinner)v.findViewById(R.id.spinnerSemestreACusar);
			   	String semestre = spinner.getSelectedItem().toString();
		   	    DatabaseHelper mHelper =  ((MainActivity)getActivity()).mHelper;
		   	    e.setID(mHelper.insertStudent(e));
				if(e.getID() != -1){
					//crear un semestre
					Semester sem = new Semester(semestre, e.getID(),e.getPromAcum());
					sem.setID(mHelper.insertSemester(e.getID(),sem));
					if(sem.getID() == -1){
						go = false;
						toast.setText("Error al Ingresar Datos");
	                  	toast.show();
	                  	mHelper.clearAll();
	                  	edit.setError(null);
	                  	((MainActivity)getActivity()).mHelper.closeDB();
					}else{
						((MainActivity)getActivity()).setPreferences("semestre", sem.getSemestre());
					}
				}else{
					go = false;
					toast.setText("Error al Ingresar Datos");
                  	toast.show();
                  	edit.setError(null);
				}
			}
			return go;
		}
	  
	 
}
