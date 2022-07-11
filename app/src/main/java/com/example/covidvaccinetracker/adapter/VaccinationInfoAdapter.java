package com.example.covidvaccinetracker.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.covidvaccinetracker.R;
import com.example.covidvaccinetracker.model.VaccineModel;

import java.util.List;

public class VaccinationInfoAdapter extends RecyclerView.Adapter<VaccinationInfoAdapter.ViewHolder> {

    private LayoutInflater layoutInflater;
    private List<VaccineModel> list_vaccine_center;

    public VaccinationInfoAdapter(Context mcontext, List<VaccineModel> list_vaccine_center) {
        this.layoutInflater = layoutInflater.from(mcontext);
        this.list_vaccine_center = list_vaccine_center;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.vaccination_item,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VaccinationInfoAdapter.ViewHolder holder, int position) {
        holder.vaccinationCenter.setText(list_vaccine_center.get(position).getVaccineCenter());
        holder.vaccinationCenterAddress.setText(list_vaccine_center.get(position).getVaccinationCenterAddress());
        holder.vaccinationTiming.setText(list_vaccine_center.get(position).getVaccinationTimings() + " - " + list_vaccine_center.get(position).getVaccineCenterTime());
        holder.vaccinationAge.setText(list_vaccine_center.get(position).getVaccinationAge());
        holder.vaccinationAvailable.setText(list_vaccine_center.get(position).getVaccinationAvailability());
        holder.vaccineName.setText(list_vaccine_center.get(position).getVaccineName());
        holder.vaccineCharges.setText(list_vaccine_center.get(position).getVaccinationCharges());


    }

    @Override
    public int getItemCount() {

        return list_vaccine_center.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView vaccinationCenter;
        TextView vaccinationCenterAddress;
        TextView vaccinationTiming;
        TextView vaccineName;
        TextView vaccineCharges;
        TextView vaccinationAge;
        TextView vaccinationAvailable;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            vaccinationAge = itemView.findViewById(R.id.vaccinationAge);
            vaccinationAvailable = itemView.findViewById(R.id.vaccineAvailability);
            vaccineCharges = itemView.findViewById(R.id.vaccineFees);
            vaccinationCenter = itemView.findViewById(R.id.vaccinationCenter);
            vaccineName = itemView.findViewById(R.id.vaccineName);
            vaccinationTiming = itemView.findViewById(R.id.vaccinationTimings);
            vaccinationCenterAddress = itemView.findViewById(R.id.vaccineLocation);

        }
    }
}
