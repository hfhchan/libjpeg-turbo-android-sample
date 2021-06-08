package com.github.hfhchan.libjpegturbo.sampleapp

import android.graphics.BitmapFactory
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.github.hfhchan.libjpegturbo.sampleapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        try {
            val byteFile = assets.open("negative-space-sandpiper_rock_nature_beach.jpg").readBytes()
            val output = BitmapFactory.decodeByteArray(byteFile, 0, byteFile.size)

            binding.sampleImg.setImageBitmap(output)
            binding.sampleText.text =
                String.format("%s %s %s", output.width, output.height, output.rowBytes)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}