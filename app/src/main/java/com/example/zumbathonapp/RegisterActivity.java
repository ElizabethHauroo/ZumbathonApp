package com.example.zumbathonapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class RegisterActivity extends AppCompatActivity {

    EditText fullName,email,password,zinID;
    Button registerBtn,goToLogin;
    boolean valid = true;
    FirebaseAuth fAuth;
    FirebaseFirestore fStore;
    CheckBox isInstructorBox, isParticipantBox;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();

        fullName = findViewById(R.id.registerName);
        email = findViewById(R.id.registerEmail);
        password = findViewById(R.id.registerPassword);
        zinID = findViewById(R.id.registerZinID);
        registerBtn = findViewById(R.id.registerBtn);
        goToLogin = findViewById(R.id.gotoLogin);

        isParticipantBox = findViewById(R.id.isParticipant);
        isInstructorBox = findViewById(R.id.isInstructor);

        //checkbox logic, only one should be selected at a time
        isParticipantBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(compoundButton.isChecked()){
                    isInstructorBox.setChecked(false);
                }
            }
        });

        isInstructorBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(compoundButton.isChecked()) {
                    isParticipantBox.setChecked(false);
                }
            }
        });



        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                checkField(fullName);
                checkField(email);
                checkField(password);
                if(isInstructorBox.isChecked()) {
                    checkField(zinID);
                }

                //checkbox verification
                if(!(isInstructorBox.isChecked() || isParticipantBox.isChecked())){
                    Toast.makeText(RegisterActivity.this, "Select User Type", Toast.LENGTH_SHORT).show();
                    return;
                }

                if(valid){
                    // start the user registration
                    fAuth.createUserWithEmailAndPassword(email.getText().toString(), password.getText().toString()).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                        @Override
                        public void onSuccess(AuthResult authResult) {

                            FirebaseUser user = fAuth.getCurrentUser();
                            Toast.makeText(RegisterActivity.this, "Account Created", Toast.LENGTH_SHORT).show();
                            //once the account is created, I want to store the other fields to firebase
                            DocumentReference df = fStore.collection("Users").document(user.getUid());
                            Map<String, Object> userInfo = new HashMap<>();
                            //the key is the string used to identify a value/field in db
                            userInfo.put("FullName", fullName.getText().toString());
                            userInfo.put("UserEmail", email.getText().toString());
                            //if instructor is selected, save this too
                            // userInfo.put("ZinID", zinID.getText().toString());
                            if(isInstructorBox.isChecked()){
                                //checkField(zinID);
                                userInfo.put("isInstructor", "1");
                                userInfo.put("Account", "Instructor");
                                userInfo.put("Price", 15);

                            }
                            if(isParticipantBox.isChecked()){
                                userInfo.put("isParticipant", "1");
                                userInfo.put("account", "Participant");
                                userInfo.put("Price", 20);
                            }
                            //specify if the user is admin, instructor or participant
                            userInfo.put("isUser", "1");
                            //save to firestore
                            df.set(userInfo); //can add success listener if I need to debug


                            startActivity(new Intent(getApplicationContext(), HomeActivity.class));
                            finish(); //not allowing them to go back
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(RegisterActivity.this, "Failed to create Account", Toast.LENGTH_SHORT).show();
                        }
                    });

                }//user reg

            }
        });
        goToLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
            }
        });




    } //OnCreate

    public boolean checkField(EditText textField){
        if(textField.getText().toString().isEmpty()){
            textField.setError("Error");
            valid = false;
        }else {
            valid = true;
        }

        return valid;
    }
}