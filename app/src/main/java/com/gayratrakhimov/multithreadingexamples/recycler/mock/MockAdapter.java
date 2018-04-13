package com.gayratrakhimov.multithreadingexamples.recycler.mock;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gayratrakhimov.multithreadingexamples.R;

import java.util.ArrayList;
import java.util.List;

public class MockAdapter extends RecyclerView.Adapter<MockHolder> {

    final private List<Mock> mockList = new ArrayList<>();

    @Override
    public MockHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View inflate = inflater.inflate(R.layout.listitem_mock, parent, false);
        return new MockHolder(inflate);
    }

    @Override
    public void onBindViewHolder(MockHolder holder, int position) {

        holder.bind(mockList.get(position));

    }

    @Override
    public int getItemCount() {
        return mockList.size();
    }

    public void addData(List<Mock> mocks){
        mockList.addAll(mocks);
        notifyDataSetChanged();
    }

}
