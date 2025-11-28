package com.example.fragment;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class FragmentTwo extends Fragment {

    private String fileName;

    public FragmentTwo() {}

    public static FragmentTwo newInstance(String fileName) {
        FragmentTwo fragment = new FragmentTwo();
        Bundle args = new Bundle();
        args.putString("fileName", fileName);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_two, container, false);
        TextView textContent = view.findViewById(R.id.textContent);

        if (getArguments() != null) {
            fileName = getArguments().getString("fileName");
            textContent.setText(readFileFromRaw(fileName));
        }

        return view;
    }

    private String readFileFromRaw(String fileName) {
        StringBuilder text = new StringBuilder();
        try {
            String rawName = fileName.replace(".txt", "");
            int resId = getResources().getIdentifier(rawName, "raw", getActivity().getPackageName());
            InputStream inputStream = getResources().openRawResource(resId);
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

            String line;
            while ((line = reader.readLine()) != null) {
                text.append(line).append("\n");
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
            return "Error reading file: " + fileName;
        }
        return text.toString();
    }
}