package MediaCenter.Dados;

import MediaCenter.LogicaDeNegocio.Funcionalidades.Arquivo;
import MediaCenter.LogicaDeNegocio.Funcionalidades.Musica;
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
            ResultSet rs = stm.executeQuery("SELECT * FROM PlaylistMusica");
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

            Statement stm = Conexao.getConexao().createStatement();
            String query = ("SELECT * FROM PlaylistMusica WHERE nome = '" + this.colunas.get(0));
            ResultSet rs = stm.executeQuery(query);


            return rs.next();
        }
        catch (SQLException e) {
            throw new NullPointerException(e.getMessage());
        }
    }

    public Playlists get(Object key) {

        try {
            Arquivo arquivo = null;
            Statement stm = Conexao.getConexao().createStatement();
            String sql = "SELECT p.nome, p.email, m.id, m.nome, m.autor, m.album," +
                    " m.categoria, m.caminho FROM PlaylistMusica as p" +
                    " INNER JOIN Musica as m ON p.idMusica = m.id WHERE p.nome ='" + (String) key + "'";
            ResultSet rs = stm.executeQuery(sql);




            List<Arquivo> arquivosPlaylist = new ArrayList<>();

            String nome = rs.getString(1);;
            String email = rs.getString(2);;


            while (rs.next()) {

                //garantir que só traz musicas da mesma playlist
                assert((nome.equals(rs.getString(1))));

                //garantir que só traz musicas do mesmo utilizador
                assert((email.equals(rs.getString(2))));

                arquivo = new Musica(Integer.valueOf(rs.getString(3)), rs.getString(4),
                                        rs.getString(5), rs.getString(6),
                                        rs.getString(7), rs.getString(8));

                arquivosPlaylist.add(arquivo);

            }

            Playlists p = new Playlists(nome, email, arquivosPlaylist);

            return p;
        }
        catch (Exception e) {throw new NullPointerException(e.getMessage());}
    }

    public Playlists put(String key, Playlists value) {
        try {

            Statement stm = Conexao.getConexao().createStatement();
            remove(key);

            for (int i = 0; i < value.getArquivos().size() - 1; i++) {
                String query = "INSERT INTO PlaylistMusica(nome, email, idMusica;) " +
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
            String query = "DELETE * FROM PlaylistMusica where nome ='" + this.colunas.get(0) +  "' ;";
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
            stm.executeUpdate("DELETE * FROM PlaylistMusica");
        }
        catch (Exception e) {throw new NullPointerException(e.getMessage());}
    }

    public Set<String> keySet() {
        try {
            Statement stm = Conexao.getConexao().createStatement();
            ResultSet rs = stm.executeQuery("SELECT '" + this.colunas.get(0) + "' FROM PlaylistMusica;");

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
        throw new NullPointerException("Not implemented!");
    }

    public Set<Entry<String, Playlists>> entrySet() {
        throw new NullPointerException("Not implemented!");
    }


    //Devolve uma lista de ids de musica da playlist
    public Collection<Integer> getPorEmail(String email) {
        try {
            Statement stm = Conexao.getConexao().createStatement();
            String sql = "SELECT'" + this.colunas.get(2) + "' FROM PlaylistMusica WHERE nome ='" + email + "'";
            ResultSet rs = stm.executeQuery(sql);

            List<Integer> idsMusica = new ArrayList<>();

            while (rs.next()) {
                idsMusica.add(Integer.valueOf(rs.getString(1)));
            }

            return idsMusica;
        }
        catch (Exception e) {throw new NullPointerException(e.getMessage());}
    }


    public Collection<Arquivo> getArquivosPorNomePlaylist(String nomePlaylist) {
        try {
            Statement stm = Conexao.getConexao().createStatement();
            String sql = "SELECT m.id, m.nome, m.autor, m.album," +
                    " m.categoria, m.caminho FROM PlaylistMusica as p "+
                    " INNER JOIN Musica as m ON p.idMusica = m.id WHERE p.nome ='" + nomePlaylist + "'";

            ResultSet rs = stm.executeQuery(sql);

            List<Arquivo> arqMusica = new ArrayList<>();
            Arquivo arq = null;

            while (rs.next()) {
                arq = new Musica(Integer.valueOf(rs.getString(1)), rs.getString(2),
                                    rs.getString(3), rs.getString(4),
                                    rs.getString(5), rs.getString(6));
                arqMusica.add(arq);

            }
            return arqMusica;
        }
        catch (Exception e) {throw new NullPointerException(e.getMessage());}

    }




}