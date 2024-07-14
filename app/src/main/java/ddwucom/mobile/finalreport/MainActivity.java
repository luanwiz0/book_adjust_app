package ddwucom.mobile.finalreport;

// 과제명: 도서 관리 앱
// 분반: 01분반
// 학번: 20200962 성명: 김효민
// 제출일: 2022년 6월 24일

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    final int ADD_CODE = 100;
    final int UPDATE_CODE = 200;

    ListView listView;
    BookAdapter adapter;
    ArrayList<Book> bookList = null;
    BookDBManager bookDBManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.listView);
        bookList = new ArrayList<Book>();
        bookDBManager = new BookDBManager(this);

        adapter = new BookAdapter(this, R.layout.custom_adapter_view, bookList);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Book book = bookList.get(i);
                Intent intent = new Intent(MainActivity.this, UpdateActivity.class);
                intent.putExtra("book", book);
                startActivityForResult(intent, UPDATE_CODE);
            }
        });

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                int pos = i;
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("책 정보 삭제")
                        .setMessage(bookList.get(pos).getTitle() + "을 삭제하시겠습니까?")
                        .setPositiveButton("확인", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                int result;
                                if(bookDBManager.removeBook(bookList.get(pos).get_id())){
                                    result = R.string.delete_ok;
                                    bookList.clear();
                                    bookList.addAll(bookDBManager.getAllBook());
                                    adapter.notifyDataSetChanged();
                                } else{
                                    result = R.string.delete_failed;
                                }
                                Toast.makeText(MainActivity.this, result, Toast.LENGTH_SHORT).show();
                            }
                        })
                        .setNegativeButton("취소", null)
                        .setCancelable(false)
                        .show();

                return true;
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Intent intent = null;
        switch (item.getItemId()){
            case R.id.searchBookMenu:
                intent = new Intent(this, SearchActivity.class);
                startActivity(intent);
                break;
            case R.id.addMenu:
                intent = new Intent(this, AddActivity.class);
                startActivityForResult(intent, ADD_CODE);
                break;
            case R.id.devIntroMenu:
                intent = new Intent(this, DevIntroActivity.class);
                startActivity(intent);
                break;
            case R.id.appFinishMenu:
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("앱 종료")
                        .setMessage("앱을 종료하시겠습니까?")
                        .setPositiveButton("종료", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                finish();
                            }
                        })
                        .setNegativeButton("취소", null)
                        .setCancelable(false)
                        .show();
                break;
        }
        return true;
    }

    @Override
    protected void onResume() {
        super.onResume();
        bookList.clear();
        bookList.addAll(bookDBManager.getAllBook());
        adapter.notifyDataSetChanged();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == ADD_CODE){
            switch (resultCode){
                case RESULT_OK:
                    String title = data.getStringExtra("title");
                    Toast.makeText(this, title + " 추가 완료", Toast.LENGTH_SHORT).show();
                    break;
                case RESULT_CANCELED:
                    Toast.makeText(this, "책 정보 추가 취소", Toast.LENGTH_SHORT).show();
                    break;
            }
        }
        else if(requestCode == UPDATE_CODE){
            switch (resultCode){
                case RESULT_OK:
                    String title = data.getStringExtra("title");
                    Toast.makeText(this, title + " 수정 완료", Toast.LENGTH_SHORT).show();
                    break;
                case RESULT_CANCELED:
                    Toast.makeText(this, "책 정보 수정 취소", Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    }
}