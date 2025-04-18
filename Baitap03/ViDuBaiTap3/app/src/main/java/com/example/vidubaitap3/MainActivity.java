package com.example.vidubaitap3;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.ProgressBar;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    Button btnContextMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        ImageView img1= (ImageView)
                findViewById(R.id.imageView1);
        img1.setImageResource(R.drawable.kotlin);

        ConstraintLayout bg = findViewById(R.id.main);

//        random background
        ArrayList<Integer> arrayList = new ArrayList<>();
        arrayList.add(R.drawable.bg1);
        arrayList.add(R.drawable.bg2);
        arrayList.add(R.drawable.bg3);
        arrayList.add(R.drawable.bg4);
        Random random = new Random();
        int vitri = random.nextInt(arrayList.size());
        bg.setBackgroundResource(arrayList.get(vitri));

        //Imagebutton
        ImageButton img2 = (ImageButton)
                findViewById(R.id.imageButton1);
        img2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                img1.setImageResource(R.drawable.dart);
                img1.getLayoutParams().width=550;
                img1.getLayoutParams().height=550;
            }
        });

        //switch
        Switch sw = findViewById(R.id.switch1);
        sw.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){ //isChecked = true
                    Toast.makeText(MainActivity.this,"Wifi đang bật",Toast.LENGTH_LONG).show();
                }
                else{
                    Toast.makeText(MainActivity.this,"Wifi đang tắt",Toast.LENGTH_LONG).show();
                }

            }
        });

        //checkbox
        CheckBox ck1 = (CheckBox) findViewById(R.id.checkBox2);
        ck1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    bg.setBackgroundResource(R.drawable.bg3);
                }else{
                    bg.setBackgroundResource(R.drawable.bg4);
                }
            }
        });

        //RadioGroup
        RadioGroup radioGroup = findViewById(R.id.radioGroup1);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                //checkID trả về ID radio
                if(checkedId == R.id.radioButton){
                    bg.setBackgroundResource(R.drawable.bg3);
                }
                else {
                    bg.setBackgroundResource(R.drawable.bg4);
                }
            }
        });

        //progrebar
        ProgressBar progressBar = findViewById(R.id.progressBar2);
        img2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                int current = progressBar.getProgress();
//                progressBar.setProgress(current+10);
                //test 2
                CountDownTimer countDownTimer = new CountDownTimer(10000,1000) {
                    @Override
                    public void onTick(long millisUntilFinished) {
                        int current = progressBar.getProgress();
//chạy đều đều >100 quay về 0
                        if (current>= progressBar.getMax()){
                            current=0;
                        }
                        progressBar.setProgress(current + 10); //thiết lập progress
                    }

                    @Override
                    public void onFinish() {
                        Toast.makeText(MainActivity.this,"Hết giờ",Toast.LENGTH_LONG).show();
                    }
                };
                countDownTimer.start();
            }
        });

        //seek bar
        SeekBar seekBar = (SeekBar) findViewById(R.id.seekBar);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                //progress: giá trị của seekbar
                Log.d("AAA","Giá trị:" + progress);
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                Log.d("AAA","Start");
            }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                Log.d("AAA","Stop");
            }});

        // Kết nối Toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar); // Rất quan trọng!

        //popup menu
        Button btnButton = findViewById(R.id.btnPopMenu);
        btnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShowPopupMenu(v); // gọi khi button được nhấn
            }
        });

        //Context menu
        btnContextMenu = findViewById(R.id.btnContextMenu);
        registerForContextMenu(btnContextMenu);
        //Alert Dialog
        Button btnDeleteAlertDialog = findViewById(R.id.btnDeleteAlertDialog);
        btnDeleteAlertDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                XacNhanXoa(1);
            }
        });

        //dialog custom
        Button btnDialogCustom = findViewById(R.id.btnDialogCustom);
        btnDialogCustom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog1();
            }
        });

    }

    private void Dialog1() {
        Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_student);
        dialog.setCanceledOnTouchOutside(false);

        // Ánh xạ
        EditText editMSSV = dialog.findViewById(R.id.editMSSV);
        EditText editHoTen = dialog.findViewById(R.id.editHoTen);
        Button btnCapNhat = dialog.findViewById(R.id.btnCapNhat);

        // Xử lý nút CẬP NHẬT
        btnCapNhat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mssv = editMSSV.getText().toString();
                String hoten = editHoTen.getText().toString();
                Toast.makeText(MainActivity.this, "MSSV: " + mssv + "\nHọ tên: " + hoten, Toast.LENGTH_LONG).show();
                dialog.dismiss();
            }
        });

        dialog.show();
    }


    private void XacNhanXoa( final int vitri){
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle("Thông báo");
        alert.setMessage("Bạn có muốn đăng xuất không");
        alert.setPositiveButton("Có", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(MainActivity.this, "Bạn đã chọn co", Toast.LENGTH_LONG).show();
            }
        });
        alert.setNegativeButton("Không", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(MainActivity.this, "Bạn đã chọn khong", Toast.LENGTH_LONG).show();

            }
        });
        alert.show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_setting,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if(item.getItemId() == R.id.menuSetting){
            Toast.makeText(MainActivity.this, "Bạn chọn: Cài đặt", Toast.LENGTH_SHORT).show();

        }else if (item.getItemId() == R.id.menuShare){
            Toast.makeText(MainActivity.this, "Bạn chọn: menuShare", Toast.LENGTH_SHORT).show();

        }else if (item.getItemId() == R.id.menuLogout){
            Toast.makeText(MainActivity.this, "Bạn chọn: Đăng xuất", Toast.LENGTH_SHORT).show();

        }
        return super.onOptionsItemSelected(item);
    }

    private void ShowPopupMenu(View view){
        //popup menu
//        Button btnButton = findViewById(R.id.btnPopMenu);
        PopupMenu popupMenu = new PopupMenu(this,view);
        popupMenu.getMenuInflater().inflate(R.menu.menu_setting,popupMenu.getMenu());
//bắt sự kiện
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                if (item.getItemId() == R.id.menuSetting) {
                    // Lệnh xử lý Setting
                    Toast.makeText(MainActivity.this, "Bạn đang chọn Setting", Toast.LENGTH_LONG).show();

                } else if (item.getItemId() == R.id.menuShare) {
                    ((Button)view).setText("Chia sẻ");

                } else if (item.getItemId() == R.id.menuLogout) {
                    // Xử lý logout nếu có
                }

                return false;
            }
        });
        popupMenu.show();
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v,
                                    ContextMenu.ContextMenuInfo menuInfo) {
        getMenuInflater().inflate(R.menu.menu_setting,menu);
        menu.setHeaderTitle("Context Menu");
        menu.setHeaderIcon(R.mipmap.ic_launcher);
        super.onCreateContextMenu(menu, v, menuInfo);
    }

    //bắt sự kiện Context Menu
    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.menuSetting) {
            Toast.makeText(MainActivity.this, "Bạn đang chọn Setting", Toast.LENGTH_LONG).show();
            return true;
        } else if (id == R.id.menuShare) {
            btnContextMenu.setText("Chia sẻ");
            return true;
        } else if (id == R.id.menuLogout) {
            // Thực hiện hành động đăng xuất nếu cần
            return true;
        }

        return super.onContextItemSelected(item);
    }

}