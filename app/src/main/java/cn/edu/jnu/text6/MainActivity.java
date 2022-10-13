package cn.edu.jnu.text6;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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

import cn.edu.jnu.text6.R;

public class MainActivity extends AppCompatActivity {

    public static final int MENU_ID_ADD = 1;
    public static final int MENU_ID_UPDATE = 2;
    public static final int MENU_ID_DELETE = 3;
    private ArrayList<String> mainStringSet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerViewMain = findViewById(R.id.recycleview_main);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerViewMain.setLayoutManager(linearLayoutManager);

        mainStringSet = new ArrayList<>();
        for(int i = 0 ; i < 100 ; i++)
        {
            mainStringSet.add("item" + i);
        }
        MainRecycleViewMain mainRecycleViewMain = new MainRecycleViewMain(mainStringSet);
        recyclerViewMain.setAdapter(mainRecycleViewMain);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId())
        {
            case MENU_ID_ADD :
                Toast.makeText(this,"item add" + item.getOrder() + " clicked" , Toast.LENGTH_LONG)
                        .show();
                break;
            case MENU_ID_UPDATE:
                Toast.makeText(this,"item update" + item.getOrder() + "clicked" , Toast.LENGTH_LONG)
                        .show();
                break;
            case MENU_ID_DELETE:
                Toast.makeText(this,"item delete" + item.getOrder() + "clicked" , Toast.LENGTH_LONG)
                        .show();
                break;

        }
        return super.onContextItemSelected(item);
    }

    public class MainRecycleViewMain extends RecyclerView.Adapter<MainRecycleViewMain.ViewHolder>{

        private ArrayList<String> localDataSet;

        public class ViewHolder extends RecyclerView.ViewHolder implements View.OnCreateContextMenuListener {
            private final TextView textView;
            private final ImageView imageViewImage;

            public ViewHolder(@NonNull View itemView) {
                super(itemView);

                imageViewImage = itemView.findViewById(R.id.imageview_item_image);
                textView =itemView.findViewById(R.id.textview_item_caption);

                itemView.setOnCreateContextMenuListener(this);
            }

            public TextView getTextView(){
                return textView;
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


        public MainRecycleViewMain(ArrayList<String> dataset) {
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
        public void onBindViewHolder(@NonNull MainRecycleViewMain.ViewHolder holder, int position) {
            //Get element from your dataset at this position and replace the
            //contents of the view with that element
            holder.getTextView().setText(localDataSet.get(position));
            holder.getImageViewImage().setImageResource(position % 2 == 1 ? R.drawable.zr : R.drawable.westbrook);
        }

        @Override
        public int getItemCount() {
            return localDataSet.size();
        }
    }
}