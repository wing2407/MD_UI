package cn.com.hewoyi.md_ui;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


public class HomeFragmentRvAdapter extends RecyclerView.Adapter<HomeFragmentRvAdapter.ViewHolder> {

    private Context mContext;
    private int[] ids = {R.mipmap.b1,R.mipmap.b2,R.mipmap.b3,R.mipmap.b4,R.mipmap.b5,R.mipmap.b6,R.mipmap.b7,R.mipmap.b8,R.mipmap.b9};

    public HomeFragmentRvAdapter(Context c) {
        mContext = c;

    }


    @Override
    public HomeFragmentRvAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.fragment_home_recycler_item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(HomeFragmentRvAdapter.ViewHolder holder, int position) {

            holder.imageView.setImageResource(ids[position]);
            holder.appName.setText("AppName");
            holder.downNum.setText("DownNum");

    }

    @Override
    public int getItemCount() {
        return ids.length;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView appName;
        TextView downNum;

        public ViewHolder(View v) {
            super(v);
            imageView = (ImageView) v.findViewById(R.id.fg_home_rv_item_img);
            appName = (TextView) v.findViewById(R.id.fg_home_rv_item_name);
            downNum = (TextView) v.findViewById(R.id.fg_home_rv_item_downNum);
        }
    }


}
