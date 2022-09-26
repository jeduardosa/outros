package learn.exercicio.bin2dec;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

public class MainActivity extends AppCompatActivity {
    private TextInputEditText textBinario;
    private TextInputEditText textDecimal;
    private Button btnCalcular;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textBinario = findViewById(R.id.textBinario);
        textDecimal = findViewById(R.id.textDecimal);
        btnCalcular = findViewById(R.id.buttonCalcular);

        btnCalcular.setOnClickListener(new View.OnClickListener() {
       /*       Convert
                Binário = 2
                Oct = 8
                Hexadecimal = 16               */
            @Override
            public void onClick(View view) {

                String num = textBinario.getText().toString().trim();
                if (!num.isEmpty()) {

                    int resultado = Integer.parseInt(num, 2);
                    textDecimal.setText("" + resultado);
                }else{
                    Toast.makeText(getApplicationContext(),"Digite apenas 0 e 1", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

}