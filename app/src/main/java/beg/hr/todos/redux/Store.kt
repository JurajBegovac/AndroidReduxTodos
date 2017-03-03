package beg.hr.todos.redux

import rx.Observable
import rx.subjects.BehaviorSubject

/**
 * Created by juraj on 03/03/2017.
 */

// ========== STATE =============

data class State(val visibilityFilter: VisibilityFilter, val todos: List<Todo>) {
    companion object {
        val INITIAL_STATE: State = State(VisibilityFilter.SHOW_ALL, emptyList())
    }
}

data class Todo(val text: String, val completed: Boolean)

// ============ STORE ============
/**
 * The Store is the object that brings actions and reducers together.
 * The store has the following responsibilities:
 * Holds application state;
 * Allows access to state via getState();
 * Allows state to be updated via dispatch(action);
 * Registers listeners via subscribe(listener);
 * Handles unregistering of listeners via the function returned by subscribe(listener)
 *
 * It's important to note that you'll only have a single store in a Redux application.
 * When you want to split your data handling logic, you'll use reducer composition instead
 * of many stores.
 */

class Store(initState: State = State.INITIAL_STATE) {

    val stateObservable: BehaviorSubject<State> = BehaviorSubject.create(initState)

    fun dispatch(action: Action) {
        stateObservable.onNext(todoApp(getState(), action))
    }

    fun observe(): Observable<State> = stateObservable.asObservable()

    fun getState(): State = stateObservable.value
}