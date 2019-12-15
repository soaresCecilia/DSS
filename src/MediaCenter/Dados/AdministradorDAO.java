package MediaCenter.Dados;

import MediaCenter.LogicaDeNegocio.Usuarios.Administrador;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

public class AdministradorDAO implements Map<String, Administrador> {
    private static AdministradorDAO inst = null;
    private final List<String> colunas = Arrays.asList("email","nome","password");


    private AdministradorDAO () {
    }

    //singleton
    public static AdministradorDAO getInstance() {
        if (inst == null) {
            inst = new AdministradorDAO();
        }
        return inst;
    }
    public void clear () {
        try {
            Statement stm = Conexao.getConexao().createStatement();
            stm.executeUpdate("DELETE FROM Administrador");
        }
        catch (Exception e) {throw new NullPointerException(e.getMessage());}
    }

    public boolean containsKey(Object key) throws NullPointerException {
        try {
            Statement stm = Conexao.getConexao().createStatement();
            String sql = "SELECT nome FROM Administrador WHERE email ='" + (String)key + "'";
            ResultSet rs = stm.executeQuery(sql);
            return rs.next();
        }
        catch (Exception e) {throw new NullPointerException(e.getMessage());}
    }

    public boolean containsValue(Object value) {
        if(!(value instanceof Administrador))
            return false;

        try {
            List<String> administradorLinha = ((Administrador) value).administardorToLinha((Administrador)value);

            Statement stm = Conexao.getConexao().createStatement();
            StringBuilder query = new StringBuilder("SELECT * FROM Administrador WHERE ");



            //percorre todas as linhas da tabela, excepto a última
            for(int i = 0; i < (this.colunas.size()-1) ; i++){
                query.append(this.colunas.get(i) + " = '" + administradorLinha.get(i) + "' and ");
            }

            //ultima linha para pôr;
            query.append(this.colunas.get(this.colunas.size()-1) + " = '" + administradorLinha.get(this.colunas.size()-1) + "' ; ");

            ResultSet rs = stm.executeQuery(query.toString());

            return rs.next();
        }
        catch (SQLException e) {
            throw new NullPointerException(e.getMessage());
        }
    }

    public Set<Map.Entry<String,Administrador>> entrySet() {

        try {
            Set<Entry<String, Administrador>> setAdministrador = new HashSet<>();
            Statement stm = Conexao.getConexao().createStatement();
            ResultSet rs = stm.executeQuery("SELECT * FROM Administrador;");

            List<String> linha;

            while (rs.next()) {

                linha = new ArrayList<>();

                for( int i = 1; i <= this.colunas.size(); i++)
                    linha.add(rs.getString(i));


                Administrador admin = new Administrador(linha.get(0), linha.get(1), linha.get(2));
                setAdministrador.add(new AbstractMap.SimpleEntry(admin.chave(),admin));
            }

            return setAdministrador;
        }
        catch (SQLException e) {throw new NullPointerException(e.getMessage());}
    }

    public boolean equals(Object o) {
        return o.getClass().equals( this.getClass());
    }

    public Administrador get(Object key) {
        try {
            Administrador admin = null;
            Statement stm = Conexao.getConexao().createStatement();
            String query = "SELECT * FROM Administrador WHERE email ='"+(String)key+"'";
            ResultSet rs = stm.executeQuery(query);
            if (rs.next())
                admin = new Administrador(rs.getString(1),rs.getString(2),rs.getString(3));
            return admin;
        }
        catch (Exception e) {throw new NullPointerException(e.getMessage());}
    }



    //transacoes
    public Administrador put(String key, Administrador value) {
        try {
            Administrador admin = null;
            Statement stm = Conexao.getConexao().createStatement();
            stm.executeUpdate("DELETE FROM Administrador WHERE email='"+key+"'");
            String query = "INSERT INTO Administrador(nome, email, password) VALUES ('" + value.getNome()+"','" + key + "','" + value.getPassword()+"')";
            stm.executeUpdate(query);
            return new Administrador(value.getEmail(),value.getNome(),value.getPassword());
        }
        catch (Exception e) {throw new NullPointerException(e.getMessage());}
    }



    public int hashCode() {
        return this.inst.hashCode();
    }

    public boolean isEmpty() {
        try {
            Statement stm = Conexao.getConexao().createStatement();
            ResultSet rs = stm.executeQuery("SELECT nome FROM Administrador");
            return !rs.next();
        }
        catch (Exception e) {throw new NullPointerException(e.getMessage());}
    }

    public Set<String> keySet() {
        try {
            Statement stm = Conexao.getConexao().createStatement();
            ResultSet rs = stm.executeQuery("SELECT " + this.colunas.get(1) + " FROM Administrador;");

            Set<String> setChaves = new HashSet<>();
            while (rs.next()) {
                setChaves.add(rs.getString(1));
            }

            return setChaves;
        }
        catch (SQLException e) {throw new NullPointerException(e.getMessage());
        }

    }

    //transacoes
    public Administrador update(String key, Object value) throws SQLException {

            if (!(value instanceof Administrador))
                return null;

            Administrador admin = (Administrador) value;
            List<String> linha = admin.administardorToLinha(admin);


            Connection con = Conexao.getConexao();

            try {
            //Once the auto-commit mode is set to false , you can commit or rollback the transaction.
            con.setAutoCommit(false);

            Statement stm = con.createStatement();

            StringBuilder query = new StringBuilder("UPDATE Administrador SET ");

            //até à penultima coluna
            for(int i = 0; i < (this.colunas.size()-1) ; i++){
                query.append(this.colunas.get(i) + " = '" + linha.get(i) + "' , ");
            }


            query.append(this.colunas.get(this.colunas.size()-1) + " = '" +
                    linha.get(this.colunas.size()-1) + "' WHERE " +
                    this.colunas.get(0) + " = '" + key + "';");


            stm.executeUpdate(query.toString());

            con.commit();

            return admin;
        }
        catch (SQLException e) {
                //Se correr mal faz rollback
            con.rollback();
            throw new NullPointerException(e.getMessage());
        }

    }

    public Administrador remove(Object key) {

            Administrador admin = this.get(key);
            Connection con = Conexao.getConexao();
        try {
            con.setAutoCommit(false);
            Statement stm = con.createStatement();
            String query = "DELETE FROM Administrador where " + this.colunas.get(0) +  " = '" + key + "' ;";
            stm.executeUpdate(query);
            return admin;
        }
        catch (Exception e) {
            try {
                con.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            throw new NullPointerException(e.getMessage());}
    }

    public void putAll(Map<? extends String, ? extends Administrador> admins) {
        for (Administrador admin : admins.values()) {
            this.put(admin.chave(), admin);
        }
    }


    public Map<String, Administrador> getTodos() {
        Map<String, Administrador> mapAdministradores = new HashMap<>();
        Collection<Administrador> colecao = values();

        colecao.forEach(admin -> mapAdministradores.put(admin.chave(), admin));

        return mapAdministradores;
    }



    public int size() {
        try {
            int tam = 0;
            Statement stm = Conexao.getConexao().createStatement();
            ResultSet rs = stm.executeQuery("SELECT email FROM Administrador");
            for (tam = 0;rs.next();tam++);
            return tam;
        }
        catch (Exception e) {throw new NullPointerException(e.getMessage());}
    }




    //transacoes
    public Collection<Administrador> values() {
        try {
            Collection colecao = new HashSet<Administrador>();
            Statement stm = Conexao.getConexao().createStatement();
            ResultSet rs = stm.executeQuery("SELECT * FROM Administrador");
            while (rs.next()) {
                colecao.add(new Administrador(rs.getString(1),rs.getString(2),rs.getString(3)));
            }
            return colecao;
        }
        catch (Exception e) {throw new NullPointerException(e.getMessage());}
    }

}