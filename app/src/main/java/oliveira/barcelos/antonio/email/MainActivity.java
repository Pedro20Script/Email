package oliveira.barcelos.antonio.email;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnEnviar = (Button) findViewById(R.id.btnEnviar);

        btnEnviar.setOnClickListener(new View.OnClickListener() {
            @Override

            //Ação do botão
            public void onClick(View v) {

                //Armazena o email, o assunto e o texto
                EditText etEmail = (EditText) findViewById(R.id.etEmail);
                String email = etEmail.getText().toString();

                EditText etAssunto = (EditText) findViewById(R.id.etAssunto);
                String assunto = etAssunto.getText().toString();

                EditText etTexto = (EditText) findViewById(R.id.etTexto);
                String texto = etTexto.getText().toString();

                //Intent enviar email
                Intent i = new Intent(Intent.ACTION_SENDTO);

                //Seta a uri pra só email
                i.setData(Uri.parse("mailto:"));

                //Adiciona os itens em uma lista
                String[] emails = new String[] {email};
                i.putExtra(Intent.EXTRA_EMAIL, emails);
                i.putExtra(Intent.EXTRA_SUBJECT, assunto);
                i.putExtra(Intent.EXTRA_TEXT, texto);

                //Tenta encontrar o app de email
                try {
                    startActivity(Intent.createChooser(i, "Escolha o APP"));
                }
                //Caso não encontre
                catch (ActivityNotFoundException e) {
                    Toast.makeText(MainActivity.this, "Não há nenhum app que posso " +
                            "realizar essa operação", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}