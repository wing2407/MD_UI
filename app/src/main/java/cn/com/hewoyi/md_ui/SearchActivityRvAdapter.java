package cn.com.hewoyi.md_ui;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Stack;


public class SearchActivityRvAdapter extends RecyclerView.Adapter<SearchActivityRvAdapter.ViewHolder> {

    Context mContext;
    Stack<String> dataList;

    public SearchActivityRvAdapter(Context c) {
        mContext = c;

        dataList = loadSearchHistory();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.activity_search_recycler_item, parent, false);


        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.textView.setText(dataList.pop());//出栈
    }

    @Override
    public int getItemCount() {
        return dataList.size();
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


    /**
     * 加载历史记录
     *
     * @return
     */
    public Stack<String> loadSearchHistory() {
        FileInputStream in = null;
        BufferedReader reader = null;
        Stack<String> stack = new Stack<String>();
        try {
            in = mContext.openFileInput("SearchHistory");
            reader = new BufferedReader(new InputStreamReader(in));
            String line;
            //不为空且去除重复数据,前面有!号
            while (((line = reader.readLine()) != null)) {
                if (!stack.contains(line)) {
                    stack.add(line);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        stack.add("删除所有历史记录");  //加入删除提示文字
        return stack;
    }

    public String[] data =
            {"Cupcake", "Donut", "Eclair",
                    "Froyo", "Gingerbread", "Honeycomb",
                    "Icecream Sandwich", "Jelly Bean", "Kitkat", "Lollipop", "Cupcake", "Donut", "Eclair",
                    "Froyo", "Gingerbread", "Honeycomb",
                    "Icecream Sandwich", "Jelly Bean", "Kitkat", "Lollipop"};

}
