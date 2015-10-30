package cn.com.hewoyi.md_ui;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class CategoryFragmentRvAdapter extends RecyclerView.Adapter<CategoryFragmentRvAdapter.ViewHolder> {

    Context mContext;

    public CategoryFragmentRvAdapter(Context c) {
        mContext = c;
    }

    @Override
    public CategoryFragmentRvAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.fragment_category_recycler_item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(CategoryFragmentRvAdapter.ViewHolder holder, int position) {
        holder.img.setImageResource(imgs[position]);
        holder.name.setText(names[position]);
        holder.detail.setText(details[position]);

    }

    @Override
    public int getItemCount() {
        return names.length;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView img;
        TextView name;
        TextView detail;

        public ViewHolder(View itemView) {
            super(itemView);
            img = (ImageView) itemView.findViewById(R.id.fg_category_rv_img);
            name = (TextView) itemView.findViewById(R.id.fg_category_rv_name);
            detail = (TextView) itemView.findViewById(R.id.fg_category_rv_detail);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(mContext,names[getAdapterPosition()],Toast.LENGTH_LONG).show();
                }
            });
        }
    }

    public static final int[] imgs = {R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher,
            R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher,
            R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher,R.mipmap.ic_launcher,R.mipmap.ic_launcher};

    public static final String[] names = {
            "视频播放","音乐电台","社交通信","拍照美化","网上购物",
            "交通导航","便捷生活","旅游出行","常用工具","新闻阅读",
            "教育学习", "系统优化","金融理财","医疗健康"};

    public static final String[] details = {
            "在线视频 直播 真人视频 短视频 播放器","在线音乐 K歌 铃声 乐器 电台","聊天 交友 婚恋 短信 电话 社区",
            "相机 照片美化 拍视频 图片分享 特效","商城 团购 海淘 折扣","地图导航 火车票务 公交地铁 打车","电影 美食 天气 租房 生活 时钟日历",
            "机票旅店 攻略 周边游","浏览器 输入法 wifi 工具 手机美化 文档 办公 储存","新闻 保障 电纸书 漫画 小说",
            "英语 在线教育 词典翻译 考试驾考","安全 省电 流量","银行 股票投资 彩票 记账 支付","医疗 健康 减肥健身 健康"
    };
}
