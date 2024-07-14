package ddwucom.mobile.finalreport;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class BookAdapter extends BaseAdapter {

    private Context context; //inflater 객체 생성용
    private int layout; //layout-id 전달
    private ArrayList<Book> myDataList; //원본 데이터
    private LayoutInflater layoutInflater; //inflater 객체

    public BookAdapter(Context context, int layout, ArrayList<Book> myDataList) {
        this.context = context;
        this.layout = layout;
        this.myDataList = myDataList;
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return myDataList.size();
    }

    @Override
    public Object getItem(int i) {
        return myDataList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return myDataList.get(i).get_id();
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder;

        if(view == null){
            view = layoutInflater.inflate(layout, viewGroup, false);

            holder = new ViewHolder();
            holder.tvTitle = view.findViewById(R.id.bookTitle);
            holder.tvAuthor = view.findViewById(R.id.author);
            holder.tvCategory = view.findViewById(R.id.category);
            holder.tvPrice = view.findViewById(R.id.price);
            view.setTag(holder);
        }
        else{
            holder = (ViewHolder) view.getTag();
        }

        holder.tvTitle.setText(myDataList.get(i).title);
        holder.tvAuthor.setText(myDataList.get(i).author);
        holder.tvCategory.setText(myDataList.get(i).category);
        holder.tvPrice.setText(myDataList.get(i).price);

        return view;
    }

    static class ViewHolder{
        TextView tvTitle;
        TextView tvAuthor;
        TextView tvPrice;
        TextView tvCategory;
    }
}
