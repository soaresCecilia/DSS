package MediaCenter.Dados;
import MediaCenter.LogicaDeNegocio.Funcionalidades.Arquivo;
import MediaCenter.LogicaDeNegocio.Funcionalidades.Musica;
import MediaCenter.LogicaDeNegocio.Funcionalidades.Video;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;




class Key {
    private Integer id;
    private String email;


    public Integer getId() {
        return this.id;
    }

    public String getEmail() {
        return this.email;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setEmail(String email) {
        this.email = email;
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
    private final List<String> colunas = Arrays.asList("id","email","categoriaNova"); //as chaves da tabela são idArquivo e email
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
            ResultSet rs = stm.executeQuery("SELECT id FROM ArquivosMudados");
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
            String query = "SELECT categoriaNova FROM ArquivosMudados WHERE id ='" + ((Key)key).getId() +
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
            String query = "SELECT * FROM ArquivosMudados WHERE id ='" + id + "'";

            ResultSet rs = stm.executeQuery(query);
            return rs.next();

        } catch (SQLException e) {
            throw new NullPointerException(e.getMessage());
        }
    }

    public Arquivo get(Object key) {
        if(!(key instanceof Key))
            return null;

        Integer id = ((Key) key).getId();


        try {
            Musica m = null;
            Statement stm = Conexao.getConexao().createStatement();
            String sqlMusica = "SELECT * FROM Musica WHERE id ='" + id +"'";
            ResultSet rsMusica = stm.executeQuery(sqlMusica);
            if (rsMusica.next()) {
                m = new Musica(Integer.valueOf(rsMusica.getString(1)), rsMusica.getString(2), rsMusica.getString(3),
                        rsMusica.getString(4), rsMusica.getString(5), rsMusica.getString(6));
                return m;
            }
            else {
                Video v = null;
                Statement st = Conexao.getConexao().createStatement();
                String sql = "SELECT * FROM Video WHERE id ='"+ id +"'";
                ResultSet rs = st.executeQuery(sql);
                if (rs.next())
                    v = new Video(Integer.valueOf(rs.getString(1)),rs.getString(2),rs.getString(3),
                            rs.getString(4), rs.getString(5), rs.getString(6));
                return v;
            }
        }
        catch (Exception e) {
            throw new NullPointerException(e.getMessage());
        }
    }

    @Override
    public Arquivo put(Key key, Arquivo value) {

        try {
        Statement stm = Conexao.getConexao().createStatement();
        stm.executeUpdate("DELETE FROM ArquivosMudados WHERE id ='"+ key.getId() + "and email ='" + key.getEmail() + "'");
        String query = "INSERT INTO ArquivosMudados(id, email, categoriaNova;) " +
                    "VALUES ('" + value.getID() + "','" + key.getEmail() + "','" + value.getCategoria() + "')";
        stm.executeUpdate(query);

        return value;
        }
        catch (Exception e) {throw new NullPointerException(e.getMessage());}
    }

    public Arquivo remove(Object key) {
        if(!(key instanceof Key))
            return null;

        Integer id = ((Key) key).getId();

        try {
            Arquivo a = this.get(id);
            Statement stm = Conexao.getConexao().createStatement();
            String querymusica = "DELETE FROM Musica where " + this.colunas.get(0) +  " = '" + id + "' ;";
            String queryvideo = "DELETE FROM Video where " + this.colunas.get(0) +  " = '" + id + "' ;";
            stm.executeUpdate(querymusica);
            stm.executeUpdate(queryvideo);

            return a;
        }
        catch (Exception e) {throw new NullPointerException(e.getMessage());}
    }

    @Override
    public void putAll(Map<? extends Key, ? extends Arquivo> colecao) {


            for(Map.Entry entry: colecao.entrySet()) {
                Key k = (Key) entry.getKey();

                for (Arquivo a : colecao.values()) {
                    this.put(k,a);
                }
            }

    }

    @Override
    public void clear() {
        try {
            Statement stm = Conexao.getConexao().createStatement();
            stm.executeUpdate("DELETE FROM ArquivosMudados");
        }
        catch (Exception e) {throw new NullPointerException(e.getMessage());}
    }
    @Override
    public Set<Key> keySet() {

        try {
            Statement stm = Conexao.getConexao().createStatement();
            ResultSet rs = stm.executeQuery("SELECT " + this.colunas.get(0) + "','" + this.colunas.get(1) + " FROM ArquivosMudados;");

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
    public Collection<Arquivo> values() {
        try {
            Collection colecao = new HashSet<Arquivo>();
            Key k = null;
            Statement stm = Conexao.getConexao().createStatement();
            ResultSet rs = stm.executeQuery("SELECT * FROM ArquivosMudados");
            while (rs.next()) {
                k.setId(Integer.valueOf(rs.getString(1)));
                k.setEmail(rs.getString(2));

                colecao.add(this.get(k));
            }
            return colecao;
        }
        catch (Exception e) {throw new NullPointerException(e.getMessage());}
    }
    @Override
    public Set<Entry<Key, Arquivo>> entrySet() {
        try {
            Set<Map.Entry<Key, Arquivo>> setArquivo = new HashSet<>();
            Statement stm = Conexao.getConexao().createStatement();
            ResultSet rs = stm.executeQuery("SELECT * FROM ArquivosMudados;");

            Key k = null;

            while (rs.next()) {

               k.setId(Integer.valueOf(rs.getString(1)));
               k.setEmail(rs.getString(2));


                setArquivo.add(new AbstractMap.SimpleEntry(k,this.get(k)));
            }

            return setArquivo;
        }
        catch (SQLException e) {throw new NullPointerException(e.getMessage());}
    }

}