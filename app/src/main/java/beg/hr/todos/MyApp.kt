package beg.hr.todos

import android.app.Application
import beg.hr.todos.redux.Store
import beg.hr.todos.redux.addTodo

/**
 * Created by juraj on 08/03/2017.
 */
class MyApp : Application() {

    lateinit var store: Store

    override fun onCreate() {
        super.onCreate()
        store = Store()
        store.dispatch(addTodo("Learn about actions"))
        store.dispatch(addTodo("Learn about reducers"))
        store.dispatch(addTodo("Learn about store"))
    }

}