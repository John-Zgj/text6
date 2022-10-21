package cn.edu.jnu.text6;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class InputShopItemActivity extends AppCompatActivity {

    public static final int RESULT_CODE_SUCCESS = 666;
    private int position;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_shop_item);

        position = this.getIntent().getIntExtra("position",0);
        String title = this.getIntent().getStringExtra("title");
        Double price = this.getIntent().getDoubleExtra("price",0);

        EditText editTextTitle = findViewById(R.id.edit_Text_Shop_Item_Title);
        EditText editTextPrice = findViewById(R.id.edit_Text_Shop_Item_Price);

        if(null != title){
            editTextTitle.setText(title);
            editTextPrice.setText(price.toString());
        }


        Button buttonOk = this.findViewById(R.id.button_ok);
        buttonOk.setOnClickListener(view -> {
            Intent intent = new Intent();
            Bundle bundle = new Bundle();
            bundle.putString("title",editTextTitle.getText().toString());
            bundle.putDouble("price",Double.parseDouble(editTextPrice.getText().toString()));
            bundle.putInt("position",position);

            intent.putExtras(bundle);
            setResult(RESULT_CODE_SUCCESS,intent);
            InputShopItemActivity.this.finish();
        });

    }
}