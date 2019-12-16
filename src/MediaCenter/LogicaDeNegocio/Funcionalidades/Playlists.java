package MediaCenter.LogicaDeNegocio.Funcionalidades;

import MediaCenter.Dados.ArquivoDAO;

import java.util.*;

public class Playlists {

    //garantir que não há duas playlists com nomes iguais
    private String nome;
    private String email;
    private List<Arquivo> arquivos;
    private ArquivoDAO arquivosPlaylist;

    public Playlists(String nome, String email, List<Arquivo> arquivos) {
        this.nome = nome;
        this.email = email;
        this.arquivos = arquivos;
    }


    public Arquivo getArquivoDaPlaylist(Integer id) {

        for (Arquivo a: arquivos) {
            if (a.getID() == id) {
                return a;

            }
        }

        return null;
    }




    public List<Object>  playlistToLinha(Playlists p) {
        List<Object> playlist = new ArrayList<>();

            playlist.add(p.getNome());
            playlist.add(p.getEmail());
            playlist.add(p.getArquivos());

        return playlist;
    }


    /**
     *
     * @param idPlaylist
     * @param arquivos
     */
    public void criarPlaylist(String idPlaylist, Collection<Arquivo> arquivos) {
        // TODO - implement Playlists.criarPlaylist
        throw new UnsupportedOperationException();
    }

    public Collection<Arquivo> getAllPlaylists() {
        // TODO - implement Playlists.getAllPlaylists
        throw new UnsupportedOperationException();
    }

    /**
     *
     * @param idPlaylist
     */
    public Collection<Arquivo> getPlaylist(String idPlaylist) {
        // TODO - implement Playlists.getPlaylist
        throw new UnsupportedOperationException();
    }

    /**
     *
     * @param idPlaylist
     * @param arquivo
     */
    public void addArquivoPlaylist(String idPlaylist, Arquivo arquivo) {
        // TODO - implement Playlists.addArquivoPlaylist
        throw new UnsupportedOperationException();
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }


    public List<Arquivo> getArquivos() {
        return arquivos;
    }

}