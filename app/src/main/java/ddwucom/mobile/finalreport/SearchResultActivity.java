package ddwucom.mobile.finalreport;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class SearchResultActivity extends AppCompatActivity {

    final int UPDATE_CODE = 200;

    String title, author;
    ListView listView_result;
    BookAdapter adapter;
    ArrayList<Book> bookList = null;
    BookDBManager bookDBManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_result);

        title = getIntent().getStringExtra("title");
        author = getIntent().getStringExtra("author");

        listView_result = findViewById(R.id.listView_result);
        bookList = new ArrayList<Book>();
        bookDBManager = new BookDBManager(this);

        adapter = new BookAdapter(this, R.layout.custom_adapter_view, bookList);
        listView_result.setAdapter(adapter);

        listView_result.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Book book = bookList.get(i);
                Intent intent = new Intent(SearchResultActivity.this, UpdateActivity.class);
                intent.putExtra("book", book);
                startActivityForResult(intent, UPDATE_CODE);
            }
        });

        listView_result.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                int pos = i;
                AlertDialog.Builder builder = new AlertDialog.Builder(SearchResultActivity.this);
                builder.setTitle("책 정보 삭제")
                        .setMessage(bookList.get(pos).getTitle() + "을 삭제하시겠습니까?")
                        .setPositiveButton("확인", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                int result;
                                if(bookDBManager.removeBook(bookList.get(pos).get_id())){
                                    result = R.string.delete_ok;
                                    bookList.clear();
                                    if(title.length() > 0)
                                        bookList.addAll(bookDBManager.getBookByName(title));
                                    else
                                        bookList.addAll(bookDBManager.getBookByAuthor(author));
                                    adapter.notifyDataSetChanged();
                                } else{
                                    result = R.string.delete_failed;
                                }
                                Toast.makeText(SearchResultActivity.this, result, Toast.LENGTH_SHORT).show();
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
    protected void onResume() {
        super.onResume();
        bookList.clear();
        if(title != null)
            bookList.addAll(bookDBManager.getBookByName(title));
        else
            bookList.addAll(bookDBManager.getBookByAuthor(author));
        adapter.notifyDataSetChanged();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == UPDATE_CODE){
            switch (resultCode){
                case RESULT_OK:
                    String title = data.getStringExtra("title");
                    Toast.makeText(this, title + "수정 완료", Toast.LENGTH_SHORT).show();
                    break;
                case RESULT_CANCELED:
                    Toast.makeText(this, "책 정보 수정 취소", Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    }
}
