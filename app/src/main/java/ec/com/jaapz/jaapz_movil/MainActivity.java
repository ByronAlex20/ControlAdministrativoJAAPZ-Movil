package ec.com.jaapz.jaapz_movil;

import android.content.ContentValues;
import android.content.Intent;
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
import ec.com.jaapz.jaapz_movil.util.ControllerHelper;

public class MainActivity extends AppCompatActivity {
    Button btnLogin;
    Button btnRegistrar;
    TextInputEditText et_usuario;
    ControllerHelper helper = new ControllerHelper();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnLogin = (Button)findViewById(R.id.btn_login);
        et_usuario = (TextInputEditText) findViewById(R.id.et_usuario);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                iniciarSesion();
            }
        });
    }
    public void iniciarSesion(){
        try{
            if (validarDatosInicio() == true){
                Intent intent = new Intent(this,PrincipalActivity.class);
                startActivity(intent);
            }else{
                et_usuario.setError("Usuario o Clave Incorrecto");
            }
        }catch (Exception ex){
        }
    }
    public boolean validarDatosInicio(){
        try{
            boolean bandera = false;
            int cantidad = 0;
            String cadena = "select count(*) as cantidad from seg_usuario where (cedula = '" + et_usuario.getText()
                    + "' or usuario = '" + helper.Encriptar(et_usuario.getText().toString()) + "') and estado = 'A'";
            ConexionPostgreSQL db = new ConexionPostgreSQL();
            ResultSet resultSet = db.executeQuery(cadena);
            if(resultSet != null){
                while (resultSet.next()){
                   cantidad = resultSet.getInt("cantidad");
                }
            }else{
                Toast.makeText(this, db.get_mensaje(), Toast.LENGTH_SHORT).show();
            }
            if (cantidad == 0)
                return false;
            else
                bandera = true;
            return  bandera;
        }catch (Exception ex){
           return false;
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
