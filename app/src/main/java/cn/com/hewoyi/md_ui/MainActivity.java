package cn.com.hewoyi.md_ui;


import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Build;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;

import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(cn.com.hewoyi.md_ui.R.layout.activity_main);


        //设定状态栏的颜色，当版本大于4.4时起作用
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            SystemBarTintManager tintManager = new SystemBarTintManager(this);
            tintManager.setStatusBarTintEnabled(true);
            //重新指定状态栏颜色
            //tintManager.setStatusBarTintResource(R.);
        }


        //设置ToolBar
        Toolbar mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mToolbar.setTitleTextColor(Color.WHITE);
        setSupportActionBar(mToolbar);

        //设置抽屉DrawerLayout
        final DrawerLayout mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, mToolbar,
                R.string.drawer_open, R.string.drawer_close);

        //抽屉菜单布局加载
        mDrawerToggle.syncState();//初始化状态
        mDrawerLayout.setDrawerListener(mDrawerToggle);
        mDrawerLayout.setClickable(true);

        //设置导航栏NavigationView的点击事件
        NavigationView mNavigationView = (NavigationView) findViewById(R.id.navigation_view);
        mNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.item_one:
                        Toast.makeText(MainActivity.this, "点击了-->" + menuItem.getTitle(), Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.item_two:
                        Toast.makeText(MainActivity.this, "点击了-->" + menuItem.getTitle(), Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.item_three:
                        Toast.makeText(MainActivity.this, "点击了-->" + menuItem.getTitle(), Toast.LENGTH_SHORT).show();
                        break;
                }
                menuItem.setChecked(false);//点击了重新设为未选中状态
                mDrawerLayout.closeDrawers();//关闭抽屉
                return true;
            }
        });

        //设置Viewpager
        final ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
       // viewPager.setPageTransformer(true, new ZoomOutPageTransformer());
        viewPager.setPageTransformer(true,new DepthPageTransformer());
        setupViewPager(viewPager);
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
                switch (tab.getPosition()) {
                    case 0:
                        //Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
                        break;
                    case 1:
                        //showToast("Two");
                        break;
                    case 2:
                        //showToast("Three");
                        break;
                    case 4:
                        //
                        break;
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });

    }


    DatabaseTable db = new DatabaseTable(this);


    private void handleIntent(Intent intent) {

        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            String query = intent.getStringExtra(SearchManager.QUERY);
            Cursor c = db.getWordMatches(query, null);
            //process Cursor and display results
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);

            SearchManager searchManager =
                    (SearchManager) getSystemService(Context.SEARCH_SERVICE);
            SearchView searchView =
                    (SearchView) menu.findItem(R.id.action_search).getActionView();
            searchView.setSearchableInfo(
                    searchManager.getSearchableInfo(getComponentName()));
            searchView.setIconifiedByDefault(false);

        return true;
    }



    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFrag(HomeFragment.newInstance(), "主页");
        adapter.addFrag(DummyFragment.newInstance(getResources().getColor(R.color.primary_material_light)), "分类");
        adapter.addFrag(DummyFragment.newInstance(getResources().getColor(R.color.primary_material_light)), "游戏");
        adapter.addFrag(DummyFragment.newInstance(getResources().getColor(R.color.primary_material_light)), "排行");
        viewPager.setAdapter(adapter);
    }
}
