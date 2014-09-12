package com.example.promapp;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class AddAsignaturaFragment extends DialogFragment{
	View vi;
	Toast toast;
	@Override   
	 public void onCreate(Bundle savedInstanceState) {       
		 super.onCreate(savedInstanceState);    
		 //student = new Student();  
	 }
	 @Override   
	 public Dialog onCreateDialog(Bundle savedInstanceState) { 
		 //TODO: cargar un promedio deseado de sugerencia
		 vi = getActivity().getLayoutInflater().inflate(R.layout.fragment_add_asignatura, null); 
		 AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
		 builder.setView(vi);
		 builder.setTitle(R.string.text_title_nueva_asignatura);
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
	                    	 if(!insertSubject(vi)){    
	                    		 
	                    	 }else{
		                         toast.setText("Datos Ingresados Correctamente");
		                       	 toast.show();

		                       	 dismiss();
		                         
		                       	 //recargar layout menuprincipal
		                       
		                       	((MainActivity)getActivity()).reloadFragment("semestre");
	                        }
	                     }
	                 });
	     }
	 }
		private boolean insertSubject(View v){
			 //obtener los views y sus datos
			//Estudiante
			 boolean go = true;
			 Asignatura e = new Asignatura();
		   	 EditText edit = (EditText)v.findViewById(R.id.editTextNombreAsignatura);
		   	 String nombre = edit.getText().toString();
		   	 //TODO:verificar que la asignatura no exista
		   	 if(!e.setNombre(nombre)){
		   		 edit.setError("Olvidaste colocar el nombre!");
		   		 go =false;
		   	 }
		   	 edit = (EditText)v.findViewById(R.id.editTextCreditosAsignatura);
		   	 String creditos = edit.getText().toString();
		   	 if(!e.setCreditos(creditos)){
		   		 edit.setError("Ingrese creditos validos");
		   		 go =false;
		   	 }
		   	 edit = (EditText)v.findViewById(R.id.editTextNotaRequerida);
		   	 String nota = edit.getText().toString();
		   	 if(!e.setNotaRequerida(nota)){//el nota deseado es la requeridad
		   		 edit.setError("Estos creditos no son validos");
		   		go =false;
		   	 }
		   	 
		   	  //TODO: verificar si el promedio deseado cumple con las restriccioen
		   	  
		   	 
		   	 if(go){
				String sem_id = ((MainActivity) getActivity()).getPreferences("semester_id");
				long id = Long.parseLong(sem_id);
			   	e.setSemestreID(id);
		   	    DatabaseHelper mHelper =  ((MainActivity)getActivity()).mHelper;
		   	    e.setID(mHelper.insertSubject(e,id));
				if(e.getID() == -1){
					go = false;
					toast.setText("Error al Ingresar Datos");
                  	toast.show();
                  	edit.setError(null);					
				}else{
					((MainActivity)getActivity()).setPreferences(e.getNombre(),e.getID()+"" );

				}
			}
		   	((MainActivity)getActivity()).mHelper.closeDB();
			return go;
		}

}
