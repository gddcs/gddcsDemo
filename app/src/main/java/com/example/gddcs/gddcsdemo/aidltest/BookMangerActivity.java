package com.example.gddcs.gddcsdemo.aidltest;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

import com.example.gddcs.gddcsdemo.R;
import com.example.gddcs.gddcsdemo.view.activity.AppBaseActivity;

import java.util.Arrays;
import java.util.List;

/**
* @author GDDCS
* create at 2019/4/1 15:12
* description: aidl 客户端
*/

public class BookMangerActivity extends AppBaseActivity {
    private ServiceConnection mConnection;
    private IBookManager bookManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_manger);

        // 创建ServiceConnection对象
        mConnection = new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {
                // 成功连接回调
                try {
                    bookManager = IBookManager.Stub.asInterface(service);

                    // 获取service中得图书数据
                    List<Book> list = bookManager.getBookList();
                    Log.e(getTAG(), "onServiceConnected--------"+ list.toString());

                    // 将客户端数据传给服务端
                    Book mBook = new Book(3,"Android开发艺术探索");
                    bookManager.addBook(mBook);
                    Log.e(getTAG(), "添加了-Android开发艺术探索-------");
                    List<Book> newlist = bookManager.getBookList();
                    Log.e(getTAG(), "查询所有图书："+newlist.toString());
                    bookManager.registerListener(mIOnNewBookArrivedListener);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onServiceDisconnected(ComponentName name) {
                // 未连接回调
                Log.e(getTAG(), "onServiceDisconnected--------");
            }
        };

        Intent intent = new Intent(this, BookMangerService.class);
        // 绑定service
        bindService(intent, mConnection, Context.BIND_AUTO_CREATE);
    }

    private IOnNewBookArrivedListener mIOnNewBookArrivedListener = new IOnNewBookArrivedListener.Stub() {
        @Override
        public void onNewBookArrived(Book book) throws RemoteException {
            Log.e(getTAG(),"onNewBookArrived:"+book.getBookName());
        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (null != bookManager && bookManager.asBinder().isBinderAlive()){
            Log.e(getTAG(),"unregisterListener listener:"+mIOnNewBookArrivedListener);
            try {
                bookManager.unregisterListener(mIOnNewBookArrivedListener);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
        if (null != mConnection){
            unbindService(mConnection);
        }
    }
}
