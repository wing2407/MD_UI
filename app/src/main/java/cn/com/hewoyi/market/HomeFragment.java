package cn.com.hewoyi.market;

import android.os.Bundle;
import android.os.Message;
import android.os.Handler;
import android.os.SystemClock;
import android.support.v4.view.ViewPager;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.support.v4.app.Fragment;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;



/**
 * Home主页界面Fragment
 */
public class HomeFragment extends Fragment {

    private ViewPager adViewPager;  //广告栏
    private List<View> pageViews;   //view数据
    private ImageView[] imageViews; //小圆点数组
    private HomeFragmentAdAdapter adapter;
    private AtomicInteger atomicInteger = new AtomicInteger(0);
    private boolean isContinue = true;  //滚动标志



    public static HomeFragment newInstance() {
       /* HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();

        fragment.setArguments(args);*/
        ArrayList<String> list = new ArrayList<>() ;

        return new HomeFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       /* if (getArguments() != null) {
        }*/


    }

    /**
     * 初始化UI视图
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_home, container, false);
        //加入广告栏
        initViewPager(v);
        RecyclerView rv_new = (RecyclerView)v.findViewById(R.id.fg_home_rv_new);
        rv_new.setHasFixedSize(true);
//        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));//这里用线性显示 类似于listview
//        mRecyclerView.setLayoutManager(new GridLayoutManager(this, 2));//这里用线性宫格显示 类似于grid view
//        mRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, OrientationHelper.VERTICAL));//这里用线性宫格显示 类似于瀑布流
        rv_new.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));//横向显示
        rv_new.setAdapter(new HomeFragmentRvAdapter(getActivity()));

        RecyclerView rv_app = (RecyclerView)v.findViewById(R.id.fg_home_rv_app);
        rv_app.setHasFixedSize(true);
        rv_app.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
        rv_app.setAdapter(new HomeFragmentRvAdapter(getActivity()));

        RecyclerView rv_game = (RecyclerView)v.findViewById(R.id.fg_home_rv_game);
        rv_game.setHasFixedSize(true);
        rv_game.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
        rv_game.setAdapter(new HomeFragmentRvAdapter(getActivity()));

        RecyclerView rv_theme = (RecyclerView)v.findViewById(R.id.fg_home_rv_theme);
        rv_theme.setHasFixedSize(true);
        rv_theme.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
        rv_theme.setAdapter(new HomeFragmentRvAdapter(getActivity()));

        return v;
    }


    /*
   * 每隔固定时间切换广告栏图片
   */
    private final Handler viewHandler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            adViewPager.setCurrentItem(msg.what);
            super.handleMessage(msg);
        }

    };

    private void atomicOption() {
        atomicInteger.incrementAndGet();
        if (atomicInteger.get() > imageViews.length - 1) {
            atomicInteger.getAndAdd(-pageViews.size());
        }
        SystemClock.sleep(1500);

    }


    private void initPageAdapter() {
        pageViews = new ArrayList<>();

        ImageView img1 = new ImageView(getActivity());
        img1.setBackgroundResource(R.mipmap.ic_launcher);
        pageViews.add(img1);

        ImageView img2 = new ImageView(getActivity());
        img2.setBackgroundResource(R.mipmap.ic_launcher);
        pageViews.add(img2);

        ImageView img3 = new ImageView(getActivity());
        img3.setBackgroundResource(R.mipmap.ic_launcher);
        pageViews.add(img3);

        ImageView img4 = new ImageView(getActivity());
        img4.setBackgroundResource(R.mipmap.ic_launcher);
        pageViews.add(img4);

        ImageView img5 = new ImageView(getActivity());
        img5.setBackgroundResource(R.mipmap.ic_launcher);
        pageViews.add(img5);

        ImageView img6 = new ImageView(getActivity());
        img6.setBackgroundResource(R.mipmap.ic_launcher);
        pageViews.add(img6);

        adapter = new HomeFragmentAdAdapter(pageViews);
    }

    /**
     * 滚动广告列表
     */
    private void initViewPager(View v) {

        //从布局文件中获取ViewPager父容器
        LinearLayout pagerLayout = (LinearLayout) v.findViewById(R.id.view_pager_content);
        //创建ViewPager
        adViewPager = new ViewPager(getActivity().getApplicationContext());

        //获取屏幕像素相关信息
        DisplayMetrics dm = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(dm);

        //根据屏幕信息设置ViewPager广告容器的宽高
        adViewPager.setLayoutParams(new LayoutParams(dm.widthPixels, dm.heightPixels * 3 / 9));

        //将ViewPager容器设置到布局文件父容器中
        pagerLayout.addView(adViewPager);

        //初始化广告图片
        initPageAdapter();
        initCirclePoint(v);

        adViewPager.setAdapter(adapter);
        adViewPager.setOnPageChangeListener(new AdPageChangeListener());

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    if (isContinue) {
                        viewHandler.sendEmptyMessage(atomicInteger.get());
                        atomicOption();
                    }
                }
            }
        }).start();
    }

    /**
     * 初始化小圆点
     */
    private void initCirclePoint(View v) {
        ViewGroup group = (ViewGroup) v.findViewById(R.id.viewGroup);
        imageViews = new ImageView[pageViews.size()];
        //广告栏的小圆点图标
        for (int i = 0; i < pageViews.size(); i++) {
            //创建一个ImageView, 并设置宽高. 将该对象放入到数组中
            ImageView imageView = new ImageView(getActivity());
            imageView.setLayoutParams(new LayoutParams(15, 15));
            imageViews[i] = imageView;

            //初始值, 默认第0个选中
            if (i == 0) {
                imageViews[i]
                        .setBackgroundResource(android.R.drawable.presence_online);
            } else {
                imageViews[i]
                        .setBackgroundResource(android.R.drawable.presence_invisible);
            }
            //将小圆点放入到布局中
            group.addView(imageViews[i]);
        }
    }

    /**
     * ViewPager 页面改变监听器
     */
    private final class AdPageChangeListener implements ViewPager.OnPageChangeListener {

        /**
         * 页面滚动状态发生改变的时候触发
         */
        @Override
        public void onPageScrollStateChanged(int arg0) {
        }

        /**
         * 页面滚动的时候触发
         */
        @Override
        public void onPageScrolled(int arg0, float arg1, int arg2) {
        }

        /**
         * 页面选中的时候触发
         */
        @Override
        public void onPageSelected(int arg0) {
            //获取当前显示的页面是哪个页面
            atomicInteger.getAndSet(arg0);
            imageViews[arg0]
                    .setBackgroundResource(android.R.drawable.presence_online);
            //重新设置原点布局集合
            for (int i = 0; i < imageViews.length; i++) {
                if (arg0 != i) {
                    imageViews[i]
                            .setBackgroundResource(android.R.drawable.presence_invisible);
                }
            }
        }
    }

}
