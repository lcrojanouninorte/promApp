package com.example.promapp;




import android.R.bool;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.util.AttributeSet;
import android.util.Log;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;


public class MenuPrincipalFragment extends Fragment {
	private static final String DIALOG_FIRST = "FIRST";
	private String TAG = MenuPrincipalFragment.class.getSimpleName();
	private SemestreFragment semesterFragment;
	private SimuladorSemestralFragment simulador ;
	private AsignaturasListFragment asignaturas ;
	
	public MenuPrincipalFragment() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
	}
		
	private DatabaseHelper mHelper;
	private Student student;
	 Toast toast;
	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View rootView = inflater.inflate(R.layout.fragment_menu_principal, container, false);
		toast = Toast.makeText(getActivity().getApplicationContext(),"Bienvenido Nuevamente", Toast.LENGTH_SHORT);
		DatabaseHelper mHelper =  ((MainActivity)getActivity()).mHelper;
        student = mHelper.getStudent();
		  if(student == null){
			  //Dialog Fragment si es primera ves en la aplicacion
	        	FragmentManager fm = getActivity().getSupportFragmentManager(); 
		    	FirstTimeFragment nAsig = new FirstTimeFragment();                
		    	nAsig.show(fm, "FirstTime"); 
	        }else{
	        	//actualizar vista 
	            ((TextView) rootView.findViewById(R.id.textViewNombreEstudiante))
	            	        .setText("Nombre: "+student.getNombre());
	            ((TextView) rootView.findViewById(R.id.textViewPromedioAcum))
            		        .setText("Prom. Acum: "+student.getPromAcum());
	            ((TextView) rootView.findViewById(R.id.textViewCreditosCursados))
	            			.setText("Creditos Cursados: "+student.getCredCursados());
	            ((TextView) rootView.findViewById(R.id.textViewPromDeseado))
            				.setText("Prom. Requerido: "+student.getPromAcumDeseado());
	            toast.setText("Hola! Bienvenido de nuevo");
	            toast.show();
	        }
		Button button = (Button) rootView.findViewById(R.id.buttonEditarSemestre);
		button.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {	
				//Abrir fragment semestre, y cargar los datos requeridos
				Log.d(TAG, "Editar Semestre on click");
				int val;
				Bundle args = new Bundle();
		    	SemestreFragment semestre = new SemestreFragment();
			    FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
				ft.replace(R.id.container, semestre,"semestre")
				.addToBackStack(null)
				.commit();
			}
		});
		Button buttonSimuladorSemestral = ((Button) rootView.findViewById(R.id.buttonSimuladorPromSemestral));
		buttonSimuladorSemestral.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Log.d(TAG, "Editar Simulador Prom Semestral");
				int val;
				
				Bundle args = new Bundle();
				((MainActivity)getActivity()).showSimuladorSemestralFragment();
			}
		});
		Button buttonSimuladorAsignatura = ((Button) rootView.findViewById(R.id.buttonSimPromAsignaturas));
		buttonSimuladorAsignatura.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				
				Log.d(TAG, "Editar Simulador asignaturas");
				int val;
				Bundle args = new Bundle();
				((MainActivity)getActivity()).showAsignaturaFragment();
			}
		});
		Button buttonHistoria = ((Button) rootView.findViewById(R.id.buttonHistoria));
		buttonHistoria.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Log.d(TAG, "Mostrar historia");
				int val;
				Bundle args = new Bundle();
				//((MainActivity)getActivity()).showHistoria();
			}
		});
	   mHelper.closeDB();
	    
		return rootView;
	}

}
