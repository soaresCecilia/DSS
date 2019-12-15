package MediaCenter.Dados;

import MediaCenter.LogicaDeNegocio.Funcionalidades.Categoria;
import MediaCenter.LogicaDeNegocio.Funcionalidades.Musica;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;





public class CategoriaMusicaDAO implements Map<Integer, Categoria> {
    private static CategoriaMusicaDAO inst = null;
    private final List<String> colunas = Arrays.asList("id", "email", "categoria");
    //tabela : CategoriaMusica

    private CategoriaMusicaDAO () {
    }

    //singleton
    public static CategoriaMusicaDAO getInstance() {
        if (inst == null) {
            inst = new CategoriaMusicaDAO();
        }
        return inst;
    }


    @Override
    public int size() {
        try {
            int tam = 0;
            Statement stm = Conexao.getConexao().createStatement();
            ResultSet rs = stm.executeQuery("SELECT id FROM CategoriaMusica");
            for (tam = 0;rs.next();tam++);
            return tam;
        }
        catch (Exception e) {throw new NullPointerException(e.getMessage());}

    }

    @Override
    public boolean isEmpty() {
        try {
            Statement stm = Conexao.getConexao().createStatement();
            ResultSet rs = stm.executeQuery("SELECT id FROM CategoriaMusica");
            return !rs.next();
        }
        catch (Exception e) {throw new NullPointerException(e.getMessage());}
    }

    @Override
    public boolean containsKey(Object key) {
        try {
            Statement stm = Conexao.getConexao().createStatement();
            String query = "SELECT nome FROM CategoriaMusica WHERE id ='" + (Integer)key + "'";
            ResultSet rs = stm.executeQuery(query);
            return rs.next();
        }
        catch (Exception e) {throw new NullPointerException(e.getMessage());}
    }

    @Override
    public boolean containsValue(Object value) {
        if(!(value instanceof Categoria))
            return false;

        try {
            List<Object> categoriaLinha = ((Categoria) value).categoriaToLinha((Categoria) value);

            Statement stm = Conexao.getConexao().createStatement();
            StringBuilder query = new StringBuilder("SELECT * FROM CategoriaMusica WHERE ");


            //percorre todas as colunas da tabela, excepto a última
            for(int i = 0; i < (this.colunas.size()-1) ; i++){
                query.append(this.colunas.get(i) + " = '" + categoriaLinha.get(i) + "' and ");
            }

            //valor da ultima coluna para pôr;
            query.append(this.colunas.get(this.colunas.size()-1) + " = '" + categoriaLinha.get(this.colunas.size()-1) + "' ; ");

            ResultSet rs = stm.executeQuery(query.toString());

            return rs.next();
        }
        catch (SQLException e) {
            throw new NullPointerException(e.getMessage());
        }
    }

    @Override
    public Categoria get(Object key) {
        try {
            Categoria c = null;
            Statement stm = Conexao.getConexao().createStatement();
            String sql = "SELECT * FROM CategoriaMusica WHERE id='"+(Integer)key+"'";
            ResultSet rs = stm.executeQuery(sql);
            if (rs.next())
                //adicionar parametros conforme o contrutor da categoria
                c = new Categoria(rs.getString(2), rs.getString(1));
            return c;
        }
        catch (Exception e) {throw new NullPointerException(e.getMessage());}
    }

    @Override
    public Categoria put(Integer key, Categoria value) {
        try {
            Categoria c = null;
            Statement stm = Conexao.getConexao().createStatement();
            stm.executeUpdate("DELETE FROM CategoriaMusica WHERE id='"+key+"'");
            String query = "INSERT INTO CategoriaMusica(id, email, categoria;) " +
                    "VALUES ('" + key + "','"+ value.getEmail() + "','" + value.getCategoriaArquivo() + "')";
            stm.executeUpdate(query);
            return new Categoria(value.getCategoriaArquivo(), value.getEmail());
        }
        catch (Exception e) {throw new NullPointerException(e.getMessage());}
    }

    @Override
    public Categoria remove(Object key) {

        try {
            Categoria c = this.get(key);
            Statement stm = Conexao.getConexao().createStatement();
            String query = "DELETE FROM CategoriaMusica where id =" + this.colunas.get(0) +  "' ;";
            stm.executeUpdate(query);


            return c;
        }
        catch (Exception e) {throw new NullPointerException(e.getMessage());}
    }

    @Override
    public void putAll(Map<? extends Integer, ? extends Categoria> colecao) {
        for(Map.Entry entrada: colecao.entrySet()) {
            Integer key = (Integer)entrada.getKey();
            for (Categoria c : colecao.values()) {
                this.put(key, c);
            }
        }
    }

    @Override
    public void clear() {

        try {
            Statement stm = Conexao.getConexao().createStatement();
            stm.executeUpdate("DELETE FROM CategoriaMusica");
        }
        catch (Exception e) {throw new NullPointerException(e.getMessage());}

    }

    @Override
    public Set<Integer> keySet() {
        try {
            Statement stm = Conexao.getConexao().createStatement();
            ResultSet rs = stm.executeQuery("SELECT " + this.colunas.get(0) + " FROM CategoriaMusica;");

            Set<Integer> setChaves = new HashSet<>();
            while (rs.next()) {
                setChaves.add(Integer.valueOf(rs.getString(1)));
            }

            return setChaves;
        }
        catch (SQLException e) {throw new NullPointerException(e.getMessage());
        }
    }

    @Override
    public Collection<Categoria> values() {
        try {
            Collection colecao = new HashSet<Categoria>();
            Statement stm = Conexao.getConexao().createStatement();
            ResultSet rs = stm.executeQuery("SELECT * FROM CategoriaMusica");
            while (rs.next()) {
                colecao.add(new Categoria(rs.getString(2),rs.getString(3)));
            }
            return colecao;
        }
        catch (Exception e) {throw new NullPointerException(e.getMessage());}

    }

    @Override
    public Set<Entry<Integer, Categoria>> entrySet() {
        try {
            Set<Map.Entry<Integer, Categoria>> setCategoriaMusica = new HashSet<>();
            Statement stm = Conexao.getConexao().createStatement();
            ResultSet rs = stm.executeQuery("SELECT * FROM CategoriaMusica;");

            List<String> linha;

            while (rs.next()) {

                linha = new ArrayList<>();

                for( int i = 1; i <= this.colunas.size(); i++)
                    linha.add(rs.getString(i));


                //acrescentar os parametros do construtor categoria
                Categoria c = new Categoria(linha.get(2), linha.get(1));

                setCategoriaMusica.add(new AbstractMap.SimpleEntry(linha.get(0),c));
            }

            return setCategoriaMusica;
        }
        catch (SQLException e) {throw new NullPointerException(e.getMessage());}
    }
}