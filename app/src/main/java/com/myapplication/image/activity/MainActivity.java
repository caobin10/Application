package com.myapplication.image.activity;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;
import com.myapplication.image.Adapter.RecyclerViewAddImageAdapter;
import com.myapplication.image.R;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity {
    private RecyclerView send_recycler;
    public RecyclerViewAddImageAdapter adapter;
    private final int REQUEST_CODE = 1;
    private List<Uri> urilist;
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_test);
        send_recycler = (RecyclerView)findViewById(R.id.send_recycler);
        initData();
    }
    public void initData() {
        urilist= new ArrayList<>();
        GridLayoutManager  layoutManager= new GridLayoutManager(this,3);
        send_recycler.setLayoutManager(layoutManager);
        adapter = new RecyclerViewAddImageAdapter(this, urilist);
        send_recycler.setAdapter(adapter);
        adapter.setClickListener(new RecyclerViewAddImageAdapter.OnItemClickListener() {
            @Override
            public void onClick(View view, int position) {
                //提取图像原数据
                Intent intent = new Intent(Intent.ACTION_PICK, null);
                intent.setType("image/*");
                startActivityForResult(intent, REQUEST_CODE);
            }
            @Override
            public void onSelected(View view, int position) {
                Toast.makeText(MainActivity.this, "didia", Toast.LENGTH_SHORT).show();
            }
        });
        adapter.setLongClickListener(new RecyclerViewAddImageAdapter.OnItemLongClickListener() {
            @Override
            public void onLongClick(View view, final int position) {
                final String[] items = { "删除"};
                android.app.AlertDialog.Builder listDialog = new android.app.AlertDialog.Builder(MainActivity.this);
                listDialog.setItems(items, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        switch (i) {
                            case 0://删除item
                                urilist.remove(position);
                                adapter.notifyDataSetChanged();
                                break;
                            default:
                                break;
                        }
                    }
                });
                listDialog.show();
            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        if (resultCode == RESULT_OK && requestCode == 1) {
            urilist.add(data.getData());
            adapter.notifyDataSetChanged();
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
