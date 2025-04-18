package com.example.sqlite;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import java.util.List;

public class NotesAdapter extends BaseAdapter {
    private MainActivity context;
    private int layout;
    private List<NotesModel> noteList;

    public NotesAdapter(MainActivity context, int layout, List<NotesModel> noteList) {
        this.context = context;
        this.layout = layout;
        this.noteList = noteList;
    }

    private static class ViewHolder {
        TextView textViewNote;
        ImageView imageViewEdit;
        ImageView imageViewDelete;
    }

    @Override
    public int getCount() {
        return noteList.size();
    }

    @Override
    public Object getItem(int position) {
        return noteList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(layout, parent, false);

            viewHolder.textViewNote = convertView.findViewById(R.id.textViewNameNote);
            viewHolder.imageViewDelete = convertView.findViewById(R.id.imageViewDelete);
            viewHolder.imageViewEdit = convertView.findViewById(R.id.imageViewEdit);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        // Gán dữ liệu vào View
        NotesModel notes = noteList.get(position);
        viewHolder.textViewNote.setText(notes.getNameNote());

        // Sự kiện cập nhật ghi chú
        viewHolder.imageViewEdit.setOnClickListener(v -> {
            Toast.makeText(context, "Cập nhật: " + notes.getNameNote(), Toast.LENGTH_SHORT).show();
            context.ShowDialogUpdateNotes(notes.getIdNote(), notes.getNameNote());
        });

        viewHolder.imageViewDelete.setOnClickListener(v -> {
            new AlertDialog.Builder(context)
                    .setTitle("Xóa ghi chú")
                    .setMessage("Bạn có chắc chắn muốn xóa ghi chú này?")
                    .setPositiveButton("Xóa", (dialog, which) -> {
                        context.DeleteNote(notes.getNameNote(), notes.getIdNote()); // Truyền đúng 2 tham số
                    })
                    .setNegativeButton("Hủy", null)
                    .show();
        });


        return convertView;
    }
}
