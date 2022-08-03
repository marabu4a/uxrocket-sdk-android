package com.napoleonit.getcrmandroid.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.napoleonit.getcrmandroid.databinding.FragmentHomeBinding
import com.napoleonit.uxrocket.UXRocket
import com.napoleonit.uxrocket.data.models.http.AttributeParameter
import com.napoleonit.uxrocket.data.models.http.ContextEvent
import com.napoleonit.uxrocket.shared.getCurrentDateString

class HomeFragment : Fragment() {
    companion object {
        fun getInstance() = HomeFragment()
    }

    private lateinit var binding: FragmentHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        UXRocket.logEvent(
            itemIdentificator = "HomePage",
            itemName = "Home page",
            event = ContextEvent.OPEN_PAGE,
            parameters = listOf(AttributeParameter("1", value = "Value sample 1"), AttributeParameter("7", value = "Value sample 2"))
        )
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentHomeBinding.inflate(layoutInflater)

        val params = listOf(AttributeParameter(id = "1", value = "190"))

        UXRocket.getUIConfiguration(
            activityOrFragmentName = "HomePage",
            parameters = params,
            callback = {
                UXRocket.customizeItems(items = listOf(binding.homeBannersButton, binding.addCustomBannerButton), it)
                binding.homeBannersButton.setOnClickListener {
                    UXRocket.logEvent(
                        itemIdentificator = "home_banners_button",
                        itemName = "home banners button pressed",
                        event = ContextEvent.BUTTONS
                    )
                    UXRocket.logCampaignEvent("HomePage", "home_banners_button", totalValue = 1)
                }

                binding.addCustomBannerButton.setOnClickListener {
                    UXRocket.logEvent(
                        itemIdentificator = "add_custom_banner_button",
                        itemName = "Add custom banner button pressed",
                        event = ContextEvent.BUTTONS
                    )
                    UXRocket.logCampaignEvent("HomePage", "add_custom_banner_button", totalValue = 1)
                }
            })

        return binding.root
    }
}