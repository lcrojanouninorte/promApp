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
import android.widget.Button;
import android.widget.EditText;
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
	@Override
	public View onCreateView(LayoutInflater inflater,@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.fragment_simulador_semestral, container, false);
		toast = new Toast(getActivity());
		EditText promDeseado =(EditText) v.findViewById(R.id.editTextPromAcumDeseado);
		EditText promRequerido =(EditText) v.findViewById(R.id.editTextPromRequerido);
		EditText creditosCursados =(EditText) v.findViewById(R.id.editTextCreditosCursados);
		//leer de la bd los datos solicitdados del fragment
		DatabaseHelper helper = ((MainActivity)getActivity()).mHelper;
		Student stud = helper.getStudent();
		Semester sem = helper.getSemester(stud.getID());
		if(sem != null && stud!=null){
			promDeseado.setText(""+stud.getPromAcumDeseado());
			promRequerido.setText(""+sem.getPromRequerido());
			creditosCursados.setText(""+stud.getCredCursados());
		}else{
			toast.setText("No hay usuario creado");
		}
		helper.closeDB();
		
		
		//los text boxes con los datos reales
		
		
	//Button button = (Button) rootView.findViewById(R.id.buttonCambiarDeseado);

		
		return v;
	}

}
