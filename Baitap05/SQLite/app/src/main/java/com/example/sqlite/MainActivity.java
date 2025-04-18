package com.example.sqlite;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
import androidx.appcompat.widget.Toolbar;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    DatabaseHandler databaseHandler;
    ListView listView;
    ArrayList<NotesModel> arrayList;
    NotesAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Ánh xạ ListView
        listView = findViewById(R.id.listView1);
        arrayList = new ArrayList<>();
        adapter = new NotesAdapter(this, R.layout.row_notes, arrayList);
        listView.setAdapter(adapter);

        // Ánh xạ Toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar); // Kích hoạt Toolbar làm ActionBar

        // Khởi tạo database
        InitDatabaseSQLite();
        LoadDataFromSQLite();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

    }

    private void InitDatabaseSQLite() {
        databaseHandler = new DatabaseHandler(this, "notes.sqlite", null, 1);
        databaseHandler.QueryData("CREATE TABLE IF NOT EXISTS Notes(Id INTEGER PRIMARY KEY AUTOINCREMENT, NameNotes VARCHAR(200))");
    }

    private void LoadDataFromSQLite() {
        Cursor cursor = databaseHandler.GetData("SELECT * FROM Notes");
        arrayList.clear();
        while (cursor.moveToNext()) {
            int id = cursor.getInt(0);
            String name = cursor.getString(1);
            arrayList.add(new NotesModel(id, name));
        }
        cursor.close();
        adapter.notifyDataSetChanged();
    }

    // Khởi tạo Menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }




    // Xử lý sự kiện khi chọn item trong Menu
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.menuAddNotes) {
            ShowDialogAddNotes();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    // Hiển thị Dialog thêm Notes
    private void ShowDialogAddNotes() {
        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialog_add_note);

        EditText editTextName = dialog.findViewById(R.id.editTextName);
        Button buttonAdd = dialog.findViewById(R.id.buttonEdit);
        Button buttonCancel = dialog.findViewById(R.id.buttonHuy);

        buttonAdd.setOnClickListener(v -> {
            String noteName = editTextName.getText().toString().trim();
            if (!noteName.isEmpty()) {
                databaseHandler.QueryData("INSERT INTO Notes VALUES(null, '" + noteName + "')");
                LoadDataFromSQLite();
                Toast.makeText(MainActivity.this, "Đã thêm!", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            } else {
                Toast.makeText(MainActivity.this, "Vui lòng nhập tên!", Toast.LENGTH_SHORT).show();
            }
        });

        buttonCancel.setOnClickListener(v -> dialog.dismiss());

        dialog.show();



    }
    public void ShowDialogUpdateNotes(int noteId, String noteName) {
        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialog_update_note);

        EditText editTextName = dialog.findViewById(R.id.editTextName);
        Button buttonEdit = dialog.findViewById(R.id.buttonEdit);
        Button buttonHuy = dialog.findViewById(R.id.buttonHuy);

        editTextName.setText(noteName);

        buttonEdit.setOnClickListener(v -> {
            String updatedName = editTextName.getText().toString().trim();
            if (!updatedName.isEmpty()) {
                databaseHandler.QueryData("UPDATE Notes SET NameNotes = '" + updatedName + "' WHERE Id = " + noteId);
                LoadDataFromSQLite();
                Toast.makeText(MainActivity.this, "Cập nhật thành công!", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            } else {
                Toast.makeText(MainActivity.this, "Vui lòng nhập tên!", Toast.LENGTH_SHORT).show();
            }
        });

        buttonHuy.setOnClickListener(v -> dialog.dismiss());

        dialog.show();
    }
    // Hàm dialog xóa
    public void DeleteNote(String name, final int id) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Bạn có muốn xóa Notes \"" + name + "\" này không?");

        // Nút xác nhận xóa
        builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Thực hiện câu lệnh xóa trong database
                databaseHandler.QueryData("DELETE FROM Notes WHERE Id = '" + id + "'");

                // Hiển thị thông báo xóa thành công
                Toast.makeText(MainActivity.this, "Đã xóa Notes \"" + name + "\" thành công", Toast.LENGTH_SHORT).show();

                // Gọi hàm load lại danh sách dữ liệu sau khi xóa
                LoadDataFromSQLite();
            }
        });

        // Nút hủy bỏ xóa
        builder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss(); // Đóng hộp thoại khi người dùng từ chối xóa
            }
        });

        builder.show(); // Hiển thị hộp thoại
    }


}
