package com.example.promapp;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.webkit.WebView.FindListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class SimuladorSemestralFragment extends Fragment {
	
	public SimuladorSemestralFragment() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
	}
		
Toast toast;
EditText promDeseado;
float maxDeseado;
Student stud;
Semester sem;
	@Override
	public View onCreateView(LayoutInflater inflater,@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.fragment_simulador_semestral, container, false);
		toast = Toast.makeText(getActivity().getApplicationContext(),"Bienvenido Nuevamente", Toast.LENGTH_SHORT);
		promDeseado =(EditText) v.findViewById(R.id.editTextPromAcumDeseado);
		EditText promRequerido =(EditText) v.findViewById(R.id.editTextPromRequerido);
		EditText creditosCursados =(EditText) v.findViewById(R.id.editTextCreditosCursados);
		TextView reco =(TextView) v.findViewById(R.id.textViewRecomendacin);
		//leer de la bd los datos solicitdados del fragment
		DatabaseHelper helper = ((MainActivity)getActivity()).mHelper;
		stud = helper.getStudent();
		sem = helper.getSemester(stud.getID());
		
		if(sem != null && stud!=null){
			((MainActivity) getActivity()).setPreferences("estudiante_id", stud.getID()+"");
			promDeseado.setText(""+stud.getPromAcumDeseado());
			promRequerido.setText(""+sem.getPromRequerido());
			creditosCursados.setText(""+stud.getCredCursados());
			
		 //calcular un maximo deseado como sugerencia
	
		//primero verificar, si ya el estudiante ingreso creditos a cursar
		if(sem.getCreditos()>0){
			maxDeseado = ((MainActivity)getActivity()).simHelper.getMaxDeseado
					  (stud.getPromAcum(), stud.getPromAcumDeseado(), 
					   stud.getCredCursados(),sem.getCreditos());
			((MainActivity) getActivity()).setPreferences("maxDeseado", maxDeseado+"");
			reco.setText("Te recomiendo un promedio deseado entre " + stud.getPromAcum()
					+ " y " + maxDeseado + " Para " + sem.getCreditos() + " Creditos.");
			
		}else{		
			float maxDeseado = ((MainActivity)getActivity()).simHelper.getMaxDeseado
					  (stud.getPromAcum(), stud.getPromAcumDeseado(), stud.getCredCursados(),15);
			((MainActivity) getActivity()).setPreferences("maxDeseado", maxDeseado+"");
			reco.setText("Te recomiendo un promedio deseado entre " + stud.getPromAcum()
					+ " y " + maxDeseado + " Si Tomar�s m�s de 15 Creditos.");
		}  
		}else{
			toast.setText("No hay usuario creado");
		}
		helper.closeDB();
		
		
		Button button = (Button) v.findViewById(R.id.buttonCambiarAcumDeseado);
		button.setOnClickListener(new OnClickListener() {
			//cambiar promedio acumulado deseado, y todos los que dependan de este
			@Override
			public void onClick(View v) {
				String prom = promDeseado.getText().toString();	
				//TODO: verificar que sea menor que el promedio maximo
				if(!prom.isEmpty()){
					float ndeseado = Float.parseFloat(prom);
					if(ndeseado<=maxDeseado){
						String mensaje = ((MainActivity) getActivity()).mHelper.setPromDeseadoAcumulado(stud, sem, ndeseado);
						if(mensaje != "OK"){
							 promDeseado.setError("Estas en limites imposibles, Utiliza el rango sugerido!");
						}else{
							((MainActivity) getActivity()).reloadFragment("semestre");
							toast.setText(mensaje);
							toast.show();
						}
					}else{
						 promDeseado.setError("Mira la sugerecia, o deberas sacar m�s de 5 en el semestre!");
					}
				}else{
					 promDeseado.setError("No lo dejes vacio!");
				}
			}
		});

		
		return v;
	}

}