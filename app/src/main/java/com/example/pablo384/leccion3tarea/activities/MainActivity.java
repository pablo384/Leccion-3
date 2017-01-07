package com.example.pablo384.leccion3tarea.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;

import com.example.pablo384.leccion3tarea.R;
import com.example.pablo384.leccion3tarea.adapter.FruitAdapter;
import com.example.pablo384.leccion3tarea.models.Fruit;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView myrcv;
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView.Adapter adapter;

    private List<Fruit> fruits;
    private int contador=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.fruits=getAllFruits();

        myrcv=(RecyclerView)findViewById(R.id.my_recycler_view);
        layoutManager=new LinearLayoutManager(this);
        adapter=new FruitAdapter(fruits, R.layout.recyclerview_fruit_items, this, new FruitAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Fruit fruit, int position) {
                fruit.addcantidad(1);
                adapter.notifyItemChanged(position);
            }
        });
        myrcv.setHasFixedSize(true);
        myrcv.setLayoutManager(layoutManager);
        myrcv.setAdapter(adapter);
        // No registramos para el menu contexto nada aquí, lo movemos al ViewHolder del adaptador
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_add,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.opcionAdd:
                // Rescatamos el número de frutas para saber en que posición insertaremos
                int position=fruits.size();
                fruits.add(position, new Fruit("Plum "+(++contador),"Fruta agregada por el usuario",R.drawable.plum_bg, R.mipmap.ic_plum,0));
                adapter.notifyItemInserted(position);
                layoutManager.scrollToPosition(position);
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private List<Fruit> getAllFruits(){
        return new ArrayList<Fruit>(){{
            add(new Fruit("Strawberry", "Strawberry description", R.drawable.strawberry_bg, R.mipmap.ic_strawberry, 0));
            add(new Fruit("Orange", "Orange description", R.drawable.orange_bg, R.mipmap.ic_orange, 0));
            add(new Fruit("Apple", "Apple description", R.drawable.apple_bg, R.mipmap.ic_apple, 0));
            add(new Fruit("Banana", "Banana description", R.drawable.banana_bg, R.mipmap.ic_banana, 0));
            add(new Fruit("Cherry", "Cherry description", R.drawable.cherry_bg, R.mipmap.ic_cherry, 0));
            add(new Fruit("Pear", "Pear description", R.drawable.pear_bg, R.mipmap.ic_pear, 0));
            add(new Fruit("Raspberry", "Raspberry description", R.drawable.raspberry_bg, R.mipmap.ic_raspberry, 0));

        }};
    }
}
