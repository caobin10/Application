package com.myapplication.image.Adapter;

/*
 * Created by Administrator on 2018/9/12.
 */

import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.myapplication.image.R;
import com.myapplication.image.fileUtils.FileUtil;

import java.util.List;

public class RecyclerViewAddImageAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final int ITEM_ONE = 1;
    private static final int ITEM_TWO = 2;
    private Context context;
    private List<Uri> data;
    private Bitmap bitmap;
    private OnItemClickListener clickListener;
    private OnItemLongClickListener longclickListener;
    private int MAXIMAGES = 10;

    public RecyclerViewAddImageAdapter(Context context, List<Uri> data) {
        this.context = context;
        this.data = data;
    }

    public void setClickListener(OnItemClickListener clickListener) {
        this.clickListener = clickListener;
    }

    public void setLongClickListener(OnItemLongClickListener longclickListener) {
        this.longclickListener = longclickListener;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        RecyclerView.ViewHolder holder;
        if (viewType == ITEM_TWO) {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.send_item_add, parent, false);
            holder = new AnoViewHolder(view);
        } else {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.send_item, parent, false);
            holder = new ViewHolder(view);
        }
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        if (holder instanceof AnoViewHolder) {
        } else {
            bitmap = FileUtil.bitmapFactory(context, data.get(position));
            ((ViewHolder) holder).imageView.setImageBitmap(bitmap);
        }
    }

    @Override
    public int getItemCount() {
        int count = data == null ? 1 : data.size() + 1;
        if (count >= MAXIMAGES) {
            return data.size();
        } else {
            return count;
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (position > data.size() - 1) {
            return ITEM_TWO;
        } else {
            return ITEM_ONE;
        }
    }

    public interface OnItemClickListener {
        void onClick(View view, int position);

        void onSelected(View view, int position);
    }

    public interface OnItemLongClickListener {
        void onLongClick(View view, int position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {
        private ImageView imageView;

        public ViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageView6);
            imageView.setOnClickListener(this);
            imageView.setOnLongClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (clickListener != null) {
                clickListener.onSelected(itemView, getAdapterPosition());
            }
        }

        @Override
        public boolean onLongClick(View v) {
            if (longclickListener != null) {
                longclickListener.onLongClick(imageView, getAdapterPosition());
            }
            return false;
        }
    }

    public class AnoViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private ImageView img;

        public AnoViewHolder(View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.defaultimg);
            img.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (clickListener != null) {
                clickListener.onClick(itemView, getAdapterPosition());
            }
        }
    }
}
