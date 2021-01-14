package me.simple.rvc

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private val mItems = mutableListOf<Any>()
    private val mAdapter = Adapter()

    private val recyclerView by lazy { findViewById<RecyclerView>(R.id.recyclerView) }
    private val btn by lazy { findViewById<Button>(R.id.btn) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mItems.add("")
        mItems.add("")
        mItems.add("")
        mItems.add("")
        mItems.add("")
        mItems.add("")
        mItems.add("")

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = mAdapter

        btn.setOnClickListener {
            mItems.add("")
            mAdapter.notifyItemInserted(mItems.lastIndex)
            recyclerView.postDelayed({
                recyclerView.smoothScrollToPosition(mItems.lastIndex)
            },100)
        }
    }

    inner class Adapter : RecyclerView.Adapter<VH>() {
        override fun onCreateViewHolder(
            parent: ViewGroup,
            viewType: Int
        ): VH {
            return VH(
                LayoutInflater.from(this@MainActivity)
                    .inflate(R.layout.item_layout, parent, false)
            )
        }

        override fun getItemCount() = mItems.size

        override fun onBindViewHolder(
            holder: VH,
            position: Int
        ) {
            holder.tvItem.text = position.toString()
        }
    }


    class VH(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvItem = itemView.findViewById<TextView>(R.id.tvItem)
    }
}