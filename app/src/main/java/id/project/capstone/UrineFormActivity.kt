package id.project.capstone

import android.Manifest
import android.content.pm.PackageManager
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import id.project.capstone.databinding.ActivityUrineFormBinding
import id.project.capstone.util.getImageUri

class UrineFormActivity : AppCompatActivity() {

    private lateinit var binding: ActivityUrineFormBinding

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
            submit()
        }

    }

    private fun submit() {
        TODO("Not yet implemented")
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