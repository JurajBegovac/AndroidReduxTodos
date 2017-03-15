package beg.hr.todos.presentational_components

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import beg.hr.todos.R
import beg.hr.todos.commons.inflate
import kotlinx.android.synthetic.main.item_todo.view.*
import java.util.*

/**
 * Created by juraj on 03/03/2017.
 */
class TodosAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var items: ArrayList<Todo> = ArrayList()

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        bindViewHolder(holder, items[position])
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder = TodosHolder(parent)

    fun bindViewHolder(holder: RecyclerView.ViewHolder, item: Todo) {
        holder as TodosHolder
        holder.bind(item)
    }

    class TodosHolder(parent: ViewGroup) : RecyclerView.ViewHolder(parent.inflate(R.layout.item_todo)) {
        fun bind(item: Todo) = with(itemView) {
            // todo handle click
            itemView.setOnClickListener { item.onClick.accept(true) }
            title.text = item.text
            completed.isChecked = item.completed
        }
    }

    fun addTodo(item: Todo) {
        val initPosition = if (items.size > 0) items.size - 1 else 0
        items.add(item)
        notifyItemRangeInserted(initPosition, items.size)
    }

    fun addTodos(todos: List<Todo>) {
        val initPosition = if (items.size > 0) items.size - 1 else 0
        items.addAll(todos)
        notifyItemRangeInserted(initPosition, items.size)
    }
}