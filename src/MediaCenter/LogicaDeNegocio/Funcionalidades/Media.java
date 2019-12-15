package MediaCenter.LogicaDeNegocio.Funcionalidades;

import MediaCenter.Dados.*;

import java.util.Collection;

public class Media {

    private CategoriaMusicaDAO categoriasMusica;
    private CategoriaVideoDAO categoriasVideo;
    private PlaylistsUserMusicaDAO playlistsMusica;
    private PlaylistsUserVideoDAO playlistsVideo;
    private ArquivoDAO arquivos;
    private static int idArquivos;

    /**
     *
     * @param email
     * @param arquivo
     */
    public void upload(String email, Arquivo arquivo) {
        // TODO - implement Media.upload
        throw new UnsupportedOperationException();
    }

    /**
     *
     * @param email
     * @param idArquivo
     * @param novaCategoria
     */
    public void alterarCategoria(String email, int idArquivo, String novaCategoria) {
        // TODO - implement Media.alterarCategoria
        throw new UnsupportedOperationException();
    }

    /**
     *
     * @param idArquivo
     * @param email
     */
    public void removerArquivo(int idArquivo, String email) {
        // TODO - implement Media.removerArquivo
        throw new UnsupportedOperationException();
    }

    /**
     *
     * @param categoria
     * @param local
     * @param artista
     * @param ano
     * @param mail
     */
    public void play(String categoria, String local, String artista, int ano, String mail) {
        // TODO - implement Media.play
        throw new UnsupportedOperationException();
    }

    /**
     *
     * @param mail
     * @param arqs
     */
    public void criarPlaylist(String mail, Collection<Integer> arqs) {
        // TODO - implement Media.criarPlaylist
        throw new UnsupportedOperationException();
    }

    /**
     *
     * @param arquivo
     */
    public void download(Arquivo arquivo) {
        // TODO - implement Media.download
        throw new UnsupportedOperationException();
    }

    public void getColecaoUtilizador() {
        // TODO - implement Media.getColecaoUtilizador
        throw new UnsupportedOperationException();
    }

    /**
     *
     * @param arqs
     */
    public Collection<Arquivo> sortArquivosByArtista(Collection<Integer> arqs) {
        // TODO - implement Media.sortArquivosByArtista
        throw new UnsupportedOperationException();
    }

    /**
     *
     * @param arqs
     */
    public Collection<Arquivo> sortArquivosByGenero(Collection<Integer> arqs) {
        // TODO - implement Media.sortArquivosByGenero
        throw new UnsupportedOperationException();
    }

    /**
     *
     * @param id
     * @param artista
     * @param ano
     * @param mail
     */
    public void playPlaylist(int id, String artista, int ano, String mail) {
        // TODO - implement Media.playPlaylist
        throw new UnsupportedOperationException();
    }

    /**
     *
     * @param userLoggedEmail
     */
    public void removerPlaylistsConvidado(String userLoggedEmail) {
        // TODO - implement Media.removerPlaylistsConvidado
        throw new UnsupportedOperationException();
    }

}