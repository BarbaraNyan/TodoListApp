package com.example.applicationtry

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.applicationtry.databinding.ItemTodoBinding
import com.example.applicationtry.model.TodoItem

class TodoAdapter(private val listener: OnItemClickListener): ListAdapter<TodoItem, TodoAdapter.TodoItemViewHolder> (TodoComparator()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoItemViewHolder {
        val binding = ItemTodoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TodoItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TodoItemViewHolder, position: Int) {
        val currentItem = getItem(position)
        holder.bind(currentItem.title)
    }

    inner class TodoItemViewHolder(private val binding: ItemTodoBinding): RecyclerView.ViewHolder(binding.root){
        init{
            binding.apply {
                cbDone.setOnClickListener {
                    val position = bindingAdapterPosition
                    if(position != RecyclerView.NO_POSITION){
                        val todo = getItem(position)
                        listener.onCheckBoxListener(todo, cbDone.isChecked)
                    }
                }
            }
        }
//        init {
//            binding.apply {
//                cbDone.setOnClickListener {
//                    val position = adapterPosition
//                    if (position!=RecyclerView.NO_POSITION){
//                        val todo = getItem(position)
//                        listener.onCheckBoxClick(todo, cbDone.isChecked)
//                    }
//                }
//            }
//        }

//        init{
//            binding.apply {
//                cbDone.setOnClickListener {
//
//                }
//            }
//        }

        private val todoItemTitle = binding.tvTodoTitle
        fun bind(text: String?){
            todoItemTitle.text = text
        }

//        companion object{
//            fun create(parent: ViewGroup): TodoItemViewHolder{
//                val b = ItemTodoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
//            }
//        }

//        private val todoItemView: TextView = itemView.findViewById(R.id.textView)
//
//        fun bind(text: String?) {
//            wordItemView.text = text
//        }
//
//        companion object {
//            fun create(parent: ViewGroup): WordViewHolder {
//                val view: View = LayoutInflater.from(parent.context)
//                    .inflate(R.layout.recyclerview_item, parent, false)
//                return WordViewHolder(view)
//            }
//        }
    }

    class TodoComparator: DiffUtil.ItemCallback<TodoItem>(){
        override fun areItemsTheSame(oldItem: TodoItem, newItem: TodoItem) =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: TodoItem, newItem: TodoItem) =
            oldItem == newItem
    }

    interface OnItemClickListener {
        fun onCheckBoxListener(todoItem: TodoItem, isChecked: Boolean)
        fun onItemDelete(todoItem: TodoItem)
    }


}