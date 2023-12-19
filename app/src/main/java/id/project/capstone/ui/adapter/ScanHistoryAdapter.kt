package id.project.capstone.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import id.project.capstone.data.source.local.ResultUrineEntity
import id.project.capstone.databinding.ItemHistoryBinding

class ScanHistoryAdapter :
    ListAdapter<ResultUrineEntity, ScanHistoryAdapter.ViewHolder>(FavoriteUserDiffCallback()) {
    private var onItemClickListener: ((ResultUrineEntity) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ItemHistoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val user = getItem(position)
        holder.bind(user)


    }

    fun setOnItemClickListener(listener: (ResultUrineEntity) -> Unit) {
        onItemClickListener = listener
    }

    inner class ViewHolder(private val binding: ItemHistoryBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(result: ResultUrineEntity) {
            binding.urineColor.text = result.color
            Glide.with(itemView.context).load(result.image).into(binding.imageHistory)


            itemView.setOnClickListener {
                onItemClickListener?.invoke(result)
            }
        }
    }
}

class FavoriteUserDiffCallback : DiffUtil.ItemCallback<ResultUrineEntity>() {
    override fun areItemsTheSame(
        oldItem: ResultUrineEntity, newItem: ResultUrineEntity
    ): Boolean {

        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(
        oldItem: ResultUrineEntity, newItem: ResultUrineEntity
    ): Boolean {
        return oldItem == newItem
    }

}