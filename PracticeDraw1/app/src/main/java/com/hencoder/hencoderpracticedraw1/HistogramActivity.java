package com.hencoder.hencoderpracticedraw1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.hencoder.hencoderpracticedraw1.model.HistogramModel;
import com.hencoder.hencoderpracticedraw1.practice.HistogramLayout;

import java.util.ArrayList;
import java.util.List;

public class HistogramActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_histogram);

        HistogramLayout histogramLayout = (HistogramLayout) findViewById(R.id.hl_test);
        histogramLayout.setHistogramModelList(getData());
    }

    private List<HistogramModel> getData() {
        List<HistogramModel> histogramModelList = new ArrayList<>(5);
        histogramModelList.add(new HistogramModel("Froyo", 12));
        histogramModelList.add(new HistogramModel("GB", 16));
        histogramModelList.add(new HistogramModel("ICS", 26));
        histogramModelList.add(new HistogramModel("JB", 45));
        histogramModelList.add(new HistogramModel("L", 35));
        return histogramModelList;
    }
}
