package com.alphazer0.testes;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;

public class PesquisaEditText extends AppCompatActivity {
    //ARRAY LISTA PRINCIPAL
    private String[] cidades;

    //ELEMENTO LIST VIEW
    private ListView listView;

    //CAMPO DE PESQUISA
    private EditText filtro;

    //NOVO ARRAY QUE RETORNA O ITEM PESQUISADO
    private ArrayList<String> filtrar =new ArrayList<>();
    //==================================================================================================
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pesquisa_edittext);

        bindDosElementos();

        insereValoresNaLista();

        configuraAdapter();

        filtrar();

        configuraAdapterListaPesquisa();
    }
    //==================================================================================================
    private void configuraAdapter() {
        //CONFIGURANDO O ADAPTER E COM A LISTA PRINCIPAL
        listView.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,cidades));
    }
    //==================================================================================================
    private void insereValoresNaLista() {
        //SETANDO VALORES AO ARRAY
        cidades = new String[]{"Rio Verde","Brazilia","Cairo","Bahia"};
    }
    //==================================================================================================
    private void configuraAdapterListaPesquisa() {
        filtro.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                filtrar();
                listView.setAdapter(new ArrayAdapter<>(PesquisaEditText.this, android.R.layout.simple_list_item_1, filtrar));
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }
    //==================================================================================================
    private void bindDosElementos() {
        listView = findViewById(R.id.listView);
        filtro = findViewById(R.id.campoPesquisa);
    }
    //==================================================================================================
    public void filtrar(){
        String filtrado;
        filtrar.clear();

        for(int i = 0;i< cidades.length;i++){
            //subSequence pesquisa em strings
            filtrado = (String) cidades[i].subSequence(0, filtro.getText().length());
            if(filtro.getText().length() <= cidades[i].length()) {

                if (filtro.getText().toString().equalsIgnoreCase((String) cidades[i].subSequence(0, filtro.getText().length()))) {
                    filtrar.add(cidades[i]);
                }else{
                    Log.e("Filtro",i+" "+filtrado);
                }
            }
        }
    }
//==================================================================================================
}
