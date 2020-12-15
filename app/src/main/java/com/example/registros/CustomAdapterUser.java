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
import androidx.recyclerview.widget.RecyclerView.ViewHolder;
public class CustomAdapterUser extends RecyclerView.Adapter<ViewHolderUser> {
    ListActivityUser listActivityUser;
    List<UserModel> mUserModelList;
    public CustomAdapterUser(ListActivityUser listActivityUser, List<UserModel> userModelList) {
        this.listActivityUser= listActivityUser;
        this.mUserModelList = userModelList;
    }
    @NonNull
    @Override
    public ViewHolderUser onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.model_user, viewGroup, false);
        ViewHolderUser viewHolder = new ViewHolderUser(itemView);
        viewHolder.setOnClickListener(new ViewHolderUser.ClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                String user = mUserModelList.get(position).getUser();
                String mail = mUserModelList.get(position).getMail();
                String phone = mUserModelList.get(position).getPhone();
                Toast.makeText(listActivityUser, user + " " + mail + " " + phone, Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onItemLongClick(View view, final int position) {
                AlertDialog.Builder builder = new AlertDialog.Builder(listActivityUser);
                String[] options = {"Actualizar datos", "Eliminar"};
                builder.setItems(options, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (which == 0) {
                            String id = mUserModelList.get(position).getId();
                            String user = mUserModelList.get(position).getUser();
                            String password = mUserModelList.get(position).getPassword();
                            String phone = mUserModelList.get(position).getPhone();
                            String mail = mUserModelList.get(position).getMail();
                            String family = mUserModelList.get(position).getFamily();
                            String proceedings = mUserModelList.get(position).getProceedings();
                            String allergies = mUserModelList.get(position).getAllergies();
                            String status = mUserModelList.get(position).getStatus();
                            Intent actualizarDatos = new Intent(listActivityUser, MainActivity.class);
                            actualizarDatos.putExtra("updateId", id);
                            actualizarDatos.putExtra("updateUser", user);
                            actualizarDatos.putExtra("updatePassword", password);
                            actualizarDatos.putExtra("updatePhone", phone);
                            actualizarDatos.putExtra("updateMail", mail);
                            actualizarDatos.putExtra("updateFamily", family);
                            actualizarDatos.putExtra("updateProceedings", proceedings);
                            actualizarDatos.putExtra("updateAllergies", allergies);
                            actualizarDatos.putExtra("updateStatus", status);
                            listActivityUser.startActivity(actualizarDatos);
                            // String id, String user, String password, String phone, String mail, String family, String proceedings, String allergies, Strinf status
                        }
                        if (which == 1) {
                            listActivityUser.eliminarRegistro(position);
                        }
                    }
                }).create().show();
            }
        });
        return viewHolder;
    }
    @Override
    public void onBindViewHolder(@NonNull ViewHolderUser viewHolderUser, int i) {
        viewHolderUser.tvUser.setText(
                mUserModelList.get(i).getUser()
                        + " " + mUserModelList.get(i).getMail()
                        + " " + mUserModelList.get(i).getPhone()
        );
    }

    @Override
    public int getItemCount() {
        return mUserModelList.size();
    }
}