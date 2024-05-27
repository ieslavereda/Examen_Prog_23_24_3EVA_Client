package es.ieslavereda.examen.model;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import es.ieslavereda.examen.Activity1;
import es.ieslavereda.examen.R;

public class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewAdapter.MyViewHolder> {


    private List<Linea> values;
    private LayoutInflater inflater;
    private Context context;
    private View.OnClickListener listener;

    public MyRecyclerViewAdapter(@NonNull Context context) {
        this.context = context;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        values = new ArrayList<>();
    }

    public void insertValue(Fruta fruta,int cantidad){
        values.add(new Linea(fruta,cantidad));
        notifyItemInserted(values.size());
    }
    public void remove(int pos) {
        values.remove(pos);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MyRecyclerViewAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.simple_element,parent,false);
        view.setOnClickListener(listener);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyRecyclerViewAdapter.MyViewHolder holder, int position) {

        Linea linea = values.get(position);

        holder.ivFruta.setImageResource(linea.getFruta().getImageResource());
        holder.tvNombre.setText(linea.getFruta().getNombre());
        holder.tvValue.setText(linea.getFruta().getPrecio()*linea.getCantidad()+"");

        holder.updateBackground(context.getResources().getColor((position%2==0)?R.color.recycler_even:R.color.recycler_odd, context.getTheme()));
    }

    @Override
    public int getItemCount() {
        return values.size();
    }

    public void setListener(View.OnClickListener listener) {
        this.listener=listener;
    }



    public class Linea {

        private Fruta fruta;
        private int cantidad;

        public Linea(Fruta fruta, int cantidad) {

            this.fruta = fruta;
            this.cantidad = cantidad;

        }

        public Fruta getFruta() {
            return fruta;
        }

        public int getCantidad() {
            return cantidad;
        }

    }


    public class MyViewHolder extends RecyclerView.ViewHolder{

        ImageView ivFruta;
        TextView tvNombre;
        TextView tvValue;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            ivFruta = itemView.findViewById(R.id.ivFrutaSE);
            tvNombre = itemView.findViewById(R.id.tvNombre);
            tvValue = itemView.findViewById(R.id.tvValue);
        }
        public void updateBackground(int color){
            itemView.setBackgroundColor(color);
        }
    }
}
