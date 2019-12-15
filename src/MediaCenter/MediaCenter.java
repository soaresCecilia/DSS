package MediaCenter.LogicaDeNegocio;

import MediaCenter.LogicaDeNegocio.Funcionalidades.Arquivo;
import MediaCenter.LogicaDeNegocio.Funcionalidades.Media;
import MediaCenter.LogicaDeNegocio.Usuarios.RecursosHumanos;

import java.util.Collection;

public class MediaCenter {

    private Media media;
    private RecursosHumanos recursosHumanos;
    private String userLoggedemail;

    /**
     *
     * @param nome
     * @param autor
     * @param categoria
     * @param genero
     * @param tipoArq
     */
    public void upload(String nome, String autor, String categoria, String genero, int tipoArq) {
        // TODO - implement MediaCenter.upload
        throw new UnsupportedOperationException();
    }

    /**
     *
     * @param idArquivo
     * @param novaCategorai
     */
    public void alterarCategoria(int idArquivo, String novaCategorai) {
        // TODO - implement MediaCenter.alterarCategoria
        throw new UnsupportedOperationException();
    }

    /**
     *
     * @param idArquivo
     */
    public void removerArquivo(int idArquivo) {
        // TODO - implement MediaCenter.removerArquivo
        throw new UnsupportedOperationException();
    }

    /**
     *
     * @param categoria
     * @param local
     * @param artista
     * @param ano
     */
    public Collection<Integer> play(String categoria, String local, String artista, int ano) {
        // TODO - implement MediaCenter.play
        throw new UnsupportedOperationException();
    }

    /**
     *
     * @param arqs
     * @param modo
     */
    public void criarPlaylist(Collection<Integer> arqs, String modo) {
        // TODO - implement MediaCenter.criarPlaylist
        throw new UnsupportedOperationException();
    }

    /**
     *
     * @param email
     * @param password
     * @param tipo
     */
    public void iniciarSessao(String email, String password, int tipo) {
        // TODO - implement MediaCenter.iniciarSessao
        throw new UnsupportedOperationException();
    }

    public void terminarSessao() {
        // TODO - implement MediaCenter.terminarSessao
        throw new UnsupportedOperationException();
    }

    /**
     *
     * @param nome
     * @param email
     * @param password
     * @param nif
     * @param tipo
     */
    public void registarEntidade(String nome, String email, String password, Integer nif, int tipo) {
        // TODO - implement MediaCenter.registarEntidade
        throw new UnsupportedOperationException();
    }

    /**
     *
     * @param email
     */
    public void eliminarUser(String email) {
        // TODO - implement MediaCenter.eliminarUser
        throw new UnsupportedOperationException();
    }

    /**
     *
     * @param tipoUtilizador
     * @param tipoEdicao
     * @param novoCampo
     */
    public void editarDados(int tipoUtilizador, int tipoEdicao, String novoCampo) {
        // TODO - implement MediaCenter.editarDados
        throw new UnsupportedOperationException();
    }

    /**
     *
     * @param arquivo
     */
    public void download(Arquivo arquivo) {
        // TODO - implement MediaCenter.download
        throw new UnsupportedOperationException();
    }

    /**
     *
     * @param id
     * @param artista
     * @param ano
     */
    public void playPlaylist(int id, String artista, int ano) {
        // TODO - implement MediaCenter.playPlaylist
        throw new UnsupportedOperationException();
    }

}