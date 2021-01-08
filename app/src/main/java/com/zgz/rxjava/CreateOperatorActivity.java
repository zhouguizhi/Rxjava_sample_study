package com.zgz.rxjava;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.zgz.rxjava.util.LogUtil;
import java.util.concurrent.TimeUnit;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableOnSubscribe;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;

public class CreateOperatorActivity extends AppCompatActivity {
    private TextView tvCreateOperator;
    private TextView tvDeferOperator;
    private TextView tvEmptyOperator;
    private TextView tvErrorOperator;
    private TextView tvFromOperator;
    private TextView tvJustOperator;
    private TextView tvTimerOperator;
    private TextView tvRangeOperator;
    private TextView tvNeverOperator;
    private TextView tvIntervalOperator;
    private TextView tvGenerateOperator;
    private Disposable disposable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_operator);
        initView();
        initListener();
    }

    private void initListener() {
        tvIntervalOperator.setOnClickListener(view -> testIntervalOperator());
        tvCreateOperator.setOnClickListener(view -> testCreateOperator());
        tvDeferOperator.setOnClickListener(view -> testDeferOperator());
        tvEmptyOperator.setOnClickListener(view -> testEmptyOperator());
        tvErrorOperator.setOnClickListener(view -> testErrorOperator());
        tvFromOperator.setOnClickListener(view -> testFromOperator());
        tvJustOperator.setOnClickListener(view -> testJustOperator());
        tvTimerOperator.setOnClickListener(view -> testTimerOperator());
        tvRangeOperator.setOnClickListener(view -> testRangeOperator());
        tvNeverOperator.setOnClickListener(view -> testNeverOperator());
        tvGenerateOperator.setOnClickListener(view -> testGenerateOperator());
    }

    /**
     * generate
     */
    public void testGenerateOperator(){

    }
    /**
     * 返回一个每隔指定的时间间隔就发射一个序列号的 Observable 对象，可用来做倒计时等操作
     * interval Observable.interval(5000, TimeUnit.MILLISECONDS) 每隔几秒
     * 这个可以用在倒计时 比如登录获取验证码等场景
     */
    public void testIntervalOperator(){
        //这是倒计时功能
        Observable.intervalRange(1,5,1000,5000,TimeUnit.MILLISECONDS).subscribe(new Observer<Long>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {
                LogUtil.e("interval:onSubscribe");
            }

            @Override
            public void onNext(@NonNull Long aLong) {
                LogUtil.e("interval:onNext:="+aLong);
            }

            @Override
            public void onError(@NonNull Throwable e) {
                LogUtil.e("interval:onError");
            }

            @Override
            public void onComplete() {
                LogUtil.e("interval:onComplete");
            }
        });
//        Observable.interval(1000,1000,TimeUnit.MILLISECONDS).subscribe(new Observer<Long>() {
//            @Override
//            public void onSubscribe(@NonNull Disposable d) {
//                LogUtil.e("interval:onSubscribe");
//            }
//
//            @Override
//            public void onNext(@NonNull Long aLong) {
//                LogUtil.e("interval:onNext:="+aLong);
//            }
//
//            @Override
//            public void onError(@NonNull Throwable e) {
//                LogUtil.e("interval:onError");
//            }
//
//            @Override
//            public void onComplete() {
//                LogUtil.e("interval:onComplete");
//            }
//        });
//        Observable.interval(1000, TimeUnit.MILLISECONDS).subscribe(new Observer<Long>() {
//            @Override
//            public void onSubscribe(@NonNull Disposable d) {
//                LogUtil.e("interval:onSubscribe");
//            }
//
//            @Override
//            public void onNext(@NonNull Long aLong) {
//                LogUtil.e("interval:onNext"+aLong);
//            }
//
//            @Override
//            public void onError(@NonNull Throwable e) {
//                LogUtil.e("interval:onError");
//            }
//
//            @Override
//            public void onComplete() {
//                LogUtil.e("interval:onComplete");
//            }
//        });
    }
    /**
     * never 创建一个不发射任何数据和通知的 Observable 对象,观察者接受到后什么方法都不会调用
     */
    public void testNeverOperator(){
        Observable.never().subscribe(new Observer<Object>() {

            @Override
            public void onSubscribe(@NonNull Disposable d) {
                LogUtil.e("never:onSubscribe");
            }

            @Override
            public void onNext(@NonNull Object o) {
                LogUtil.e("never:onNext:="+o);
            }

            @Override
            public void onError(@NonNull Throwable e) {
                LogUtil.e("never:onError");
            }

            @Override
            public void onComplete() {
                LogUtil.e("never:onComplete");
            }
        });
    }
    /**
     * range 创建一个发射指定范围内的连续整数的 Observable 对象
     */
    public void testRangeOperator(){
        Observable.range(100,7).subscribe(new Observer<Integer>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {
                LogUtil.e("range:onSubscribe");
            }

            @Override
            public void onNext(@NonNull Integer integer) {
                LogUtil.e("range:onNext:="+integer);
            }

            @Override
            public void onError(@NonNull Throwable e) {
                LogUtil.e("range:onError");
            }
            @Override
            public void onComplete() {
                LogUtil.e("range:onComplete");
            }
        });
    }
    /**
     * timer 是创建延迟发送的Observable
     */
    public void testTimerOperator(){
        Observable.timer(1000,TimeUnit.MILLISECONDS).subscribe(new Observer<Long>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {
                LogUtil.e("timer:onSubscribe：");
            }

            @Override
            public void onNext(@NonNull Long aLong) {
                LogUtil.e("timer:onNext："+aLong);
            }

            @Override
            public void onError(@NonNull Throwable e) {
                LogUtil.e("timer:onError：");
            }

            @Override
            public void onComplete() {
                LogUtil.e("timer:onComplete：");
            }
        });
    }
    /**
     * just 它是内部发射数据源 just()接受的是一个可变参数,最多9个
     */
    public void testJustOperator(){
        Observable.just(1,2,3).subscribe(new Observer<Integer>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {
                LogUtil.e("just:onSubscribe");
            }
            @Override
            public void onNext(@NonNull Integer integer) {
                LogUtil.e("just:onNext："+integer);
                try {
                    Thread.sleep(4000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onError(@NonNull Throwable e) {
                LogUtil.e("just:onError");
            }
            @Override
            public void onComplete() {
                LogUtil.e("just:onComplete");
            }
        });
    }
    /**
     * from 操作符
     */
    private void testFromOperator() {

    }
    /**
     *
     */
    public void testErrorOperator(){

    }
    /**
     *empty 创建一个不发射任何数据的Observable
     * 只会执行onSubscribe() 和onComplete()方法
     */
    public void testEmptyOperator(){
        Observable<String> observable = Observable.empty();
        observable.subscribe(new Observer<String>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {
                LogUtil.e("onSubscribe");
            }
            @Override
            public void onNext(@NonNull String s) {
                LogUtil.e("onNext");
            }

            @Override
            public void onError(@NonNull Throwable e) {
                LogUtil.e("onError");
            }

            @Override
            public void onComplete() {
                LogUtil.e("onComplete");
            }
        });
    }
    /**
     * defer 创建型操作符 只有订阅时才去创建新的(Observable.just(time))被观察者Observable
     * 然后发送数据到下游(观察者)
     */
    public void testDeferOperator(){
        Observable<Long> observable = Observable.defer(() -> {
            long time = System.currentTimeMillis();
            System.out.println("zhouguizhi--time:"+time);
            return Observable.just(time);
        });

        observable.subscribe(time -> System.out.println("zhouguizhi前:"+time));

        try {
            Thread.sleep(1000);
            System.out.println("zhouguizhi休息1秒后:");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        observable.subscribe(time -> System.out.println("zhouguizhi后:"+time));
    }
    /**
     * create创建型操作符
     */
    private void testCreateOperator() {
        //起点
        Observable.create((ObservableOnSubscribe<String>) emitter -> {
            emitter.onNext("发送请求");
            emitter.onError(new Throwable("自定义异常"));
            emitter.onComplete();
        })//终点
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        LogUtil.e("onSubscribe");
                    }
                    @Override
                    public void onNext(String o) {
                        LogUtil.e("onNext"+o);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        LogUtil.e("onError:"+e.getMessage());
                    }

                    @Override
                    public void onComplete() {
                        LogUtil.e("onComplete");
                    }
                });
    }

    private void initView() {
        tvGenerateOperator = findViewById(R.id.activity_create_operator_tv_generate_operator);
        tvIntervalOperator = findViewById(R.id.activity_create_operator_tv_interval_operator);
        tvNeverOperator = findViewById(R.id.activity_create_operator_tv_never_operator);
        tvRangeOperator = findViewById(R.id.activity_create_operator_tv_range_operator);
        tvTimerOperator = findViewById(R.id.activity_create_operator_tv_timer_operator);
        tvJustOperator = findViewById(R.id.activity_create_operator_tv_just_operator);
        tvFromOperator = findViewById(R.id.activity_create_operator_tv_from_operator);
        tvErrorOperator = findViewById(R.id.activity_create_operator_tv_error_operator);
        tvCreateOperator = findViewById(R.id.activity_create_operator_tv_create_operator);
        tvDeferOperator = findViewById(R.id.activity_create_operator_tv_defer_operator);
        tvEmptyOperator = findViewById(R.id.activity_create_operator_tv_empty_operator);
    }

    /**
     * 切断下游,让下游不再接受上游事件
     */
    public void onCutSubscribeClickListener(View view) {
        Observable.create((ObservableOnSubscribe<Integer>) emitter -> {
            for(int i=0;i<10;i++){
                emitter.onNext(i);
            }
            emitter.onComplete();
        }).subscribe(new Observer<Integer>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {
                disposable = d;
                LogUtil.e("onSubscribe......");
            }
            @Override
            public void onNext(@NonNull Integer integer) {
                LogUtil.e("onNext......"+integer);
                //模拟只接受一个事件后终止
                if( null!=disposable){
                    disposable.dispose();
                }
            }
            @Override
            public void onError(@NonNull Throwable e) {
                LogUtil.e("onError......");
            }
            @Override
            public void onComplete() {
                LogUtil.e("onComplete......");
            }
        });
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        //一般在activity销毁的时候使用该方法,比如一个耗时的任务,任务完成后再取更新ui,如果调用了dispose
        //方法就不会取更新ui
        if(null!=disposable){
            disposable.dispose();
        }
    }
}