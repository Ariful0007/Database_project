package com.example.database_project;

public class Poem_model {
    String poemname,poetname,mainpoem;

    public Poem_model() {
    }

    public Poem_model(String poemname, String poetname, String mainpoem) {
        this.poemname = poemname;
        this.poetname = poetname;
        this.mainpoem = mainpoem;
    }

    public String getPoemname() {
        return poemname;
    }

    public void setPoemname(String poemname) {
        this.poemname = poemname;
    }

    public String getPoetname() {
        return poetname;
    }

    public void setPoetname(String poetname) {
        this.poetname = poetname;
    }

    public String getMainpoem() {
        return mainpoem;
    }

    public void setMainpoem(String mainpoem) {
        this.mainpoem = mainpoem;
    }
}
