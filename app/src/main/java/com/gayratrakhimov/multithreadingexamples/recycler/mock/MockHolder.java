package com.gayratrakhimov.multithreadingexamples.recycler.mock;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.gayratrakhimov.multithreadingexamples.R;
import com.gayratrakhimov.multithreadingexamples.recycler.ContactsAdapter;

public class MockHolder extends RecyclerView.ViewHolder {

    private TextView mName;
    private TextView mValue;
    private String mId;

    public MockHolder(View view) {
        super(view);
        mName = view.findViewById(R.id.tv_name);
        mValue = view.findViewById(R.id.tv_value);
    }

    public void bind(Mock mock) {
        mName.setText(mock.getName());
        mValue.setText(mock.getValue());
        mId = mock.getValue();
    }

    public void setListener(ContactsAdapter.OnItemClickListener mListener) {
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListener.onItemClick(mId);
            }
        });
    }

}
