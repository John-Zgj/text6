package cn.edu.jnu.text6;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import cn.edu.jnu.text6.R;

public class MainActivity extends AppCompatActivity {

    private ArrayList<String> mainStringSet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerViewMain = findViewById(R.id.recycleview_main);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerViewMain.setLayoutManager(linearLayoutManager);

        mainStringSet = new ArrayList<String>();
        for(int i = 1 ; i < 100 ; i++)
        {
            mainStringSet.add("item" + i);
        }
        MainRecycleViewMain mainRecycleViewMain = new MainRecycleViewMain(mainStringSet);
        recyclerViewMain.setAdapter(mainRecycleViewMain);
    }

    public class MainRecycleViewMain extends RecyclerView.Adapter<MainRecycleViewMain.ViewHolder>{

        private ArrayList<String> localDataSet;

        public class ViewHolder extends RecyclerView.ViewHolder{
            private final TextView textView;

            public ViewHolder(@NonNull View itemView) {
                super(itemView);

                textView = (TextView) itemView.findViewById(R.id.textview_item_caption);
            }

            public TextView getTextView(){
                return textView;
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
        }

        @Override
        public int getItemCount() {
            return localDataSet.size();
        }
    }
}