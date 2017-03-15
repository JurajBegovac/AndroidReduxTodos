package beg.hr.todos.redux

import beg.hr.todos.redux.VisibilityFilter.SHOW_COMPLETED
import rx.functions.Func2
import rx.subjects.PublishSubject

/**
 * Created by juraj on 14/03/2017.
 */

// create our stream as a subject so arbitrary data can be sent on the stream
val action: PublishSubject<Action> = PublishSubject.create()

// Initial State
val initState: State = State.INITIAL_STATE

// Redux reducer
val reducer: Func2<State, Action, State> =
        Func2 { state, action -> State(visibilityFilter(state.visibilityFilter, action), todos(state.todos, action)) }

// Reduxification
val store = action.scan(initState, reducer).share()

// Higher order function to send actions to the stream
fun actionDispatcher(func: () -> Action) {
    action.onNext(func())
}

// Example action function
fun addTodo2(text: String) = actionDispatcher { Action(ActionTypes.ADD_TODO, AddPayload(nextTodoId++, text)) }

fun toggleTodo2(id: Int) = actionDispatcher { Action(ActionTypes.TOGGLE_TODO, id) }

fun setVisibilityFilter2(filter: VisibilityFilter) = actionDispatcher { Action(ActionTypes.SET_VISIBILITY_FILTER, filter) }

fun main(args: Array<String>) {
    val subscription = store.subscribe { println("1 " + it) }
    val subscription2 = store.subscribe { println("2 " + it) }
    addTodo2("Learn about actions")
    addTodo2("Learn about reducers")
    toggleTodo2(0)
    toggleTodo2(1)
    setVisibilityFilter2(SHOW_COMPLETED)
    subscription.unsubscribe()
    addTodo2("Test")
    subscription2.unsubscribe()
}