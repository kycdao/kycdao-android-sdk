package spoti.hu.kyctestapp.page

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView
import spoti.hu.kyctestapp.R
import spoti.hu.kyctestapp.base.BaseFragment
import spoti.hu.kyctestapp.data.NFTCellItemWrapper
import spoti.hu.kyctestapp.databinding.FragmentSelectNftImageBinding

class SelectNFTImageFragment : BaseFragment<FragmentSelectNftImageBinding>() {
    override fun createBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentSelectNftImageBinding {
        return FragmentSelectNftImageBinding.inflate(inflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupNFTs()
    }

    private fun setupNFTs() {
        val adapter = NFTCellAdapter()
        binding.nftList.adapter = adapter
        adapter.setData(
            listOf(
                NFTCellItemWrapper(""),
                NFTCellItemWrapper(""),
                NFTCellItemWrapper(""),
                NFTCellItemWrapper("")
            )
        )

        PagerSnapHelper().attachToRecyclerView(binding.nftList)
        binding.indicator.attachToRecyclerView(binding.nftList)
    }


    class NFTCellAdapter : RecyclerView.Adapter<NFTCellAdapter.ViewHolder>() {

        private var dataSet: List<NFTCellItemWrapper> = emptyList()

        fun setData(data: List<NFTCellItemWrapper>) {
            dataSet = data
            notifyDataSetChanged()
        }

        class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
            private val artwork: ImageView

            init {
                artwork = view.findViewById(R.id.artwork)
            }
        }

        override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
            val view = LayoutInflater.from(viewGroup.context)
                .inflate(R.layout.cell_nft_image, viewGroup, false)

            return ViewHolder(view)
        }

        override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
//            viewHolder.artwork.load(dataSet[position].url)

        }

        override fun getItemCount() = dataSet.size
    }

}