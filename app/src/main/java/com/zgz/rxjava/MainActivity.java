package com.zgz.rxjava;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void createOperator(View view) {
        startActivity(new Intent(this,CreateOperatorActivity.class));
    }

    public void filterOperator(View view) {
        startActivity(new Intent(this,FilterOperatorActivity.class));
    }

    public void transformOperator(View view) {
        startActivity(new Intent(this,TransformOperatorActivity.class));
    }
}