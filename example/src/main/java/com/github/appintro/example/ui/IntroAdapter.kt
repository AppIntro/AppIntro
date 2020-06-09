package com.github.appintro.example.ui

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.github.appintro.example.R

class IntroAdapter(
        private val entries: List<IntroEntry>
) : RecyclerView.Adapter<IntroAdapter.IntroAdapterViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IntroAdapterViewHolder {
        val root = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_intro, parent, false)
        return IntroAdapterViewHolder(root)
    }

    override fun getItemCount() = entries.size

    override fun onBindViewHolder(holder: IntroAdapterViewHolder, position: Int) {
        holder.bind(entries[position])
    }

    inner class IntroAdapterViewHolder(
            private val root: View
    ) : RecyclerView.ViewHolder(root) {
        private val title: TextView = root.findViewById(R.id.item_title)
        private val description: TextView = root.findViewById(R.id.item_description)
        private val button: TextView = root.findViewById(R.id.item_button)

        fun bind(entry: IntroEntry) {
            title.setText(entry.title)
            description.setText(entry.description)
            button.setOnClickListener {
                val intent = Intent(root.context, entry.activityClass)
                root.context.startActivity(intent)
            }
        }
    }
}
