package com.example.registros;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.List;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;



public class CustomAdapterProfession extends  RecyclerView.Adapter<ViewHolderProfession> {
    ListActivityProfession listActivityProfession;
    List<ProfessionModel> mProfessionModelList;

    public CustomAdapterProfession (ListActivityProfession listActivityProfession, List<ProfessionModel> professionModelList) {
        this.listActivityProfession = listActivityProfession;
        this.mProfessionModelList = professionModelList;
    }

    @NonNull
    @Override
    public ViewHolderProfession onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.modelprofession, viewGroup, false);
        ViewHolderProfession viewHolder = new ViewHolderProfession(itemView);
        viewHolder.setOnClickListener(new ViewHolderProfession.ClickListener() {
            @Override
            public void onItemClick (View view,int position){
                String name = mProfessionModelList.get(position).getName();
                Toast.makeText(listActivityProfession, name, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onItemLongClick (View view,final int position){
                AlertDialog.Builder builder = new AlertDialog.Builder(listActivityProfession);
                String[] options = {"Actualizar datos", "Eliminar"};
                builder.setItems(options, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (which == 0) {
                            String id = mProfessionModelList.get(position).getId();
                            String name = mProfessionModelList.get(position).getName();
                            String birthday = mProfessionModelList.get(position).getBirthday();
                            String gender = mProfessionModelList.get(position).getGender();
                            String address = mProfessionModelList.get(position).getAddress();
                            String consultingoom = mProfessionModelList.get(position).getConsultingoom();
                            String specialty = mProfessionModelList.get(position).getSpecialty();
                            String phone = mProfessionModelList.get(position).getPhone();
                            String professionalid = mProfessionModelList.get(position).getProfessionalid();


                            Intent actualizarDatos = new Intent(listActivityProfession, ActivityProfession.class);
                            actualizarDatos.putExtra("updateId", id);
                            actualizarDatos.putExtra("updateName", name);
                            actualizarDatos.putExtra("updateBirthday", birthday);
                            actualizarDatos.putExtra("updateGender", gender);
                            actualizarDatos.putExtra("updateAddress", address);
                            actualizarDatos.putExtra("updateConsultingoom", consultingoom);
                            actualizarDatos.putExtra("updateSpecialty", specialty);
                            actualizarDatos.putExtra("updatePhone", phone);
                            actualizarDatos.putExtra("updateProfessionalid", professionalid);

                            listActivityProfession.startActivity(actualizarDatos);
                            // String id, String nombre, String apaterno, String amaterno, String sexo, String direccion, String facebook, String instagram
                        }

                        if (which == 1) {
                            listActivityProfession.eliminarRegistro(position);
                        }
                    }
                }).create().show();
            }
        });
        return viewHolder;
    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolderProfession viewHolderProfession, int i) {
        viewHolderProfession.tvName.setText(
                mProfessionModelList.get(i).getName()

        );
    }


    @Override
    public int getItemCount() {
        return mProfessionModelList.size();
    }
}