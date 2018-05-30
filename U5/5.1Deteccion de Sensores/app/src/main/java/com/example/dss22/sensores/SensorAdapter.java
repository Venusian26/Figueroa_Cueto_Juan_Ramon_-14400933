package com.example.dss22.sensores;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by dss22 on 8/05/18.
 */

class SensorAdapter extends RecyclerView.Adapter<SensorAdapter.SensorViewHolder>{

    ArrayList<Sensors> list;
    Context context;

    public SensorAdapter(ArrayList<Sensors> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public SensorViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.item_list,null);
        return new SensorViewHolder(view);
    }

    @Override
    public void onBindViewHolder(SensorViewHolder holder, int position) {
        final Sensors sensor = list.get(position);
        holder.nombre.setText(sensor.getNombre());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class SensorViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView nombre;

        public SensorViewHolder(View itemView) {
            super(itemView);
            nombre = itemView.findViewById(R.id.textView_nombre);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            LayoutInflater li = LayoutInflater.from(context);
            View promptsView = li.inflate(R.layout.informacion, null);

            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                    context);

            Sensors sensor = list.get(getAdapterPosition());

            TextView nombre,fabricante,version,rango,delay,power;

            nombre = promptsView.findViewById(R.id.nombre);
            fabricante = promptsView.findViewById(R.id.fabricante);
            version = promptsView.findViewById(R.id.version);

            rango = promptsView.findViewById(R.id.rango);
            delay = promptsView.findViewById(R.id.delay);
            power = promptsView.findViewById(R.id.power);

            nombre.setText(sensor.getNombre());
            fabricante.setText(sensor.getFabricante());
            version.setText(sensor.getVersion());
            rango.setText(sensor.getRango());
            delay.setText(sensor.getDelay());
            power.setText(sensor.getPower());
            alertDialogBuilder.setView(promptsView);

            // set dialog message
            alertDialogBuilder
                    .setCancelable(false)
                    .setPositiveButton("Cerrar",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog,int id) {
                                    dialog.cancel();
                                }
                            });


            AlertDialog alertDialog = alertDialogBuilder.create();
            alertDialog.show();
        }

    }

}
