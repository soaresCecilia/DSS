package MediaCenter.LogicaDeNegocio.Usuarios;

import java.util.ArrayList;
import java.util.List;

public class Utilizador {
    private String email;
    private String nome;
    private String password;

    public Utilizador(String email, String nome, String password) {
        this.email = email;
        this.nome = nome;
        this.password = password;
    }


    public Utilizador(List<String> linha) {
        this.email = linha.get(0);
        this.nome = linha.get(1);
        this.password = linha.get(2);

    }


    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getNome() {
        return nome;
    }


    public List<String> utilizadorToLinha(Utilizador u) {
        List<String> utilizador = new ArrayList<>();
            utilizador.add(u.email);
            utilizador.add(u.nome);
            utilizador.add(u.password);

            return utilizador;

    }


    public Utilizador linhaToUtilizador(List<String> linha) {
        return new Utilizador(linha);
    }


    public String chave() {
        return this.email;
    }

}
