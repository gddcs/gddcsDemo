// IBookManager.aidl
package com.example.gddcs.gddcsdemo.aidltest;

import com.example.gddcs.gddcsdemo.aidltest.Book;
import com.example.gddcs.gddcsdemo.aidltest.IOnNewBookArrivedListener;


// Declare any non-default types here with import statements

interface IBookManager {
     List<Book> getBookList();
     void addBook(in Book book);
     void registerListener(IOnNewBookArrivedListener listener);
     void unregisterListener(IOnNewBookArrivedListener listener);
}
