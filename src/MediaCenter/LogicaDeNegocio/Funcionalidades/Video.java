package MediaCenter.LogicaDeNegocio.Funcionalidades;

import java.util.ArrayList;
import java.util.List;

public class Video implements Arquivo {

    private int idVideo;
    private String titulo;
    private String autor;
    private String temporada;
    private String categoria;
    private String caminho;


    public Video(int idVideo, String titulo, String autor, String temporada, String categoria, String caminho) {
        this.idVideo = idVideo;
        this.titulo = titulo;
        this.autor = autor;
        this.temporada = temporada;
        this.categoria = categoria;
        this.caminho = caminho;
    }

    @Override
    public String getNome() {
        return this.titulo;
    }

    @Override
    public int getID() {
        return this.idVideo;
    }


    public String getAlbum_Temporada() {
        return this.temporada;
    }

    @Override
    public String getCaminho() {
        return this.caminho;
    }

    @Override
    public String getAutor() {
        return this.autor;
    }

    @Override
    public String getCategoria() {
        return this.categoria;
    }

    public String getTipoArq() {
        return "Video";
    }

    public List<Object>  arquivoToLinha(Arquivo arq) {
        List<Object> video = new ArrayList<>();

        if(arq.getTipoArq().equals("Video")) {

            video.add(arq.getID());
            video.add(arq.getNome());
            video.add(arq.getAutor());
            video.add(arq.getAlbum_Temporada());
            video.add(arq.getCategoria());
            video.add(arq.getCaminho());
        }
        return video;
    }

    public int chave() {
        return this.idVideo;
    }


}