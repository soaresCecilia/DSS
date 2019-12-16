package MediaCenter.Dados;

import MediaCenter.LogicaDeNegocio.Funcionalidades.Colecoes;
import MediaCenter.LogicaDeNegocio.Funcionalidades.ColecoesMusica;
import MediaCenter.LogicaDeNegocio.Funcionalidades.ColecoesVideo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

public class ColecoesVideoDAO implements ColecoesDAO {
    private static ColecoesVideoDAO inst = null;
    private final List<String> colunas = Arrays.asList("id", "email", "acao");
    //tabela : ColecoesVideo

    private ColecoesVideoDAO() {
    }

    //singleton
    public static ColecoesVideoDAO getInstance() {
        if (inst == null) {
            inst = new ColecoesVideoDAO();
        }
        return inst;
    }


    @Override
    public int size() {
        try {
            int tam = 0;
            Statement stm = Conexao.getConexao().createStatement();
            ResultSet rs = stm.executeQuery("SELECT * FROM ColecoesVideo");
            for (tam = 0;rs.next();tam++);
            return tam;
        }
        catch (Exception e) {throw new NullPointerException(e.getMessage());}
    }

    @Override
    public boolean isEmpty() {
        try {
            Statement stm = Conexao.getConexao().createStatement();
            ResultSet rs = stm.executeQuery("SELECT id FROM ColecoesVideo");
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
            String query = "SELECT id FROM ColecoesVideo WHERE id ='" + ((Key)key).getId() +
                    " and email ='" + ((Key)key).getEmail() +  "'";
            ResultSet rs = stm.executeQuery(query);
            return rs.next();
        }
        catch (Exception e) {throw new NullPointerException(e.getMessage());}
    }


    @Override
    public Colecoes get(Object key) {
        try {
            ColecoesVideo c = null;
            String acao = "";
            List<Integer> uploads = new ArrayList<>();
            List<Integer> downloads = new ArrayList<>();
            Statement stm = Conexao.getConexao().createStatement();
            String sql = "SELECT * FROM ColecoesVideo WHERE email ='"+((Key)key).getEmail()+"'";
            ResultSet rs = stm.executeQuery(sql);
            while (rs.next()) {

                if(rs.getString(3).equals("u")) {
                    uploads.add(Integer.valueOf(rs.getString(1)));
                }
                else if(rs.getString(3).equals("d")){
                    downloads.add(Integer.valueOf(rs.getString(1)));
                }

            }
            //adicionar parametros conforme o contrutor da categoria
            c = new ColecoesVideo(uploads, downloads);
            return c;
        }
        catch (Exception e) {throw new NullPointerException(e.getMessage());}
    }

    @Override
    public Colecoes put(Key key, Colecoes value) {
        try {
            Statement stm = Conexao.getConexao().createStatement();
            remove(key);

            for(int i = 0; i < value.tamColecaoUploads()-1; i++) {
                String query = "INSERT INTO ColecoesVideo(id, email, acao;) " +
                        "VALUES ('" + key.getId() + "','" + key.getEmail() + "', u)";
                stm.executeUpdate(query);
            }

            for(int i = 0; i < value.tamColecaoDownloads()-1; i++) {
                String query = "INSERT INTO ColecoesVideo(id, email, acao;) " +
                        "VALUES ('" + key.getId() + "','" + key.getEmail() + "', d)";
                stm.executeUpdate(query);
            }


            return value;
        }
        catch (Exception e) {throw new NullPointerException(e.getMessage());}
    }


    @Override
    public Colecoes remove(Object key) {
        if(!(key instanceof Key)) {
            return null;
        }

        try {
            ColecoesVideo c = (ColecoesVideo) this.get(key);
            Statement stm = Conexao.getConexao().createStatement();
            String query = "DELETE FROM ColecoesVideo where where " + this.colunas.get(1) +  " = '" + ((Key) key).getEmail() + "' ;";
            stm.executeUpdate(query);


            return c;
        }
        catch (Exception e) {throw new NullPointerException(e.getMessage());}
    }

    @Override
    public void putAll(Map<? extends Key, ? extends Colecoes> colecao) {
        for(Map.Entry entrada: colecao.entrySet()) {
            Key key = (Key)entrada.getKey();
            for (Colecoes c : colecao.values()) {
                this.put(key, c);
            }
        }
    }


    @Override
    public void clear() {
        try {
            Statement stm = Conexao.getConexao().createStatement();
            stm.executeUpdate("DELETE * FROM ColecoesVideo");
        }
        catch (Exception e) {throw new NullPointerException(e.getMessage());}
    }

    public Set<Key> keySet() {
        try {
            Statement stm = Conexao.getConexao().createStatement();
            ResultSet rs = stm.executeQuery("SELECT " + this.colunas.get(0) + "','" + this.colunas.get(1) + " FROM ColecoesVideo;");

            Key k = null;
            Set<Key> setChaves = new HashSet<>();

            while (rs.next()) {
                k.setId(Integer.valueOf(rs.getString(1)));
                k.setEmail(rs.getString(2));
                setChaves.add(k);
            }

            return setChaves;
        }
        catch (SQLException e) {throw new NullPointerException(e.getMessage());
        }
    }

    @Override
    public Collection<Colecoes> values() {
        throw new NullPointerException("Not implemented!");
    }

    @Override
    public Set<Entry<Key, Colecoes>> entrySet() {
        throw new NullPointerException("Not implemented!");
    }

    @Override
    public boolean containsValue(Object value) {
        throw new NullPointerException("Not implemented!");
    }

}