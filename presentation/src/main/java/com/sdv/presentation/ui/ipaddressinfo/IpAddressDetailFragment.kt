package com.sdv.presentation.ui.ipaddressinfo

import android.os.Bundle
import android.text.InputFilter
import android.text.Spannable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil.inflate
import com.sdv.presentation.R
import com.sdv.presentation.base.BaseFragment
import com.sdv.presentation.databinding.IpAddressDetailFragmentBinding
import kotlinx.android.synthetic.main.ip_address_detail_fragment.*
import kotlinx.android.synthetic.main.ip_address_detail_fragment.view.*
import org.koin.androidx.viewmodel.ext.android.viewModel


class IpAddressDetailFragment : BaseFragment() {
    override val viewModel by viewModel<IpAddressDetailViewModel>()
    lateinit var dataBinding: IpAddressDetailFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        dataBinding = inflate(inflater,
            R.layout.ip_address_detail_fragment, container, false)
        dataBinding.viewmodel = viewModel
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeChanges()
        val ipAddressFilters = ipAddressFilter()

        dataBinding.edtIpAddress.filters = ipAddressFilters
        dataBinding.btnSearch.setOnClickListener {
            viewModel.getIpAddressInfo(edt_ip_address.text.toString())
        }
    }

    override fun observeChanges() {
        viewModel.showProgress.observe(viewLifecycleOwner){
            progress_bar_posts.isVisible = it
        }

        viewModel.showErrorLiveData.observe(viewLifecycleOwner){
            Toast.makeText(requireContext(), it, Toast.LENGTH_LONG).show()
        }

        viewModel.ipAddressDetailLiveData.observe(viewLifecycleOwner){
            dataBinding.linearDetail.isVisible = true
            dataBinding.tvStatus.text = getString(R.string.status).format(it.status)
            dataBinding.tvCountryCode.text = getString(R.string.countryCode).format(it.countryCode)
            dataBinding.tvCountry.text = getString(R.string.country).format(it.country)
            dataBinding.tvCity.text = getString(R.string.city).format(it.city)
            dataBinding.tvZip.text = getString(R.string.zip).format(it.zip)
            dataBinding.tvLat.text = getString(R.string.lat).format(it.lat)
            dataBinding.tvLon.text = getString(R.string.lon).format(it.lon)
            dataBinding.tvIsp.text = getString(R.string.isp).format(it.isp)
        }
    }

    private fun ipAddressFilter(): Array<InputFilter> {
        return arrayOf(InputFilter { source, start, end, dest, dstart, dend ->
            if (end > start) {
                val toCheck = if (source is Spannable) {
                    source.toString()
                } else {
                    val destString = dest.toString()
                    destString.substring(0, dstart) + source.subSequence(
                        start,
                        end
                    ) + destString.substring(dend)
                }
                if (!toCheck.matches("^\\d{1,3}(\\.(\\d{1,3}(\\.(\\d{1,3}(\\.(\\d{1,3})?)?)?)?)?)?".toRegex())) {
                    return@InputFilter if (source is Spannable) {
                        dest
                    } else {
                        ""
                    }
                } else {
                    val splits = toCheck.split("\\.".toRegex()).toTypedArray()
                    for (i in splits.indices) {
                        if (splits[i] != "" && Integer.valueOf(splits[i]) > 255) {
                            return@InputFilter if (source is Spannable) {
                                dest
                            } else {
                                ""
                            }
                        }
                    }
                }
            }
            null
        })
    }
}