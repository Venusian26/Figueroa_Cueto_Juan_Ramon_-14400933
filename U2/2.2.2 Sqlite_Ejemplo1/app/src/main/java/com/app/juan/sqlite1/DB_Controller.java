package com.app.juan.sqlite1;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.TextView;
import android.widget.Toast;

public class DB_Controller extends SQLiteOpenHelper {
    public DB_Controller(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, "MIBASE.db", factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE ESTUDIANTES(ID INTEGER PRIMARY KEY AUTOINCREMENT, NOMBRE TEXT UNIQUE, APELLIDO TEXT); ");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS ESTUDIANTES;");
        onCreate(sqLiteDatabase);
    }

    public void insertar_estudiante(String NOMBRE, String APELLIDO){
        ContentValues contentValues = new ContentValues();
        contentValues.put("NOMBRE",NOMBRE);
        contentValues.put("APELLIDO",APELLIDO);
        this.getWritableDatabase().insertOrThrow("ESTUDIANTES","",contentValues);
    }

    public void borrar_estudiante(String NOMBRE){
        this.getWritableDatabase().delete("ESTUDIANTES","NOMBRE='"+NOMBRE+"'",null);
    }

    public  void actualizar(String viejo_NOMBRE, String nuevo_NOMBRE){
        this.getWritableDatabase().execSQL("UPDATE ESTUDIANTES SET NOMBRE='"+nuevo_NOMBRE+"' WHERE NOMBRE='"+viejo_NOMBRE+"'");
    }

    public  void enlistar_estudiantes(TextView textView){
        Cursor cursor = this.getReadableDatabase().rawQuery("SELECT * FROM ESTUDIANTES",null);
        textView.setText("");
        while(cursor.moveToNext()){
            textView.append(cursor.getString(1)+" "+cursor.getString(2)+"\n");
        }
    }

}
