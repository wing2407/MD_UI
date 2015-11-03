package cn.com.hewoyi.md_ui;


import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.ListPopupWindow;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

public class RankingFragment extends Fragment {


    public static RankingFragment newInstance() {
        RankingFragment fragment = new RankingFragment();
        Bundle args = new Bundle();

        fragment.setArguments(args);
        return fragment;
    }

    public RankingFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
    }





    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_ranking, container, false);

        final Button button = (Button) view.findViewById(R.id.fg_ranking_imgbtn);
        final ListPopupWindow mListPop = new ListPopupWindow(getActivity());
        mListPop.setAdapter(new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, rankingType));
        mListPop.setWidth(ListPopupWindow.WRAP_CONTENT);
        mListPop.setAnchorView(button);//设置ListPopupWindow的锚点，即关联PopupWindow的显示位置和这个锚点
        mListPop.setModal(true);//设置是否是模式
        mListPop.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                button.setText(rankingType[position]);
                mListPop.dismiss();
            }
        });
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListPop.show();
            }
        });

        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.fg_ranking_rv);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity().getBaseContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);

        List<String> list = new ArrayList<String>();
        for (int i = 0; i < data.length; i++) {
            list.add(data[i]);
        }

        SimpleRecyclerAdapter adapter;
        adapter = new SimpleRecyclerAdapter(getActivity(), list);
        recyclerView.setAdapter(adapter);

        return view;
    }


    public static final String[] data = {"Cupcake", "Donut", "Eclair",
            "Froyo", "Gingerbread", "Honeycomb",
            "Icecream Sandwich", "Jelly Bean", "Kitkat", "Lollipop", "Cupcake", "Donut", "Eclair",
            "Froyo", "Gingerbread", "Honeycomb",
            "Icecream Sandwich", "Jelly Bean", "Kitkat", "Lollipop", "Cupcake", "Donut", "Eclair",
            "Froyo", "Gingerbread", "Honeycomb",
            "Icecream Sandwich", "Jelly Bean", "Kitkat", "Lollipop", "Cupcake", "Donut", "Eclair",
            "Froyo", "Gingerbread", "Honeycomb",
            "Icecream Sandwich", "Jelly Bean", "Kitkat", "Lollipop"};

    public static final String[] rankingType = {"默认排行", "按热度排行", "按下载排行", "按关注排行", "按评分排行", "按评论排行", "周下载排行", "月下载排行", "总下载排行"};

}
