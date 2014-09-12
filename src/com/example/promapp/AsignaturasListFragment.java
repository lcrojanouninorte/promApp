package com.example.promapp;



import java.util.ArrayList;
import java.util.zip.Inflater;



import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;



public class AsignaturasListFragment extends ListFragment{
	String[] text; 
  
	Bundle args;
 
  @Override  
  public View onCreateView(LayoutInflater inflater, ViewGroup container,  
    Bundle savedInstanceState) {  
	 // View rootView = inflater.inflate(R.layout.listfragment_asignaturas, container, false);
	  //obtener las asignaturas creadas, si las hay
	  args = new Bundle();
	  String sem_id = ((MainActivity) getActivity()).getPreferences("semester_id");
	  long id = Long.parseLong(sem_id);
	  String[] asignaturas = ((MainActivity) getActivity()).mHelper.getSubjectsNames(id);
	  if(asignaturas != null){
		  text = asignaturas;
	  }else{
		  text = new String[1];
		  text[0] = "!No has ingresado ninguna asignatura!";
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
			AsignaturaFragment asigFragment = new AsignaturaFragment();
	 
			val = text[position];
			args.putString("nombre_asignatura", val);
			asigFragment.setArguments(args); 
			FragmentTransaction ft = getFragmentManager().beginTransaction();
			ft.replace(R.id.container, asigFragment,"asignatura");	
			ft.addToBackStack(null);
			ft.commit();
		    Toast.makeText(getActivity(), val, Toast.LENGTH_SHORT).show();
		}
  }  

}
