package com.batuhan.cryptocrazy.viewmodel

import androidx.lifecycle.ViewModel
import com.batuhan.cryptocrazy.model.Crypto
import com.batuhan.cryptocrazy.repository.CryptoRepository
import com.batuhan.cryptocrazy.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class CryptoDetailViewModel @Inject constructor(
    private val repository: CryptoRepository
) : ViewModel() {

    suspend fun getCrypto(id: String): Resource<Crypto> {
        return repository.getCrypto(id)
    }
}