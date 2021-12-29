package com.example.a2021hackthon.view.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.example.a2021hackthon.databinding.FragmentHomeFaceSelectPhotoBinding
import com.example.a2021hackthon.view.utils.ImagePicker


class SelectPhotoFragment : Fragment() {

    private val navController by lazy {
        findNavController()
    }

    private lateinit var binding: FragmentHomeFaceSelectPhotoBinding

    private lateinit var photoResultLauncher: ActivityResultLauncher<Intent>
    private lateinit var cameraResultLauncher: ActivityResultLauncher<Intent>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeFaceSelectPhotoBinding.inflate(inflater, container, false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        init()
        observe()

        binding.btnAlbum.setOnClickListener {
            ImagePicker.selectStart(photoResultLauncher)
        }

        binding.btnCamera.setOnClickListener {
            ImagePicker.cameraStart(cameraResultLauncher)
        }
    }

    private fun init() {
        photoResultLauncher = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) {
            ImagePicker.initSelection(it)
        }

        cameraResultLauncher = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) {
            ImagePicker.initCamera(it, requireActivity().contentResolver)
        }
    }

    private fun observe() {
        ImagePicker.image.observe(viewLifecycleOwner) {
            navController.navigate(
                SelectPhotoFragmentDirections.actionHomeFaceSelectPhotoFragmentToAnalyzePhotoFragment(it.toString())
            )
        }

        ImagePicker.cameraImage.observe(viewLifecycleOwner) {
            navController.navigate(
                SelectPhotoFragmentDirections.actionHomeFaceSelectPhotoFragmentToAnalyzePhotoFragment(it.toString())
            )
        }
    }
}