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

    inner class VideoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val playerView: PlayerView = itemView.findViewById(R.id.playerView)
        val usernameTextView: TextView = itemView.findViewById(R.id.userName)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VideoViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.video_item_layout, parent, false)
        return VideoViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: VideoViewHolder, position: Int) {
        val videoUri = videoList[position]
        val username = "User ${position + 1}"
        holder.usernameTextView.text = username
        initializePlayer(holder, videoUri)
    }

    override fun getItemCount(): Int {
        return videoList.size
    }

    @OptIn(UnstableApi::class) private fun initializePlayer(holder: VideoViewHolder, videoUri: String) {
        holder.playerView.player = SimpleExoPlayer.Builder(holder.itemView.context).build().also { exoPlayer ->
            val mediaItem = MediaItem.fromUri(Uri.parse(videoUri))
            exoPlayer.setMediaItem(mediaItem)
            exoPlayer.prepare()
            exoPlayer.playWhenReady = true
            exoPlayer.repeatMode = SimpleExoPlayer.REPEAT_MODE_ONE // Optional: Loop video
            player = exoPlayer
        }
    }

    @OptIn(UnstableApi::class) override fun onViewDetachedFromWindow(holder: VideoViewHolder) {
        super.onViewDetachedFromWindow(holder)
        player?.release()
        player = null
    }
}