package com.jetbrains;

//import business.*;
import java.util.*;
import java.sql.*;
import com.jetbrains.Utilizador;

public class UtilizadorDAO implements Map<String, Utilizador> {
    private static UtilizadorDAO inst = null;
    private final List<String> colunas = Arrays.asList("email","nome","password");


    private UtilizadorDAO () {
        }

        //singleton
        public static UtilizadorDAO getInstance() {
            if (inst == null) {
                inst = new UtilizadorDAO();
            }
            return inst;
        }

        public void clear () {
            try {
                Statement stm = Conexao.getConexao().createStatement();
                stm.executeUpdate("DELETE FROM Utilizador");
            }
            catch (Exception e) {throw new NullPointerException(e.getMessage());}
        }

        public boolean containsKey(Object key) throws NullPointerException {
            try {
                Statement stm = Conexao.getConexao().createStatement();
                String sql = "SELECT nome FROM Utilizador WHERE email='" + (String)key + "'";
                ResultSet rs = stm.executeQuery(sql);
                return rs.next();
            }
            catch (Exception e) {throw new NullPointerException(e.getMessage());}
        }

        public boolean containsValue(Object value) {
            if(!(value instanceof Utilizador))
                return false;

            try {
                List<String> utilizadorLinha = ((Utilizador) value).utilizadorToLinha((Utilizador)value);

                Statement stm = Conexao.getConexao().createStatement();
                StringBuilder sql = new StringBuilder("SELECT * FROM Utilizador WHERE ");



                //percorre todas as linhas da tabela, excepto a última
                for(int i = 0; i < (this.colunas.size()-1) ; i++){
                    sql.append(this.colunas.get(i) + " = '" + utilizadorLinha.get(i) + "' and ");
                }

                //ultima linha para pôr;
                sql.append(this.colunas.get(this.colunas.size()-1) + " = '" + utilizadorLinha.get(this.colunas.size()-1) + "' ; ");

                ResultSet rs = stm.executeQuery(sql.toString());

                return rs.next();
            }
            catch (SQLException e) {
                throw new NullPointerException(e.getMessage());
            }
        }

        public Set<Map.Entry<String,Utilizador>> entrySet() {

            try {
                Set<Entry<String, Utilizador>> setUtilizador = new HashSet<>();
                Statement stm = Conexao.getConexao().createStatement();
                ResultSet rs = stm.executeQuery("SELECT * FROM Utilizador;");

                List<String> linha;

                while (rs.next()) {

                    linha = new ArrayList<>();

                    for( int i = 1; i <= this.colunas.size(); i++)
                        linha.add(rs.getString(i));


                     Utilizador u = new Utilizador(linha.get(0), linha.get(1), linha.get(2));
                    setUtilizador.add(new AbstractMap.SimpleEntry(u.chave(),u));
                }

                return setUtilizador;
            }
            catch (SQLException e) {throw new NullPointerException(e.getMessage());}
        }

        public boolean equals(Object o) {
            return o.getClass().equals( this.getClass());
        }

        public Utilizador get(Object key) {
            try {
                Utilizador user = null;
                Statement stm = Conexao.getConexao().createStatement();
                String sql = "SELECT * FROM Utilizador WHERE email='"+(String)key+"'";
                ResultSet rs = stm.executeQuery(sql);
                if (rs.next())
                    user = new Utilizador(rs.getString(1),rs.getString(2),rs.getString(3));
                return user;
            }
            catch (Exception e) {throw new NullPointerException(e.getMessage());}
        }



        //transacoes
         public Utilizador put(String key, Utilizador value) {
            try {
                Utilizador user = null;
                Statement stm = Conexao.getConexao().createStatement();
                stm.executeUpdate("DELETE FROM Utilizador WHERE email='"+key+"'");
                String sql = "INSERT INTO Utilizador(nome, email, password) VALUES ('" + value.getNome()+"','" + key + "','" + value.getPassword()+"')";
                stm.executeUpdate(sql);
            return new Utilizador(value.getEmail(),value.getNome(),value.getPassword());
            }
             catch (Exception e) {throw new NullPointerException(e.getMessage());}
         }



        public int hashCode() {
            return this.inst.hashCode();
        }

        public boolean isEmpty() {
            try {
                Statement stm = Conexao.getConexao().createStatement();
                ResultSet rs = stm.executeQuery("SELECT nome FROM Utilizador");
                return !rs.next();
            }
            catch (Exception e) {throw new NullPointerException(e.getMessage());}
        }

        public Set<String> keySet() {
            try {
                Statement stm = Conexao.getConexao().createStatement();
                ResultSet rs = stm.executeQuery("SELECT " + this.colunas.get(1) + " FROM Utilizador;");

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
        public Utilizador update(String key, Object value){
            try {

                if (!(value instanceof Utilizador))
                    return null;

                Utilizador u = (Utilizador) value;
                List<String> linha = u.utilizadorToLinha(u);


                Statement stm = Conexao.getConexao().createStatement();
                StringBuilder query = new StringBuilder("UPDATE Utilizador SET ");

                //até à penultima coluna
                for(int i = 0; i < (this.colunas.size()-1) ; i++){
                    query.append(this.colunas.get(i) + " = '" + linha.get(i) + "' , ");
                }


                query.append(this.colunas.get(this.colunas.size()-1) + " = '" +
                    linha.get(this.colunas.size()-1) + "' WHERE " +
                    this.colunas.get(0) + " = '" + key + "';");


                stm.executeUpdate(query.toString());

                return u;
            }
            catch (SQLException e) {
                throw new NullPointerException(e.getMessage());
             }

        }

        public Utilizador remove(Object key) {
            try {
                Utilizador user = this.get(key);
                Statement stm = Conexao.getConexao().createStatement();
                String sql = "DELETE FROM Utilizador where " + this.colunas.get(0) +  " = '" + key + "' ;";
                stm.executeUpdate(sql);
                return user;
            }
            catch (Exception e) {throw new NullPointerException(e.getMessage());}
        }

        public void putAll(Map<? extends String, ? extends Utilizador> ut) {
            for (Utilizador u : ut.values()) {
                this.put(u.chave(), u);
            }
        }


        public Map<String, Utilizador> getAll() {
            Map<String, Utilizador> mapUtilizadores = new HashMap<>();
            Collection<Utilizador> colecao = values();

            colecao.forEach(utilizador -> mapUtilizadores.put(utilizador.chave(), utilizador));

            return mapUtilizadores;
        }



        public int size() {
            try {
                int tam = 0;
                Statement stm = Conexao.getConexao().createStatement();
                ResultSet rs = stm.executeQuery("SELECT email FROM Utilizador");
                for (tam = 0;rs.next();tam++);
                return tam;
            }
            catch (Exception e) {throw new NullPointerException(e.getMessage());}
        }




        //transacoes
        public Collection<Utilizador> values() {
            try {
                Collection colecao = new HashSet<Utilizador>();
                Statement stm = Conexao.getConexao().createStatement();
                ResultSet rs = stm.executeQuery("SELECT * FROM Utilizador");
                while (rs.next()) {
                    colecao.add(new Utilizador(rs.getString(1),rs.getString(2),rs.getString(3)));
                }
                return colecao;
            }
            catch (Exception e) {throw new NullPointerException(e.getMessage());}
        }

    }
