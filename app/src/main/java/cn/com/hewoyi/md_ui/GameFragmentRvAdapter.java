package cn.com.hewoyi.md_ui;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


public class GameFragmentRvAdapter extends RecyclerView.Adapter<GameFragmentRvAdapter.ViewHolder> {

    Context mContext;

    public GameFragmentRvAdapter(Context c) {
        mContext = c;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.fragment_game_recycler_item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        if (position == 2) {
            holder.screen.setVisibility(View.GONE);
            holder.icon.setImageResource(icons[position%9]);
            holder.appName.setText(data[position]);
        } else {
            holder.screen.setImageResource(ids[position%9]);
            holder.icon.setImageResource(icons[position%9]);
            holder.appName.setText(data[position]);
        }
    }

    @Override
    public int getItemCount() {
        return data.length;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView screen;
        ImageView icon;
        TextView appName;
        //TextView introduction;

        public ViewHolder(View itemView) {
            super(itemView);
            screen = (ImageView) itemView.findViewById(R.id.fg_game_rv_Screenshot);
            icon = (ImageView) itemView.findViewById(R.id.fg_game_rv_icon);
            appName = (TextView) itemView.findViewById(R.id.fg_game_rv_tv_name);
            //introduction = (TextView) itemView.findViewById(R.id.fg_game_rv_tv_introduction);
        }
    }


    public static final int[] ids = {R.mipmap.a1,R.mipmap.a2,R.mipmap.a3,R.mipmap.a4,R.mipmap.a5,R.mipmap.a6,R.mipmap.a7,R.mipmap.a8,R.mipmap.a9};
    public static final int[] icons = {R.mipmap.b1,R.mipmap.b2,R.mipmap.b3,R.mipmap.b4,R.mipmap.b5,R.mipmap.b6,R.mipmap.b7,R.mipmap.b8,R.mipmap.b9};

    public static final String[] data = {"Cupcake", "Donut", "Eclair",
            "Froyo", "Gingerbread", "Honeycomb",
            "Icecream Sandwich", "Jelly Bean", "Kitkat", "Lollipop", "Cupcake", "Donut", "Eclair",
            "Froyo", "Gingerbread", "Honeycomb",
            "Icecream Sandwich", "Jelly Bean", "Kitkat", "Lollipop", "Cupcake", "Donut", "Eclair",
            "Froyo", "Gingerbread", "Honeycomb",
            "Icecream Sandwich", "Jelly Bean", "Kitkat", "Lollipop", "Cupcake", "Donut", "Eclair",
            "Froyo", "Gingerbread", "Honeycomb",
            "Icecream Sandwich", "Jelly Bean", "Kitkat", "Lollipop"};
}
