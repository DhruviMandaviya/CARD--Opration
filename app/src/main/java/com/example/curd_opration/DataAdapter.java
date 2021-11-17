package com.example.curd_opration;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class DataAdapter extends RecyclerView.Adapter<DataAdapter.ViewHolder> {
    private Context context;
    private ArrayList<DataModelClass> list;
    private OnEditListener onEditListener;

    public DataAdapter(Context context,ArrayList<DataModelClass> list,OnEditListener onEditListener) {
        this.context = context;
        this.list = list;
        this.onEditListener = onEditListener;
    }




    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent,int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.row_rc,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder,int position) {
        DataModelClass dateObj = list.get(position);
        holder.tv_name.setText(dateObj.getName());
        holder.tv_dec.setText(dateObj.getDec());
        holder.imgDelete.setOnClickListener(v->{
            list.remove(position);
            notifyDataSetChanged();
        });
        holder.imgEdit.setOnClickListener(v->{
            onEditListener.onEditClick(list.get(position),position);
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tv_name,tv_dec;
        ImageView imgEdit,imgDelete;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_name=itemView.findViewById(R.id.nameTxtId);
            tv_dec=itemView.findViewById(R.id.decTxtId);
            imgEdit=itemView.findViewById(R.id.imgEdit);
            imgDelete=itemView.findViewById(R.id.imgDelete);
        }
    }

    public void editData(DataModelClass listDataObj,int currentPosition){
        list.get(currentPosition).setDec(listDataObj.getDec());
        list.get(currentPosition).setName(listDataObj.getName());
        notifyDataSetChanged();
    }

    public interface OnEditListener{

        void onEditClick(DataModelClass listCurrentData,int CurrentPosition);

    }
}
