package com.example.da08.httpbbs;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements DataLoader.CallBack{

    RecyclerView recycler;
    BbsAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recycler = (RecyclerView) findViewById(R.id.recycler);

        DataLoader loader = new DataLoader();
        loader.getData("http://192.168.10.253:8080/Bbs/List",this); // this는 callback을 구현
        adapter = new BbsAdapter();
        recycler.setAdapter(adapter);
        recycler.setLayoutManager(new LinearLayoutManager(this));

    }

    @Override
    public void setData(List<Bbs> list) {
        // 결과처리 값 세팅
        adapter.setData(list);
        adapter.notifyDataSetChanged();

    }
}

class BbsAdapter extends RecyclerView.Adapter<BbsAdapter.Holder>{

    List<Bbs>list;

    public BbsAdapter(){

        list = new ArrayList<>();

    }

    public void setData(List<Bbs> list){
        this.list = list;
    }
    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.bbs_list,parent,false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {

        Bbs bbs = list.get(position);
        holder.id.setText(bbs.id+"");
        holder.title.setText(bbs.title);
        holder.author.setText(bbs.author);
    }


    @Override
    public int getItemCount() {
        return list.size();
    }

    class Holder extends RecyclerView.ViewHolder{

        TextView id, title, author;

        public Holder(View itemView){
            super(itemView);
            id = (TextView)itemView.findViewById(R.id.id);
            title = (TextView)itemView.findViewById(R.id.title);
            author = (TextView)itemView.findViewById(R.id.author);
        }

    }
}