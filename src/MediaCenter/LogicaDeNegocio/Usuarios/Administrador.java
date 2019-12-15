package MediaCenter.LogicaDeNegocio.Usuarios;

import java.util.ArrayList;
import java.util.List;

public class Administrador {

    private String email;
    private String nome;
    private String password;

    public Administrador(String email, String nome, String password) {
        this.email = email;
        this.nome = nome;
        this.password = password;
    }


    public List<String> administardorToLinha(Administrador admin) {
        List<String> administrador = new ArrayList<>();
        administrador.add(admin.email);
        administrador.add(admin.nome);
        administrador.add(admin.password);

        return administrador;

    }



    public String getNome() {
        return this.nome;
    }

    public String getEmail() {
        return this.email;
    }

    public String getPassword() {
        return this.password;
    }

    public boolean equals() {
        // TODO - implement Administrador.equals
        throw new UnsupportedOperationException();
    }

    public String chave() {
        return this.email;
    }

}