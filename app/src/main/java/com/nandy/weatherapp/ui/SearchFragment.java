package com.nandy.weatherapp.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

import com.jakewharton.rxbinding2.widget.RxTextView;
import com.nandy.weatherapp.OnListItemClickListener;
import com.nandy.weatherapp.R;
import com.nandy.weatherapp.SearchResultsAdapter;
import com.nandy.weatherapp.api.WeatherService;
import com.nandy.weatherapp.eventbus.SearchResultEvent;
import com.nandy.weatherapp.model.Location;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by yana on 21.10.17.
 */

public class SearchFragment extends Fragment implements TextView.OnEditorActionListener, OnListItemClickListener<String> {

    @BindView(R.id.search_input)
    EditText searchInput;
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    private SearchResultsAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_search, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
        adapter = new SearchResultsAdapter(new ArrayList<>());
        adapter.setOnListItemClickListener(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.addItemDecoration(new DividerItemDecoration(recyclerView.getContext(), LinearLayoutManager.VERTICAL));
        searchInput.setOnEditorActionListener(this);

        setupAutocomplete();
    }

    @Override
    public void onItemClick(String item) {
        onItemSelected(item);
    }

    @Override
    public boolean onEditorAction(TextView textView, int actionId, KeyEvent keyEvent) {
        if (actionId == EditorInfo.IME_ACTION_SEARCH) {
            onItemClick(searchInput.getText().toString());
            return true;
        }
        return false;
    }

    private void onItemSelected(String qwery) {
        EventBus.getDefault().post(new SearchResultEvent(qwery));
        getActivity().onBackPressed();
    }

    private void setupAutocomplete() {
        WeatherService service = new WeatherService();
        RxTextView.textChangeEvents(searchInput)
                .debounce(200, TimeUnit.MILLISECONDS)
                .map(textViewTextChangeEvent -> textViewTextChangeEvent.text().toString())
                .filter(s -> s.length() > 1)
                .flatMap(s -> service.search(s)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread()))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(locations -> {
                    List<String> strings = new ArrayList<>();
                    for (Location location : locations) {
                        strings.add(location.getName());
                    }

                    adapter.setItems(strings);
                }, Throwable::printStackTrace);
    }
}
