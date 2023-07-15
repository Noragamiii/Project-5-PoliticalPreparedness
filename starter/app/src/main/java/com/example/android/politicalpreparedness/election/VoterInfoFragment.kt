package com.example.android.politicalpreparedness.election

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.android.politicalpreparedness.databinding.FragmentVoterInfoBinding

class VoterInfoFragment : Fragment() {

    private lateinit var viewModel: VoterInfoViewModel
    private lateinit var binding: FragmentVoterInfoBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?)
    : View? {

        val application = requireNotNull(this.activity).application
        val selectedElection = VoterInfoFragmentArgs.fromBundle(requireArguments()).argElection
        val viewModelFactory = VoterInfoViewModelFactory(selectedElection, application)
        viewModel = ViewModelProvider(this, viewModelFactory).get(VoterInfoViewModel::class.java)

        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        viewModel.url.observe(viewLifecycleOwner, Observer {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(it))
            startActivity(intent)
        })

        return binding.root
    }
}