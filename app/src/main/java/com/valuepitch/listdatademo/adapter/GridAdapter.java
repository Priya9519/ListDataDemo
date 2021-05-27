package com.valuepitch.listdatademo.adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.squareup.picasso.Picasso;
import com.valuepitch.listdatademo.R;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class GridAdapter extends RecyclerView.Adapter<GridAdapter.ViewHolder> {

    private Context mContext;
    private JSONArray data;

    public GridAdapter(Context mContext, JSONArray data) {

        this.mContext = mContext;
        this.data = data;
    }

    @Override
    public GridAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.grid_item, parent, false);
        return new GridAdapter.ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final GridAdapter.ViewHolder holder, final int position) {

        if(data.length()>0) {
            try {
                JSONObject jsonobject = (JSONObject) data.get(position);
                Picasso.get()
                        .load(jsonobject.getString("image"))
                        .into(holder.imageView);
                holder.tvName.setText(jsonobject.getString("name"));

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
        TextView tvName;
        public ViewHolder(View view) {
            super(view);

            imageView =view.findViewById(R.id.imageView);
            tvName =view.findViewById(R.id.tvName);
        }
    }
}
