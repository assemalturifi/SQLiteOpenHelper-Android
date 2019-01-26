package com.example.assemalturifi.databaseapp3;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";


    private Button createStudent;
    private TextView tvSharedPreff;
    private EditText etSharedPref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate: starts");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //step4
        bindViews();

        //step35
        countRecords();
        readRecords();

        //step5
        createStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Context context = v.getRootView().getContext();

                LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                final View formElementsView = inflater.inflate(R.layout.student_form, null, false);

                final EditText editTextStudentFirstname = formElementsView.findViewById(R.id.editTextStudentFirstname);
                final EditText editTextStudentEmail =formElementsView.findViewById(R.id.editTextStudentEmail);

                new AlertDialog.Builder(context)
                        .setView(formElementsView)
                        .setTitle("Create Student")
                        .setPositiveButton("Add",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {

                                        dialog.cancel();
                                        String studentFirstname = editTextStudentFirstname.getText().toString();
                                        String studentEmail = editTextStudentEmail.getText().toString();

                                        //step34
                                        Student student = new Student();
                                        student.name= studentFirstname;
                                        student.email= studentEmail;
                                        boolean createSuccessful = new TableControllerStudent(context).create(student);
                                        if(createSuccessful){
                                            Toast.makeText(context, "Student information was saved.", Toast.LENGTH_SHORT).show();
                                        }else{
                                            Toast.makeText(context, "Unable to save student information.", Toast.LENGTH_SHORT).show();
                                        }
                                        countRecords();
                                        ((MainActivity) context).readRecords();


                                    }


                                }).show();
            }
        });




        Log.d(TAG, "onCreate: ends");
    }

    //step3
    public void bindViews(){
        createStudent = findViewById(R.id.create_student);
        tvSharedPreff = findViewById(R.id.tvSharedPref);
        etSharedPref = findViewById(R.id.etSharedPref);
    }




    //step6
    public void onSharedpref(View view) {
        SharedPreferences sharedPreferences = getSharedPreferences("MySharedPref", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        switch (view.getId()) {
            case R.id.saveData:
                editor.putString("editText", etSharedPref.getText().toString());
                editor.apply();
                Toast.makeText(this, "Saved", Toast.LENGTH_SHORT).show();
                break;

            case R.id.getData:
                String etValue = sharedPreferences.getString("editText", "Default String");
                tvSharedPreff.setText(etValue);
                Toast.makeText(this,"Data retrieved",Toast.LENGTH_SHORT).show();;
                break;

        }
    }
    //step26
    public void countRecords() {
        int recordCount = new TableControllerStudent(this).count();
        TextView textViewRecordCount = (TextView) findViewById(R.id.recordCount);
        if(recordCount==0){
            textViewRecordCount.setText("No records.");
        }
        else if(recordCount==1){
            textViewRecordCount.setText(recordCount + " record found.");
        }
        else {
            textViewRecordCount.setText(recordCount + " records found.");
        }

    }

    //step27
    public void readRecords() {

        LinearLayout linearLayoutRecords = (LinearLayout) findViewById(R.id.linearLayoutRecords);
        linearLayoutRecords.removeAllViews();

        List<Student> students = new TableControllerStudent(this).read();

        if (students.size() > 0) {

            for (Student obj : students) {

                int id = obj.id;
                String studentFirstname = obj.name;
                String studentEmail = obj.email;

                String textViewContents = studentFirstname + " - " + studentEmail;

                TextView textViewStudentItem= new TextView(this);
                textViewStudentItem.setPadding(0, 10, 0, 10);
                textViewStudentItem.setText(textViewContents);
                textViewStudentItem.setTag(Integer.toString(id));

                linearLayoutRecords.addView(textViewStudentItem);

                //ste32
                textViewStudentItem.setOnLongClickListener(new OnLongClickListenerStudentrecord());
            }

        }

        else {

            TextView locationItem = new TextView(this);
            locationItem.setPadding(8, 8, 8, 8);
            locationItem.setText("No records yet.");

            linearLayoutRecords.addView(locationItem);
        }

    }
}
