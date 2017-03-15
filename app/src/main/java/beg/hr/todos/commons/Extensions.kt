package beg.hr.todos.commons

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

/**
 * Created by juraj on 03/03/2017.
 */
fun ViewGroup.inflate(layoutId: Int, attachToRoot: Boolean = false): View {
    return LayoutInflater.from(context).inflate(layoutId, this, attachToRoot)
}
