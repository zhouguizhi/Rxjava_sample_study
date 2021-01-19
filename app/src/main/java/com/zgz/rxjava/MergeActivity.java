package com.zgz.rxjava;
import android.os.Bundle;
import android.view.View;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.zgz.rxjava.util.LogUtil;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.SingleSource;
/**
 * @Description: 合并操作符
 * @Author: zhouguizhi
 * @CreateDate: 2021/1/18 下午6:59
 * @Version: 1.0
 */
public class MergeActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_merge_operator);
    }
    /**
     * startWith:二个Observable合并后发送,并且调用startWith的先发送
     * @param view
     */
    public void onStartWithClickListener(View view) {
        Observable<String> names = Observable.just("Spock", "McCoy");
        names.startWith((SingleSource<String>) observer -> observer.onSuccess("zhouguizhi")).subscribe(s -> LogUtil.e("accept:>>>>>>"+s));
    }

    /**
     * mergeWith:合并后放在其后执行
     */
    public void onMergeClickListener(View view) {
        Observable.just(1, 2, 3)
                .mergeWith(Observable.just(4, 5, 6))
                .subscribe(integer -> LogUtil.e("accept:>>>>>>"+integer));
    }

    /**
     * concatWith:是合并后把concatWith创建的Observable的被观察者后执行
     * 和startWith是相反的,
     */
    public void onConcatWithClickListener(View view) {
        Observable<String> firstNames = Observable.just("1", "2", "3");
        firstNames.concatWith(Observable.create(emitter -> {
            emitter.onNext("4");
            emitter.onNext("5");
            emitter.onNext("6");
        })).subscribe(s -> LogUtil.e("accept:>>>>>>"+s));
    }
    /**
     * concat:顺序执行被观察者
     */
    public void onConcatClickListener(View view) {
        Observable.concat(Observable.just("1"),
                Observable.just("2"),
                Observable.just("3"),
                Observable.create(emitter -> {
                    emitter.onNext("4");
                    emitter.onComplete();
                })).subscribe(s -> LogUtil.e("accept:>>>>>>"+s));
    }
}
