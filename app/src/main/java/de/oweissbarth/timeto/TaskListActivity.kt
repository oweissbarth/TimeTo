package de.oweissbarth.timeto

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class TaskListActivity : AppCompatActivity(), TaskListAdapter.OnItemClickListener{

    private val taskViewModel: TaskViewModel by viewModels {
        TaskViewModelFactory()
    }





    val startForResult = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            result: ActivityResult ->
        if (result.resultCode == Activity.RESULT_OK) {

        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_todo_list)

        val fab: View = findViewById(R.id.addTodoButton)
        fab.setOnClickListener{
            openActivityForResult()
        }

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerview)
        val adapter = TaskListAdapter(this)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        taskViewModel.allTasks.observeForever( Observer {
                tasks ->
            Log.d("TaskListActivity", "list data updated")
            tasks?.let{Log.d("TaskListActivity", it.toString())}
            tasks?.let{adapter.submitList(it.toList())}})

    }


    fun openActivityForResult() {
        startForResult.launch(Intent(this, EditTaskActivity::class.java))
    }

    override fun onItemClick(position: Int) {
        val task = taskViewModel.allTasks.value?.get(position)
        if(task != null) {
            intent = Intent(this, EditTaskActivity::class.java)
            intent.putExtra("taskId", task?.id)
            startForResult.launch(intent)
        }
    }
}