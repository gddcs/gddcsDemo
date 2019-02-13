package com.example.gddcs.gddcsdemo;

import org.junit.Test;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.subjects.PublishSubject;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void test(){
        PublishSubject<String> stringPublishSubject = PublishSubject.create();
        stringPublishSubject.subscribe(new Observer<String>() {

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {
                System.out.println("Observable completed");
            }

            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(String s) {
                System.out.println("Observer consumed " + s);
            }
        });
        stringPublishSubject.onNext("hello world");
        stringPublishSubject.onComplete();
    }
}