package com.example.chattestapp.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.chattestapp.domain.ICheckCredentialsUseCase
import com.example.chattestapp.domain.IGetCredentialsUseCase
import com.example.chattestapp.utils.asLiveData

class MainViewModel(
    getCredentialsUseCase: IGetCredentialsUseCase,
    checkCredentialsUseCase: ICheckCredentialsUseCase
) : ViewModel() {

    private val _onOpenChatButtonClick = MutableLiveData<Unit>()
    val onOpenChatButtonClick = _onOpenChatButtonClick.asLiveData()

    private val _onCreateRequestButtonClick = MutableLiveData<Unit>()
    val onCreateRequestButtonClick = _onCreateRequestButtonClick.asLiveData()

    private val _onShowAllTicketsButtonClick = MutableLiveData<Unit>()
    val onShowAllTicketsButtonClick = _onShowAllTicketsButtonClick.asLiveData()

    private val _isMissingCredentials = MutableLiveData<Boolean>()
    val isMissingCredentials = _isMissingCredentials.asLiveData()

    private val _credentials = MutableLiveData<String>()
    val credentials = _credentials.asLiveData()

    private val _initZendesk = MutableLiveData<Unit>()
    val initZendesk = _initZendesk.asLiveData()

    init {
        if (checkCredentialsUseCase.isMissingCredentials()) {
            _isMissingCredentials.value = true
        } else {
            _credentials.value = getCredentialsUseCase.getCredentials()
            _initZendesk.value = Unit
        }
    }

    fun onOpenChatClick() {
        _onOpenChatButtonClick.value = Unit
    }

    fun onShowAllTicketsClick() {
        _onShowAllTicketsButtonClick.value = Unit
    }

    fun onCreateRequestButtonClick() {
        _onCreateRequestButtonClick.value = Unit
    }


}