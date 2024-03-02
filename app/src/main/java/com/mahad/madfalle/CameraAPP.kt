package com.mahad.madfalle

//import android.content.Intent
//import android.graphics.Bitmap
//import android.net.Uri
//import android.os.Bundle
//import android.provider.MediaStore
//import android.view.View
//import android.widget.Button
//import android.widget.ImageView
//import androidx.activity.result.ActivityResult
//import androidx.activity.result.ActivityResultCallback
//import androidx.activity.result.ActivityResultLauncher
//import androidx.appcompat.app.AppCompatActivity
//import androidx.core.content.FileProvider
//import java.io.File
//import java.io.FileNotFoundException
//import java.io.FileOutputStream
//import java.io.IOException
//import java.lang.ref.WeakReference
//
//class CameraAPP : AppCompatActivity() {
//    var btnpicture: Button? = null
//    var imageView: ImageView? = null
//    var activityResultLauncher: ActivityResultLauncher<Intent>? = null
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)
//        btnpicture = findViewById(R.id.btncamera_id)
//        imageView = findViewById(R.id.image)
//        btnpicture.setOnClickListener(View.OnClickListener {
//            val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
//            activityResultLauncher!!.launch(cameraIntent)
//        })
//        activityResultLauncher = registerForActivityResult<Intent, ActivityResult>(
//            StartActivityForResult(),
//            object : ActivityResultCallback<ActivityResult?> {
//                override fun onActivityResult(result: ActivityResult) {
//                    val extras = result.data!!.extras
//                    val imageUri: Uri?
//                    val imageBitmap = extras!!["data"] as Bitmap?
//                    val result_1 = WeakReference(
//                        Bitmap.createScaledBitmap(
//                            imageBitmap!!,
//                            imageBitmap.width, imageBitmap.height, false
//                        ).copy(Bitmap.Config.RGB_565, true)
//                    )
//                    val bm = result_1.get()
//                    imageUri = saveImage(bm, this@CameraAPP)
//                    imageView.setImageURI(imageUri)
//                }
//            })
//    }
//
//    private fun saveImage(image: Bitmap?, context: MainActivity): Uri? {
//        val imagefolder = File(context.cacheDir, "images")
//        var uri: Uri? = null
//        try {
//            imagefolder.mkdirs()
//            val file = File(imagefolder, "captured_image.jpg")
//            val stream = FileOutputStream(file)
//            image!!.compress(Bitmap.CompressFormat.JPEG, 100, stream)
//            stream.flush()
//            stream.close()
//            uri = FileProvider.getUriForFile(
//                context.applicationContext,
//                "com.allcodingtutorial.camerafull1" + ".provider",
//                file
//            )
//        } catch (e: FileNotFoundException) {
//            e.printStackTrace()
//        } catch (e: IOException) {
//            e.printStackTrace()
//        }
//        return uri
//    }
//
//    companion object {
//        private const val REQUEST_CODE = 22
//    }
//}