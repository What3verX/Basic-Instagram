package com.osmanlioglu.javainstagramclone.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.firebase.Timestamp;
import com.osmanlioglu.javainstagramclone.databinding.RecyclerItemBinding;
import com.osmanlioglu.javainstagramclone.models.postModel;

import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class postAdapter extends RecyclerView.Adapter<postAdapter.postViewHolder> {

         private ArrayList<postModel> arrayList;

    public postAdapter(ArrayList<postModel> arrayList) {
        this.arrayList = arrayList;
    }

    @NonNull
        @Override
        public postViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            RecyclerItemBinding recyclerItemBinding = RecyclerItemBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false);
            //inflate etmek: XML dosyasındaki görünümü java koduyla okunabilir hale getirmek.
            return new postViewHolder(recyclerItemBinding);
            //Burada recyclerView'ın itemi için oluşturduğumuz görünümü java kodunda okunabilir hale getirip return ettik.
        }

        @Override
        public void onBindViewHolder(@NonNull postViewHolder holder, int position) {
        holder.recyclerItemBinding.emailText.setText(arrayList.get(position).email);

        Context context = holder.itemView.getContext();


            Glide.with(context)
                    .load(arrayList.get(position).downloadUrl)
                    .into(holder.recyclerItemBinding.imageView2);
        holder.recyclerItemBinding.explainationText.setText(arrayList.get(position).explaination);

            Timestamp timestamp = arrayList.get(position).timestamp;
            long timeInMillis = timestamp.getSeconds();

            Date date = new Date(timeInMillis*1000);
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.getDefault());
            String dateString = dateFormat.format(date);
            holder.recyclerItemBinding.dateView.setText(dateString);


        }

        @Override
        public int getItemCount() {
            return arrayList.size();
        }

        public class postViewHolder extends RecyclerView.ViewHolder {

        RecyclerItemBinding recyclerItemBinding; //kullanacağımız item türünden bir değişken oluşturduk.

        public postViewHolder(RecyclerItemBinding recyclerItemBinding) { //Çağırıldığında o türde bir değişken isteyecek.
            super(recyclerItemBinding.getRoot()); //getRoot ile değişkenimizin görünümünü bağladık.
            this.recyclerItemBinding=recyclerItemBinding; //istediği değişken olarak oluşturduğumuz değişkeni verdik.
        }
    }







    }
