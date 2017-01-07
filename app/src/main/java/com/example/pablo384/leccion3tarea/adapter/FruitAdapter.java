package com.example.pablo384.leccion3tarea.adapter;


import android.app.Activity;
import android.graphics.Typeface;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextClock;
import android.widget.TextView;

import com.example.pablo384.leccion3tarea.R;
import com.example.pablo384.leccion3tarea.models.Fruit;
import com.squareup.picasso.Picasso;

import java.util.List;

public class FruitAdapter extends RecyclerView.Adapter<FruitAdapter.ViewHolder> {

    private List<Fruit> fruits;
    private int layout;
    private Activity activity;
    private OnItemClickListener listener;

    public FruitAdapter(List<Fruit> fruits, int layout, Activity activity, OnItemClickListener listener) {
        this.fruits = fruits;
        this.layout = layout;
        this.activity = activity;
        this.listener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(activity).inflate(layout,parent,false);
        ViewHolder vh=new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.bind(fruits.get(position),listener);

    }

    @Override
    public int getItemCount() {return fruits.size(); }

    // Implementamos las interfaces OnCreateContextMenuListener y OnMenuItemClickListener
    // para hacer uso del context menu en RecyclerView, y sobreescribimos los métodos

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnCreateContextMenuListener, MenuItem.OnMenuItemClickListener {

        public TextView textViewName;
        public TextView textViewDescripcion;
        public TextView textViewCantidad;
        public ImageView imageViewBackGround;

        public ViewHolder(View itemView) {
            super(itemView);

            textViewName=(TextView)itemView.findViewById(R.id.textViewName);
            textViewDescripcion=(TextView)itemView.findViewById(R.id.textViewDescription);
            textViewCantidad=(TextView)itemView.findViewById(R.id.textViewQuantity);
            imageViewBackGround=(ImageView)itemView.findViewById(R.id.imageView_fruit);

            // Añadimos al view el listener para el context menu, en vez de hacerlo en
            // el activity mediante el método registerForContextMenu
            itemView.setOnCreateContextMenuListener(this);

        }

        public void bind(final Fruit fruit, final OnItemClickListener listener){
            this.textViewName.setText(fruit.getName());
            this.textViewDescripcion.setText(fruit.getDescripcion());
            this.textViewCantidad.setText(fruit.getCantidad()+"");
            // Lógica aplicada para la limitación de la cantidad en cada elemento fruta
            if (fruit.getCantidad()==Fruit.LIMIT_QUANTITY){
                textViewCantidad.setTextColor(ContextCompat.getColor(activity, R.color.colorAlert));
                textViewCantidad.setTypeface(null, Typeface.BOLD);
            }else {
                textViewCantidad.setTextColor(ContextCompat.getColor(activity, R.color.defaultTextColor));
                textViewCantidad.setTypeface(null, Typeface.NORMAL);

            }
            // Cargamos la imagen con Picasso
            Picasso.with(activity).load(fruit.getImgBackground()).fit().into(this.imageViewBackGround);
            // Añadimos el listener click para cada elemento fruta
            this.imageViewBackGround.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onItemClick(fruit, getAdapterPosition());
                }
            });
        }
        // Sobreescribimos onCreateContextMenu, dentro del ViewHolder,
        // en vez de hacerlo en el activity

        @Override
        public void onCreateContextMenu(ContextMenu contextMenu, View v, ContextMenu.ContextMenuInfo menuInfo) {
            // Recogemos la posición con el método getAdapterPosition
            Fruit fruitSelected = fruits.get(this.getAdapterPosition());
            // Establecemos título e icono para cada elemento, mirando en sus propiedades
            contextMenu.setHeaderTitle(fruitSelected.getName());
            contextMenu.setHeaderIcon(fruitSelected.getImgIcon());
            // Inflamos el menú
            MenuInflater inflater = activity.getMenuInflater();
            inflater.inflate(R.menu.menu_context, contextMenu);
            // Por último, añadimos uno por uno, el listener onMenuItemClick para
            // controlar las acciones en el contextMenu, anteriormente lo manejábamos
            // con el método onContextItemSelected en el activity
            for (int i = 0; i < contextMenu.size(); i++)
                contextMenu.getItem(i).setOnMenuItemClickListener(this);
        }

        // Sobreescribimos onMenuItemClick, dentro del ViewHolder,
        // en vez de hacerlo en el activity bajo el nombre onContextItemSelected
        @Override
        public boolean onMenuItemClick(MenuItem menuItem) {
            // No obtenemos nuestro objeto info
            // porque la posición la podemos rescatar desde getAdapterPosition
            switch (menuItem.getItemId()) {
                case R.id.opciondelete:
                    // Observa que como estamos dentro del adaptador, podemos acceder
                    // a los métodos propios de él como notifyItemRemoved o notifyItemChanged
                    fruits.remove(getAdapterPosition());
                    notifyItemRemoved(getAdapterPosition());
                    return true;
                case R.id.opcioncreset:
                    fruits.get(getAdapterPosition()).resetcantidad();
                    notifyItemChanged(getAdapterPosition());
                    return true;
                default:
                    return false;
            }
        }


    }



    public interface OnItemClickListener {
        void onItemClick(Fruit fruit, int position);
    }
}


