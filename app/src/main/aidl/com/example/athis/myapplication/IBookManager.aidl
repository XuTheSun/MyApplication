// IBookManager.aidl
package com.example.athis.myapplication;

// Declare any non-default types here with import statements
import com.example.athis.myapplication.Book;
interface IBookManager {
    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */
    void addBook();
    List<Book> showBookList();
}
