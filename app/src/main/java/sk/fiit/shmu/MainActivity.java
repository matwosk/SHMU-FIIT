package sk.fiit.shmu;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.CombinedChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.CombinedData;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;

import org.json.JSONException;

import java.util.ArrayList;

import static sk.fiit.shmu.JsonParser.getHours;
import static sk.fiit.shmu.JsonParser.getJsonObject;
import static sk.fiit.shmu.JsonParser.getRain;
import static sk.fiit.shmu.JsonParser.getStationName;
import static sk.fiit.shmu.JsonParser.getTemperature;

public class MainActivity extends AppCompatActivity {

    private CombinedChart combinedChart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String name = "";

        try {
            name = getStationName(getJsonObject());
        } catch (JSONException e) {
            e.printStackTrace();
        }

        combinedChart = (CombinedChart) findViewById(R.id.combinedChart);
        combinedChart.setDrawGridBackground(false);
        combinedChart.setDescription(name);
        combinedChart.setDescriptionPosition(250f, 20f);
        combinedChart.setDescriptionColor(Color.rgb(255, 255, 255));
        combinedChart.setNoDataTextDescription("Chybajuce data.");
        combinedChart.setTouchEnabled(true);
        combinedChart.setDragEnabled(true);
        combinedChart.setScaleEnabled(true);
        combinedChart.setPinchZoom(true);

        YAxis leftAxis = combinedChart.getAxisLeft();
        leftAxis.setAxisMaxValue(50f);
        leftAxis.setAxisMinValue(-20f);
        leftAxis.setEnabled(false);

        YAxis rightAxis = combinedChart.getAxisRight();
        rightAxis.setEnabled(false);

//        LimitLine limitLine = new LimitLine(0);
//        limitLine.setLabel("0");
//        limitLine.setTextSize(7f);
//        limitLine.setTextColor(Color.rgb(255, 255, 255));
//        limitLine.setLineColor(Color.rgb(255, 255, 255));
//        leftAxis.addLimitLine(limitLine);
//        leftAxis.setDrawLimitLinesBehindData(true);

        leftAxis.enableGridDashedLine(1f, 1f, 0f);

        XAxis xAxis = combinedChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setTextColor(Color.rgb(255, 255, 255));
        xAxis.setEnabled(true);

        CombinedData data = null;
        try {
            data = new CombinedData(getHours(getJsonObject()));
            data.setData(generateTempData());
            data.setData(generateRainData());
        } catch (JSONException e) {
            e.printStackTrace();
        }

        combinedChart.setData(data);
        combinedChart.invalidate();

        Legend l = combinedChart.getLegend();
        l.setEnabled(false);

        combinedChart.animateX(2500, Easing.EasingOption.EaseInOutQuart);
    }

    private LineData generateTempData() throws JSONException {

        LineDataSet set = new LineDataSet(getTemperature(getJsonObject()), "Teplota");
        set.setDrawCircleHole(true);
        set.setLineWidth(2.5f);
        set.setCircleSize(7f);
        set.setCircleColor(Color.argb(255, 255, 255, 255));
        set.setColor(Color.argb(200, 255, 255, 255));
        set.setValueTextColor(Color.rgb(255, 255, 255));
        set.setValueTextSize(10f);
        set.setDrawCubic(true);
        set.setDrawValues(true);

        LineData lineData = new LineData();
        lineData.addDataSet(set);

        return lineData;
    }

    private BarData generateRainData() throws JSONException {

        ArrayList<BarEntry> entries = new ArrayList<>();

        for (int i = 0; i < 12; i++) {
            entries.add(new BarEntry((int) (Math.random() * 5), i));
        }

        BarDataSet set = new BarDataSet(getRain(getJsonObject()), "Zrazky");
        set.setColor(Color.argb(100, 255, 255, 255));
        set.setValueTextColor(Color.argb(100, 255, 255, 255));
        set.setValueTextSize(10f);

        BarData barData = new BarData();
        barData.addDataSet(set);

        return barData;
    }

}
