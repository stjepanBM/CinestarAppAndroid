package hr.algebra.articles.view

import android.graphics.Canvas
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView

class MovieTouchHelperCallback(dragDir: Int, swipeDir: Int, private val listener: OnSwipedListener)
    :ItemTouchHelper.SimpleCallback(dragDir, swipeDir){

    interface OnSwipedListener{
        fun onSwiped(position: Int)
    }

    override fun onMove(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        target: RecyclerView.ViewHolder
    ) = true

    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
        listener.onSwiped(viewHolder.adapterPosition)
    }

    override fun onSelectedChanged(viewHolder: RecyclerView.ViewHolder?, actionState: Int) {
        super.onSelectedChanged(viewHolder, actionState)
        viewHolder?.let {
            val viewForeground = (viewHolder as MoviesAdapter.ViewHolder).viewForeground
            getDefaultUIUtil().onSelected(viewForeground)
        }
    }

    override fun onChildDrawOver(
        c: Canvas,
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder?,
        dX: Float,
        dY: Float,
        actionState: Int,
        isCurrentlyActive: Boolean
    ) {
        super.onChildDrawOver(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)
        viewHolder?.let {
            val viewForeground = (viewHolder as MoviesAdapter.ViewHolder).viewForeground
            getDefaultUIUtil().onDrawOver(
                c, recyclerView, viewForeground, dX, dY, actionState, isCurrentlyActive
            )
        }
    }

    override fun onChildDraw(
        c: Canvas,
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        dX: Float,
        dY: Float,
        actionState: Int,
        isCurrentlyActive: Boolean
    ) {
        super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)
        viewHolder?.let {
            val viewForeground = (viewHolder as MoviesAdapter.ViewHolder).viewForeground
            getDefaultUIUtil().onDraw(
                c, recyclerView, viewForeground, dX, dY, actionState, isCurrentlyActive
            )
        }
    }

    override fun clearView(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder) {
        super.clearView(recyclerView, viewHolder)
        val viewForeground = (viewHolder as MoviesAdapter.ViewHolder).viewForeground
        getDefaultUIUtil().clearView(viewForeground)
    }

}