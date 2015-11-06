package cn.com.hewoyi.md_ui;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Collection;
import java.util.List;
import java.util.Map;



public class SearchActivityRvAdapter extends RecyclerView.Adapter<SearchActivityRvAdapter.ViewHolder> {

    Context mContext;
    Map<String, String> map;
    Collection<String> da;

    public SearchActivityRvAdapter(Context c) {
/*for(String s:map.values()){

}*/
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
        ImageView imageView;

        public ViewHolder(View itemView) {
            super(itemView);

            textView = (TextView) itemView.findViewById(R.id.search_rv_tv);
            imageView = (ImageView) itemView.findViewById(R.id.search_rv_img);
        }
    }


    public String[] data =
            {"Cupcake", "Donut", "Eclair",
                    "Froyo", "Gingerbread", "Honeycomb",
                    "Icecream Sandwich", "Jelly Bean", "Kitkat", "Lollipop","Cupcake", "Donut", "Eclair",
                    "Froyo", "Gingerbread", "Honeycomb",
                    "Icecream Sandwich", "Jelly Bean", "Kitkat", "Lollipop"};

}
