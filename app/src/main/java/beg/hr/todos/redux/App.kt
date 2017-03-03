package beg.hr.todos.redux

import beg.hr.todos.redux.VisibilityFilter.SHOW_COMPLETED
import rx.Subscription

/**
 * Created by juraj on 03/03/2017.
 */

fun main(args: Array<String>) {
    val store: Store = Store()
    println(store.state) // print init state
    val subscription: Subscription = store.observe().subscribe(::println)
    store.dispatch(addTodo("Learn about actions"))
    store.dispatch(addTodo("Learn about reducers"))
    store.dispatch(addTodo("Learn about store"))
    store.dispatch(toggleTodo(0))
    store.dispatch(toggleTodo(1))
    store.dispatch(setVisibilityFilter(SHOW_COMPLETED))
    subscription.unsubscribe()
}
