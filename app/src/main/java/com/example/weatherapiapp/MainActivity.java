 package com.example.weatherapiapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

 public class MainActivity extends AppCompatActivity {

    Button btn_cityID, btn_getWeatherByID, btn_getWeatherByName;
    EditText et_dataInput;
    ListView lv_weatherReport;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_cityID=findViewById(R.id.btn_getCityID);
        btn_getWeatherByID = findViewById(R.id.btn_getWeatherByCityID);
        btn_getWeatherByName = findViewById(R.id.btn_getWeatherByCityName);

        et_dataInput = findViewById(R.id.et_dataInput);
        lv_weatherReport = findViewById(R.id.lv_weatherReports);

        final WeatherDataService weatherDataService = new WeatherDataService(MainActivity.this);
        btn_cityID.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                 weatherDataService.getCityID(et_dataInput.getText().toString(), new WeatherDataService.VolleyResponseListener() {
                    @Override
                    public void onError(String message) {

                        Toast.makeText(MainActivity.this, "Something wrong", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onResponse(String cityID) {

                        Toast.makeText(MainActivity.this, "Return an ID of" + cityID, Toast.LENGTH_SHORT).show();
                    }
                });

            }
        });

        btn_getWeatherByID.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                weatherDataService.getCityForecastByID(et_dataInput.getText().toString(), new WeatherDataService.ForeCastByIDResponse() {
                    @Override
                    public void onError(String message) {
                        Toast.makeText(MainActivity.this, "Something wrong", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onResponse(List<WeatherReportModel> weatherReportModels) {

                        ArrayAdapter arrayAdapter = new ArrayAdapter(MainActivity.this, android.R.layout.simple_list_item_1, weatherReportModels);
                        lv_weatherReport.setAdapter(arrayAdapter);
                        //Toast.makeText(MainActivity.this, weatherReportModel.toString(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        btn_getWeatherByName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                weatherDataService.getCityForecastByName(et_dataInput.getText().toString(), new WeatherDataService.GetCityForecastByNameCallback() {
                    @Override
                    public void onError(String message) {
                        Toast.makeText(MainActivity.this, "Something wrong", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onResponse(List<WeatherReportModel> weatherReportModels) {

                        ArrayAdapter arrayAdapter = new ArrayAdapter(MainActivity.this, android.R.layout.simple_list_item_1, weatherReportModels);
                        lv_weatherReport.setAdapter(arrayAdapter);
                        //Toast.makeText(MainActivity.this, weatherReportModel.toString(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
}