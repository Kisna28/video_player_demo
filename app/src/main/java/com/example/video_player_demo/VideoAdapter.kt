package com.example.video_player_demo



import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.OptIn
import androidx.media3.common.MediaItem
import androidx.media3.common.util.UnstableApi
import androidx.media3.exoplayer.SimpleExoPlayer
import androidx.media3.ui.PlayerView
import androidx.recyclerview.widget.RecyclerView

class VideoAdapter(private val videoList: List<String>) : RecyclerView.Adapter<VideoAdapter.VideoViewHolder>() {

    private var player: SimpleExoPlayer? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VideoViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.video_item_layout, parent, false)
        val viewHolder = VideoViewHolder(itemView)
        viewHolder.playerView.player = player
        return viewHolder
    }

    @OptIn(UnstableApi::class) override fun onBindViewHolder(holder: VideoViewHolder, position: Int) {
        val videoUri = videoList[position]
        val username = "User ${position + 1}"
        holder.usernameTextView.text = username
        holder.playerView.player =
            SimpleExoPlayer.Builder(holder.itemView.context).build().also { exoPlayer ->
                val mediaItem = MediaItem.fromUri(Uri.parse(videoUri))
                exoPlayer.setMediaItem(mediaItem)
                exoPlayer.prepare()
                exoPlayer.playWhenReady = true
                exoPlayer.repeatMode = SimpleExoPlayer.REPEAT_MODE_ALL
            }
    }
    override fun getItemCount(): Int {
        return videoList.size
    }
    inner class VideoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val playerView: PlayerView = itemView.findViewById(R.id.playerView)
        val usernameTextView: TextView = itemView.findViewById(R.id.userName)
    }
}