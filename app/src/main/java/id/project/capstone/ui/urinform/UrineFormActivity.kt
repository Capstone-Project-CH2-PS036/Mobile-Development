package id.project.capstone.ui.urinform

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import id.project.capstone.R
import id.project.capstone.data.source.MyResult
import id.project.capstone.databinding.ActivityUrineFormBinding
import id.project.capstone.ui.result.ResultActivity
import id.project.capstone.util.getImageUri
import id.project.capstone.util.reduceFileImage
import id.project.capstone.util.uriToFile

class UrineFormActivity : AppCompatActivity() {

    private lateinit var binding: ActivityUrineFormBinding

    private val viewModel by viewModels<UrineFormViewModel> {
        UrineFormViewModelFactory.getInstance(this)
    }

    private var currentImageUri: Uri? = null

    private val permissionRequestLauncher = registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted: Boolean ->
        val messageResId = if (isGranted) {
            R.string.permission_granted
        } else {
            R.string.permission_denied
        }
        val message = getString(messageResId)
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUrineFormBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (!allPermissionsGranted()) {
            permissionRequestLauncher.launch(REQUIRED_PERMISSION)
        }

        binding.toolbar.setNavigationOnClickListener {
            onBackPressed()
        }

        binding.openCamera.setOnClickListener {
            startCamera()
        }

        binding.btnSubmit.setOnClickListener {


            if (currentImageUri !== null){
                uploadImage()

            } else{
                showToast("you must scan the image")
            }
        }


    }

    private fun uploadImage() {
        currentImageUri?.let { uri ->
            val imageFile = uriToFile(uri, this).reduceFileImage()
            viewModel.scanUrine(imageFile).observe(this) { result ->
                if (result != null) {
                    when (result) {
                        is MyResult.Loading -> {
                            showLoading(true)
                        }

                        is MyResult.Success -> {
                            showLoading(false)
                            val color = result.data.body()?.urineColor?.modelPrediction
                            val disease = result.data.body()?.diseaseDescription.toString()
                            val intent = Intent(this, ResultActivity::class.java)

                            val descriptionText = binding.edAddDescription.text.toString()
                            currentImageUri?.let {
                                intent.putExtra("image", it.toString())
                            }
                            intent.putExtra("description", descriptionText)
                            intent.putExtra("color", color)
                            intent.putExtra("disease",disease)


                            startActivity(intent)
                        }

                        is MyResult.Error -> {
                            showToast(result.error)
                            showLoading(false)
                        }
                    }
                }
            }

        }
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    private fun showLoading(isLoading: Boolean) {
        binding.progressbarUpload.visibility = if (isLoading) View.VISIBLE else View.GONE
    }



    private fun startCamera() {
        currentImageUri = getImageUri(this)
        launcherIntentCamera.launch(currentImageUri)
    }

    private val launcherIntentCamera = registerForActivityResult(
        ActivityResultContracts.TakePicture()
    ) { isSuccess ->
        if (isSuccess) {
            showImage()
        }
    }

    private fun showImage() {
        currentImageUri?.let {
            Log.d("Image URI", "showImage: $it")
            binding.imgPreview.setImageURI(it)
        }
    }

    private fun allPermissionsGranted() =
        ContextCompat.checkSelfPermission(
            this,
            REQUIRED_PERMISSION
        ) == PackageManager.PERMISSION_GRANTED


    companion object {
        private const val REQUIRED_PERMISSION = Manifest.permission.CAMERA
    }
}