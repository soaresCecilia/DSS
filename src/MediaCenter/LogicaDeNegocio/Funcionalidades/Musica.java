package MediaCenter.LogicaDeNegocio.Funcionalidades;


import java.util.ArrayList;
import java.util.List;

public class Musica implements Arquivo {
    private int idMusica;
    private String nome;
    private String autor;
    private String album;
    private String categoria;
    private String caminho;
    private String upload;
    private List<String> downloads;


    public Musica(int idMusica, String nome, String autor, String album, String categoria, String caminho) {
        this.idMusica = idMusica;
        this.nome = nome;
        this.autor = autor;
        this.album = album;
        this.categoria = categoria;
        this.caminho = caminho;
    }


    public String getCategoria() {
        return this.categoria;
    }


    public String getCaminho() {
        return this.caminho;
    }


    public String getNome() {
        return this.nome;
    }

    @Override
    public int getID() {
        return this.idMusica;
    }


    public String getAutor() {
        return this.autor;
    }

    public String getAlbum_Temporada() {
        return this.album;
    }

    public String getTipoArq() {
        return "Musica";
    }

    public List<String> getDownloads() {
        return downloads;
    }

    public List<Object>  arquivoToLinha(Arquivo arq) {
        List<Object> musica = new ArrayList<>();

        if(arq.getTipoArq().equals("Musica")) {

            musica.add(arq.getID());
            musica.add(arq.getNome());
            musica.add(arq.getAutor());
            musica.add(arq.getAlbum_Temporada());
            musica.add(arq.getCategoria());
            musica.add(arq.getCaminho());
        }
        return musica;
    }

    public int chave() {
        return this.idMusica;
    }





}
