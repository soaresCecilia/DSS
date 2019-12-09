package com.jetbrains;

import java.util.List;

public class Administrador extends Utilizador {

    public Administrador(String email, String nome, String password) {
        super(email, nome, password);
    }

    public Administrador(List<String> linha) {
        super(linha);
    }
}
