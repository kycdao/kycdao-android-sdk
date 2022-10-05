package com.kycdao.android.sdk.ui.nftselector

import androidx.lifecycle.*
import com.kycdao.android.sdk.model.AvailableImage
import com.kycdao.android.sdk.ui.nftselector.cell.model.*
import com.kycdao.android.sdk.ui.events.SingleLiveEvent
import com.kycdao.android.sdk.ui.events.ViewModelEvent
import kotlinx.coroutines.flow.*

class NftSelectorViewModel(
    val list: List<AvailableImage>
) : ViewModel() {
    private var listItems: MutableLiveData<List<RadioButtonModel>> = MutableLiveData(emptyList())
    private val selectedIndex = MutableStateFlow<Int?>(null)
    val currentSelectedItem = MutableLiveData<NftItem?>(null)
    val isButtonEnabled: LiveData<Boolean> = currentSelectedItem.map { it != null }
    val events = SingleLiveEvent<ViewModelEvent>()
    val outEvents = SingleLiveEvent<ViewModelEvent>()


    private val pageFlow = combine(listItems.asFlow(), selectedIndex) { items, index ->
        NftChoicePage(items, index)
    }

    val currentItems = pageFlow.map { it.items }.asLiveData()

    fun onContinue() {
        currentSelectedItem.value?.id?.let {
            outEvents.postValue(ContinueEvent(it))
        }
    }

    init {
        val items = list.mapIndexed { index, availableImage ->
            NftItem(
                availableImage.id,
                availableImage.url,
                index,
                false
            ) }
        listItems.postValue(items)
        events.asFlow().onEach { event ->
            when (event) {
                is NftItemSelected -> {
                    selectedIndex.tryEmit(event.id)
                    currentSelectedItem.postValue(event.item)
                }
            }
        }.launchIn(viewModelScope)
    }
}

data class ContinueEvent(val selectedId: String) : ViewModelEvent
