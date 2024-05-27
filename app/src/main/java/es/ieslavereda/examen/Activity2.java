package es.ieslavereda.examen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import es.ieslavereda.examen.model.Fruta;

public class Activity2 extends AppCompatActivity {

    private ImageView ivFruta;
    private TextView tvNombre;
    private TextView tvPrecio;
    private EditText etCantidad;
    private ImageButton ibCart;
    private FloatingActionButton btnBack;

    private Fruta fruta;
    private int cantidad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);

        fruta = (Fruta) getIntent().getExtras().get("fruta");
        cantidad = getIntent().getExtras().getInt("cantidad",1);

        ivFruta= findViewById(R.id.ivFrutaA2);
        tvNombre = findViewById(R.id.tvNombreA2);
        tvPrecio = findViewById(R.id.tvPrecioA2);
        etCantidad = findViewById(R.id.etCantidad);
        ibCart = findViewById(R.id.ibCart);
        btnBack = findViewById(R.id.btnBack);

        ivFruta.setImageResource(fruta.getImageResource());
        tvNombre.setText(fruta.getNombre());
        tvPrecio.setText(fruta.getPrecio()+"");
        etCantidad.setText(cantidad+"");

        ibCart.setOnClickListener(v->{
            if(Integer.parseInt(etCantidad.getText().toString())==0)
                Toast.makeText(getApplicationContext(),"No se puede dejar a 0 la cantidad",Toast.LENGTH_LONG).show();
            else{
                Intent intent = new Intent();
                intent.putExtra("cantidad",Integer.parseInt(etCantidad.getText().toString()));
                intent.putExtra("fruta",fruta);
                setResult(RESULT_OK,intent);
                finish();
            }
        });

        btnBack.setOnClickListener(view -> {
            finish();
        });

    }
}