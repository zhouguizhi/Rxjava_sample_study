package com.zgz.rxjava;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

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
    public void conditionOperator(View view) {
        startActivity(new Intent(this,ConditionActivity.class));
    }

    public void mergeOperator(View view) {
        startActivity(new Intent(this,MergeActivity.class));
    }
    /**
     * 线程切换
     * @param view
     */
    public void threadOperator(View view) {
        startActivity(new Intent(this,ThreadActivity.class));
    }

    public void flowableOperator(View view) {
        startActivity(new Intent(this,FlowableActivity.class));
    }
}