package sk.fiit.shmu;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.LimitLine;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private LineChart lineChart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lineChart = (LineChart) findViewById(R.id.lineChart);
        lineChart.setDrawGridBackground(false);
        lineChart.setDescription("Teplota");
        lineChart.setDescriptionColor(Color.rgb(255, 255, 255));
        lineChart.setNoDataTextDescription("Chybajuce data.");
        lineChart.setTouchEnabled(true);
        lineChart.setDragEnabled(true);
        lineChart.setScaleEnabled(true);
        lineChart.setPinchZoom(true);

        YAxis leftAxis = lineChart.getAxisLeft();
        leftAxis.setAxisMaxValue(50f);
        leftAxis.setAxisMinValue(-20f);
        leftAxis.setStartAtZero(false);
        leftAxis.setEnabled(false);

        YAxis rightAxis = lineChart.getAxisRight();
        rightAxis.setEnabled(false);


        LimitLine limitLine = new LimitLine(0);
        limitLine.setLabel("0");
        limitLine.setTextSize(7f);
        limitLine.setTextColor(Color.rgb(255, 255, 255));
        limitLine.setLineColor(Color.rgb(255, 255, 255));
        leftAxis.addLimitLine(limitLine);

        leftAxis.setDrawLimitLinesBehindData(true);
        leftAxis.enableGridDashedLine(10f, 10f, 0f);

        XAxis xAxis = lineChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setTextColor(Color.rgb(255, 255, 255));
        xAxis.setEnabled(true);

        generateDataLine();

        Legend l = lineChart.getLegend();
        l.setEnabled(false);

        lineChart.animateX(2500, Easing.EasingOption.EaseInOutQuart);
    }

    private void generateDataLine() {

        ArrayList<Entry> e1 = new ArrayList<>();

        for (int i = 0; i < 12; i++) {
            e1.add(new Entry((int) (Math.random() * 15) + 20, i));
        }

        LineDataSet d1 = new LineDataSet(e1, "Teplota");
        d1.setDrawCircleHole(false);
        d1.setLineWidth(2.5f);
        d1.setCircleSize(7f);
        d1.setCircleColor(Color.rgb(255, 255, 255));
        d1.setColor(Color.rgb(255, 255, 255));
        d1.setValueTextColor(Color.rgb(255, 255, 255));
        d1.setValueTextSize(10f);
        d1.setDrawValues(true);

        ArrayList<LineDataSet> sets = new ArrayList<>();
        sets.add(d1);

        LineData cd = new LineData(getHours(), sets);
        cd.setValueTextColor(Color.rgb(255, 255, 255));
        lineChart.setData(cd);
    }

    private ArrayList<String> getHours() {

        ArrayList<String> h = new ArrayList<>();
        h.add("00");
        h.add("02");
        h.add("04");
        h.add("06");
        h.add("08");
        h.add("10");
        h.add("12");
        h.add("14");
        h.add("16");
        h.add("18");
        h.add("20");
        h.add("22");

        return h;
    }

}
