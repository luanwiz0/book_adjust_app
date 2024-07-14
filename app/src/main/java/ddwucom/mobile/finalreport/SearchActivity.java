package ddwucom.mobile.finalreport;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class SearchActivity extends AppCompatActivity {

    EditText etTitle;
    EditText etAuthor;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        etTitle = findViewById(R.id.etTitle_search);
        etAuthor = findViewById(R.id.etAuthor_search);
    }

    public void onClick(View v){
        switch(v.getId()){
            case R.id.btn_searchTitle:
                String title = etTitle.getText().toString().trim();
                if(title.length() <= 0){
                    Toast.makeText(this, "제목을 입력해주세요", Toast.LENGTH_SHORT).show();
                    break;
                }
                Intent titleIntent = new Intent(SearchActivity.this, SearchResultActivity.class);
                titleIntent.putExtra("title", title);
                startActivity(titleIntent);
                break;
            case R.id.btn_searchAuthor:
                String author = etAuthor.getText().toString().trim();
                if(author.length() <= 0){
                    Toast.makeText(this, "저자를 입력해주세요", Toast.LENGTH_SHORT).show();
                    break;
                }
                Intent authorIntent = new Intent(SearchActivity.this, SearchResultActivity.class);
                authorIntent.putExtra("author", author);
                startActivity(authorIntent);
                break;
        }
    }
}
