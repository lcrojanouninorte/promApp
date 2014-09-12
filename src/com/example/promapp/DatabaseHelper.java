package com.example.promapp;

import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper  extends SQLiteOpenHelper {
	private static final String DB_NAME = "students.db";    
	private static final int VERSION = 7;        
	private static final String COLUMN_ID= "_id"; 
	
	private static final String TABLE_STUDENT = "students";    
	private static final String COLUMN_STUDENT_NAME = "nombre"; 
	private static final String COLUMN_STUDENT_PROM_ACUM = "prom_acum";
	private static final String COLUMN_STUDENT_PROM_ACUM_REQUERIDO = "prom_acum_requerido";
	private static final String COLUMN_STUDENT_CREDITOS_CURSADOS = "creditos_cursados"; 
	
	private static final String TABLE_SEMESTER= "semesters";    
	private static final String COLUMN_SEMESTER_NAME = "nombre"; 
	private static final String COLUMN_SEMESTER_PROM_REQUERIDO = "prom_requerido";  
	private static final String COLUMN_SEMESTER_PROM_REAL = "prom_real"; 
	private static final String COLUMN_SEMESTER_PROM_SIMULADO = "prom_simulada"; 
	private static final String COLUMN_SEMESTER_CREDITOS = "creditos";
	private static final String COLUMN_SEMESTER_ESTADO = "estado";
	private static final String COLUMN_SEMESTER_STUDENT_ID = "student_id";
	
	private static final String TABLE_SUBJECTS= "subjects";    
	private static final String COLUMN_SUBJECT_NAME = "nombre"; 
	private static final String COLUMN_SUBJECT_NOTA_REQUERIDA = "nota_requerido";  
	private static final String COLUMN_SUBJECT_NOTA_REAL = "nota_real";
	private static final String COLUMN_SUBJECT_NOTA_SIMULADA = "nota_simulada";
	private static final String COLUMN_SUBJECT_CREDITOS = "creditos";
	private static final String COLUMN_SUBJECT_ESTADO = "estado";
	private static final String COLUMN_SUBJECT_SEMESTER_ID = "semester_id";
	
	private static final String TABLE_EVALUATIONS= "evaluations";    
	private static final String COLUMN_EVALUATION_NAME = "nombre"; 
	private static final String COLUMN_EVALUATION_NOTA_REQUERIDA = "nota_requerida";  
	private static final String COLUMN_EVALUATION_NOTA_REAL = "nota_real"; 
	private static final String COLUMN_EVALUATION_NOTA_SIMULADA = "nota_simulada"; 
	private static final String COLUMN_EVALUATION_PORCENTAJE = "porcentaje";
	private static final String COLUMN_EVALUATION_ESTADO = "estado";
	private static final String COLUMN_EVALUATION_SUBJECT_ID = "subject_id";
	
	private static final String CREATE_TABLE_STUDENTS = 
			"CREATE TABLE " + TABLE_STUDENT 
			+ "(" 
            + COLUMN_ID + " INTEGER PRIMARY KEY autoincrement, " 
			+ COLUMN_STUDENT_NAME + " TEXT, " 
            + COLUMN_STUDENT_PROM_ACUM + " REAL, " 
			+ COLUMN_STUDENT_PROM_ACUM_REQUERIDO + " REAL, " 
			+ COLUMN_STUDENT_CREDITOS_CURSADOS + " INTEGER " 
			+ ");";
	private static final String CREATE_TABLE_SEMESTERS = 
			"CREATE TABLE " + TABLE_SEMESTER
			+ "(" 
            + COLUMN_ID + " INTEGER PRIMARY KEY autoincrement, " 
			+ COLUMN_SEMESTER_NAME + " TEXT, " 
            + COLUMN_SEMESTER_PROM_REAL + " REAL, " 
			+ COLUMN_SEMESTER_PROM_REQUERIDO + " REAL, " 
			+ COLUMN_SEMESTER_PROM_SIMULADO + " REAL, " 
			+ COLUMN_SEMESTER_ESTADO + " TEXT, " 
			+ COLUMN_SEMESTER_CREDITOS + " TEXT, " 
			+ COLUMN_SEMESTER_STUDENT_ID + " INTEGER, " 
			+ "FOREIGN KEY(" + COLUMN_SEMESTER_STUDENT_ID  
			+ ") references " + TABLE_STUDENT + "(" + COLUMN_ID+")" 
			+ ");";
	private static final String CREATE_TABLE_SUBJECTS = 
			"CREATE TABLE " + TABLE_SUBJECTS
			+ "(" 
            + COLUMN_ID + " INTEGER PRIMARY KEY autoincrement, " 
			+ COLUMN_SUBJECT_NAME + " TEXT, "
			+ COLUMN_SUBJECT_CREDITOS + " INTEGER, " 
            + COLUMN_SUBJECT_NOTA_REAL + " REAL, " 
			+ COLUMN_SUBJECT_NOTA_REQUERIDA + " REAL, " 
			+ COLUMN_SUBJECT_NOTA_SIMULADA + " REAL, " 
			+ COLUMN_SUBJECT_ESTADO + " TEXT, " 
			+ COLUMN_SUBJECT_SEMESTER_ID + " INTEGER, " 
			+ "FOREIGN KEY(" + COLUMN_SUBJECT_SEMESTER_ID   
			+ ") references " + TABLE_SEMESTER + "(" + COLUMN_ID +") " 
			+ ");";
	private static final String CREATE_TABLE_EVALUATIONS = 
			"CREATE TABLE " + TABLE_EVALUATIONS
			+ "(" 
            + COLUMN_ID + " INTEGER PRIMARY KEY autoincrement, " 
			+ COLUMN_EVALUATION_NAME + " TEXT, "
			+ COLUMN_EVALUATION_PORCENTAJE + " INTEGER, "
            + COLUMN_EVALUATION_NOTA_REAL + " REAL, " 
			+ COLUMN_EVALUATION_NOTA_REQUERIDA + " REAL, "
			+ COLUMN_EVALUATION_NOTA_SIMULADA + " REAL, "
			+ COLUMN_EVALUATION_ESTADO + " TEXT, " 
			+ COLUMN_EVALUATION_SUBJECT_ID + " INTEGER, "
			+ "FOREIGN KEY(" + COLUMN_EVALUATION_SUBJECT_ID 
			+") references " + TABLE_SUBJECTS + "(" + COLUMN_ID +") " 
			+ ");";
	public DatabaseHelper(Context context) {        
		super(context, DB_NAME, null, VERSION);    
	}
	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(CREATE_TABLE_EVALUATIONS);
		db.execSQL(CREATE_TABLE_SUBJECTS);
		db.execSQL(CREATE_TABLE_SEMESTERS);
		db.execSQL(CREATE_TABLE_STUDENTS);       
			
		       
		
	    if (!db.isReadOnly()) {
	        // Enable foreign key constraints
	        db.execSQL("PRAGMA foreign_keys=ON;");
	        
	    }
	}
	@Override
	public void onOpen(SQLiteDatabase db) {
	    super.onOpen(db);
	    if (!db.isReadOnly()) {
	        // Enable foreign key constraints
	        db.execSQL("PRAGMA foreign_keys=ON;");
	        
	    }
	}
	@Override
	public void onConfigure(SQLiteDatabase db) {
	    if (!db.isReadOnly()) {
	        // Enable foreign key constraints
	        db.execSQL("PRAGMA foreign_keys=ON;");
	        
	    }
	}
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
	
		   // on upgrade drop older tables
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_EVALUATIONS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SUBJECTS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SEMESTER);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_STUDENT);


        
     
 
        // create new tables
        onCreate(db);
		
	} 
	public void clearAll(){
		SQLiteDatabase bd = getWritableDatabase();
		bd.execSQL("delete from "+ TABLE_EVALUATIONS);
		bd.execSQL("delete from "+ TABLE_SUBJECTS);
		bd.execSQL("delete from "+ TABLE_SEMESTER);
		bd.execSQL("delete from "+ TABLE_STUDENT);
		
		
		
		

	}
	 public long insertStudent(Student student) {        
		 ContentValues cv = new ContentValues();        
		 cv.put(COLUMN_STUDENT_NAME, student.getNombre());
		 cv.put(COLUMN_STUDENT_PROM_ACUM, student.getPromAcum());
		 cv.put(COLUMN_STUDENT_PROM_ACUM_REQUERIDO, student.getPromAcumDeseado());
		 cv.put(COLUMN_STUDENT_CREDITOS_CURSADOS, student.getCreditos());
	
		 return getWritableDatabase().insert(TABLE_STUDENT, null, cv);    
	} 
	 public long insertSemester(long student, Semester semester) {        
		 ContentValues cv = new ContentValues();        
		 cv.put(COLUMN_SEMESTER_NAME, semester.getName());
		 cv.put(COLUMN_SEMESTER_CREDITOS, semester.getCreditos());
		 cv.put(COLUMN_SEMESTER_ESTADO, semester.getEstado());
		 cv.put(COLUMN_SEMESTER_PROM_REQUERIDO, semester.getPromRequerido());
		 cv.put(COLUMN_SEMESTER_PROM_SIMULADO, semester.getPromSimulado());
		 cv.put(COLUMN_SEMESTER_PROM_REAL, semester.getPromReal());
		 cv.put(COLUMN_SEMESTER_STUDENT_ID, student); 
		 
		 return getWritableDatabase().insert(TABLE_SEMESTER, null, cv);    
	}
	
	public long insertSubject(Asignatura asignatura, long semester) {
		 ContentValues cv = new ContentValues();        
		 cv.put(COLUMN_SUBJECT_NAME, asignatura.getNombre());
		 cv.put(COLUMN_SUBJECT_CREDITOS, asignatura.getCreditos());
		 cv.put(COLUMN_SUBJECT_ESTADO, asignatura.getEstado());
		 cv.put(COLUMN_SUBJECT_NOTA_REQUERIDA, asignatura.getNotaRequerida());
		 cv.put(COLUMN_SUBJECT_NOTA_REAL, asignatura.getNotaReal());
		 cv.put(COLUMN_SUBJECT_NOTA_SIMULADA, asignatura.getNotaSimulada());
		 cv.put(COLUMN_SUBJECT_SEMESTER_ID, semester); 
		 
		 return getWritableDatabase().insert(TABLE_SUBJECTS, null, cv); 
	}
	public long insertEvaluation(Evaluation e) {
		 ContentValues cv = new ContentValues();        
		 cv.put(COLUMN_EVALUATION_NAME, e.getNombre());
		 cv.put(COLUMN_EVALUATION_PORCENTAJE, e.getPorcentaje());
		 cv.put(COLUMN_EVALUATION_ESTADO, e.getEstado());
		 cv.put(COLUMN_EVALUATION_NOTA_REQUERIDA, e.getNota_requerida());
		 cv.put(COLUMN_EVALUATION_NOTA_REAL, e.getNota_real());
		 cv.put(COLUMN_EVALUATION_NOTA_SIMULADA, e.getNota_simulada());
		 cv.put(COLUMN_EVALUATION_SUBJECT_ID, e.getAsignaturaId()); 
		 
		 return getWritableDatabase().insert(TABLE_EVALUATIONS, null, cv); 
	}
	
	

	// closing database
	    public void closeDB() {
	        SQLiteDatabase db = this.getReadableDatabase();
	        if (db != null && db.isOpen())
	            db.close();
	    }
	
    public Student getStudent() {
    	//debido a que solo estamos almacenado un estudiante no importa que registro estamso consultando
		Cursor cursor = getWritableDatabase().rawQuery("SELECT * FROM " + TABLE_STUDENT, null);
		if (cursor.moveToFirst()){
			Student s = new Student();
			s.setID(cursor.getLong(cursor.getColumnIndex(COLUMN_ID)));
			s.setNombre(cursor.getString(cursor.getColumnIndex(COLUMN_STUDENT_NAME)));
			s.setPromAcum(cursor.getString(cursor.getColumnIndex(COLUMN_STUDENT_PROM_ACUM)));
			s.setPromAcumDeseado(cursor.getFloat(cursor.getColumnIndex(COLUMN_STUDENT_PROM_ACUM_REQUERIDO)));
			s.setCredCursados(cursor.getInt(cursor.getColumnIndex(COLUMN_STUDENT_CREDITOS_CURSADOS)));						
			return s;
		}else{
			return null;
		}
	
	}
	public Semester getSemester(long id) {
		Cursor cursor = getWritableDatabase().rawQuery(
				"SELECT * FROM " + TABLE_SEMESTER + " WHERE " + 
		COLUMN_SEMESTER_STUDENT_ID + " = " + id + "", null);
		if (cursor.moveToFirst()){
			Semester s = new Semester();
			s.setID(cursor.getLong(cursor.getColumnIndex(COLUMN_ID)));
			s.setNombre(cursor.getString(cursor.getColumnIndex(COLUMN_SEMESTER_NAME)));
			s.setCreditos(cursor.getInt(cursor.getColumnIndex(COLUMN_SEMESTER_CREDITOS)));
			s.setEstado(cursor.getString(cursor.getColumnIndex(COLUMN_SEMESTER_ESTADO)));
			s.setPromRequerido(cursor.getFloat(cursor.getColumnIndex(COLUMN_SEMESTER_PROM_REQUERIDO)));
			s.setPromReal(cursor.getFloat(cursor.getColumnIndex(COLUMN_SEMESTER_PROM_REAL)));
			s.setPromSimulado(cursor.getFloat(cursor.getColumnIndex(COLUMN_SEMESTER_PROM_SIMULADO)));
			s.setStudentId(cursor.getLong(cursor.getColumnIndex(COLUMN_SEMESTER_STUDENT_ID)));
			
		
			return s;
		} else{
			return null;
		}
		
		
	}
	public ArrayList<Asignatura> getSubjects(long id) {
		ArrayList<Asignatura> asignaturas = new ArrayList<Asignatura>();
		// TODO Auto-generated method stub
		Cursor cursor = getWritableDatabase().rawQuery(
				"SELECT * FROM " + TABLE_SUBJECTS + " WHERE " + 
		COLUMN_SUBJECT_SEMESTER_ID + " = " + id + "", null);
		
		while(cursor.moveToNext()){
			Asignatura s = new Asignatura();
			//s.setID(cursor.getLong(cursor.getColumnIndex(COLUMN_ID)));
			//s.setNombre(cursor.getString(cursor.getColumnIndex(COLUMN_SEMESTER_NAME)));
			//s.setCreditos(cursor.getInt(cursor.getColumnIndex(COLUMN_SEMESTER_CREDITOS)));
			//s.setEstado(cursor.getString(cursor.getColumnIndex(COLUMN_SEMESTER_ESTADO)));
			//s.setPromRequerido(cursor.getFloat(cursor.getColumnIndex(COLUMN_SEMESTER_PROM_REQUERIDO)));
			//s.setPromReal(cursor.getFloat(cursor.getColumnIndex(COLUMN_SEMESTER_PROM_REAL)));
			//s.setStudentId(cursor.getLong(cursor.getColumnIndex(COLUMN_SEMESTER_STUDENT_ID)));
			asignaturas.add(s);
		}
		if(asignaturas.isEmpty()){
			return null;
		}else{
			return asignaturas;
		}
		
		
	}
	public String[] getSubjectsNames(long id) {
		Cursor cursor = getWritableDatabase().rawQuery(
				"SELECT "+COLUMN_SUBJECT_NAME+" FROM " + TABLE_SUBJECTS + " WHERE " + 
		COLUMN_SUBJECT_SEMESTER_ID + " = " + id + "", null);
		if(cursor.getCount()>0){
		    String[] nombres = new String[cursor.getCount()];
		    int i = 0;
			while(cursor.moveToNext()){
				nombres[i]=cursor.getString(0);
				i = i+1;
			}
			return nombres;
		}else{
			return null;
		}
		
		
		
	}
	public Asignatura getSubjectByName(long semester_id, String name) {
		Cursor cursor = getWritableDatabase().rawQuery(
			"SELECT * FROM " + TABLE_SUBJECTS + " WHERE " + 
			COLUMN_SUBJECT_SEMESTER_ID + " = " + semester_id + 
			" AND " +COLUMN_SUBJECT_NAME + " = '" + name +"'" , null);
		Asignatura s;
		if(cursor.moveToFirst()){
			s = new Asignatura();
			s.setID(cursor.getLong(cursor.getColumnIndex(COLUMN_ID)));
			s.setNombre(cursor.getString(cursor.getColumnIndex(COLUMN_SUBJECT_NAME)));
			s.setCreditos(cursor.getString(cursor.getColumnIndex(COLUMN_SUBJECT_CREDITOS)));
			s.setEstado(cursor.getString(cursor.getColumnIndex(COLUMN_SUBJECT_ESTADO)));
			s.setNotaRequerida(cursor.getString(cursor.getColumnIndex(COLUMN_SUBJECT_NOTA_REQUERIDA)));
			s.setNotaReal(cursor.getString(cursor.getColumnIndex(COLUMN_SUBJECT_NOTA_REAL)));
			s.setNota_simulada(cursor.getString(cursor.getColumnIndex(COLUMN_SUBJECT_NOTA_SIMULADA)));
			s.setSemestreID(cursor.getLong(cursor.getColumnIndex(COLUMN_SUBJECT_SEMESTER_ID)));
			
			return s;
		}else{
			return null;
		}
		
	}
	public Evaluation getEvaluationByName(long subject_id, String name) {
		Cursor cursor = getWritableDatabase().rawQuery(
			"SELECT * FROM " + TABLE_EVALUATIONS + " WHERE " + 
			COLUMN_EVALUATION_SUBJECT_ID + " = " + subject_id + 
			" AND " +COLUMN_EVALUATION_NAME + " = " + name , null);
		Evaluation s;
		if(cursor.moveToFirst()){
			s = new Evaluation();
			s.setID(cursor.getLong(cursor.getColumnIndex(COLUMN_ID)));
			s.setNombre(cursor.getString(cursor.getColumnIndex(COLUMN_EVALUATION_NAME)));
			s.setPorcentaje(cursor.getInt(cursor.getColumnIndex(COLUMN_EVALUATION_PORCENTAJE)));
			s.setEstado(cursor.getString(cursor.getColumnIndex(COLUMN_EVALUATION_ESTADO)));
			s.setNota_Requerida(cursor.getFloat(cursor.getColumnIndex(COLUMN_EVALUATION_NOTA_REQUERIDA)));
			s.setNota_real(cursor.getFloat(cursor.getColumnIndex(COLUMN_EVALUATION_NOTA_REAL)));
			s.setNota_simulada(cursor.getFloat(cursor.getColumnIndex(COLUMN_EVALUATION_NOTA_SIMULADA)));
			s.setAsignaturaID(cursor.getLong(cursor.getColumnIndex(COLUMN_EVALUATION_SUBJECT_ID)));
			
			return s;
		}else{
			return null;
		}
		
	}
	public String[] getEvaluacionesNames(long asignatura_id) {
		Cursor cursor = getWritableDatabase().rawQuery(
				"SELECT "+COLUMN_EVALUATION_NAME+" FROM " + TABLE_EVALUATIONS + " WHERE " + 
		COLUMN_EVALUATION_SUBJECT_ID + " = " + asignatura_id + "", null);
		if(cursor.getCount()>0){
		    String[] nombres = new String[cursor.getCount()];
		    int i = 0;
			while(cursor.moveToNext()){
				nombres[i]=cursor.getString(0);
				i = i+1;
			}
			return nombres;
		}else{
			return null;
		}
		
	}



}
