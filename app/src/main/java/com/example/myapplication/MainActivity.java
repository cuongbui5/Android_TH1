package com.example.myapplication;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Message;
import android.provider.MediaStore;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener,
                                                               View.OnClickListener,
                                                               RadioGroup.OnCheckedChangeListener{
    String[] citys = { "Hà Nội", "Nam Định",
            "Nghệ An", "Hồ Chính Minh",
            "Đà Nẵng", "Bình Dương" };
    private EditText editName,editPhone;
    private ListView lvUser;
    private Spinner spinnerCity;
    private Button btnAdd,btnChooseImg;
    private RadioGroup radioGroup;
    private ImageView ivUser;
    private String gender;
    private String city;
    private final List<User> users=new ArrayList<>();
    private UserAdapter userAdapter;
    private Uri img;
    private ActivityResultLauncher<Intent> galleryLauncher;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        galleryLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                        // Xử lý ảnh đã chọn
                        Intent data = result.getData();
                        Uri selectedImageUri = data.getData();

                        // Hiển thị ảnh lên ImageView
                        if (selectedImageUri != null) {
                            img=selectedImageUri;
                            ivUser.setImageURI(selectedImageUri);
                        }
                    }
                });

    }

    private void initViews() {
        editName=findViewById(R.id.editName);
        editPhone=findViewById(R.id.editPhone);
        lvUser=findViewById(R.id.lvUser);
        btnAdd=findViewById(R.id.btnAdd);
        radioGroup=findViewById(R.id.rgGender);
        spinnerCity=findViewById(R.id.spinnerCity);
        btnChooseImg=findViewById(R.id.btnChooseImg);
        ivUser=findViewById(R.id.ivUser);
        ArrayAdapter ad = new ArrayAdapter(
                this,
                android.R.layout.simple_spinner_item,
                citys);

        spinnerCity.setAdapter(ad);
        spinnerCity.setOnItemSelectedListener(this);
        btnAdd.setOnClickListener(this);
        userAdapter=new UserAdapter(this,users);
        radioGroup.setOnCheckedChangeListener(this);

        lvUser.setAdapter(userAdapter);

        btnChooseImg.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            galleryLauncher.launch(intent);
        });

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        city=citys[position];


    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    public void onClick(View v) {
        String name=editName.getText().toString().trim();
        String phone=editPhone.getText().toString().trim();
        if(name.equals("") || phone.equals("") || Objects.equals(gender, "") || Objects.equals(city, "")|| img == null){
            return;
        }
        User user=new User(name,phone,gender,city, img);
        users.add(user);
        userAdapter.notifyDataSetChanged();
        editName.setText("");
        editPhone.setText("");
        ivUser.setImageURI(null);




    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        RadioButton radioButton=findViewById(checkedId);
        gender=radioButton.getText().toString();

    }
}