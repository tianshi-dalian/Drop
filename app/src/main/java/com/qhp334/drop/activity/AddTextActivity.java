package com.qhp334.drop.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.qhp334.drop.R;

public class AddTextActivity extends Activity {
    private Button cancaleButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_text);

        cancaleButton = findViewById(R.id.cancel_addtext);
        cancaleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setResult(RESULT_CANCELED);
                finish();
            }
        });
    }
}
