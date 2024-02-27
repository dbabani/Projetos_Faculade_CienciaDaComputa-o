package com.bcopstein.demo.Dominio;

import java.util.List;

public interface RepDescontosInterface {
    public void save(Desconto d);
    public List<Desconto> all();
    public void clear();
}
