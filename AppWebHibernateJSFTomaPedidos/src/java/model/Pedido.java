/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.math.BigDecimal;

/**
 *
 * @author RKOrtega
 */
public class Pedido {

    private int Id;
    private int menuId;
    private String menu;
    private BigDecimal valor;
    private int cantidad;

    public Pedido() {
    }

    public Pedido(int Id, int menuId, String menu, BigDecimal valor, int cantidad) {
        this.Id = Id;
        this.menuId = menuId;
        this.menu = menu;
        this.valor = valor;
        this.cantidad = cantidad;
    }

    public int getId() {
        return Id;
    }

    public void setId(int Id) {
        this.Id = Id;
    }

    public String getMenu() {
        return menu;
    }

    public void setMenu(String menu) {
        this.menu = menu;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public int getMenuId() {
        return menuId;
    }

    public void setMenuId(int menuId) {
        this.menuId = menuId;
    }

}
