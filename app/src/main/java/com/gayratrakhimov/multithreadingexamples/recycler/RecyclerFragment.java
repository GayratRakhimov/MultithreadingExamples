package com.gayratrakhimov.multithreadingexamples.recycler;

import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.gayratrakhimov.multithreadingexamples.R;

import java.util.Random;

public class RecyclerFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener, android.support.v4.app.LoaderManager.LoaderCallbacks<Cursor> {

//    private final MockAdapter mMockAdapter = new MockAdapter();

    private RecyclerView mRecycler;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private LinearLayout mErrorView;

    private final ContactsAdapter mContactsAdapter = new ContactsAdapter();

    private Random mRandom = new Random();

    public static RecyclerFragment newInstance() {
        return new RecyclerFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_recycler, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        mRecycler = view.findViewById(R.id.recycler);
        mSwipeRefreshLayout = view.findViewById(R.id.swipe_refresh_layout);
        mSwipeRefreshLayout.setOnRefreshListener(this);
        mErrorView = view.findViewById(R.id.error_view);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mRecycler.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecycler.setAdapter(mContactsAdapter);
//        mMockAdapter.addData(MockGenerator.generate(5), true);
    }

    @Override
    public void onRefresh() {
//        mSwipeRefreshLayout.postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                int count = mRandom.nextInt(4);
//
//                if (count == 0) {
//                    showError();
//                } else {
//                    showData(count);
//                }
//
//                if (mSwipeRefreshLayout.isRefreshing()) {
//                    mSwipeRefreshLayout.setRefreshing(false);
//                }
//
//            }
//        }, 2000);
        getLoaderManager().restartLoader(0, null, this);
    }

//    private void showError() {
//        mErrorView.setVisibility(View.VISIBLE);
//        mRecycler.setVisibility(View.GONE);
//    }
//
//    private void showData(int count) {
//        mErrorView.setVisibility(View.GONE);
//        mRecycler.setVisibility(View.VISIBLE);
//    }

    @Override
    public android.support.v4.content.Loader<Cursor> onCreateLoader(int i, Bundle bundle) {
        return new android.support.v4.content.CursorLoader(getActivity(),
                ContactsContract.Contacts.CONTENT_URI,
                new String[]{ContactsContract.Contacts._ID, ContactsContract.Contacts.DISPLAY_NAME},
                null, null, ContactsContract.Contacts._ID);
    }

    @Override
    public void onLoadFinished(android.support.v4.content.Loader<Cursor> loader, Cursor cursor) {
        mContactsAdapter.swapCursor(cursor);
        mSwipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void onLoaderReset(android.support.v4.content.Loader<Cursor> loader) {

    }

}
