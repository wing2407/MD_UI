package cn.com.hewoyi.md_ui;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;


public class SearchActivityRvAdapter extends RecyclerView.Adapter<SearchActivityRvAdapter.ViewHolder> {

    Context mContext;
    Stack<String> dataList;

    public SearchActivityRvAdapter(Context c) {
        mContext = c;

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.activity_search_recycler_item, parent, false);


        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.textView.setText(data[position]);
    }

    @Override
    public int getItemCount() {
        return data.length;
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView textView;

        public ViewHolder(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.search_rv_tv);
            itemView.findViewById(R.id.search_rv_item).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(dataList.get(getAdapterPosition()).equals("删除所有历史记录")){
                        try {
                            mContext.openFileOutput("SearchHistory", Context.MODE_PRIVATE).close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }else {
                        Toast.makeText(mContext,dataList.get(getAdapterPosition()),Toast.LENGTH_LONG).show();
                    }
                }
            });
        }
    }
    

    public void saveSearchHistory(String searchText){}  //保存搜索记录

    public List<String> loadSearchHistory(){return new ArrayList<>();}       //加载搜索记录

    public void deleteSearchHistory(String text){}  //删除某一项搜索记录

    public void clearSearchHistory(){}      //清除所有搜索记录

    public String[] data =
            {"Cupcake", "Donut", "Eclair",
                    "Froyo", "Gingerbread", "Honeycomb",
                    "Icecream Sandwich", "Jelly Bean", "Kitkat", "Lollipop", "Cupcake", "Donut", "Eclair",
                    "Froyo", "Gingerbread", "Honeycomb",
                    "Icecream Sandwich", "Jelly Bean", "Kitkat", "Lollipop"};

}
