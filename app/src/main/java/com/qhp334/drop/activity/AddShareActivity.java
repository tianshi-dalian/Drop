package com.qhp334.drop.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.qhp334.drop.MainActivity;
import com.qhp334.drop.R;

public class AddShareActivity extends Activity {
    private Button addTextButton;
    private Button cancalButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_share);

        addTextButton = findViewById(R.id.text_addButton);
        cancalButton = findViewById(R.id.cancel_add);

        addTextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AddShareActivity.this,AddTextActivity.class);
                startActivity(intent);

            }
        });

        cancalButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setResult(RESULT_CANCELED);
                finish();
            }
        });
    }
}
