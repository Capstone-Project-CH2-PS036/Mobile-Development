package id.project.capstone.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import id.project.capstone.data.source.local.ResultUrineEntity
import id.project.capstone.databinding.ItemHistoryBinding
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

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

            val formattedTimestamp = formatTimestamp(result.timestamp)
            binding.tanggalScan.text = formattedTimestamp

            itemView.setOnClickListener {
                onItemClickListener?.invoke(result)
            }
        }
        private fun formatTimestamp(timestamp: Long): String {
            val sdf = SimpleDateFormat("dd-MMM-yyyy HH:mm:ss", Locale.getDefault())
            val date = Date(timestamp)
            return sdf.format(date)
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