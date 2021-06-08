package com.github.hfhchan.libjpegturbo.sampleapp

import android.graphics.BitmapFactory
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.github.hfhchan.libjpegturbo.sampleapp.databinding.ActivityMainBinding
import org.libjpegturbo.turbojpeg.TJ
import org.libjpegturbo.turbojpeg.TJCompressor
import java.nio.ByteBuffer


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        try {
            val byteFile = assets.open("negative-space-sandpiper_rock_nature_beach.jpg").readBytes()
            val bmp = BitmapFactory.decodeByteArray(byteFile, 0, byteFile.size)

            val size: Int = bmp.rowBytes * bmp.height
            val bytes = ByteArray(size)
            val b: ByteBuffer = ByteBuffer.wrap(bytes)
            bmp.copyPixelsToBuffer(b)
            bmp.recycle()

            val tjc = TJCompressor()
            tjc.setSourceImage(bytes, 0, 0, bmp.width, 0, bmp.height, TJ.PF_RGBX)
            tjc.setSubsamp(TJ.SAMP_444)
            tjc.setJPEGQuality(100)
            val outputBytes: ByteArray = tjc.compress(0)

            val output = BitmapFactory.decodeByteArray(outputBytes, 0, tjc.compressedSize)
            tjc.close()

            binding.sampleImg.setImageBitmap(output)
            binding.sampleText.text =
                String.format("%s %s %s", output.width, output.height, output.rowBytes)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}