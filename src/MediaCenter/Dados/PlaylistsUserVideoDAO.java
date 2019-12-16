package MediaCenter.Dados;

import MediaCenter.LogicaDeNegocio.Funcionalidades.Arquivo;
import MediaCenter.LogicaDeNegocio.Funcionalidades.Playlists;
import MediaCenter.LogicaDeNegocio.Funcionalidades.Video;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

public class PlaylistsUserVideoDAO implements Map<String, Playlists> {
    private static PlaylistsUserVideoDAO inst = null;
    private final List<String> colunas = Arrays.asList("nome", "email", "idVideo");
    //tabela : PlaylistVideo - a chave primaria da tabela é o nome da playlist


    private PlaylistsUserVideoDAO () {
    }

    //singleton
    public static PlaylistsUserVideoDAO getInstance() {
        if (inst == null) {
            inst = new PlaylistsUserVideoDAO();
        }
        return inst;
    }



    public int size() {
        try {
            int tam = 0;
            Statement stm = Conexao.getConexao().createStatement();
            ResultSet rs = stm.executeQuery("SELECT * FROM PlaylistVideo");
            for (tam = 0;rs.next();tam++);
            return tam;
        }
        catch (Exception e) {throw new NullPointerException(e.getMessage());}
    }

    public boolean isEmpty() {
        try {
            Statement stm = Conexao.getConexao().createStatement();
            ResultSet rs = stm.executeQuery("SELECT * FROM PlaylistVideo");
            return !rs.next();
        }
        catch (Exception e) {throw new NullPointerException(e.getMessage());}
    }

    public boolean containsKey(Object key) {
        try {
            Statement stm = Conexao.getConexao().createStatement();
            String query = "SELECT * FROM PlaylistVideo WHERE nome ='" + (String)key + "'";
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
            String query = ("SELECT * FROM PlaylistVideo WHERE nome = '" + this.colunas.get(0));
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
            String sql = "SELECT p.nome, p.email, v.id, v.titulo, v.autor, v.temporada," +
                    " v.categoria, v.caminho FROM PlaylistVideo as p" +
                    " INNER JOIN Video as v ON p.idVideo = v.id WHERE p.nome ='" + (String) key + "'";
            ResultSet rs = stm.executeQuery(sql);




            List<Arquivo> arquivosPlaylist = new ArrayList<>();

            String nome = rs.getString(1);;
            String email = rs.getString(2);;


            while (rs.next()) {

                //garantir que só traz musicas da mesma playlist
                assert((nome.equals(rs.getString(1))));

                //garantir que só traz musicas do mesmo utilizador
                assert((email.equals(rs.getString(2))));

                arquivo = new Video(Integer.valueOf(rs.getString(3)), rs.getString(4),
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
            //stm.executeUpdate("DELETE FROM PlaylistVideo WHERE id ='" + key + "','" + );

            for (int i = 0; i < value.getArquivos().size() - 1; i++) {
                String query = "INSERT INTO PlaylistVideo(nome, email, idVideo;) " +
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
            String query = "DELETE * FROM PlaylistsVideo where nome ='" + this.colunas.get(0) +  "' ;";
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
            stm.executeUpdate("DELETE * FROM PlaylistsVideo");
        }
        catch (Exception e) {throw new NullPointerException(e.getMessage());}
    }

    public Set<String> keySet() {
        try {
            Statement stm = Conexao.getConexao().createStatement();
            ResultSet rs = stm.executeQuery("SELECT '" + this.colunas.get(0) + "' FROM PlaylistsVideo;");

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


    //Devolve uma lista de ids de videos da playlist
    public Collection<Integer> getPorEmail(String email) {
        try {
            Statement stm = Conexao.getConexao().createStatement();
            String sql = "SELECT'" + this.colunas.get(2) + "' FROM PlaylistVideo WHERE nome ='" + email + "'";
            ResultSet rs = stm.executeQuery(sql);

            List<Integer> idsVideo = new ArrayList<>();


            while (rs.next()) {
                idsVideo.add(Integer.valueOf(rs.getString(1)));
            }

            return idsVideo;
        }
        catch (Exception e) {throw new NullPointerException(e.getMessage());}
    }


    public Collection<Arquivo> getArquivosPorNomePlaylist(String nomePlaylist) {
        try {
            Statement stm = Conexao.getConexao().createStatement();
            String sql = "SELECT v.id, v.titulo, v.autor, v.temporada," +
                    " v.categoria, v.caminho FROM PlaylistVideo as p "+
                    " INNER JOIN Video as v ON p.idVideo = v.id WHERE p.nome ='" + nomePlaylist + "'";

            ResultSet rs = stm.executeQuery(sql);

            List<Arquivo> arqVideo = new ArrayList<>();
            Arquivo arq = null;

            while (rs.next()) {
                arq = new Video(Integer.valueOf(rs.getString(1)), rs.getString(2),
                        rs.getString(3), rs.getString(4),
                        rs.getString(5), rs.getString(6));

                arqVideo.add(arq);
            }

            return arqVideo;
        }
        catch (Exception e) {throw new NullPointerException(e.getMessage());}

    }


}