package MediaCenter.Dados;

import MediaCenter.LogicaDeNegocio.Funcionalidades.Arquivo;
import MediaCenter.LogicaDeNegocio.Funcionalidades.Playlists;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

public class PlaylistsUserMusicaDAO implements Map<String, Playlists> {
    private static PlaylistsUserMusicaDAO inst = null;
    private final List<String> colunas = Arrays.asList("nome", "email", "idMusica");
    //tabela : PlaylistMusica - a chave primaria da tabela é o nome da playlist


    private PlaylistsUserMusicaDAO () {
    }

    //singleton
    public static PlaylistsUserMusicaDAO getInstance() {
        if (inst == null) {
            inst = new PlaylistsUserMusicaDAO();
        }
        return inst;
    }



    public int size() {
        try {
            int tam = 0;
            Statement stm = Conexao.getConexao().createStatement();
            ResultSet rs = stm.executeQuery("SELECT * FROM PlaylistMusica");
            for (tam = 0;rs.next();tam++);
            return tam;
        }
        catch (Exception e) {throw new NullPointerException(e.getMessage());}
    }

    public boolean isEmpty() {
        try {
            Statement stm = Conexao.getConexao().createStatement();
            ResultSet rs = stm.executeQuery("SELECT id FROM PlaylistMusica");
            return !rs.next();
        }
        catch (Exception e) {throw new NullPointerException(e.getMessage());}
    }

    public boolean containsKey(Object key) {
        try {
            Statement stm = Conexao.getConexao().createStatement();
            String query = "SELECT nome FROM PlaylistMusica WHERE nome ='" + (String)key + "'";
            ResultSet rs = stm.executeQuery(query);
            return rs.next();
        }
        catch (Exception e) {throw new NullPointerException(e.getMessage());}
    }

    public boolean containsValue(Object value) {
        if(!(value instanceof Playlists))
            return false;

        try {
            List<Object> playlistLinha = ((Playlists) value).playlistToLinha((Playlists) value);

            Statement stm = Conexao.getConexao().createStatement();
            StringBuilder query = new StringBuilder("SELECT * FROM PlaylistMusica WHERE ");


            //percorre todas as colunas da tabela, excepto a última
            for(int i = 0; i < (this.colunas.size()-1) ; i++){
                query.append(this.colunas.get(i) + " = '" + playlistLinha.get(i) + "' and ");
            }

            //valor da ultima coluna para pôr;
            query.append(this.colunas.get(this.colunas.size()-1) + " = '" + playlistLinha.get(this.colunas.size()-1) + "' ; ");

            ResultSet rs = stm.executeQuery(query.toString());

            return rs.next();
        }
        catch (SQLException e) {
            throw new NullPointerException(e.getMessage());
        }
    }

    public Playlists get(Object key) {

        try {
            String nome = "";
            String email = "";
            Playlists p = null;
            Arquivo arquivo = null;
            Statement stm = Conexao.getConexao().createStatement();
            String sql = "SELECT * FROM PlaylistMusica WHERE nome ='" + (String) key + "'";
            ResultSet rs = stm.executeQuery(sql);


            List<Arquivo> arquivosPlaylist = new ArrayList<>();


            while (rs.next()) {

                nome = rs.getString(1);

                email = rs.getString(2);

                Integer id = (Integer.valueOf(rs.getString(3)));

                arquivo = p.getArquivoDaPlaylist(id);

                arquivosPlaylist.add(arquivo);


            }

            //adicionar parametros conforme o contrutor
            p = new Playlists(nome, email, arquivosPlaylist);

            return p;
        }
        catch (Exception e) {throw new NullPointerException(e.getMessage());}
    }

    public Playlists put(String key, Playlists value) {
        try {

            Statement stm = Conexao.getConexao().createStatement();
            //stm.executeUpdate("DELETE FROM PlaylistVideo WHERE id ='" + key + "','" + );

            for (int i = 0; i < value.getArquivos().size() - 1; i++) {
                String query = "INSERT INTO PlaylistVideo(nome, email, idMusica;) " +
                        "VALUES ('" + key + "','" + value.getEmail() + "','" + value.getArquivoDaPlaylist(i).getID() + "')";
                stm.executeUpdate(query);
            }


            return new Playlists(value.getNome(), value.getEmail(), value.getArquivos());
        }
        catch (Exception e) {throw new NullPointerException(e.getMessage());}
    }

    public Playlists remove(Object key) {
        try {
            Playlists p = this.get(key);
            Statement stm = Conexao.getConexao().createStatement();
            String query = "DELETE FROM PlaylistMusica where nome =" + this.colunas.get(0) +  "' ;";
            stm.executeUpdate(query);


            return p;
        }
        catch (Exception e) {throw new NullPointerException(e.getMessage());}
    }

    public void putAll(Map<? extends String, ? extends Playlists> colecao) {
        for(Map.Entry entrada: colecao.entrySet()) {
            String key = (String)entrada.getKey();
            for (Playlists p : colecao.values()) {
                this.put(key, p);
            }
        }
    }

    public void clear() {
        try {
            Statement stm = Conexao.getConexao().createStatement();
            stm.executeUpdate("DELETE FROM PlaylistMusica");
        }
        catch (Exception e) {throw new NullPointerException(e.getMessage());}
    }

    public Set<String> keySet() {
        try {
            Statement stm = Conexao.getConexao().createStatement();
            ResultSet rs = stm.executeQuery("SELECT " + this.colunas.get(0) + " FROM PlaylistMusica;");

            Set<String> setChaves = new HashSet<>();
            while (rs.next()) {
                setChaves.add(rs.getString(1));
            }

            return setChaves;
        }
        catch (SQLException e) {throw new NullPointerException(e.getMessage());
        }
    }

    public Collection<Playlists> values() {
        return null;
    }

    public Set<Entry<String, Playlists>> entrySet() {
        return null;
    }


}