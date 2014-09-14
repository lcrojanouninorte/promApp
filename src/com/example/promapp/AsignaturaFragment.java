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
import android.widget.ToggleButton;

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
	String mensaje;
	Asignatura asig;
	ToggleButton b ;
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
			double req = ((MainActivity)getActivity()).roundTwoDecimals(asig.getNotaRequerida());
			((TextView) rootView.findViewById(R.id.editTextChangeRequerido))
			
						.setText(req+"");
			((TextView) rootView.findViewById(R.id.textViewTitleEditAsig))
	        			.setText(asig.getNombre());
			int por = ((MainActivity)getActivity()).mHelper.getPorcentajeIngresado(asig.getID());
            ((TextView) rootView.findViewById(R.id.textViewPorcentaje))
	        			.setText("Porcentaje Ingresado: "+por);
            
			((TextView) rootView.findViewById(R.id.textViewNotaRequerida))
				        .setText(""+req);
			((TextView) rootView.findViewById(R.id.textViewNotaSimulada))
						.setText(""+asig.getNotaSimulada());
			toast.setText(nombre_asignatura);
			toast.show();
		}else{
			toast.setText("Algo anda mal con la db");
			toast.show();
		}
		
		//// get your ToggleButton
		 b = (ToggleButton) rootView.findViewById(R.id.toggleButtonFinalizarAsignatura);
		 if (asig.getEstado().equals("Finalizado")){
			 b.setChecked(true);
		 }else{
			 b.setChecked(false);
		 }
		// attach an OnClickListener
		b.setOnClickListener(new OnClickListener()
		{
		    @Override
		    public void onClick(View v)
		    {
		      if( b.isChecked() ){
		    	  //Finalizar Asignatura
		    	  toast.setText("Finalizada!");
		    	  
		    	mensaje = ((MainActivity)getActivity()).mHelper.finalizarAsignatura(asig);
		    	if(mensaje.equals("OK")){
		    		
		    	}else{
		    		toast.setText(mensaje);
		    		b.setChecked(false);
		    	}
		    	  
		      }else{
		    	  //Todo: recalcular
		    	  toast.setText("En Curso!");
		      }
		      toast.show();
		    }
		   
		});
		Button button2 = (Button) rootView.findViewById(R.id.buttonSimuladorSemestral);
		button2.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				SimulatorHelper s = new SimulatorHelper();
				Evaluation[] evals = ((MainActivity)getActivity()).mHelper.getEvaluations(asig.getID());
				Evaluation[] evalsSim = s.simularPromedioEvaluaciones(asig, evals);
			}
		});
	
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
		args.putFloat("nota_requerida", asig.getNotaRequerida());
		evaluaciones = new EvaluationListFragment();
		evaluaciones.setArguments(args);
		FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
			ft.replace(R.id.row1, evaluaciones,"lista_evaluaciones")
		    .commit();
		return rootView;
	}

}
