package com.winds.todork.view.Adapters;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import com.winds.todork.Entity.TODO;
import com.winds.todork.R;
import com.winds.todork.Utils.SortNotes;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Created by Ramakrishna on 18-Aug-20.
 */


public class TodoAdapter extends RecyclerView.Adapter<TodoAdapter.ViewHolder> implements Filterable {

    List<TODO> notes = new ArrayList<>();
    private onItemClickListner listner;
    private List<TODO> notesListFiltered  = new ArrayList<>();;

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.note_item, viewGroup, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.bind(i);

    }

    @Override
    public int getItemCount() {
        return notesListFiltered.size();
    }


    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String charString = charSequence.toString();
                if (charString.isEmpty()) {
                    notesListFiltered = notes;
                } else {
                    List<TODO> filteredList = new ArrayList<>();
                    for (TODO row : notes) {

                        // name match condition. this might differ depending on your requirement
                        // here we are looking for name or phone number match
                        if (row.getTitle().toLowerCase().contains(charString.toLowerCase()) || row.getDescription().contains(charSequence)) {
                            filteredList.add(row);
                        }
                    }

                    notesListFiltered = filteredList;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = notesListFiltered;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                notesListFiltered = (ArrayList<TODO>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }




    public void setNotes(List<TODO> notes) {
        this.notes = notes;
        this.notesListFiltered = notes;
        notifyDataSetChanged();
    }







    public class ViewHolder extends RecyclerView.ViewHolder {

        private   TextView title;
        private   TextView description;
        private CheckBox cbSelect;
        private CardView rlItem;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            rlItem= itemView.findViewById(R.id.rlItem);
            title = itemView.findViewById(R.id.note_item_title);
            description = itemView.findViewById(R.id.note_item_description);
            cbSelect = itemView.findViewById(R.id.cbSelect);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    if (listner != null && position != RecyclerView.NO_POSITION) {
                        listner.onItemClick(notesListFiltered.get(position));
                    }
                }
            });


        }

        public void bind(int position) {


            if(notesListFiltered.get(position).isChecked()){
                rlItem.setBackgroundColor(Color.parseColor("#A9A9A9"));

            }else{
                rlItem.setBackgroundColor(Color.WHITE);

            }
            //in some cases, it will prevent unwanted situations
            cbSelect.setOnCheckedChangeListener(null);


            //if true, your checkbox will be selected, else unselected
            cbSelect.setChecked(notesListFiltered.get(position).isChecked());

            title.setText(notesListFiltered.get(position).getTitle());
            description.setText(notesListFiltered.get(position).getDescription());
            cbSelect.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    //set your object's last status
                    int position = getAdapterPosition();
                    notesListFiltered.get(position).setChecked(isChecked);
                    Collections.sort(notesListFiltered,new SortNotes());
                    notifyDataSetChanged();

                }
            });
        }
    }

    public TODO getNoteAt(int position) {
        return notesListFiltered.get(position);
    }









public interface onItemClickListner {
        void onItemClick(TODO note);
    }

    public void setOnItemClickListner(onItemClickListner listner) {
        this.listner = listner;
    }
}
