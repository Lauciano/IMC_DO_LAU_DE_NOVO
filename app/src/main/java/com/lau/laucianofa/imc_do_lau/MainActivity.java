package com.lau.laucianofa.imc_do_lau;

import android.content.Context;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    AlertDialog alertDialog, nameDialog;
    AlertDialog.Builder alertBuilder, nameBuilder;
    Button calcular, nomear;
    EditText peso, altura, nome;
    String usuario;
    TextView alertView, resultado;
    View alertPopup, namePopup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Definindo elementos de interface
        peso = (EditText) findViewById(R.id.pesoEditText);
        altura = (EditText) findViewById(R.id.alturaEditText);
        calcular = (Button) findViewById(R.id.calcularButton);
        resultado = (TextView) findViewById(R.id.resultadoTextView);

        calcular.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Escondendo teclado virtual
                InputMethodManager inputManager = (InputMethodManager)
                        getSystemService(Context.INPUT_METHOD_SERVICE);
                inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),
                        InputMethodManager.HIDE_NOT_ALWAYS);

                // Checando se usuário digitou peso e altura
                if (peso.getText().toString().isEmpty() || altura.getText().toString().isEmpty()) {
                    // Definindo elementos de alertPopup para peso e altura
                    alertBuilder = new AlertDialog.Builder(MainActivity.this);
                    alertPopup = getLayoutInflater().inflate(R.layout.result_popup, null);
                    alertView = (TextView) alertPopup.findViewById(R.id.resultadoTextView);
                    alertView.setText("Por favor, insira peso e altura.");
                    alertBuilder.setView(alertPopup);
                    alertDialog = alertBuilder.create();
                    alertDialog.show();

                } else {
                    // Cálculo de IMC
                    // Declarando variáveis
                    double imc, ps, al;
                    // ps recebe peso em kg
                    ps = Double.parseDouble(peso.getText().toString());
                    // al recebe altura em m
                    al = Double.parseDouble(altura.getText().toString()) / 100;
                    // imc recebe calculo do IMC
                    imc = ps / (al * al);

                    // Pedindo o nome do usuário
                    usuario = "usuário";
                    nameBuilder = new AlertDialog.Builder(MainActivity.this);
                    namePopup = getLayoutInflater().inflate(R.layout.name_popup, null);
                    nome = (EditText) namePopup.findViewById(R.id.nameEditText);
                    nomear = (Button) namePopup.findViewById(R.id.nameButton);

                    // Implementando função do botão no pedido de nome
                    nomear.setOnClickListener(new View.OnClickListener() {
                        public void onClick(View v) {
                            // Definindo nome de usuário a ser exibido
                            if (!nome.getText().toString().isEmpty())
                                usuario = nome.getText().toString();

                            // Fechando pedido de nome
                            nameDialog.dismiss();
                        }
                    });

                    // Exibindo Pedido de Nome
                    nameBuilder.setView(namePopup);
                    nameDialog = nameBuilder.create();
                    nameDialog.show();


                    // Exibir resultados
                    resultado.setText("Olá, " + usuario + ", seu IMC é "
                            + ((double) Math.round(imc * 10)) / 10);
                }
            }
        });
    }
}
