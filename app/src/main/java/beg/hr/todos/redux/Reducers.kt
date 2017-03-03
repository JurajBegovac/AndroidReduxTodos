package beg.hr.todos.redux

import beg.hr.todos.redux.VisibilityFilter.SHOW_ALL

/**
 * Created by juraj on 03/03/2017.
 */
// ========== REDUCERS =============
// update the state according to actions
/**
 * Things you should never do inside a reducer:
 * Mutate its arguments;
 * Perform side effects like API calls and routing transitions;
 * Call non-pure functions, e.g. Date.now() or Math.random().
 */

fun todoApp(state: State = State.INITIAL_STATE, action: Action): State =
        State(visibilityFilter(state.visibilityFilter, action), todos(state.todos, action))


fun todos(state: List<Todo> = emptyList(), action: Action): List<Todo> {
    when (action.type) {
        ActionTypes.ADD_TODO -> {
            val text = action.payload as String
            return state.plusElement(Todo(text, false))
        }
        ActionTypes.TOGGLE_TODO -> {
            val todoIndex = action.payload as Int
            return state
                    .mapIndexed {
                        i, todo ->
                        if (i == todoIndex) todo.copy(completed = !todo.completed)
                        else todo
                    }
        }
        else -> return state
    }
}

fun visibilityFilter(state: VisibilityFilter = SHOW_ALL, action: Action): VisibilityFilter {
    when (action.type) {
        ActionTypes.SET_VISIBILITY_FILTER -> return action.payload as VisibilityFilter
        else -> return state
    }
}