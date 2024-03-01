package com.example.video_player_demo


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.io.File

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val directoryPath = "/storage/emulated/0/Bemrr"
        val videoList = getVideoFiles(directoryPath)

        val recyclerView: RecyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = VideoAdapter(videoList)

    }
    private fun getVideoFiles(directoryPath: String): List<String> {
        val directory = File(directoryPath)
        val videoFiles = mutableListOf<String>()
        if (directory.exists() && directory.isDirectory) {
            directory.listFiles()?.forEach { file ->
                if (file.isFile && file.extension.equals("mkv", ignoreCase = true)) {
                    videoFiles.add(file.absolutePath)
                }
            }
        }
        return videoFiles
    }
}