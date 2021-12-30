package com.example.a2021hackthon.view.fragment

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.a2021hackthon.R
import com.example.a2021hackthon.databinding.FragmentKakaoMapBinding
import com.example.a2021hackthon.model.remote.dto.Place
import com.example.a2021hackthon.view.utils.MessageUtils
import com.example.a2021hackthon.viewmodel.KaKaoMapViewModel
import dagger.hilt.android.AndroidEntryPoint
import net.daum.mf.map.api.MapPOIItem
import net.daum.mf.map.api.MapPoint
import net.daum.mf.map.api.MapView

@AndroidEntryPoint
class KakaoMapFragment : Fragment() {

    private lateinit var binding: FragmentKakaoMapBinding
    private val viewModel: KaKaoMapViewModel by viewModels()

    private val navArgs: KakaoMapFragmentArgs by navArgs()

    private lateinit var mapView: MapView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentKakaoMapBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        init()
        observe()

        viewModel.getSearchPlace(navArgs.keyword)

        binding.btnClose.setOnClickListener {
            findNavController().navigate(R.id.action_kakaoMapFragment_to_homeFragment)
        }
    }

    private fun init() {
        mapView = MapView(requireActivity())
        binding.relativeLayout.addView(mapView)
    }

    private fun observe() = with (viewModel) {
        isSuccess.observe(viewLifecycleOwner) {
            setEventListener(it)
            createMarker(it)
        }

        isFailure.observe(viewLifecycleOwner) {
            MessageUtils.showDialog(requireActivity(), it)
        }
    }

    private fun createMarker(list: List<Place>) {
        var tag = 0
        list.forEach {
            val marker = MapPOIItem().apply {
                mapPoint = MapPoint.mapPointWithGeoCoord(it.y, it.x)
                this.tag = tag
                itemName = it.place_name
                markerType = MapPOIItem.MarkerType.RedPin
                isCustomImageAutoscale = false
            }

            mapView.addPOIItem(marker)
            tag++
        }

        val focusMapPoint = MapPoint.mapPointWithGeoCoord(list[1].y, list[1].x)
        mapView.setMapCenterPointAndZoomLevel(focusMapPoint, 3, true)
    }

    private fun setEventListener(list: List<Place>) {
        mapView.setPOIItemEventListener(object : MapView.POIItemEventListener {
            override fun onPOIItemSelected(p0: MapView?, p1: MapPOIItem?) {}

            override fun onCalloutBalloonOfPOIItemTouched(p0: MapView?, p1: MapPOIItem?) {}

            override fun onCalloutBalloonOfPOIItemTouched(
                mapView: MapView?,
                poiItem: MapPOIItem?,
                buttonType: MapPOIItem.CalloutBalloonButtonType?
            ) {
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(list[poiItem!!.tag].place_url))
                startActivity(intent)
            }

            override fun onDraggablePOIItemMoved(p0: MapView?, p1: MapPOIItem?, p2: MapPoint?) {}
        })
    }

}