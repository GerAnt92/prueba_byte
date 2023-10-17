package com.example.loginscreen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText username;
    EditText password;
    Button loginButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        loginButton = (Button) findViewById(R.id.loginButton);



        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*if(username.getText().toString().equals("Labnos") && password.getText().toString().equals("1234")){
                    Toast.makeText(MainActivity.this,"Login Succesful!", Toast.LENGTH_SHORT).show();
                    openConsumir_Api();
                } else {
                    Toast.makeText(MainActivity.this,"LoginFailed", Toast.LENGTH_SHORT).show();
                }*/

                String name = username.getText().toString();
                String pass = password.getText().toString();

                boolean check=validateInfo(name,pass);

                if (check==true){
                    // Muestra mensaje login exitoso
                    Toast.makeText(getApplicationContext(),"Datos válidos",Toast.LENGTH_SHORT).show();
                    // Redirige segundo activity
                    openConsumir_Api();
                    // Envía usuario a segundo activity
                    Intent intent = new Intent(MainActivity.this, Consumir_Api.class);
                    intent.putExtra("Usuario",name);
                    startActivity(intent);
                    finish();
                }else{
                    Toast.makeText(getApplicationContext(),"Verifique usuario y contraseña",Toast.LENGTH_SHORT).show();
                }

            }
        });

    }

    private Boolean validateInfo(String name, String pass){
        if(name.length()==0){
            username.requestFocus();
            username.setError("El campo usuario no puede estar vacio");
            return  false;
        }
        else if (!name.matches("[a-zA-Z]+")){
            username.requestFocus();
            username.setError("Ingresar unicamente letras");
            return false;
        }
        else if (name=="Administrador"){
            username.requestFocus();
            username.setError("Usuario incorrecto");
            return false;
        }
        else if (name.length()<8){
            username.requestFocus();
            username.setError("El usuario no puede ser menor a 8 caracteres");
            return  false;
        }
        else if (pass.length()<=5){
            password.requestFocus();
            password.setError("La contraseña no puede ser menor a 6 caracteres");
            return  false;
        }
        else if (!pass.matches("[a-zA-Z]+")){
            password.requestFocus();
            password.setError("La contraseña debe tener al menos una mayúscula");
            return false;
        } else if (pass=="Perezmarro"){
            password.requestFocus();
            password.setError("Contraseña incorrecta");
            return false;
        }
        else{return true;
        }
    }

    public void openConsumir_Api(){
        Intent intent = new Intent(this,Consumir_Api.class);
        startActivity(intent);
    }

}