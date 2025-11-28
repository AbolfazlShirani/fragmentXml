package com.example.fragment;

import android.content.Context;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class FragmentOne extends Fragment {

    private OnFileSelectedListener listener;
    private String[] files = {"file1", "file2"};

    public interface OnFileSelectedListener {
        void onFileSelected(String fileName);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFileSelectedListener) {
            listener = (OnFileSelectedListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFileSelectedListener");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_one, container, false);
        ListView listView = view.findViewById(R.id.listView);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                getActivity(),
                android.R.layout.simple_list_item_1,
                files
        );
        listView.setAdapter(adapter);

        listView.setOnItemClickListener((AdapterView<?> parent, View v, int position, long id) -> {
            listener.onFileSelected(files[position]);
        });

        return view;
    }
}