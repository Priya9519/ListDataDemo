package com.valuepitch.listdatademo.adapter;


import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.valuepitch.listdatademo.R;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.ViewHolder> {

    private Context mContext;
    private JSONArray data;
    private BannerAdapter bannerAdapter;
    private GridAdapter gridAdapter;
    private ListAdapter listAdapter;

    public CustomAdapter(Context mContext, JSONArray data) {

        this.mContext = mContext;
        this.data = data;
    }

    @Override
    public CustomAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.main_list_item, parent, false);
        return new CustomAdapter.ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final CustomAdapter.ViewHolder holder, final int position) {

        if(data.length()>0) {
            try {
                JSONObject jsonobject = (JSONObject) data.get(position);
                Log.v("Hello",data.length()+"");
                JSONArray bannerArr = jsonobject.getJSONArray("banner");
                JSONArray listArr = jsonobject.getJSONArray("list");
                JSONArray gridArr = jsonobject.getJSONArray("grid");

                RecyclerView.LayoutManager layoutManagerList = new LinearLayoutManager(mContext);
                holder.rvBanner.setLayoutManager(layoutManagerList);
                holder.rvBanner.setNestedScrollingEnabled(false);
                holder.rvBanner.setHasFixedSize(false);

                bannerAdapter = new BannerAdapter(mContext, bannerArr);
                holder.rvBanner.setAdapter(bannerAdapter);

                if(gridArr.length()%4==0){
                    holder.rvGrid.setLayoutManager(new GridLayoutManager(mContext, 4));
                }
                else if(gridArr.length()%3==0) {

                    holder.rvGrid.setLayoutManager(new GridLayoutManager(mContext, 3));
                }
                else{
                    holder.rvGrid.setLayoutManager(new GridLayoutManager(mContext, 2));
                }

                holder.rvGrid.setNestedScrollingEnabled(false);
                holder.rvGrid.setHasFixedSize(false);
                holder.rvGrid.addItemDecoration(new DividerItemDecoration(mContext, DividerItemDecoration.HORIZONTAL));
                holder.rvGrid.addItemDecoration(new DividerItemDecoration(mContext, DividerItemDecoration.VERTICAL));

                gridAdapter = new GridAdapter(mContext, gridArr);
                holder.rvGrid.setAdapter(gridAdapter);

                RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(mContext);
                holder.rvList.setLayoutManager(layoutManager);
                holder.rvList.setNestedScrollingEnabled(false);
                holder.rvList.setHasFixedSize(false);

                listAdapter = new ListAdapter(mContext, listArr);
                holder.rvList.setAdapter(listAdapter);


            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public int getItemCount() {
        return (null != data ? data.length() : 0);
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

      RecyclerView rvBanner,rvList,rvGrid;

        public ViewHolder(View view) {
            super(view);

            rvBanner =view.findViewById(R.id.rvBanner);
            rvList =view.findViewById(R.id.rvList);
            rvGrid =view.findViewById(R.id.rvGrid);
        }
    }
}
