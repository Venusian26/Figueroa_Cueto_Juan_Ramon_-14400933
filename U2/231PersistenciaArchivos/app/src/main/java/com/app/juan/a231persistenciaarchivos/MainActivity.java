package com.app.juan.a231persistenciaarchivos;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private final String EMAIL="Email";
    private EditText mEditTextName;
    private RadioGroup radioGroup;
    private CheckBox check1,check2,check3;
    Spinner spinnerEjemplo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //TextView textView=(TextView) findViewById(R.id.textView);
        mEditTextName=(EditText) findViewById(R.id.editText);
        radioGroup=(RadioGroup) findViewById(R.id.radioGroup);
        check1=(CheckBox) findViewById(R.id.checkBox);
        check2=(CheckBox) findViewById(R.id.checkBox2);
        check3=(CheckBox) findViewById(R.id.checkBox3);
        spinnerEjemplo=(Spinner) findViewById(R.id.spinnerzo);

        //SharedPreferences sharedPreferences=getPreferences(MODE_PRIVATE);
        //String email=sharedPreferences.getString(EMAIL,null);
        llenarSpinnersEjemplo();
    }
    public void saveText(View view){
        //SharedPreferences sharedPreferences=getPreferences(MODE_PRIVATE);
        //String email=sharedPreferences.getString(EMAIL,null);

        SharedPreferences.Editor editor=getPreferences(MODE_PRIVATE).edit();
        editor.putString(EMAIL,mEditTextName.getText().toString());
        editor.putInt("Radio",radioGroup.getCheckedRadioButtonId());
        editor.putBoolean("Check1",check1.isChecked());
        editor.putBoolean("Check2",check2.isChecked());
        editor.putBoolean("Check3",check3.isChecked());
        editor.putInt("spinnerEjemplo",spinnerEjemplo.getSelectedItemPosition());
        editor.commit();
    }
    public void getTodo(View view){
        SharedPreferences sharedPreferences=getPreferences(MODE_PRIVATE);
        String email=sharedPreferences.getString(EMAIL,null);
        int radio=sharedPreferences.getInt("Radio",0);
        boolean che1=sharedPreferences.getBoolean("Check1",false);
        boolean che2=sharedPreferences.getBoolean("Check2",false);
        boolean che3=sharedPreferences.getBoolean("Check3",false);
        int it=sharedPreferences.getInt("spinnerEjemplo",0);
        radioGroup.check(radio);
        mEditTextName.setText(email);
        check1.setChecked(che1);
        check2.setChecked(che2);
        check3.setChecked(che3);
        spinnerEjemplo.setSelection(it);

    }

    private void llenarSpinnersEjemplo() {
        ArrayList<EjemploSpinner> list = new ArrayList<>();
        list.add(new EjemploSpinner(1,"Acuario"));
        list.add(new EjemploSpinner(2,"Piscis"));
        list.add(new EjemploSpinner(3,"Aries"));
        list.add(new EjemploSpinner(4,"Tauro"));
        list.add(new EjemploSpinner(5,"Geminis"));
        list.add(new EjemploSpinner(6,"Cancer"));
        list.add(new EjemploSpinner(7,"Leo"));
        list.add(new EjemploSpinner(8,"Virgo"));
        list.add(new EjemploSpinner(9,"Libra"));
        list.add(new EjemploSpinner(10,"Escorpio"));
        list.add(new EjemploSpinner(11,"Sagitario"));
        list.add(new EjemploSpinner(12,"Capricornio"));


        spinnerEjemplo = (Spinner) findViewById(R.id.spinnerzo);
        ArrayAdapter<EjemploSpinner> adapter =
                new ArrayAdapter<>(this, R.layout.spinner_layaout_ejemplo, list);
        adapter.setDropDownViewResource(R.layout.spinner_layaout_ejemplo);
        spinnerEjemplo.setAdapter(adapter);

    }

}
