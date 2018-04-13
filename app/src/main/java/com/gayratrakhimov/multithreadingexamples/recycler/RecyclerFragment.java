package com.gayratrakhimov.multithreadingexamples.recycler;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gayratrakhimov.multithreadingexamples.R;
import com.gayratrakhimov.multithreadingexamples.recycler.mock.MockAdapter;
import com.gayratrakhimov.multithreadingexamples.recycler.mock.MockGenerator;

public class RecyclerFragment extends Fragment {

    private RecyclerView mRecyclerView;
    private final MockAdapter mMockAdapter = new MockAdapter();

    public static RecyclerFragment newInstance() {
        return new RecyclerFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_recycler, container, false);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        RecyclerView recycler = view.findViewById(R.id.recycler);
        recycler.setLayoutManager(new LinearLayoutManager(getActivity()));
        recycler.setAdapter(mMockAdapter);
        mMockAdapter.addData(MockGenerator.generate(20));
    }

}
