package com.example.ex_001;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import de.hdodenhof.circleimageview.CircleImageView;

import android.view.Window;
import android.view.WindowManager;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    private CircleImageView profileImage;
    private TextView studentName, studentId, resultText;
    private EditText editTextInput;
    private Button btnReverse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Khởi tạo các view
        profileImage = findViewById(R.id.profile_image);
        studentName = findViewById(R.id.student_name);
        studentId = findViewById(R.id.student_id);
        editTextInput = findViewById(R.id.editTextInput);
        btnReverse = findViewById(R.id.btnReverse);
        resultText = findViewById(R.id.resultText);

        // Đặt dữ liệu tĩnh (bạn có thể thay đổi bằng dữ liệu động nếu cần)
        profileImage.setImageResource(R.drawable.img);
        studentName.setText("Trịnh Tấn Hào");
        studentId.setText("MSSV: 22110315");

        // Chế độ toàn màn hình
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        // Khởi tạo ArrayList kiểu số
        ArrayList<Integer> numbers = new ArrayList<>();

        // Thêm các số vào mảng
        numbers.add(1);
        numbers.add(2);
        numbers.add(3);
        numbers.add(4);
        numbers.add(5);
        numbers.add(6);
        numbers.add(7);
        numbers.add(8);
        numbers.add(9);
        numbers.add(10);

        // Duyệt qua mảng và phân loại số chẵn và số lẻ
        StringBuilder oddNumbers = new StringBuilder();
        StringBuilder evenNumbers = new StringBuilder();

        for (int number : numbers) {
            if (number % 2 == 0) {
                // Nếu là số chẵn
                evenNumbers.append(number).append(" ");
            } else {
                // Nếu là số lẻ
                oddNumbers.append(number).append(" ");
            }
        }

        // In ra Log các số lẻ và số chẵn
        Log.d(TAG, "Số chẵn: " + evenNumbers.toString());
        Log.d(TAG, "Số lẻ: " + oddNumbers.toString());

        // Đặt sự kiện cho Button
        btnReverse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String inputText = editTextInput.getText().toString();

                // Nếu chuỗi rỗng, yêu cầu nhập dữ liệu
                if (inputText.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Vui lòng nhập chuỗi", Toast.LENGTH_SHORT).show();
                } else {
                    // Đảo ngược chuỗi và chuyển thành chữ hoa
                    String reversedText = new StringBuilder(inputText).reverse().toString().toUpperCase();

                    // Hiển thị kết quả lên TextView và Toast
                    resultText.setText(reversedText);
                    Toast.makeText(MainActivity.this, reversedText, Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
