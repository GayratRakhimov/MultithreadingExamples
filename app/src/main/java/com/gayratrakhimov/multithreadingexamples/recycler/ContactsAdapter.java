package com.gayratrakhimov.multithreadingexamples.recycler;

import android.database.Cursor;
import android.provider.ContactsContract;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gayratrakhimov.multithreadingexamples.R;
import com.gayratrakhimov.multithreadingexamples.recycler.mock.Mock;
import com.gayratrakhimov.multithreadingexamples.recycler.mock.MockHolder;

public class ContactsAdapter extends RecyclerView.Adapter<MockHolder> {

    private Cursor mCursor;

    @Override
    public MockHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View inflate = inflater.inflate(R.layout.listitem_mock, parent, false);
        return new MockHolder(inflate);
    }

    @Override
    public void onBindViewHolder(MockHolder holder, int position) {
        if(mCursor.moveToPosition(position)){
            String name = mCursor.getString(mCursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
            int id = mCursor.getInt(mCursor.getColumnIndex(ContactsContract.Contacts._ID));
            holder.bind(new Mock(name, id));
        }
    }

    @Override
    public int getItemCount() {
        return mCursor != null ? mCursor.getCount() : 0;
    }

    public void swapCursor(Cursor cursor){
        if(cursor!=null && cursor!=mCursor){
            if(mCursor!=null) mCursor.close();
            mCursor = cursor;
            notifyDataSetChanged();
        }
    }

}
