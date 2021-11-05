package com.snayab.ahooraelevator.helpers;

import android.util.Log;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public abstract class RxJavaCountDownTimer {

//    i think this is not a good idea i can use this class for all viewModels but iam not sure!

    private TimeUnit timeUnit;
    private Long startValue;
    private Disposable disposable;

    public RxJavaCountDownTimer(Long startValue, TimeUnit timeUnit) {
        this.timeUnit = timeUnit;
        this.startValue = startValue;
    }

    public abstract void onTick(long tickValue);

    public abstract void onFinish();

    public void start() {
        Observable.zip(
                Observable.range(1, startValue.intValue()), Observable.interval(1, timeUnit), (integer, aLong) -> {
                    Long l = startValue - integer;

                    return l;
                }
        ).subscribeOn(Schedulers.single())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Long>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        disposable = d;
                    }

                    @Override
                    public void onNext(Long aLong) {
                        onTick(aLong);
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onComplete() {
                        onFinish();
                    }
                });
    }

    public void cancel() {
        if (disposable != null) disposable.dispose();
    }
}
