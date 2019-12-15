package MediaCenter.LogicaDeNegocio.Funcionalidades;

import MediaCenter.Dados.ArquivoDAO;

import java.util.*;

public class Categoria {

    private String categoriaArquivo;
    private ArquivoDAO arquivos;
    private String email;


    public String getEmail() {
        return email;
    }

    public Categoria(String categoriaArquivo, String email) {
        this.categoriaArquivo = categoriaArquivo;
        this.email = email;
    }


    public String getCategoriaArquivo() {
        return this.categoriaArquivo;
    }

    public List<Object>  categoriaToLinha(Categoria arq) {
        List<Object> categoria = new ArrayList<>();

            categoria.add(arq.getCategoriaArquivo());
            categoria.add(arq.getEmail());

        return categoria;
    }

    /**
     * @param emailUserUpl
     * @param arquivo
     */
    public void addArquivo(String emailUserUpl, Arquivo arquivo) {
        // TODO - implement Categoria.addArquivo
        throw new UnsupportedOperationException();
    }

    /**
     * @param emailUserUpl
     */
    public Collection<Arquivo> getUploads(String emailUserUpl) {
        // TODO - implement Categoria.getUploads
        throw new UnsupportedOperationException();
    }

    public Collection<Arquivo> getAllArquivos() {
        // TODO - implement Categoria.getAllArquivos
        throw new UnsupportedOperationException();
    }

    /**
     * @param emailUserUpl
     * @param idArquivo
     */
    public void removeArquivo(String emailUserUpl, int idArquivo) {
        // TODO - implement Categoria.removeArquivo
        throw new UnsupportedOperationException();
    }
}
