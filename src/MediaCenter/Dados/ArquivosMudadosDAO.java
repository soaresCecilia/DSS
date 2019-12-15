package MediaCenter.Dados;
import MediaCenter.LogicaDeNegocio.Funcionalidades.Arquivo;
import MediaCenter.LogicaDeNegocio.Funcionalidades.Musica;
import MediaCenter.LogicaDeNegocio.Funcionalidades.Video;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;


/*

Tu dentro do DAO de arquivos mudados metes que esse DAO implementa Map<Intwger, Arquivo>, tu vais ter uma
tabela com arquivos e outra tabela com arquivos mudados (esta tabela de arquivos mudados tem e-mail, id arquivo e
nova categoria). Dentro do DÃO de arquivos mudados vais ter de ir ver primeiro à tabela de arquivos mudados buscar o id
do arquivo e depois vais busca-lo à tabela dos arquivos (nesta tabela está o arquivo com a categoria default),
depois pegas neste arquivo e mudas-lhe a categoria para a nova


 */

class Key {
    private Integer id;
    private String email;


    public Integer getId() {
        return this.id;
    }

    public String getEmail() {
        return this.email;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj != null && obj instanceof Key) {
            Key k = (Key) obj;
            return id.equals(k.id) && email.equals(k.email);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return (id.toString() + email).hashCode();
    }
}

public class ArquivosMudadosDAO implements Map<Key, Arquivo> {
    private static ArquivosMudadosDAO inst = null;
    private final List<String> colunas = Arrays.asList("idArquivo","email","categoriaNova"); //as chaves da tabela são idArquivo e email
    //nome da tabela : ArquivosMudados


    private ArquivosMudadosDAO () {
    }

    //singleton
    public static ArquivosMudadosDAO getInstance() {
        if (inst == null) {
            inst = new ArquivosMudadosDAO();
        }
        return inst;
    }

    @Override
    public int size() {

        try {
            int tam = 0;
            Statement stm = Conexao.getConexao().createStatement();
            ResultSet rs = stm.executeQuery("SELECT id FROM ArquivosMudados");
            for (tam = 0;rs.next();tam++);
            return tam;
        }
        catch (Exception e) {throw new NullPointerException(e.getMessage());}
    }

    @Override
    public boolean isEmpty() {
        try {
            Statement stm = Conexao.getConexao().createStatement();
            ResultSet rs = stm.executeQuery("SELECT nome FROM ArquivosMudados");
            return !rs.next();
        }
        catch (Exception e) {throw new NullPointerException(e.getMessage());}
    }

    @Override
    public boolean containsKey(Object key) {
        if(!(key instanceof Key))
         return false;

        try {
            Statement stm = Conexao.getConexao().createStatement();
            String query = "SELECT categoriaNova FROM ArquivosMudados WHERE idArquivo ='" + ((Key)key).getId() +
                    " and email ='" + ((Key)key).getEmail() +  "'";
            ResultSet rs = stm.executeQuery(query);
            return rs.next();
        }
        catch (Exception e) {throw new NullPointerException(e.getMessage());}
    }


    public boolean containsValue(Object value) {
        if (!(value instanceof Arquivo))
            return false;

        Integer id = ((Arquivo) value).getID();

        try {
            Statement stm = Conexao.getConexao().createStatement();
            String query = "SELECT * FROM ArquivosMudados WHERE idArquivo ='" + id + "'";

            ResultSet rs = stm.executeQuery(query);
            return rs.next();

        } catch (SQLException e) {
            throw new NullPointerException(e.getMessage());
        }
    }

    public Arquivo get(Object key) {
        return null;
    }

    @Override
    public Arquivo put(Key key, Arquivo value) {
        return null;
    }

    public Arquivo remove(Object key) {
        return null;
    }

    @Override
    public void putAll(Map<? extends Key, ? extends Arquivo> m) {

    }

    @Override
    public void clear() {

    }
    @Override
    public Set<Key> keySet() {
        return null;
    }
    @Override
    public Collection<Arquivo> values() {
        return null;
    }
    @Override
    public Set<Entry<Key, Arquivo>> entrySet() {
        return null;
    }
}