package com.example.pharmacheck.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.pharmacheck.entity.Medicine
import com.example.pharmacheck.repository.MedicineRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MedicineViewModel (private val repository: MedicineRepository) : ViewModel() {

    val allMedicines: LiveData<List<Medicine>> = repository.getAllMedicines().asLiveData()

    private val _interactions = MutableStateFlow<List<String>>(emptyList())
    val interactions: StateFlow<List<String>> = _interactions

    fun checkInteractions(selectedMedicineIds: List<String>) {
        viewModelScope.launch {
            val incompatibleMedicines = repository.getInteractions(selectedMedicineIds)
            _interactions.value = incompatibleMedicines
        }
    }

    fun getMedicineById(medicineId: Int): Medicine {
        return repository.getMedicineById(medicineId)
    }
}

class MedicineViewModelFactory(private val repository: MedicineRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MedicineViewModel::class.java)) {
            @Suppress ("UNCHECKED_CAST")
            return MedicineViewModel(repository) as T
        } else {
            throw IllegalArgumentException("Unknown viewmodel class")
        }
    }
}