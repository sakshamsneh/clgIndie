package com.fileindie.saksham.clgindie.fragment;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.fileindie.saksham.clgindie.R;
import com.fileindie.saksham.clgindie.activity.LoginActivity;
import com.fileindie.saksham.clgindie.activity.MainActivity;
import com.fileindie.saksham.clgindie.activity.SignupActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

/**
 * Created by SAKSHIM on 21/03/2018.
 */

public class ProfileFragment extends Fragment {
    private Button btnChangeEmail, btnChangePassword, btnSendResetEmail, btnRemoveUser,
            changeEmail, changePassword, sendEmail, remove, signOut;

    private EditText oldEmail, newEmail, password, newPassword;
    private ProgressBar progressBar;
    private FirebaseAuth.AuthStateListener authListener;
    private FirebaseAuth auth;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View retView= inflater.inflate(R.layout.fragment_profile, container, false);
        final FragmentActivity fragmentBelongActivity = getActivity();
        if(retView!=null) {
            //get firebase auth instance
            auth = FirebaseAuth.getInstance();

            //get current user
            final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

            authListener = new FirebaseAuth.AuthStateListener() {
                @Override
                public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                    FirebaseUser user = firebaseAuth.getCurrentUser();
                    if (user == null) {
                        startActivity(new Intent(fragmentBelongActivity, LoginActivity.class));
                    }
                }
            };

            btnChangeEmail = (Button) retView.findViewById(R.id.change_email_button);
            btnChangePassword = (Button) retView.findViewById(R.id.change_password_button);
            btnSendResetEmail = (Button) retView.findViewById(R.id.sending_pass_reset_button);
            btnRemoveUser = (Button) retView.findViewById(R.id.remove_user_button);
            changeEmail = (Button) retView.findViewById(R.id.changeEmail);
            changePassword = (Button) retView.findViewById(R.id.changePass);
            sendEmail = (Button) retView.findViewById(R.id.send);
            remove = (Button) retView.findViewById(R.id.remove);
            signOut = (Button) retView.findViewById(R.id.sign_out);

            oldEmail = (EditText) retView.findViewById(R.id.old_email);
            newEmail = (EditText) retView.findViewById(R.id.new_email);
            password = (EditText) retView.findViewById(R.id.password);
            newPassword = (EditText) retView.findViewById(R.id.newPassword);

            oldEmail.setVisibility(View.GONE);
            newEmail.setVisibility(View.GONE);
            password.setVisibility(View.GONE);
            newPassword.setVisibility(View.GONE);
            changeEmail.setVisibility(View.GONE);
            changePassword.setVisibility(View.GONE);
            sendEmail.setVisibility(View.GONE);
            remove.setVisibility(View.GONE);

            progressBar = (ProgressBar) retView.findViewById(R.id.progressBar);

            if (progressBar != null) {
                progressBar.setVisibility(View.GONE);
            }
            btnChangeEmail.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    oldEmail.setVisibility(View.GONE);
                    newEmail.setVisibility(View.VISIBLE);
                    password.setVisibility(View.GONE);
                    newPassword.setVisibility(View.GONE);
                    changeEmail.setVisibility(View.VISIBLE);
                    changePassword.setVisibility(View.GONE);
                    sendEmail.setVisibility(View.GONE);
                    remove.setVisibility(View.GONE);
                }
            });

            changeEmail.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    progressBar.setVisibility(View.VISIBLE);
                    if (user != null && !newEmail.getText().toString().trim().equals("")) {
                        user.updateEmail(newEmail.getText().toString().trim())
                                .addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        if (task.isSuccessful()) {
                                            Toast.makeText(fragmentBelongActivity, "Email address is updated. Please sign in with new email id!", Toast.LENGTH_LONG).show();
                                            signOut();
                                            progressBar.setVisibility(View.GONE);
                                        } else {
                                            Toast.makeText(fragmentBelongActivity, "Failed to update email!", Toast.LENGTH_LONG).show();
                                            progressBar.setVisibility(View.GONE);
                                        }
                                    }
                                });
                    } else if (newEmail.getText().toString().trim().equals("")) {
                        newEmail.setError("Enter email");
                        progressBar.setVisibility(View.GONE);
                    }
                }
            });

            btnChangePassword.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    oldEmail.setVisibility(View.GONE);
                    newEmail.setVisibility(View.GONE);
                    password.setVisibility(View.GONE);
                    newPassword.setVisibility(View.VISIBLE);
                    changeEmail.setVisibility(View.GONE);
                    changePassword.setVisibility(View.VISIBLE);
                    sendEmail.setVisibility(View.GONE);
                    remove.setVisibility(View.GONE);
                }
            });

            changePassword.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    progressBar.setVisibility(View.VISIBLE);
                    if (user != null && !newPassword.getText().toString().trim().equals("")) {
                        if (newPassword.getText().toString().trim().length() < 6) {
                            newPassword.setError("Password too short, enter minimum 6 characters");
                            progressBar.setVisibility(View.GONE);
                        } else {
                            user.updatePassword(newPassword.getText().toString().trim())
                                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            if (task.isSuccessful()) {
                                                Toast.makeText(fragmentBelongActivity, "Password is updated, sign in with new password!", Toast.LENGTH_SHORT).show();
                                                signOut();
                                                progressBar.setVisibility(View.GONE);
                                            } else {
                                                Toast.makeText(fragmentBelongActivity, "Failed to update password!", Toast.LENGTH_SHORT).show();
                                                progressBar.setVisibility(View.GONE);
                                            }
                                        }
                                    });
                        }
                    } else if (newPassword.getText().toString().trim().equals("")) {
                        newPassword.setError("Enter password");
                        progressBar.setVisibility(View.GONE);
                    }
                }
            });

            btnSendResetEmail.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    oldEmail.setVisibility(View.VISIBLE);
                    newEmail.setVisibility(View.GONE);
                    password.setVisibility(View.GONE);
                    newPassword.setVisibility(View.GONE);
                    changeEmail.setVisibility(View.GONE);
                    changePassword.setVisibility(View.GONE);
                    sendEmail.setVisibility(View.VISIBLE);
                    remove.setVisibility(View.GONE);
                }
            });

            sendEmail.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    progressBar.setVisibility(View.VISIBLE);
                    if (!oldEmail.getText().toString().trim().equals("")) {
                        auth.sendPasswordResetEmail(oldEmail.getText().toString().trim())
                                .addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        if (task.isSuccessful()) {
                                            Toast.makeText(fragmentBelongActivity, "Reset password email is sent!", Toast.LENGTH_SHORT).show();
                                            progressBar.setVisibility(View.GONE);
                                        } else {
                                            Toast.makeText(fragmentBelongActivity, "Failed to send reset email!", Toast.LENGTH_SHORT).show();
                                            progressBar.setVisibility(View.GONE);
                                        }
                                    }
                                });
                    } else {
                        oldEmail.setError("Enter email");
                        progressBar.setVisibility(View.GONE);
                    }
                }
            });

            btnRemoveUser.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    progressBar.setVisibility(View.VISIBLE);
                    if (user != null) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(fragmentBelongActivity);
                        builder.setTitle("Delete User?");
                        builder.setMessage("Are you sure?")
                                .setCancelable(false)
                                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        user.delete()
                                                .addOnCompleteListener(new OnCompleteListener<Void>() {
                                                    @Override
                                                    public void onComplete(@NonNull Task<Void> task) {
                                                        if (task.isSuccessful()) {
                                                            Toast.makeText(fragmentBelongActivity, "Your profile is deleted:( Create a account now!", Toast.LENGTH_SHORT).show();
                                                            startActivity(new Intent(fragmentBelongActivity, SignupActivity.class));
                                                            progressBar.setVisibility(View.GONE);
                                                        } else {
                                                            Toast.makeText(fragmentBelongActivity, "Failed to delete your account!", Toast.LENGTH_SHORT).show();
                                                            progressBar.setVisibility(View.GONE);
                                                        }
                                                    }
                                                });
                                    }
                                })
                                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        dialog.cancel();
                                    }
                                });
                        AlertDialog alert = builder.create();
                        alert.show();
                    }
                }
            });

            signOut.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    signOut();
                }
            });
        }
        return retView;
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    public void signOut() {
        auth.signOut();
    }

    @Override
    public void onResume() {
        super.onResume();
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void onStart() {
        super.onStart();
        auth.addAuthStateListener(authListener);
    }

    @Override
    public void onStop() {
        super.onStop();
        if (authListener != null) {
            auth.removeAuthStateListener(authListener);
        }
    }
}
