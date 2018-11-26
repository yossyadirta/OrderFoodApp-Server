package com.example.yossy.orderfoodserver;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.yossy.orderfoodserver.Common.Common;
import com.example.yossy.orderfoodserver.Model.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.rengwuxian.materialedittext.MaterialEditText;

import info.hoang8f.widget.FButton;

public class SignIn extends AppCompatActivity {

    EditText edtNoAdmin, edtPassword;
    Button btnSignIn;

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference table_user = database.getReference("User");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        edtPassword = (MaterialEditText)findViewById(R.id.edtPassword);
        edtNoAdmin = (MaterialEditText)findViewById(R.id.edtNoAdmin);
        btnSignIn = (FButton)findViewById(R.id.btnSignIn);

        database = FirebaseDatabase.getInstance();
        table_user = database.getReference("User");

        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signInUser(edtNoAdmin.getText().toString(),edtPassword.getText().toString());
            }
        });

    }

    private void signInUser(final String noAdmin, String password) {
        final ProgressDialog mDialog = new ProgressDialog(SignIn.this);
        mDialog.setMessage("Please wait...");
        mDialog.show();

        final String localNoAdmin = noAdmin;
        final String localPassword = password;

        table_user.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
           if (dataSnapshot.child(localNoAdmin).exists())
           {
               mDialog.dismiss();
               User user = dataSnapshot.child(localNoAdmin).getValue(User.class);
               user.setNoAdmin(localNoAdmin);
               if(Boolean.parseBoolean(user.getIsAdmin()))
               {
                   if(user.getPassword().equals(localPassword))
                   {
                       Intent login = new Intent(SignIn.this,Home.class);
                       Common.currentUser = user;
                       startActivity(login);
                   }
                   else
                       Toast.makeText(SignIn.this,"Wrong Password",Toast.LENGTH_SHORT).show();
               }
               else
                   Toast.makeText(SignIn.this,"Only login with Admin account",Toast.LENGTH_SHORT).show();
           }
           else
           {
               mDialog.dismiss();
               Toast.makeText(SignIn.this,"User not exist",Toast.LENGTH_SHORT).show();

           }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
