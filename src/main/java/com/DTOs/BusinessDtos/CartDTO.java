package com.DTOs.BusinessDtos;

import java.util.ArrayList;
import java.util.List;

public class CartDTO {
    int id;
    float total;
    List<LineItemDTO> items = new ArrayList<LineItemDTO>();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }

    public List<LineItemDTO> getItems() {
        return items;
    }

    public void setItems(List<LineItemDTO> items) {
        this.items = items;
    }

}
