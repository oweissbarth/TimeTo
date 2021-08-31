package de.oweissbarth.timeto

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.ListAdapter

class TaskListAdapter(private val listener: OnItemClickListener): ListAdapter<Task, TaskListAdapter.TaskViewHolder>(TaskComparator()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val view: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.recyclerview_item, parent, false)
        return TaskViewHolder(view)
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        val current = getItem(position)
        holder.bind(current.name)

        //holder.
    }


    inner class TaskViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {
        private val taskItemView: TextView = itemView.findViewById(R.id.textView)


        fun bind(text: String?) {
            taskItemView.text = text
        }

        init {
            itemView.setTag(this)
            itemView.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            val position = adapterPosition
            if (position != RecyclerView.NO_POSITION) {
                listener.onItemClick(position)
            }
        }

    }

    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }

    class TaskComparator : DiffUtil.ItemCallback<Task>() {
        override fun areItemsTheSame(oldItem: Task, newItem: Task): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: Task, newItem: Task): Boolean {
            Log.d("TaskListAdapter", "Checking if contents of task: ${oldItem.name} (${oldItem.id}) and  task: ${newItem.name} ($newItem.id) are the same.")
            return oldItem.id == newItem.id && oldItem.name == newItem.name
        }
    }
}
