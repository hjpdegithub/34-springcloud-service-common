package com.springboot.boot.modules.admin.entity;


public class NewTable {
    private Long id;

    private String newTablecol;

    private String newTablecol1;

    private String newTablecol2;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNewTablecol() {
        return newTablecol;
    }

    public void setNewTablecol(String newTablecol) {
        this.newTablecol = newTablecol == null ? null : newTablecol.trim();
    }

    public String getNewTablecol1() {
        return newTablecol1;
    }

    public void setNewTablecol1(String newTablecol1) {
        this.newTablecol1 = newTablecol1 == null ? null : newTablecol1.trim();
    }

    public String getNewTablecol2() {
        return newTablecol2;
    }

    public void setNewTablecol2(String newTablecol2) {
        this.newTablecol2 = newTablecol2 == null ? null : newTablecol2.trim();
    }
}