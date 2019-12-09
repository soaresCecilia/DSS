package com.jetbrains;

import com.jetbrains.*;

import java.sql.Connection;
import java.util.Map;
import java.util.Set;


public class Main {

    public static void main(String[] args) {
        Conexao.iniciarConexao("com.mysql.cj.jdbc.Driver", "jdbc:mysql://localhost:3306/caderneta", "root", "por password do workbench");

        Utilizador user = new Utilizador("aluno", "cecilia", "tttt");

        Utilizador user2 = new Utilizador("aluninho", "Debbis", "tatu");

        UtilizadorDAO u = UtilizadorDAO.getInstance();

        int tam = u.size();
        boolean b = u.containsValue(user);
        boolean b2 = u.containsKey("123");
        boolean b3 = u.containsKey("aluno");

        System.out.println("O numero linhas na tabela é " + tam);
        System.out.println("A resposta é " + b);
        System.out.println("A resposta à pergunta se contem a chave 123 é " + b2);
        System.out.println("A resposta à pergunta se contem a chave aluno é " + b3);

        Set<Map.Entry<String,Utilizador>> entry = u.entrySet();

        for(Map.Entry<String, Utilizador> e : entry) {
            System.out.println(e);
        }

        Utilizador us = u.get("aluno2");

        System.out.println(us);

        //Utilizador us2 = u.put("aluninho", user2);

       // System.out.println(us2);

        boolean b4 = u.isEmpty();

        System.out.println(b4);

        Set<String> setS = u.keySet();

        for(String s : setS) {
            System.out.println(s);
        }

        u.remove("aluninho");

        Utilizador us2 = u.put("aluninho", user2);

        Utilizador ut = u.update("aluninho", (new Utilizador("aluninho", "Balbina", "ola")));

        Map<String, Utilizador> users = u.getTodos();
        for(Utilizador l : users.values()) {
            System.out.println(l.getEmail());
        }

    }
}
