package ddwucom.mobile.finalreport;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class UpdateActivity extends AppCompatActivity {
    final static String TAG = "UpdateActivity";

    Book book;
    EditText etTitle, etAuthor, etPublisher, etPrice, etCategory, etDescription;
    String title, author, publisher, price, category, description;
    BookDBManager bookDBManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        book = (Book) getIntent().getSerializableExtra("book");
        bookDBManager = new BookDBManager(this);

        etTitle = findViewById(R.id.etTitle_update);
        etAuthor = findViewById(R.id.etAuthor_update);
        etPublisher = findViewById(R.id.etPublisher_update);
        etPrice = findViewById(R.id.etPrice_update);
        etCategory = findViewById(R.id.etCategory_update);
        etDescription = findViewById(R.id.etDescription_update);

        etTitle.setText(book.getTitle());
        etAuthor.setText(book.getAuthor());
        etPublisher.setText(book.getPublisher());
        etPrice.setText(book.getPrice());
        etCategory.setText(book.getCategory());
        etDescription.setText(book.getDescription());
    }

    public void onClick(View v){
        switch (v.getId()){
            case R.id.btn_ok:
                if(!checkEssential()){
                    Toast.makeText(this, "필수 항목 입력을 확인해 주세요", Toast.LENGTH_SHORT).show();
                    break;
                }

                book.setTitle(title);
                book.setAuthor(author);
                book.setPublisher(publisher);
                book.setPrice(price);
                book.setCategory(category);
                book.setDescription(description);

                if(bookDBManager.updateBook(book)){
                    Intent resultIntent = new Intent();
                    resultIntent.putExtra("title", title);
                    setResult(RESULT_OK, resultIntent);
                }else{
                    Toast.makeText(this, "책 정보 추가 실패", Toast.LENGTH_SHORT).show();
                }
                finish();
                break;

            case R.id.btn_cancel:
                setResult(RESULT_CANCELED);
                finish();
                break;
        }
    }

    public boolean checkEssential(){
        title = etTitle.getText().toString().trim();
        author = etAuthor.getText().toString().trim();
        publisher = etPublisher.getText().toString().trim();
        price = etPrice.getText().toString().trim();
        category = etCategory.getText().toString().trim();
        description = etDescription.getText().toString().trim();

        if(title.length() <= 0 || author.length() <= 0 || price.length() <= 0 || category.length() <= 0)
            return false;
        return true;
    }
}
