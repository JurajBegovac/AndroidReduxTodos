package beg.hr.todos.redux


/**
 * Created by juraj on 03/03/2017.
 */
// ========== ACTIONS =============
// represent the facts about “what happened”

data class Action(val type: String, val payload: Any)

/**
 * Action types
 */
class ActionTypes {
    companion object {
        val ADD_TODO: String = "ADD_TODO"
        val TOGGLE_TODO: String = "TOGGLE_TODO"
        val SET_VISIBILITY_FILTER: String = "SET_VISIBILITY_FILTER"
    }
}

/**
 * Other constants
 */
enum class VisibilityFilter {
    SHOW_ALL, SHOW_COMPLETED, SHOW_ACTIVE
}

data class AddPayload(val id: Int, val text: String)

var nextTodoId = 0

/**
 * Action creators
 *
 * Action creators can also be asynchronous and have side-effects
 */
// todo handle id - add todo just needs to have text and not payload
fun addTodo(text: String): Action = Action(ActionTypes.ADD_TODO, AddPayload(nextTodoId++, text))

fun toggleTodo(id: Int): Action = Action(ActionTypes.TOGGLE_TODO, id)

fun setVisibilityFilter(filter: VisibilityFilter): Action = Action(ActionTypes.SET_VISIBILITY_FILTER, filter)
