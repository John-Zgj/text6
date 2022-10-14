package cn.edu.jnu.text6;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import cn.edu.jnu.text6.data.ShopItem;

public class MainActivity extends AppCompatActivity {

    public static final int MENU_ID_ADD = 1;
    public static final int MENU_ID_UPDATE = 2;
    public static final int MENU_ID_DELETE = 3;
    private ArrayList<ShopItem> shopItems;
    private MainRecycleViewAdapter mainRecycleViewAdapter;

    private ActivityResultLauncher<Intent> addDataLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult()
            ,result -> {
        if(result != null)
        {
            Intent intent = result.getData();
            if(result.getResultCode() == InputShopItemActivity.RESULT_CODE_SUCCESS)
            {
                Bundle bundle = intent.getExtras();
                String title = bundle.getString("title");
                double price = bundle.getDouble("price");
                shopItems.add(1,new ShopItem("title", price , R.drawable.xjzr));
                mainRecycleViewAdapter.notifyItemInserted(1);
            }
            //shopItems.add(item.getOrder(),new ShopItem("added" + item.getOrder() , Math.random() *10 , R.drawable.xjzr));
            //shopItems.add(1,new ShopItem("added", Math.random() *10 , R.drawable.xjzr));
        }
            });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerViewMain = findViewById(R.id.recycleview_main);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerViewMain.setLayoutManager(linearLayoutManager);

        shopItems = new ArrayList<>();
        for(int i = 0 ; i < 20 ; i++)
        {
            shopItems.add(new ShopItem("item" + i , Math.random() *10 ,
                    i % 2 == 1?R.drawable.zr : R.drawable.westbrook));
        }
        mainRecycleViewAdapter = new MainRecycleViewAdapter(shopItems);
        recyclerViewMain.setAdapter(mainRecycleViewAdapter);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId())
        {
            case MENU_ID_ADD :
                //Toast.makeText(this,"item add" + item.getOrder() + " clicked" , Toast.LENGTH_LONG)
                //       .show();
                Intent intent = new Intent(this, InputShopItemActivity.class);
                addDataLauncher.launch(intent);

                break;
            case MENU_ID_UPDATE:
                //Toast.makeText(this,"item update" + item.getOrder() + "clicked" , Toast.LENGTH_LONG)
                //        .show();
                shopItems.get(item.getOrder()).setTitle("updated 6");
                mainRecycleViewAdapter.notifyItemChanged(item.getOrder());
                break;
            case MENU_ID_DELETE:
                //Toast.makeText(this,"item delete" + item.getOrder() + "clicked" , Toast.LENGTH_LONG)
                //        .show();
                AlertDialog alertDialog = new AlertDialog.Builder(this)
                        .setTitle(R.string.confirmation)
                        .setMessage(R.string.ask)
                        .setNegativeButton(R.string.no, (dialogInterface, i) -> {

                        })
                        .setPositiveButton(R.string.yes, (dialogInterface, i) -> {
                            shopItems.remove(item.getOrder());
                            mainRecycleViewAdapter.notifyItemRemoved(item.getOrder());
                        }).create();
                alertDialog.show();
                break;

        }
        return super.onContextItemSelected(item);
    }

    public class MainRecycleViewAdapter extends RecyclerView.Adapter<MainRecycleViewAdapter.ViewHolder>{

        private ArrayList<ShopItem> localDataSet;

        public class ViewHolder extends RecyclerView.ViewHolder implements View.OnCreateContextMenuListener {
            private final TextView textViewTitle;
            private final TextView textViewPrice;
            private final ImageView imageViewImage;

            public TextView getTextViewPrice() {
                return textViewPrice;
            }


            public ViewHolder(@NonNull View itemView) {
                super(itemView);

                imageViewImage = itemView.findViewById(R.id.imageview_item_image);
                textViewTitle =itemView.findViewById(R.id.textview_item_caption);
                textViewPrice = itemView.findViewById(R.id.textview_item_price);

                itemView.setOnCreateContextMenuListener(this);
            }

            public TextView getTextViewTitle(){
                return textViewTitle;
            }

            public ImageView getImageViewImage() {
                return imageViewImage;
            }

            @Override
            public void onCreateContextMenu(ContextMenu contextMenu, View view, ContextMenu.ContextMenuInfo contextMenuInfo) {
                contextMenu.add(0,MENU_ID_ADD, getAdapterPosition(),"Add" + getAdapterPosition());
                contextMenu.add(0,MENU_ID_UPDATE, getAdapterPosition(),"Update" + getAdapterPosition());
                contextMenu.add(0,MENU_ID_DELETE, getAdapterPosition(),"Delete" + getAdapterPosition());

            }
        }


        public MainRecycleViewAdapter(ArrayList<ShopItem> dataset) {
            localDataSet = dataset;
        }

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            //Create a new view,which defines the UI of th list item;
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_main,parent,false);

            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
            //Get element from your dataset at this position and replace the
            //contents of the view with that element
            holder.getTextViewTitle().setText(localDataSet.get(position).getTitle());
            holder.getTextViewPrice().setText(String.valueOf(localDataSet.get(position).getPrice()));
            holder.getImageViewImage().setImageResource(localDataSet.get(position).getResourceId());
        }

        @Override
        public int getItemCount() {
            return localDataSet.size();
        }
    }
}