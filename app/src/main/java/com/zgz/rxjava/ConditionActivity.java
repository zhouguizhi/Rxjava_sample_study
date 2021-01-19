package com.zgz.rxjava;
import android.os.Bundle;
import android.view.View;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.functions.Predicate;
/**
 * @Description: 条件操作符 返回的都是boolean
 * @Author: zhouguizhi
 * @CreateDate: 2021/1/18 下午6:11
 * @Version: 1.0
 */
public class ConditionActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_condition_operator);
    }
    /**
     * all 条件操作符
     * @param view
     */
    public void allOperatorClickListener(View view) {
        Observable.just(1,2,3,4,5,6)
                .all(new Predicate<Integer>() {
                    @Override
                    public boolean test(Integer integer) throws Throwable {
                        return false;
                    }
                }).subscribe(new Consumer<Boolean>() {
            @Override
            public void accept(Boolean aBoolean) throws Throwable {

            }
        });
    }
}
