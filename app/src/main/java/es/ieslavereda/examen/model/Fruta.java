package es.ieslavereda.examen.model;

import java.io.Serializable;

import es.ieslavereda.examen.R;

public enum Fruta implements Serializable {

    FRESA("Fresa", 5.25f, R.mipmap.fresa),
    KIWI("Kiwi", 4.25f, R.mipmap.kiwi),
    LIMON("Limon", 1.75f, R.mipmap.limon),
    MANZANA("Manzana", 3.5f, R.mipmap.manzana),
    NARANJA("Naranja", 3f, R.mipmap.naranja),
    PERA("Pera", 2.15f, R.mipmap.pera),
    PLATANOS("Platano", 2.75f, R.mipmap.platanos);

    private String nombre;
    private float precio;
    private int imageResource;

    Fruta(String nombre, float precio, int imageResource) {
        this.nombre = nombre;
        this.precio = precio;
        this.imageResource = imageResource;
    }

    public String getNombre() {
        return nombre;
    }

    public float getPrecio() {
        return precio;
    }

    public int getImageResource() {
        return imageResource;
    }

    public Fruta next() {

        int pos = ordinal() + 1;

        if (pos == Fruta.values().length)
            pos = 0;

        return Fruta.values()[pos];
    }

    public Fruta previous() {

        int pos = ordinal() - 1;

        if (pos < 0)
            pos = Fruta.values().length - 1;

        return Fruta.values()[pos];
    }
}
