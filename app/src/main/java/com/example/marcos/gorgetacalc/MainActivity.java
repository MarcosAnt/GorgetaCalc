package com.example.marcos.gorgetacalc;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private double valorTotalAtual;
    private int percentGorgeta;
    private EditText edtDica10;
    private EditText edtDica15;
    private EditText edtDica20;
    private EditText edtTotal10;
    private EditText edtTotal15;
    private EditText edtTotal20;
    private TextView tvSkGorgeta;
    private EditText edtValor;
    private EditText edtTotalDica;
    private EditText edtValorTotal;

    private final TextWatcher edtValorTotalWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int start, int before, int count) {
            try{
                valorTotalAtual = Double.parseDouble(charSequence.toString());
            }
            catch(NumberFormatException e){
                valorTotalAtual = 0.0;
            }
            updateCustom();
            updateStandart();
        }

        @Override
        public void afterTextChanged(Editable editable) {

        }
    };

    private SeekBar.OnSeekBarChangeListener skGorgetaListenner = new SeekBar.OnSeekBarChangeListener() {
        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            percentGorgeta = progress;
            updateCustom();
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {

        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SeekBar skGorgeta;

        edtDica10     = (EditText)findViewById(R.id.edtDica10);
        edtDica15     = (EditText)findViewById(R.id.edtDica15);
        edtDica20     = (EditText)findViewById(R.id.edtDica20);
        edtTotal10    = (EditText)findViewById(R.id.edtTotal10);
        edtTotal15    = (EditText)findViewById(R.id.edtTotal15);
        edtTotal20    = (EditText)findViewById(R.id.edtTotal20);
        tvSkGorgeta   = (TextView)findViewById(R.id.tvSkGorgeta);
        edtTotalDica  = (EditText)findViewById(R.id.edtTotalDica);
        edtValorTotal = (EditText)findViewById(R.id.edtValorTotal);
        edtValor      = (EditText)findViewById(R.id.edtValor);
        skGorgeta     = (SeekBar)findViewById(R.id.skGorgeta);

        valorTotalAtual = skGorgeta.getProgress();

        //Evento de mudanlçao de texto no campo
        edtValorTotal.addTextChangedListener(edtValorTotalWatcher);

        //Evento de mudança do SeekBar
        skGorgeta.setOnSeekBarChangeListener(skGorgetaListenner);
    }

    //Atualiza os EditText das porcentagens do campo "Dica"
    private void updateStandart() {
        double dica10  = (double) valorTotalAtual * .10;
        double total10 = (double) valorTotalAtual + dica10;
        double dica15  = (double) valorTotalAtual * .15;
        double total15 = (double) valorTotalAtual + dica15;
        double dica20  = (double) valorTotalAtual * .20;
        double total20 = (double) valorTotalAtual + dica20;

        edtDica10.setText(String.format("%.02f", dica10));
        edtTotal10.setText(String.format("%.02f", total10));
        edtDica15.setText(String.format("%.02f", dica15));
        edtTotal15.setText(String.format("%.02f", total15));
        edtDica20.setText(String.format("%.02f", dica20));
        edtTotal20.setText(String.format("%.02f", total20));

        return;
    }

    //Atualiza os EditText das porcentagem inserida
    private void updateCustom() {
        tvSkGorgeta.setText(String.valueOf(percentGorgeta) + "%");

        double valorGorgeta = (double) valorTotalAtual * (percentGorgeta * .01);
        double valorTotal = (double) valorTotalAtual + valorGorgeta;

        edtTotalDica.setText(String.format("%.02f", valorGorgeta).toString());
        edtValorTotal.setText(String.format("%.02f", valorTotal).toString());

        return;
    }

}
