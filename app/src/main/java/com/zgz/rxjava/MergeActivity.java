package com.zgz.rxjava;
import android.os.Bundle;
import android.view.View;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.zgz.rxjava.util.LogUtil;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.SingleSource;
import io.reactivex.rxjava3.functions.BiFunction;
import io.reactivex.rxjava3.functions.Consumer;
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
     * concat:顺序执行被观察者 把多个被观察者合并后一个最后发送
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

    /**
     * zip:多个观察者合并后发送,合并的数量要保持一致
     */
    public void onZipClickListener(View view) {
        StringBuffer sb = new StringBuffer();
        Observable observable1 =  Observable.just("姓名","年龄");
        Observable observable2 =  Observable.just("周桂枝","18");
        Observable.zip(observable1, observable2, (BiFunction<String, String, String>) (s, s2) -> {
            sb.append(s).append(s2);
            return sb.toString();
        }).subscribe((Consumer<String>) s -> LogUtil.e("accept:>>>>>>"+s));
    }
}
