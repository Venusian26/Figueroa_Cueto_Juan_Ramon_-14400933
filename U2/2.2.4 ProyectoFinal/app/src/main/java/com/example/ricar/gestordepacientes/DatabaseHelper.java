package com.example.ricar.gestordepacientes;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Ricar on 27/09/2017.
 */


public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "PacientesVersionCinco";

    ////tabla de Pacientes
    public static final String TABLE_CLIENTES = "CLIENTES";
    public static final String COL_ID = "ID";
    public static final String COL_NOMBRE = "NOMBRE";
    public static final String COL_DIRECCION = "DIRECCION";
    public static final String COL_CELULAR = "CELULAR";
    public static final String COL_MAIL = "MAIL";
    public static final String COL_FECHA = "FECHA";

    ////TABLA DE MEDICAMENTOS
    public static final String TABLE_MEDICAMENTOS = "MEDICAMENTOS";
    public static final String MED_ID = "ID";
    public static final String MED_NOMBRE = "NOMBRE";
    public static final String MED_PADECIMIENTOS = "PADECIMIENTOS";
    public static final String MED_INTRUCCIONES = "INSTRUCCIONES";
    public static final String MED_FECHACONSULTA = "FECHACONSULTA";
    public static final String MED_FECHAINICIO = "FECHAINICIO";
    public static final String MED_FECHAFIN = "FECHAFIN";
    public static final String MED_VIGENCIA = "VIGENCIA";
    public static final String MED_PACIENTE = "PACIENTE_ID";



    DatabaseHelper DBHelper;
    SQLiteDatabase db;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
        if (!db.isReadOnly()) {
            // Enable foreign key constraints
            db.execSQL("PRAGMA foreign_keys=ON");
        }
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_CLIENTES +" (ID INTEGER PRIMARY KEY AUTOINCREMENT,NOMBRE TEXT,DIRECCION TEXT," +
                "CELULAR TEXT,MAIL TEXT,FECHA TEXT)");



        db.execSQL("create table " + TABLE_MEDICAMENTOS +" (ID INTEGER PRIMARY KEY AUTOINCREMENT,NOMBRE TEXT," +
                "PADECIMIENTOS TEXT,INSTRUCCIONES TEXT,FECHACONSULTA TEXT,FECHAINICIO TEXT," +
                "FECHAFIN TEXT,VIGENCIA TEXT,PACIENTE_ID INTEGER,FOREIGN KEY(PACIENTE_ID) " +
                "REFERENCES CLIENTES(ID))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_CLIENTES);
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_MEDICAMENTOS);


        onCreate(db);
    }




    ////////FUNCIONES DE LA TABLA DE CLIENTES
    public boolean insertDataCliente(String nombre,String direccion,String celular,String mail,String fecha) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_NOMBRE,nombre);
        contentValues.put(COL_DIRECCION,direccion);
        contentValues.put(COL_CELULAR,celular);
        contentValues.put(COL_MAIL,mail);
        contentValues.put(COL_FECHA,fecha);


        long result = db.insert(TABLE_CLIENTES,null ,contentValues);
        if(result == -1)
            return false;
        else
            return true;
    }

    public Cursor getAllDataCliente() {
        SQLiteDatabase db = this.getWritableDatabase();
        //Cursor res = db.rawQuery("select * from "+TABLE_CLIENTES,null);
        Cursor res = db.rawQuery("select * from CLIENTES order by NOMBRE",null);
        return res;
    }

    public Cursor getPacientesActivos() {
        SQLiteDatabase db = this.getWritableDatabase();
        //Cursor res = db.rawQuery("select * from "+TABLE_CLIENTES,null);
       // Cursor res = db.rawQuery("SELECT CLIENTES.NOMBRE FROM CLIENTES,MEDICAMENTOS WHERE CLIENTES.ID=MEDICAMENTOS.PACIENTE_ID ORDER BY CLIENTES.NOMBRE;",null);
        Cursor res = db.rawQuery("SELECT CLIENTES.NOMBRE,CLIENTES.ID FROM CLIENTES,MEDICAMENTOS WHERE CLIENTES.ID=MEDICAMENTOS.PACIENTE_ID ORDER BY CLIENTES.NOMBRE;",null);
        return res;
    }

    public boolean updateDataCliente(String id,String nombre,String direccion,String celular,String mail,String fecha) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_ID,id);
        contentValues.put(COL_NOMBRE,nombre);
        contentValues.put(COL_DIRECCION,direccion);
        contentValues.put(COL_CELULAR,celular);
        contentValues.put(COL_MAIL,mail);
        contentValues.put(COL_FECHA,fecha);

        db.update(TABLE_CLIENTES, contentValues, "ID = ?",new String[] { id });
        return true;
    }

    public Integer deleteDataCliente (String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_CLIENTES, "ID = ?",new String[] {id});
    }

////________________________________________________________

    //---Recuperar todos los contactos---
    public Cursor getAllContacts()
    {
        return db.query(TABLE_CLIENTES, new String[] {COL_ID,COL_NOMBRE,COL_DIRECCION,
                COL_CELULAR, COL_MAIL,COL_FECHA}, null, null, null, null,COL_NOMBRE );
    }
////_________________________________________________________

    ///FUNCIONES DE LA TABLA DE MEDICAMENTOS

    public boolean insertDataMed(String nombre,String padecimiento,String instrucciones,String fechaconsulta,String fechainicio,
                                 String fechafin,String vigencia, String paciente) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(MED_NOMBRE,nombre);
        contentValues.put(MED_PADECIMIENTOS,padecimiento);
        contentValues.put(MED_INTRUCCIONES,instrucciones);
        contentValues.put(MED_FECHACONSULTA,fechaconsulta);
        contentValues.put(MED_FECHAINICIO,fechainicio);
        contentValues.put(MED_FECHAFIN,fechafin);
        contentValues.put(MED_VIGENCIA,vigencia);
        contentValues.put(MED_PACIENTE,paciente);


        long result = db.insert(TABLE_MEDICAMENTOS,null ,contentValues);
        if(result == -1)
            return false;
        else
            return true;
    }

    public Cursor getAllDataMed() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+TABLE_MEDICAMENTOS,null);
        return res;
    }


    public Cursor getMedicamentoDisponible(int a) {
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor res = db.rawQuery("SELECT MEDICAMENTOS.NOMBRE FROM MEDICAMENTOS WHERE VIGENCIA='si'AND ID="+a+";",null);
        return res;
    }

    public boolean updateDataMed(String id,String nombre,String padecimiento,String instrucciones,String fechaconsulta,String fechainicio,
                                 String fechafin,String vigencia, String paciente) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(MED_ID,id);
        contentValues.put(MED_NOMBRE,nombre);
        contentValues.put(MED_PADECIMIENTOS,padecimiento);
        contentValues.put(MED_INTRUCCIONES,instrucciones);
        contentValues.put(MED_FECHACONSULTA,fechaconsulta);
        contentValues.put(MED_FECHAINICIO,fechainicio);
        contentValues.put(MED_FECHAFIN,fechafin);
        contentValues.put(MED_VIGENCIA,vigencia);
        contentValues.put(MED_PACIENTE,paciente);


        db.update(TABLE_MEDICAMENTOS, contentValues, "ID = ?",new String[] { id });
        return true;
    }

    public Integer deleteDataMed (String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_MEDICAMENTOS, "ID = ?",new String[] {id});
    }
}
