package beg.hr.todos.presentational_components

import java.util.function.Consumer

/**
 * Created by juraj on 03/03/2017.
 */
class Todo(val id: Int, val text: String, val completed: Boolean, val onClick: Consumer<Boolean>)
