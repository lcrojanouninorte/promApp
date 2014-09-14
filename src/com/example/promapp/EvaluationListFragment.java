package com.example.promapp;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

public class EvaluationListFragment extends ListFragment {
	String[] text; 
	  @Override  
	  public View onCreateView(LayoutInflater inflater, ViewGroup container,  Bundle savedInstanceState) {  
		 // View rootView = inflater.inflate(R.layout.listfragment_asignaturas, container, false);
		  //obtener las asignaturas creadas, si las hay
		  long asignatura_id = getArguments().getLong("asignatura_id");
		  List<HashMap<String,String>>   aList = ((MainActivity) getActivity()).mHelper.getEvaluationsItems(asignatura_id);
		 // String[] evaluaciones = ((MainActivity) getActivity()).mHelper.getEvaluacionesNames(asignatura_id);
		  if( aList  != null){
			  //text = evaluaciones;
			  String[] from = { "nombre","nota","porcentaje","estado"};
	          // Ids of views in listview_layout
	          int[] to = { R.id.nombre,R.id.nota,R.id.porcentaje,R.id.estado};

	      // Instantiating an adapter to store each items
	      // R.layout.listview_layout defines the layout of each item
	      SimpleAdapter adapter = new SimpleAdapter(getActivity().getBaseContext(), 
	    		  aList, R.layout.list_item_evaluaciones_layout, from, to);
	      setListAdapter(adapter);

	      return super.onCreateView(inflater, container, savedInstanceState);

		      
			  
		  }else{
			  text = new String[1];
			  text[0] = "!No has ingresado ninguna evaluación!";
			  ArrayAdapter<String> adapter = new ArrayAdapter<String>(  
					     inflater.getContext(), 
					     android.R.layout.simple_list_item_1,  
					     text);  
					   setListAdapter(adapter);  
					   return super.onCreateView(inflater, container, savedInstanceState); 
		  }

		
		  
	
	 
	  }  
	  @Override  
	  public void onListItemClick(ListView l, View v, int position, long id) {  
		  super.onListItemClick(l, v, position, id);
			String val;
			if(text[position] != "!No has ingresado ninguna asignatura!"){

			}
	  }  
}
