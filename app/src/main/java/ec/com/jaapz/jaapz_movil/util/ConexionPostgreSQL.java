package ec.com.jaapz.jaapz_movil.util;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;

public class ConexionPostgreSQL extends _Default implements Runnable{
    private Connection conn;
    private String host = "192.168.101.17";
    private String base = "bd_jaapz";
    private int port = 5432;
    private String user = "postgres";
    private String pass = "alexisr20";
    private String url = "jdbc:postgresql://%s:%d/%s";

    public ConexionPostgreSQL(){
        super();
        this.url = String.format(this.url,this.host,this.port,this.base);
    }

    @Override
    public void run() {
        try{
            Class.forName("org.postgresql.Driver");
            this.conn = DriverManager.getConnection(this.url,this.user,this.pass);
        }catch (Exception ex){
            this._mensaje = ex.getMessage();
            this._estado = false;
        }
    }
    private void conectar(){
        Thread thread = new Thread(this);
        thread.start();
        try{
            thread.join();
        }catch (Exception ex){
            System.out.println("Hola: " + ex.getMessage());
            this._mensaje = ex.getMessage();
            this._estado = false;
        }
    }
    private void desconectar(){
        if(this.conn != null){
            try{
                this.conn.close();
            }catch(Exception ex){
                this._mensaje = ex.getMessage();
                this._estado = false;
            }finally{
                this.conn = null;
            }
        }
    }
    public ResultSet executeQuery(String query){
        this.conectar();
        ResultSet resultSet = null;
        try{
            resultSet = new ExecutePostgreSQL(this.conn,query).execute().get();
        }catch (Exception ex){
            this._mensaje = ex.getMessage();
            this._estado = false;
        }
        return  resultSet;
    }

    public Connection getConn() {
        return conn;
    }

    public void setConn(Connection conn) {
        this.conn = conn;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getBase() {
        return base;
    }

    public void setBase(String base) {
        this.base = base;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
