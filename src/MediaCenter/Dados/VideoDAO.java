package MediaCenter.Dados;

import MediaCenter.LogicaDeNegocio.Funcionalidades.Arquivo;
import MediaCenter.LogicaDeNegocio.Funcionalidades.Video;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

public class VideoDAO implements ArquivoDAO {
    private static VideoDAO inst = null;
    private final List<String> colunas = Arrays.asList("id","titulo","autor","temporada","categoria","caminho");


    private VideoDAO () {
    }

    //singleton
    public static VideoDAO getInstance() {
        if (inst == null) {
            inst = new VideoDAO();
        }
        return inst;
    }

    public void clear () {
        try {
            Statement stm = Conexao.getConexao().createStatement();
            stm.executeUpdate("DELETE FROM Video");
        }
        catch (Exception e) {throw new NullPointerException(e.getMessage());}
    }

    public boolean containsKey(Object key) throws NullPointerException {
        try {
            Statement stm = Conexao.getConexao().createStatement();
            String query = "SELECT nome FROM Video WHERE id ='" + (int)key + "'";
            ResultSet rs = stm.executeQuery(query);
            return rs.next();
        }
        catch (Exception e) {throw new NullPointerException(e.getMessage());}
    }

    public boolean containsValue(Object value) {
        if(!(value instanceof Video))
            return false;

        try {
            List<Object> videoLinha = ((Video) value).arquivoToLinha((Video)value);

            Statement stm = Conexao.getConexao().createStatement();
            StringBuilder query = new StringBuilder("SELECT * FROM Video WHERE ");



            //percorre todas as linhas da tabela, excepto a última
            for(int i = 0; i < (this.colunas.size()-1) ; i++){
                query.append(this.colunas.get(i) + " = '" + videoLinha.get(i) + "' and ");
            }

            //ultima linha para pôr;
            query.append(this.colunas.get(this.colunas.size()-1) + " = '" + videoLinha.get(this.colunas.size()-1) + "' ; ");

            ResultSet rs = stm.executeQuery(query.toString());

            return rs.next();
        }
        catch (SQLException e) {
            throw new NullPointerException(e.getMessage());
        }
    }

    public Set<Map.Entry<Integer, Arquivo>> entrySet() {

        try {
            Set<Map.Entry<Integer, Arquivo>> setVideo = new HashSet<>();
            Statement stm = Conexao.getConexao().createStatement();
            ResultSet rs = stm.executeQuery("SELECT * FROM Video;");

            List<String> linha;

            while (rs.next()) {

                linha = new ArrayList<>();

                for( int i = 1; i <= this.colunas.size(); i++)
                    linha.add(rs.getString(i));


                Video v = new Video(Integer.valueOf(linha.get(0)), linha.get(1), linha.get(2),
                        linha.get(3), linha.get(4), linha.get(5));

                setVideo.add(new AbstractMap.SimpleEntry(v.chave(),v));
            }

            return setVideo;
        }
        catch (SQLException e) {throw new NullPointerException(e.getMessage());}
    }

    public boolean equals(Object o) {
        return o.getClass().equals( this.getClass());
    }

    public Video get(Object key) {
        try {
            Video m = null;
            Statement stm = Conexao.getConexao().createStatement();
            String sql = "SELECT * FROM Video WHERE id='"+(Integer)key+"'";
            ResultSet rs = stm.executeQuery(sql);
            if (rs.next())
                m = new Video(Integer.valueOf(rs.getString(1)),rs.getString(2),rs.getString(3),
                        rs.getString(4), rs.getString(5), rs.getString(6));
            return m;
        }
        catch (Exception e) {throw new NullPointerException(e.getMessage());}
    }



    //transacoes
    public Arquivo put(Integer key, Arquivo value) {
        try {
            Video v = null;
            Statement stm = Conexao.getConexao().createStatement();
            stm.executeUpdate("DELETE FROM Video WHERE id='"+key+"'");
            String query = "INSERT INTO Video(id, titulo, autor, temporada, categoria, caminho;) " +
                    "VALUES ('" + value.getID() + "','" + key + "','" + value.getNome() +
                    "','" + value.getAutor() + "','" + value.getAlbum_Temporada() + value.getCategoria() +
                    "','" + value.getCaminho() + "')";
            stm.executeUpdate(query);
            return new Video(value.getID(), value.getNome(),value.getAutor(),
                    value.getAlbum_Temporada(), value.getCategoria(), value.getCaminho());
        }
        catch (Exception e) {throw new NullPointerException(e.getMessage());}
    }





    public int hashCode() {
        return this.inst.hashCode();
    }

    public boolean isEmpty() {
        try {
            Statement stm = Conexao.getConexao().createStatement();
            ResultSet rs = stm.executeQuery("SELECT nome FROM Video");
            return !rs.next();
        }
        catch (Exception e) {throw new NullPointerException(e.getMessage());}
    }

    public Set<Integer> keySet() {
        try {
            Statement stm = Conexao.getConexao().createStatement();
            ResultSet rs = stm.executeQuery("SELECT " + this.colunas.get(1) + " FROM Video;");

            Set<Integer> setChaves = new HashSet<>();
            while (rs.next()) {
                setChaves.add(Integer.valueOf(rs.getString(1)));
            }

            return setChaves;
        }
        catch (SQLException e) {throw new NullPointerException(e.getMessage());
        }

    }

    //transacoes
    public Video update(Integer key, Object value){
        try {

            if (!(value instanceof Video))
                return null;

            Video v = (Video) value;
            List<Object> linha = v.arquivoToLinha(v);


            Statement stm = Conexao.getConexao().createStatement();
            StringBuilder query = new StringBuilder("UPDATE Video SET ");

            //até à penultima coluna
            for(int i = 0; i < (this.colunas.size()-1) ; i++){
                query.append(this.colunas.get(i) + " = '" + linha.get(i) + "' , ");
            }


            query.append(this.colunas.get(this.colunas.size()-1) + " = '" +
                    linha.get(this.colunas.size()-1) + "' WHERE " +
                    this.colunas.get(0) + " = '" + key + "';");


            stm.executeUpdate(query.toString());

            return v;
        }
        catch (SQLException e) {
            throw new NullPointerException(e.getMessage());
        }

    }

    public Video remove(Object key) {
        try {
            Video v = this.get(key);
            Statement stm = Conexao.getConexao().createStatement();
            String query = "DELETE FROM Video where " + this.colunas.get(0) +  " = '" + key + "' ;";
            stm.executeUpdate(query);
            return v;
        }
        catch (Exception e) {throw new NullPointerException(e.getMessage());}
    }

    public void putAll(Map<? extends Integer, ? extends Arquivo> video) {
        if(video.values().equals("Video")) {

            for (Arquivo v : video.values()) {
                this.put(v.chave(), v);
            }
        }
    }

    public Map<Integer, Video> getTodos() {
        Map<Integer, Video> mapVideos = new HashMap<>();
        Collection<Arquivo> colecao = values();


        colecao.forEach(v -> mapVideos.put(v.chave(), (Video) v));

        return mapVideos;
    }



    public int size() {
        try {
            int tam = 0;
            Statement stm = Conexao.getConexao().createStatement();
            ResultSet rs = stm.executeQuery("SELECT id FROM Video");
            for (tam = 0;rs.next();tam++);
            return tam;
        }
        catch (Exception e) {throw new NullPointerException(e.getMessage());}
    }




    //transacoes
    public Collection<Arquivo> values() {
        try {
            Collection colecao = new HashSet<Video>();
            Statement stm = Conexao.getConexao().createStatement();
            ResultSet rs = stm.executeQuery("SELECT * FROM Video");
            while (rs.next()) {
                colecao.add(new Video(Integer.valueOf(rs.getString(1)),rs.getString(2),rs.getString(3),
                        rs.getString(4),rs.getString(5),rs.getString(6)));
            }
            return colecao;
        }
        catch (Exception e) {throw new NullPointerException(e.getMessage());}
    }


    public int proximoId() {
        try {
            int proxId = 0;
            Statement stm = Conexao.getConexao().createStatement();
            String query = "SELECT id FROM Video ORDER BY id DESC LIMIT 1;";
            ResultSet rs = stm.executeQuery(query);

            if (rs.next()) {
                proxId = Integer.valueOf(rs.getString(1));
            }

            proxId++;


            return proxId;
        } catch (SQLException e) {
            throw new NullPointerException(e.getMessage());
        }
    }

}
