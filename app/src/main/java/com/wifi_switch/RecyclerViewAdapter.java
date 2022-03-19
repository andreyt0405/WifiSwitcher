package com.wifi_switch;

import android.graphics.Color;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiInfo;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.viewHolder> {
    private List<ScanResult> scanResults;
    private WifiInfo wifiInfo;
    public RecyclerViewAdapter(List<ScanResult> scanResults, WifiInfo wifiInfo)
    {
        this.wifiInfo = wifiInfo;
        this.scanResults = scanResults;
    }
    public class viewHolder extends RecyclerView.ViewHolder{
        private EditText SSIDEditText;
        private EditText statusEditText;
        private RelativeLayout ItemListXML;
        public viewHolder(final View view)
        {
            super(view);
            SSIDEditText = view.findViewById(R.id.ssid_textedit);
            statusEditText = view.findViewById(R.id.status_textedit);
            ItemListXML = view.findViewById(R.id.ItemListXML);
        }
    }
    @NonNull
    @Override
    public RecyclerViewAdapter.viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list,parent,false);
        return new viewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapter.viewHolder holder, int position) {
        String ssid = scanResults.get(position).SSID;
        String status = scanResults.get(position).capabilities;
        holder.SSIDEditText.setText(ssid);
        holder.statusEditText.setText(status);
        if(wifiInfo.getSSID().replaceAll("\"","").equals(scanResults.get(position).SSID))
        {
            holder.ItemListXML.setBackgroundColor(Color.parseColor("#add827"));
        }
        holder.statusEditText.setText(String.valueOf(wifiInfo.getHiddenSSID()).toUpperCase());

    }

    @Override
    public int getItemCount() {
        return scanResults.size();
    }
}

