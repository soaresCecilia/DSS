package com.jetbrains;

import java.util.ArrayList;
import java.util.List;

public class Musica {
    private static int idMusica;
    private String nome;
    private String autor;
    private String album;
    private int categoria;
    private String caminho;


    public Musica(int idMusica, String nome, String autor, String album, int categoria, String caminho) {
        this.idMusica = idMusica;
        this.nome = nome;
        this.autor = autor;
        this.album = album;
        this.categoria = categoria;
        this.caminho = caminho;
    }


    public Musica(List<String> stringsRs) {
        this.idMusica = Integer.valueOf(stringsRs.get(0));
        this.nome = stringsRs.get(1);
        this.autor = stringsRs.get(2);
        this.album = stringsRs.get(3);
        this.categoria = Integer.valueOf(stringsRs.get(4));
    }

    public int getCategoria() {
        return categoria;
    }

    public String getCaminho() {
        return caminho;
    }

    public int getIdMusica() {
        return idMusica;
    }

    public String getNome() {
        return nome;
    }

    public String getAutor() {
        return autor;
    }

    public String getAlbum() {
        return album;
    }

    public List<Object> musicaToLinha(Musica m) {
        List<Object> musica = new ArrayList<>();
        musica.add(this.idMusica);
        musica.add(this.nome);
        musica.add(this.autor);
        musica.add(this.album);
        musica.add(this.categoria);
        musica.add(this.caminho);

        return musica;
    }

    public int chave() {
        return this.idMusica;
    }



}
