package com.example.pablo384.leccion3tarea.models;


public class Fruit {

    private String name;
    private String descripcion;
    private int imgBackground;
    private int imgIcon;
    private int cantidad;

    // Valores accesibles estáticamente
    public static final int LIMIT_QUANTITY = 10;
    public static final int RESET_VALUE_QUANTITY = 0;

    public Fruit(){}

    public Fruit(String name, String descripcion, int imgBackground, int imgIcon, int cantidad) {
        this.name = name;
        this.descripcion = descripcion;
        this.imgBackground = imgBackground;
        this.imgIcon = imgIcon;
        this.cantidad = cantidad;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getImgBackground() {
        return imgBackground;
    }

    public void setImgBackground(int imgBackground) {
        this.imgBackground = imgBackground;
    }

    public int getImgIcon() {
        return imgIcon;
    }

    public void setImgIcon(int imgIcon) {
        this.imgIcon = imgIcon;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    //anadir cantidad
    public void addcantidad(int cantidad) {
        if (this.cantidad < LIMIT_QUANTITY) {
            this.cantidad += cantidad;
        }
    }

    //resetear cantidad
    public void resetcantidad() {
        this.cantidad = RESET_VALUE_QUANTITY;
    }

}
