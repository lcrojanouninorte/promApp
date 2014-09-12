package com.example.promapp;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class EvaluationListFragment extends ListFragment {
	String[] text; 
	  @Override  
	  public View onCreateView(LayoutInflater inflater, ViewGroup container,  Bundle savedInstanceState) {  
		 // View rootView = inflater.inflate(R.layout.listfragment_asignaturas, container, false);
		  //obtener las asignaturas creadas, si las hay
		  long asignatura_id = getArguments().getLong("asignatura_id");
		  String[] evaluaciones = ((MainActivity) getActivity()).mHelper.getEvaluacionesNames(asignatura_id);
		  if(evaluaciones != null){
			  text = evaluaciones;
		  }else{
			  text = new String[1];
			  text[0] = "!No has ingresado ninguna evaluación!";
		  }
		  ArrayAdapter<String> adapter = new ArrayAdapter<String>(  
				     inflater.getContext(), 
				     android.R.layout.simple_list_item_1,  
				     text);  
				   setListAdapter(adapter);  
				   return super.onCreateView(inflater, container, savedInstanceState); 
	 
	  }  
	  @Override  
	  public void onListItemClick(ListView l, View v, int position, long id) {  
		  super.onListItemClick(l, v, position, id);
			String val;
			if(text[position] != "!No has ingresado ninguna asignatura!"){

			}
	  }  
}
