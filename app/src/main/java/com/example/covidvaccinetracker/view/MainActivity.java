package com.example.covidvaccinetracker.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.covidvaccinetracker.R;
import com.example.covidvaccinetracker.adapter.VaccinationInfoAdapter;
import com.example.covidvaccinetracker.model.VaccineModel;

import org.json.JSONArray;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class MainActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {

    //String URL = "https://cdn-api.co-vin.in/api/v2/appointment/sessions/public/findByPin";
    String URL = "https://cdn-api.co-vin.in/api/v2/appointment/sessions/public/findByPin";
    String areaPIN,avlDate;
    private EditText areaPincode;
    private Button search;
    ProgressBar HoldOn;
    private ArrayList<VaccineModel> vaccination_centers;
    private RecyclerView result;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        mapViews();
        onClickSetUp();
        
        
    }

    private void onClickSetUp() {
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HoldOn.setVisibility(View.VISIBLE);
                DialogFragment dp = new PickDate();
                dp.show(getSupportFragmentManager(),"Pick a Date:");


            }
        });
    }

    private void mapViews() {
        search = findViewById(R.id.getResult);
        HoldOn = findViewById(R.id.progress_circular);
        areaPincode = findViewById(R.id.enterPincode);
        result = findViewById(R.id.recyclerView);
        result.setHasFixedSize(true);
        vaccination_centers = new ArrayList<VaccineModel>();

    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

        Calendar k = Calendar.getInstance();
        k.set(Calendar.YEAR,year);
        k.set(Calendar.MONTH,month);
        k.set(Calendar.DAY_OF_MONTH,dayOfMonth);

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-YYYY");
        dateFormat.setTimeZone(k.getTimeZone());
        String d = dateFormat.format(k.getTime());
        setup(d);



    }

    private void setup(String d) {
        avlDate = d;
        fetchDataNow();
        

    }

    private void fetchDataNow() {
        vaccination_centers.clear();
        areaPIN = areaPincode.getText().toString();
        String URL_api = URL + "?pincode=" + areaPIN + "&date=" + avlDate;

        StringRequest stringrequest = new StringRequest(Request.Method.GET, URL_api, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject object = new JSONObject(response);
                    JSONArray sessionArray = object.getJSONArray("sessions");
                    for (int i = 0; i < sessionArray.length(); i++) {
                        JSONObject sesObject = sessionArray.getJSONObject(i);
                        VaccineModel vaccineModel = new VaccineModel();
                        vaccineModel.setVaccineCenter(sesObject.getString("name"));
                        vaccineModel.setVaccinationCenterAddress(sesObject.getString("address"));
                        vaccineModel.setVaccinationTimings(sesObject.getString("from"));
                        vaccineModel.setVaccineCenterTime(sesObject.getString("to"));
                        vaccineModel.setVaccineName(sesObject.getString("vaccine"));
                        vaccineModel.setVaccinationCharges(sesObject.getString("fee"));
                        vaccineModel.setVaccinationAge(sesObject.getString("min_age_limit"));
                        vaccineModel.setVaccinationAvailability(sesObject.getString("available_capacity"));
                        vaccination_centers.add(vaccineModel);
                    }
                    VaccinationInfoAdapter vaccinationInfoAdapter = new VaccinationInfoAdapter(getApplicationContext(), vaccination_centers);
                    result.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                    result.setAdapter(vaccinationInfoAdapter);
                    HoldOn.setVisibility(View.INVISIBLE);

                    Log.i("Done","Done");

                } catch (Exception e) {
                    Log.i("NOPE","nope");
                    HoldOn.setVisibility(View.INVISIBLE);
                    e.printStackTrace();

                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                HoldOn.setVisibility(View.INVISIBLE);
                //Toast.makeText(getApplicationContext(),error.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringrequest);



    }
}