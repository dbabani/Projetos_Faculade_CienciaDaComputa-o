package com.bcopstein.demo.Dominio;

import java.util.List;

public interface RepImpostosInterface {
    public void save(Imposto i);
    public List<Imposto> all();
    public void clear(); 
}
