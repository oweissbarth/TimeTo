package de.oweissbarth.timeto

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import java.lang.Integer.parseInt

class EditTodo : AppCompatActivity() {

    private lateinit var editTaskView: EditText



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_todo)
        editTaskView = findViewById(R.id.edit_task_name)


        val button = findViewById<Button>(R.id.button_save)
        button.setOnClickListener {
            val replyIntent = Intent()
            if (TextUtils.isEmpty(editTaskView.text)) {
                setResult(Activity.RESULT_CANCELED, replyIntent)
            } else {
                val title = editTaskView.text.toString()
                replyIntent.putExtra(EXTRA_REPLY, title)
                setResult(Activity.RESULT_OK, replyIntent)
            }
            finish()
        }
    }




    companion object {
        const val EXTRA_REPLY = "com.example.android.wordlistsql.REPLY"
    }
}