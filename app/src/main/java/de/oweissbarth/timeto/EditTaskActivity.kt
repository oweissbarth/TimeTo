package de.oweissbarth.timeto

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import kotlinx.coroutines.flow.singleOrNull
import java.lang.Integer.parseInt

class EditTaskActivity : AppCompatActivity() {

    private lateinit var editTaskView: EditText

    private val taskViewModel: TaskViewModel by viewModels {
        TaskViewModelFactory()
    }

    private var task: Task? = null



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_todo)
        editTaskView = findViewById(R.id.edit_task_name)

        val intent = getIntent()
        if(intent.hasExtra("taskId")){
            val taskId = intent.getStringExtra("taskId")
            if(taskId != null){
                task = taskViewModel.getById(taskId)
                task?.let{editTaskView.setText(it.name)}
            }else{
                Log.e("EditTaskActivity", "Got invalid id")
            }
        }


        val button = findViewById<Button>(R.id.button_save)
        button.setOnClickListener {
            val replyIntent = Intent()
            if (TextUtils.isEmpty(editTaskView.text)) {
                setResult(Activity.RESULT_CANCELED, replyIntent)
            } else {
                val title = editTaskView.text.toString()
                replyIntent.putExtra(EXTRA_EDIT_TASK_RESULT, Result.RESULT_UPDATE)
                setResult(Activity.RESULT_OK, replyIntent)

                task = Task(editTaskView.text.toString())

                task?.let{
                    taskViewModel.insert(it)
                }
            }
            finish()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.edit_task_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem) = when(item.itemId) {
        R.id.action_delete -> {
            val replyIntent = Intent()
            replyIntent.putExtra(EXTRA_EDIT_TASK_RESULT, Result.RESULT_DELETE)
            setResult(Activity.RESULT_OK, replyIntent)
            task?.let { taskViewModel.delete(it.id) }
            task = null
            finish()
            true
        } else -> {
            super.onOptionsItemSelected(item)
        }
    }




    companion object {
        const val EXTRA_EDIT_TASK_RESULT = "de.oweissbarth.timeto.EDITTASKRESULT"
        enum class Result {
            RESULT_UPDATE,
            RESULT_CREATE,
            RESULT_DELETE
        }
    }
}