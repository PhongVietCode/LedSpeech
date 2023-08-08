package com.example.mylibrary;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;

public class Utils {
    final private String TAG = "Utils";
    public String KEY_NAME = "name";
    public String KEY_AUTHOR = "author";
    private static ArrayList<MyBook> books;
    private static Utils instance;
    FirebaseFirestore db = FirebaseFirestore.getInstance();

    public static synchronized Utils getInstance() {
        if(instance == null){
            instance = new Utils();
        }
        return instance;
    }
    private Utils(){
        if(books == null)
        {
            books = new ArrayList<>();
            initData();
        }
    }

    private void initData() {
            //todo: init data
            books.add(new MyBook(4,"Jk Rail","KingdasdD",321,"https://cdn.pixabay.com/photo/2015/04/23/22/00/tree-736885_1280.jpg","Long time age try this for free"));
            books.add(new MyBook(5,"Hackot","KingasdqD",842,"https://previews.123rf.com/images/crazymedia/crazymedia1601/crazymedia160100006/51392867-victory-at-the-peak-of-happiness.jpg","Long time age try this for free"));
            books.add(new MyBook(6,"Alaos locase","KingbvorD",371,"https://previews.123rf.com/images/wrangel/wrangel1605/wrangel160500063/56913283-harbor-seal-phoca-vitulina-also-known-as-the-common-seal-wild-life-animal.jpg","Long time age try this for free"));
            books.add(new MyBook(7,"Trick me better solo iff you can't","KingoqkuceD",1231,"https://previews.123rf.com/images/wrangel/wrangel1609/wrangel160900335/63666586-harbor-seal-phoca-vitulina-also-known-as-the-common-seal-wildlife-animal.jpg","Long time age try this for free"));
            books.add(new MyBook(8,"On Top Queen","KingokuefD",4127,"https://cdn.pixabay.com/photo/2015/04/23/22/00/tree-736885_1280.jpg","Long time age try this for free"));
    }
    public void addBooks(String bookName){

    }
    public ArrayList<MyBook> getBook(){
        MyBook book  = new MyBook(1,"happy life","Phongcute",1029,"url","happy will give u normal life");
        db.collection("Book").document("book1")
                .set(book)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Log.d(TAG, "onSuccess: added a book");
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w(TAG, "Error adding document", e);
                    }
                });;
        return books;

    }
    public MyBook getID(int id){
        for(MyBook book:books){
            if(book.getBookID() == id){
                return book;
            }
        }
        return null;
    }
}
