package com.example.pharmacheck.viewmodel

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.example.pharmacheck.entity.Medicine
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class CompatibilityViewModel : ViewModel() {

    private val _selectedMedicines = mutableStateListOf<Medicine>()
    val selectedMedicines: List<Medicine> get() = _selectedMedicines

    private val _compatibilityResult = MutableStateFlow<String?>(null)
    val compatibilityResult: StateFlow<String?> = _compatibilityResult

    fun addMedicine(med: Medicine) {
        if (!_selectedMedicines.contains(med)) {
            _selectedMedicines.add(med)
        }
    }

    fun removeMedicine(med: Medicine) {
        _selectedMedicines.remove(med)
    }

    fun checkCompatibility() {
        if (_selectedMedicines.size < 2) {
            _compatibilityResult.value = "Выберите как минимум два лекарства для проверки."
            return
        }

        val interactions = _selectedMedicines.map { it.Interaction }
        val result : MutableSet<String> = mutableSetOf()
        for (medicine in _selectedMedicines) {
            for (medicine1 in _selectedMedicines) {
                if (medicine1.Interaction?.contains(medicine.RusName) ?: false) {
                    result.add(medicine1.RusName)
                    result.add(medicine.RusName)
                }
            }
        }

        if (!result.isEmpty()) {
            _compatibilityResult.value = "Обнаружены несовместимые лекарства: " + result.joinToString()
        } else {
            _compatibilityResult.value = "Лекарства можно принимать вместе."
        }
    }

    fun resetResult() {
        _compatibilityResult.value = null
    }
}