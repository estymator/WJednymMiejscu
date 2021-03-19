package com.example.wjednymmiejscu.ui.home;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.wjednymmiejscu.R;
import com.example.wjednymmiejscu.adapters.CalculatorAdapter;


public class MainCalculatorFragment extends Fragment {
    private static final String TAG = "CalculatorFragmentTAG";
    private static final String PARAM="row";
    private Integer mRow;
    private MainViewModel mainViewModel;
    private RecyclerView currenciesRecyclerView;

    public MainCalculatorFragment() {
        // Required empty public constructor
    }


    public static MainCalculatorFragment newInstance(Integer param1) {
        MainCalculatorFragment fragment = new MainCalculatorFragment();
        Bundle args = new Bundle();
        args.putInt(PARAM, param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainViewModel = new ViewModelProvider(requireActivity(), new MainViewModelProvider())
                .get(MainViewModel.class);
        if (getArguments() != null) {
            mRow = getArguments().getInt(PARAM);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main_calculator, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        currenciesRecyclerView = view.findViewById(R.id.mainCalc_recyclerView);
        currenciesRecyclerView.setLayoutManager(new LinearLayoutManager(requireContext(), RecyclerView.HORIZONTAL,false));
        if(mRow==1){
            currenciesRecyclerView.setAdapter(new CalculatorAdapter(mainViewModel.getMarketsList(), mainViewModel.getCalculatorFirstRowCurr()));
            mainViewModel.getCalculatorFirstRowCurr().observe(getViewLifecycleOwner(), new Observer<String>() {
                @Override
                public void onChanged(String s) {
                    mainViewModel.calculate();
                }
            });
        }else if(mRow==2){
            currenciesRecyclerView.setAdapter(new CalculatorAdapter(mainViewModel.getMarketsList(), mainViewModel.getCalculatorSecondRowCurr()));
            mainViewModel.getCalculatorSecondRowCurr().observe(getViewLifecycleOwner(), new Observer<String>() {
                @Override
                public void onChanged(String s) {
                    mainViewModel.calculate();
                }
            });
        }

    }
}