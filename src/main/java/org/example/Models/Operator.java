package org.example.Models;

public class Operator extends BaseModel{
    public int getEmPId() {
        return emPId;
    }

    public void setEmPId(int emPId) {
        this.emPId = emPId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private int emPId;
    private String name;
}
