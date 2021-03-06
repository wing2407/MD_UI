package cn.com.hewoyi.market;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.balysv.materialripple.MaterialRippleLayout;

import java.util.List;



public class SimpleRecyclerAdapter extends RecyclerView.Adapter<SimpleRecyclerAdapter.VersionViewHolder> {
    List<String> versionModels;
    Context context;
    // OnItemClickListener clickListener;


    public SimpleRecyclerAdapter(Context c, List<String> versionModels) {
        this.context = c;
        this.versionModels = versionModels;

    }

    @Override
    public VersionViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.recyclerlist_item, viewGroup, false);

        return new VersionViewHolder(view);

    }

    @Override
    public void onBindViewHolder(VersionViewHolder versionViewHolder, int i) {
        versionViewHolder.appName.setText("app:" + versionModels.get(i));
    }

    @Override
    public int getItemCount() {
        return versionModels == null ? 0 : versionModels.size();
    }


    class VersionViewHolder extends RecyclerView.ViewHolder {

        TextView appName;
        TextView appVersion;
        Button appDown;


        public VersionViewHolder(View itemView) {
            super(itemView);



            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
                View view = itemView.findViewById(R.id.app_layout);
                MaterialRippleLayout.on(view)
                        .rippleOverlay(true)
                        .rippleAlpha(0.2f)
                        .rippleColor(R.color.rippleColor)
                        .rippleHover(true)
                        .create();

                view.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(context, "进入" + versionModels.get(getAdapterPosition()) + "详情页", Toast.LENGTH_SHORT).show();
                        context.startActivity(new Intent(context, DetailActivity.class).putExtra("appName", versionModels.get(getAdapterPosition())));

                    }
                });
            }else {
                CardView cardItemLayout= (CardView) itemView.findViewById(R.id.cardlist_item);
                cardItemLayout.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(context, "进入" + versionModels.get(getAdapterPosition()) + "详情页", Toast.LENGTH_SHORT).show();
                        context.startActivity(new Intent(context, DetailActivity.class).putExtra("appName", versionModels.get(getAdapterPosition())));

                    }
                });
            }

            appName = (TextView) itemView.findViewById(R.id.app_tv_name);
            appVersion = (TextView) itemView.findViewById(R.id.app_tv_version);
            appDown = (Button) itemView.findViewById(R.id.app_btn_down);
            appDown.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(context, "下载" + versionModels.get(getAdapterPosition()), Toast.LENGTH_SHORT).show();
                }
            });

        }

      /*  @Override
        public void onClick(View v) {

            switch (v.getId()) {
                case R.id.app_btn_down:
                    Toast.makeText(context, "下载" + versionModels.get(getAdapterPosition()), Toast.LENGTH_SHORT).show();
                case R.id.cardlist_item:
                    Toast.makeText(context, "进入" + versionModels.get(getAdapterPosition()) + "详情页", Toast.LENGTH_SHORT).show();

                default:
                    break;
            }
            // clickListener.onItemClick(v, getPosition());
        }*/
    }

  /*  public interface OnItemClickListener {
        public void onItemClick(View view, int position);
    }

    public void SetOnItemClickListener(final OnItemClickListener itemClickListener) {
        this.clickListener = itemClickListener;
    }
*/
}
