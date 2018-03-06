package com.lau.laucianofa.imc_do_lau;

import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    AlertDialog alertDialog, nameDialog, resultDialog;
    AlertDialog.Builder alertBuilder, nameBuilder, resultBuilder;
    Button calcular, nomear;
    EditText peso, altura, nome;
    TextView alertView, resultView;
    View alertPopup, namePopup, resultPopup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Definindo elementos de interface
        peso = (EditText) findViewById(R.id.pesoEditText);
        altura = (EditText) findViewById(R.id.alturaEditText);
        calcular = (Button) findViewById(R.id.calcularButton);

        calcular.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Checando se usuário digitou peso e altura
                if(peso.getText().toString().isEmpty() || altura.getText().toString().isEmpty()) {
                    // Definindo elementos de alertPopup para peso e altura
                    alertBuilder = new AlertDialog.Builder(MainActivity.this);
                    alertPopup = getLayoutInflater().inflate(R.layout.result_popup, null);
                    alertView = (TextView) alertPopup.findViewById(R.id.resultadoTextView);
                    alertView.setText("Por favor, insira peso e altura.");
                    alertBuilder.setView(alertPopup);
                    alertDialog = alertBuilder.create();
                    alertDialog.show();

                } else {
                    // Pedindo o nome do usuário
                    nameBuilder = new AlertDialog.Builder(MainActivity.this);
                    namePopup = getLayoutInflater().inflate(R.layout.name_popup, null);
                    nome = (EditText) namePopup.findViewById(R.id.nameEditText);
                    nomear = (Button) namePopup.findViewById(R.id.nameButton);
                    // Implementando função do botão no pedido de nome
                    nomear.setOnClickListener(new View.OnClickListener() {
                        public void onClick(View v) {

                            // Definindo nome de usuário a ser exibido
                            String usuario = "usuário";
                            if(!nome.getText().toString().isEmpty())
                                usuario = nome.getText().toString();

                            // Cálculo de IMC
                            // Declarando variáveis
                            double imc, ps, al;
                            // ps recebe peso em kg
                            ps = Double.parseDouble(peso.getText().toString());
                            // al recebe altura em m
                            al = Double.parseDouble(altura.getText().toString()) / 100;
                            // imc recebe calculo do IMC
                            imc = ps / (al * al);

                            // Exibir resultados
                            resultBuilder = new AlertDialog.Builder(MainActivity.this);
                            resultPopup = getLayoutInflater().inflate(R.layout.result_popup,
                                    null);
                            resultView = (TextView)
                                    resultPopup.findViewById(R.id.resultadoTextView);
                            resultView.setText("Olá, " + usuario + ", seu IMC é "
                                    + ((double) Math.round(imc * 10)) / 10);
                            resultBuilder.setView(resultPopup);
                            resultDialog = resultBuilder.create();
                            resultDialog.show();

                            // Fechando pedido de nome
                            nameDialog.dismiss();
                        }
                    });
                    // Exibindo Pedido de Nome
                    nameBuilder.setView(namePopup);
                    nameDialog = nameBuilder.create();
                    nameDialog.show();


                }
            }
        });
    }
}
