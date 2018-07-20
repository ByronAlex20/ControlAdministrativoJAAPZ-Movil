package ec.com.jaapz.jaapz_movil;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.accessibility.AccessibilityManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import java.sql.ResultSet;

import ec.com.jaapz.jaapz_movil.util.ConexionPostgreSQL;
import ec.com.jaapz.jaapz_movil.util.ConexionSQLite;

public class MainActivity extends AppCompatActivity {
    Button btnLogin;
    Button btnRegistrar;
    TextInputEditText et_usuario;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnLogin = (Button)findViewById(R.id.btn_login);
        et_usuario = (TextInputEditText) findViewById(R.id.et_usuario);
    }
    public void ejecutarPrueba(){
        try{
            String dato = "";
            ConexionPostgreSQL db = new ConexionPostgreSQL();
            ResultSet resultSet = db.executeQuery("select id_usuario,nombres from seg_usuario");
            if(resultSet != null){
                while (resultSet.next()){
                    dato = resultSet.getString("id_usuario");
                    dato = dato + resultSet.getString("nombres");
                }
            }else{
                Toast.makeText(this, "No tiene datos", Toast.LENGTH_SHORT).show();
                Toast.makeText(this, db.get_mensaje(), Toast.LENGTH_SHORT).show();
            }
            et_usuario.setText(dato);
        }catch (Exception ex){
            Toast.makeText(this, ex.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    public void registrarSQLite(){
        ConexionSQLite cnn = new ConexionSQLite( this,"base_jaapz",null,1);
        SQLiteDatabase baseDatos = cnn.getWritableDatabase();//abrela base de datos en modo lectura-escritura

        //para realizar registros
        ContentValues registro = new ContentValues();
        registro.put("nombre","Administrador");
        registro.put("descripcion","Tiene Acceso al Sistema");
        registro.put("estado","A");
        Long idResultante = baseDatos.insert("seg_perfil",null,registro);
        Toast.makeText(this, "Registrado" + idResultante, Toast.LENGTH_SHORT).show();
        baseDatos.close();
    }

    public void buscarSQLite(){
        ConexionSQLite cnn = new ConexionSQLite(this,"base_jaapz",null,1);
        SQLiteDatabase baseDatos = cnn.getReadableDatabase();
        String datos = "";
        try {
            Cursor fila = baseDatos.rawQuery("SELECT * FROM SEG_PERFIL",null);
            while(fila.moveToNext()){
                datos = fila.getString(2);
            }

            et_usuario.setText(datos);
            baseDatos.close();
        }catch(Exception e){
            Toast.makeText(getApplicationContext(),e.getMessage(),Toast.LENGTH_LONG).show();
        }

    }
}
