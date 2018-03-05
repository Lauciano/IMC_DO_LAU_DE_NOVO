package com.lau.laucianofa.imc_do_lau;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    EditText peso, altura;
    TextView resultado;
    Button calcular;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        peso = (EditText) findViewById(R.id.pesoEditText);
        altura = (EditText) findViewById(R.id.alturaEditText);
        resultado = (TextView) findViewById(R.id.resultadoTextView);
        calcular = (Button) findViewById(R.id.calcularButton);

        calcular.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Code here executes on main thread after user presses button
                double imc, ps, al;
                // ps recebe peso em kg
                ps = Double.parseDouble(peso.getText().toString());
                // al recebe altura em m
                al = Double.parseDouble(altura.getText().toString()) / 100;
                // imc recebe calculo do imc
                imc = ps / (al*al);
                resultado.setText("Seu IMC é " + ((double) Math.round(imc*10))/10);
            }
        });
    }
}
