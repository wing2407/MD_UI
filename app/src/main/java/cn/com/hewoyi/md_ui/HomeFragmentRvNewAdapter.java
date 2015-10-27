package cn.com.hewoyi.md_ui;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;




/**
 * Created by Administrator on 2015/10/27.
 */
public class HomeFragmentRvNewAdapter extends RecyclerView.Adapter<HomeFragmentRvNewAdapter.ViewHolder> {

    private Context mContext;
    private int[] ids ={R.mipmap.ic_launcher,
            R.mipmap.ic_launcher,R.mipmap.ic_launcher,
            R.mipmap.ic_launcher, R.mipmap.ic_launcher,
            R.mipmap.ic_launcher, R.mipmap.ic_launcher};

    public HomeFragmentRvNewAdapter(Context c) {
        mContext = c;

    }


    @Override
    public HomeFragmentRvNewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.fragment_home_recycler_item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(HomeFragmentRvNewAdapter.ViewHolder holder, int position) {
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
            appName = (TextView)v.findViewById(R.id.fg_home_rv_item_name);
            downNum = (TextView)v.findViewById(R.id.fg_home_rv_item_downNum);
        }
    }


}
