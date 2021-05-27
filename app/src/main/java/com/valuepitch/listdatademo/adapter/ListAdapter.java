package com.valuepitch.listdatademo.adapter;


import android.content.Context;
import android.graphics.Paint;
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


public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder> {

    private Context mContext;
    private JSONArray data;

    public ListAdapter(Context mContext, JSONArray data) {

        this.mContext = mContext;
        this.data = data;

    }
    @Override
    public ListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        return new ListAdapter.ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final ListAdapter.ViewHolder holder, final int position) {

        if(data.length()>0) {
            try {
                JSONObject jsonobject = (JSONObject) data.get(position);
                Picasso.get()
                        .load(jsonobject.getString("image"))
                        .into(holder.imageView);

                holder.tvName.setText(jsonobject.getString("name"));
                holder.tvMrp.setText(jsonobject.getString("mrp"));
                holder.tvMrp.setPaintFlags(holder.tvMrp.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
                holder.tvDmartMrp.setText(jsonobject.getString("dmartMrp"));
                holder.tvSave.setText(jsonobject.getString("save"));
                holder.tvQuantity.setText(jsonobject.getString("quantity"));

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
      TextView tvName,tvMrp,tvDmartMrp,tvSave,tvQuantity;

        public ViewHolder(View view) {
            super(view);

            imageView =view.findViewById(R.id.imageView);
            tvName =view.findViewById(R.id.tvName);
            tvMrp =view.findViewById(R.id.tvMrp);
            tvDmartMrp =view.findViewById(R.id.tvDmartMrp);
            tvSave =view.findViewById(R.id.tvSave);
            tvQuantity =view.findViewById(R.id.tvQuantity);
        }
    }
}
