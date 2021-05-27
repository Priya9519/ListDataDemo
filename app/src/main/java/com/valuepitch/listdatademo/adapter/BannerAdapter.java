package com.valuepitch.listdatademo.adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.recyclerview.widget.RecyclerView;
import com.squareup.picasso.Picasso;
import com.valuepitch.listdatademo.R;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class BannerAdapter extends RecyclerView.Adapter<BannerAdapter.ViewHolder> {

    private Context mContext;
    private JSONArray data;

    public BannerAdapter(Context mContext, JSONArray data) {

        this.mContext = mContext;
        this.data = data;
    }

    @Override
    public BannerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.banner_item, parent, false);
        return new BannerAdapter.ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final BannerAdapter.ViewHolder holder, final int position) {

        if(data.length()>0) {
            try {
                JSONObject jsonobject = (JSONObject) data.get(position);
                Picasso.get()
                        .load(jsonobject.getString("image"))
                        .resize(mContext.getResources().getDisplayMetrics().widthPixels,0)
                        .into(holder.imageView);

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

      ImageView imageView;

        public ViewHolder(View view) {
            super(view);

            imageView =view.findViewById(R.id.imageView);

        }
    }
}
