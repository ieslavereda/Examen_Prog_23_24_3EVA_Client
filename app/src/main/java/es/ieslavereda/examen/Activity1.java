package es.ieslavereda.examen;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContract;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityOptionsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import es.ieslavereda.examen.model.Fruta;
import es.ieslavereda.examen.model.MyRecyclerViewAdapter;

public class Activity1 extends AppCompatActivity implements View.OnClickListener {

    private ImageButton ibRight;
    private ImageButton ibLeft;
    private ImageView ivFruta;
    private TextView tvPrecio;

    private Spinner sCantidad;

    private Button btnDetail;
    private Button btnAddToCart;

    private RecyclerView recycler;
    private MyRecyclerViewAdapter recyclerViewAdapter;


    private Fruta fruta;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_1);

        // Ejercicio 1

        ibRight = findViewById(R.id.ibRight);
        ibLeft = findViewById(R.id.ibLeft);
        ivFruta = findViewById(R.id.ivFruta);
        tvPrecio = findViewById(R.id.tvValue);

        fruta = Fruta.FRESA;
        actualizarFruta(fruta);

        ibRight.setOnClickListener(v->{
            fruta=fruta.next();
            actualizarFruta(fruta);
        });

        ibLeft.setOnClickListener(v->{
            fruta=fruta.previous();
            actualizarFruta(fruta);
        });

        // Ejercicio 2
        sCantidad = findViewById(R.id.sCantidad);
        sCantidad.setAdapter(new ArrayAdapter<Integer>(this, android.R.layout.simple_spinner_item,new Integer[]{1,5,10,15,20}));

        // Ejercicio 3
        btnDetail = findViewById(R.id.btnActivity);

        ActivityResultLauncher<Intent> activityResultLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if(result.getResultCode()==RESULT_OK){
                        Intent data = result.getData();
                        Fruta f = (Fruta) data.getExtras().get("fruta");
                        int cantidad = data.getExtras().getInt("cantidad");
                        recyclerViewAdapter.insertValue(f,cantidad);
                    }
                });

        btnDetail.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(),Activity2.class);
            intent.putExtra("fruta",fruta);
            intent.putExtra("cantidad",(Integer)sCantidad.getSelectedItem());
            activityResultLauncher.launch(intent);
        });


        // Ejercicio 4

        btnAddToCart = findViewById(R.id.btnAdd);

        recycler = findViewById(R.id.recycler);

        recyclerViewAdapter = new MyRecyclerViewAdapter(this);
        recyclerViewAdapter.setListener(this);
        recycler.setAdapter(recyclerViewAdapter);
        LinearLayoutManager linearLayout = new LinearLayoutManager(this);
        recycler.setLayoutManager(linearLayout);



        btnAddToCart.setOnClickListener(view -> {
            recyclerViewAdapter.insertValue(fruta,(Integer)sCantidad.getSelectedItem());
        });
    }

    private void actualizarFruta(Fruta fruta) {
        ivFruta.setImageResource(fruta.getImageResource());
        tvPrecio.setText(fruta.getPrecio()+" €");
    }

    @Override
    public void onClick(View view) {
        int pos = recycler.getChildAdapterPosition(view);
        recyclerViewAdapter.remove(pos);
    }
}