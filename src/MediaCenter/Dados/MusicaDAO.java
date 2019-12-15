package MediaCenter.Dados;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

import MediaCenter.LogicaDeNegocio.Funcionalidades.Arquivo;
import MediaCenter.LogicaDeNegocio.Funcionalidades.Musica;


public class MusicaDAO implements ArquivoDAO{
    private static MusicaDAO inst = null;
    private final List<String> colunas = Arrays.asList("id","nome","autor","album","categoria","caminho");


    private MusicaDAO () {
    }

    //singleton
    public static MusicaDAO getInstance() {
        if (inst == null) {
            inst = new MusicaDAO();
        }
        return inst;
    }

    public void clear () {
        try {
            Statement stm = Conexao.getConexao().createStatement();
            stm.executeUpdate("DELETE FROM Musica");
        }
        catch (Exception e) {throw new NullPointerException(e.getMessage());}
    }

    public boolean containsKey(Object key) throws NullPointerException {
        try {
            Statement stm = Conexao.getConexao().createStatement();
            String query = "SELECT nome FROM Musica WHERE id ='" + (int)key + "'";
            ResultSet rs = stm.executeQuery(query);
            return rs.next();
        }
        catch (Exception e) {throw new NullPointerException(e.getMessage());}
    }

    public boolean containsValue(Object value) {
        if(!(value instanceof Musica))
            return false;

        try {
            List<Object> musicaLinha = ((Musica) value).arquivoToLinha((Musica)value);

            Statement stm = Conexao.getConexao().createStatement();
            StringBuilder query = new StringBuilder("SELECT * FROM Musica WHERE ");



            //percorre todas as linhas da tabela, excepto a última
            for(int i = 0; i < (this.colunas.size()-1) ; i++){
                query.append(this.colunas.get(i) + " = '" + musicaLinha.get(i) + "' and ");
            }

            //ultima linha para pôr;
            query.append(this.colunas.get(this.colunas.size()-1) + " = '" + musicaLinha.get(this.colunas.size()-1) + "' ; ");

            ResultSet rs = stm.executeQuery(query.toString());

            return rs.next();
        }
        catch (SQLException e) {
            throw new NullPointerException(e.getMessage());
        }
    }

    public Set<Map.Entry<Integer, Arquivo>> entrySet() {

        try {
            Set<Map.Entry<Integer, Arquivo>> setMusica = new HashSet<>();
            Statement stm = Conexao.getConexao().createStatement();
            ResultSet rs = stm.executeQuery("SELECT * FROM Musica;");

            List<String> linha;

            while (rs.next()) {

                linha = new ArrayList<>();

                for( int i = 1; i <= this.colunas.size(); i++)
                    linha.add(rs.getString(i));


                Musica m = new Musica(Integer.valueOf(linha.get(0)), linha.get(1), linha.get(2),
                                        linha.get(3), linha.get(4), linha.get(5));

                setMusica.add(new AbstractMap.SimpleEntry(m.chave(),m));
            }

            return setMusica;
        }
        catch (SQLException e) {throw new NullPointerException(e.getMessage());}
    }


    public Musica get(Object key) {
        try {
            Musica m = null;
            Statement stm = Conexao.getConexao().createStatement();
            String sql = "SELECT * FROM Musica WHERE id='"+(Integer)key+"'";
            ResultSet rs = stm.executeQuery(sql);
            if (rs.next())
                m = new Musica(Integer.valueOf(rs.getString(1)),rs.getString(2),rs.getString(3),
                        rs.getString(4), rs.getString(5), rs.getString(6));
            return m;
        }
        catch (Exception e) {throw new NullPointerException(e.getMessage());}
    }



    //transacoes
    public Arquivo put(Integer key, Arquivo value) {
        try {
            Musica m = null;
            Statement stm = Conexao.getConexao().createStatement();
            stm.executeUpdate("DELETE FROM Musica WHERE id='"+key+"'");
            String query = "INSERT INTO Musica(id, nome, autor, album, categoria, caminho;) " +
                         "VALUES ('" + value.getID() + "','" + key + "','" + value.getNome() +
                         "','" + value.getAutor() + "','" + value.getAlbum_Temporada() + "','" + value.getCategoria() +
                         "','" + value.getCaminho() + "')";
            stm.executeUpdate(query);
            return new Musica(value.getID(), value.getNome(),value.getAutor(),
                                value.getAlbum_Temporada(), value.getCategoria(), value.getCaminho());
        }
        catch (Exception e) {throw new NullPointerException(e.getMessage());}
    }




    public boolean isEmpty() {
        try {
            Statement stm = Conexao.getConexao().createStatement();
            ResultSet rs = stm.executeQuery("SELECT nome FROM Musica");
            return !rs.next();
        }
        catch (Exception e) {throw new NullPointerException(e.getMessage());}
    }

    public Set<Integer> keySet() {
        try {
            Statement stm = Conexao.getConexao().createStatement();
            ResultSet rs = stm.executeQuery("SELECT " + this.colunas.get(0) + " FROM Musica;");

            Set<Integer> setChaves = new HashSet<>();
            while (rs.next()) {
                setChaves.add(Integer.valueOf(rs.getString(1)));
            }

            return setChaves;
        }
        catch (SQLException e) {throw new NullPointerException(e.getMessage());
        }

    }



    public Musica remove(Object key) {
        try {
            Musica m = this.get(key);
            Statement stm = Conexao.getConexao().createStatement();
            String query = "DELETE FROM Musica where " + this.colunas.get(0) +  " = '" + key + "' ;";
            stm.executeUpdate(query);
            return m;
        }
        catch (Exception e) {throw new NullPointerException(e.getMessage());}
    }



    public void putAll(Map<? extends Integer, ? extends Arquivo> musica) {
        if(musica.values().equals("Musica")) {

            for (Arquivo m : musica.values()) {
                this.put(m.chave(), m);
            }
        }
    }


    public int size() {
        try {
            int tam = 0;
            Statement stm = Conexao.getConexao().createStatement();
            ResultSet rs = stm.executeQuery("SELECT * FROM Musica");
            for (tam = 0;rs.next();tam++);
            return tam;
        }
        catch (Exception e) {throw new NullPointerException(e.getMessage());}
    }




    //transacoes
    public Collection<Arquivo> values() {
        try {
            Collection colecao = new HashSet<Musica>();
            Statement stm = Conexao.getConexao().createStatement();
            ResultSet rs = stm.executeQuery("SELECT * FROM Musica");
            while (rs.next()) {
                colecao.add(new Musica(Integer.valueOf(rs.getString(1)),rs.getString(2),rs.getString(3),
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
            String query = "SELECT id FROM Musica ORDER BY id DESC LIMIT 1;";
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


    public Map<Integer, Musica> getTodos() {
        Map<Integer, Musica> mapMusicas = new HashMap<>();
        Collection<Arquivo> colecao = values();

        colecao.forEach(m -> mapMusicas.put(m.chave(), (Musica)m));

        return mapMusicas;
    }


    //transacoes
    public Musica update(Integer key, Object value){
        try {

            if (!(value instanceof Musica))
                return null;

            Musica m = (Musica) value;
            List<Object> linha = m.arquivoToLinha(m);


            Statement stm = Conexao.getConexao().createStatement();
            StringBuilder query = new StringBuilder("UPDATE Musica SET ");

            //até à penultima coluna
            for(int i = 0; i < (this.colunas.size()-1) ; i++){
                query.append(this.colunas.get(i) + " = '" + linha.get(i) + "' , ");
            }


            query.append(this.colunas.get(this.colunas.size()-1) + " = '" +
                    linha.get(this.colunas.size()-1) + "' WHERE " +
                    this.colunas.get(0) + " = '" + key + "';");


            stm.executeUpdate(query.toString());

            return m;
        }
        catch (SQLException e) {
            throw new NullPointerException(e.getMessage());
        }

    }

    public boolean equals(Object o) {
        return o.getClass().equals( this.getClass());
    }
    public int hashCode() {
        return this.inst.hashCode();
    }

}

