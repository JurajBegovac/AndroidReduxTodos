package beg.hr.todos

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import beg.hr.todos.presentational_components.Todo
import beg.hr.todos.presentational_components.TodosAdapter
import beg.hr.todos.redux.Store
import beg.hr.todos.redux.toggleTodo
import kotlinx.android.synthetic.main.screen_main.*
import rx.Subscription
import java.util.function.Consumer

class MainActivity : AppCompatActivity() {

    lateinit var subscription: Subscription
    lateinit var store: Store

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.screen_main)

        store = (application as MyApp).store

        list.apply {
            setHasFixedSize(true)
            val linearLayoutManager = LinearLayoutManager(context)
            layoutManager = linearLayoutManager
            adapter = TodosAdapter()
        }

        add.setOnClickListener { }
        all.setOnClickListener { }
        completed.setOnClickListener { }
        active.setOnClickListener { }

    }

    override fun onResume() {
        super.onResume()
        subscription = store.observe()
                .subscribe({
                    state ->
                    val todos = state.todos
                            .map { todo ->
                                Todo(todo.id, todo.text, todo.completed, Consumer<Boolean> {
                                    store.dispatch(toggleTodo(todo.id))
                                })
                            }
                    (list.adapter as TodosAdapter).addTodos(todos)
                })
    }

    override fun onPause() {
        super.onPause()
        subscription.unsubscribe()
    }
}
