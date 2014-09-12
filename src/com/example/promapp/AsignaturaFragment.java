package com.example.promapp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class AsignaturaFragment extends Fragment{
	EvaluationListFragment evaluaciones;
	AddEvaluacionFragment nAsig;
	Bundle args;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		
	}
	String nombre_asignatura;
	Toast toast;
	View rootView;
	Asignatura asig;
	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		rootView = inflater.inflate(R.layout.fragment_asignatura, container, false);
		args = getArguments();
		nombre_asignatura = getArguments().getString("nombre_asignatura");
		String sem_id = ((MainActivity) getActivity()).getPreferences("semester_id");
		long id = Long.parseLong(sem_id);
		
		toast =  Toast.makeText(getActivity().getApplicationContext(),
	    		 "Datos agregados Correctamente", 
	    		 Toast.LENGTH_SHORT);
		asig = ((MainActivity)getActivity()).mHelper.getSubjectByName(id, nombre_asignatura );
		if(asig != null){
			((TextView) rootView.findViewById(R.id.editTextChangeRequerido))
						.setText(asig.getNotaRequerida()+"");
			((TextView) rootView.findViewById(R.id.textViewTitleEditAsig))
	        			.setText(asig.getNombre());
            ((TextView) rootView.findViewById(R.id.textViewPorcentaje))
	        			.setText("Porcentaje Ingresado: "+asig.getPorcentaje());
			((TextView) rootView.findViewById(R.id.textViewNotaRequerida))
				        .setText(""+asig.getNotaRequerida());
			((TextView) rootView.findViewById(R.id.textViewNotaSimulada))
						.setText(""+asig.getNotaSimulada());
			((TextView) rootView.findViewById(R.id.textViewDiferencia))
						.setText(""+asig.getDiferencia());
			toast.setText(nombre_asignatura);
			toast.show();
		}else{
			toast.setText("Algo anda mal con la db");
			toast.show();
		}
	
		Button button = (Button) rootView.findViewById(R.id.buttonAddNewEval);
		button.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				 FragmentManager fm = getActivity().getSupportFragmentManager(); 
		    	 AddEvaluacionFragment nAsig= new AddEvaluacionFragment(); 
				 args.putLong("asignatura", asig.getID());
				 nAsig.setArguments(args);
		    	 nAsig.show(fm, "add_eval"); 
			}
		});

		
 	 
		args.putLong("asignatura_id", asig.getID());
		evaluaciones = new EvaluationListFragment();
		evaluaciones.setArguments(args);
		FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
			ft.replace(R.id.row1, evaluaciones,"lista_evaluaciones")
		    .commit();
		return rootView;
	}

}
