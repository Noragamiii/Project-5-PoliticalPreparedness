package com.example.android.politicalpreparedness.election

import android.app.Application
import androidx.lifecycle.*
import com.example.android.politicalpreparedness.R
import com.example.android.politicalpreparedness.database.ElectionDatabase
import com.example.android.politicalpreparedness.network.models.Election
import com.example.android.politicalpreparedness.network.models.VoterInfoResponse
import com.example.android.politicalpreparedness.repository.ElectionsRepository
import com.example.android.politicalpreparedness.utils.Result
import com.example.android.politicalpreparedness.utils.SingleLiveEvent
import kotlinx.coroutines.launch

class VoterInfoViewModel(val election: Election, app: Application) : AndroidViewModel(app) {

    val showToast: SingleLiveEvent<String> = SingleLiveEvent()

    private val database = ElectionDatabase.getInstance(app)
    private val electionsRepository = ElectionsRepository(database)

    var url = MutableLiveData<String>()

    private var _isVote = MutableLiveData<Boolean>()
    val isVote: LiveData<Boolean>
        get() = _isVote

    private var _voterInfo = MutableLiveData<VoterInfoResponse>()
    val voterInfo: LiveData<VoterInfoResponse>
        get() = _voterInfo

    init {
        viewModelScope.launch {
            if (election.division.state.isNotEmpty()) {
                _isVote.value = electionsRepository.getElection(election.id.toLong()) != null
                val address = "${election.division.country},${election.division.state}"
                val result = electionsRepository.getVoterInfo(address, election.id.toLong())
                when (result) {
                    is Result.Success -> {
                        _voterInfo.value = result.data
                    }
                    else -> {
                        showToast.value = app.getString(R.string.error_upcoming_election)
                    }
                }
            }
        }
    }

    fun saveElection(election: Election) {
        viewModelScope.launch {
            if (isVote.value == true) {
                electionsRepository.delete(election)
            } else {
                electionsRepository.insert(election)
            }
            _isVote.value = electionsRepository.getElection(election.id.toLong()) != null
        }

    }

    fun onURLClick(url: String) {
        this.url.value = url
    }

}