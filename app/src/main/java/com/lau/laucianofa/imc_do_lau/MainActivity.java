package com.lau.laucianofa.imc_do_lau;

import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    AlertDialog dialog;
    AlertDialog.Builder builder;
    Button calcular;
    EditText peso, altura;
    TextView resultado;
    View popup;

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
                // Definindo elementos de popup
                builder = new AlertDialog.Builder(MainActivity.this);
                popup = getLayoutInflater().inflate(R.layout.result_popup, null);
                resultado = (TextView) popup.findViewById(R.id.resultadoTextView);

                // Checando se usuário digitou peso e altura
                if(peso.getText().toString().isEmpty() || altura.getText().toString().isEmpty()) {
                    resultado.setText("Por favor, insira peso e altura.");
                } else {
                    // Declarando variáveis
                    double imc, ps, al;
                    // ps recebe peso em kg
                    ps = Double.parseDouble(peso.getText().toString());
                    // al recebe altura em m
                    al = Double.parseDouble(altura.getText().toString()) / 100;
                    // imc recebe calculo do imc
                    imc = ps / (al * al);
                    // Exibir resultado
                    resultado.setText("Seu IMC é " + ((double) Math.round(imc * 10)) / 10);
                }
                builder.setView(popup);
                dialog = builder.create();
                dialog.show();
            }
        });
    }
}
