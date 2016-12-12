package id.sacredgeeks.etherealv2.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import id.sacredgeeks.etherealv2.Model.Jadwal;
import id.sacredgeeks.etherealv2.R;

/**
 * Created by SacredGeeks on 12/2/2016.
 */

public class JadwalAdapter extends ArrayAdapter<Jadwal> {

    private final Context context;
    private final ArrayList<Jadwal> itemsArrayList;

    public JadwalAdapter(Context context, ArrayList<Jadwal> itemsArrayList) {
        super(context, R.layout.row_jadwal, itemsArrayList);

        this.context = context;
        this.itemsArrayList = itemsArrayList;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        // 1. Creat e inflater
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        // 2. Get rowView from inflater
        View rowView = inflater.inflate(R.layout.row_jadwal, parent, false);

        // 3. Get the two text view from the rowView
        TextView namaJadwal = (TextView) rowView.findViewById(R.id.namaJadwal);
        TextView deadlineJadwal = (TextView) rowView.findViewById(R.id.deadlineJadwal);

        // 4. Set the text for textView
        namaJadwal.setText(itemsArrayList.get(position).getNamajadwal());
        deadlineJadwal.setText(itemsArrayList.get(position).getAlarm());

        // 5. retrn rowView
        return rowView;
    }


}
