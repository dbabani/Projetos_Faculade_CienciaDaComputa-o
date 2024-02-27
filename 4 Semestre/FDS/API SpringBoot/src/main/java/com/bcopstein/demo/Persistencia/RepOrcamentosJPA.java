package com.bcopstein.demo.Persistencia;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.bcopstein.demo.Dominio.ItemPedido;
import com.bcopstein.demo.Dominio.Orcamento;
import com.bcopstein.demo.Dominio.Pedido;

@Repository
public class RepOrcamentosJPA {
    private List<Orcamento> orcamentos;

    public Calendar getCalendar(int day, int month, int year) {
        Calendar date = Calendar.getInstance();
        date.set(Calendar.YEAR, year);

        // We will have to increment the month field by 1

        date.set(Calendar.MONTH, month + 1);

        // As the month indexing starts with 0

        date.set(Calendar.DAY_OF_MONTH, day);

        return date;
    }

    public RepOrcamentosJPA() {
        Date createdAt = getCalendar(30, 10, 2023).getTime();
        Date executedAt = getCalendar(01, 11, 2023).getTime();
        Date validade = new Date();
        validade.setTime(createdAt.getTime() + 21 * 24 * 60 * 60 * 1000);
        ItemPedido item1 = new ItemPedido(0, 20);
        ItemPedido item2 = new ItemPedido(1, 10);
        ItemPedido item3 = new ItemPedido(2, 5);
        List<ItemPedido> itensPedidos = new ArrayList<>();
        itensPedidos.add(item1);
        itensPedidos.add(item2);
        itensPedidos.add(item3);
        Pedido pedido = new Pedido(0, 0, "Joao", itensPedidos);

        orcamentos = new LinkedList<>();
        orcamentos.add(
                new Orcamento(0, createdAt, 0, "Joao", pedido, 4500.00, null, null, 4500.00, true, validade,
                        executedAt));
    }

    public void save(Orcamento o) {
        orcamentos.add(o);
    }

    public List<Orcamento> all() {
        return orcamentos;
    }
}