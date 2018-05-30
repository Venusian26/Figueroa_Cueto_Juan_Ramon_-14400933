package com.example.dss22.sensores;

import java.io.Serializable;

/**
 * Created by dss22 on 8/05/18.
 */

class Sensors implements Serializable {
    private String nombre,fabricante,version,delay,rango,power;

    public Sensors(String nombre, String fabricante, String version, String rango, String delay, String power) {
        this.nombre = nombre;
        this.fabricante = fabricante;
        this.version = version;
        this.rango = rango;
        this.delay = delay;
        this.power = power;
    }

    public String getNombre() {
        return nombre;
    }

    public String getFabricante() {
        return fabricante;
    }

    public String getVersion() {
        return version;
    }

    public String getDelay() {
        return delay;
    }

    public String getRango() {
        return rango;
    }

    public String getPower() {
        return power;
    }

}
