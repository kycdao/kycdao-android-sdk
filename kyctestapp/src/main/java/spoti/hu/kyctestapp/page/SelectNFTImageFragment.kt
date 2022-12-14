package spoti.hu.kyctestapp.page

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.kycdao.android.sdk.model.TokenImage
import kotlinx.coroutines.launch
import spoti.hu.kyctestapp.R
import spoti.hu.kyctestapp.base.BaseFragment
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
        setupSelectNFT()
    }

    private var selectedImage: TokenImage? = null

    private fun setupNFTs() {
        val adapter = NFTCellAdapter()
        binding.nftList.adapter = adapter

        val snapHelper = PagerSnapHelper()
        snapHelper.attachToRecyclerView(binding.nftList)
        binding.indicator.attachToRecyclerView(binding.nftList)


        //TODO: bring better solution!
        binding.nftList.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    binding.nftList.layoutManager?.let { manager ->
                        val pos: Int? = snapHelper.findSnapView(manager)?.let {
                            binding.nftList.layoutManager?.getPosition(
                                it
                            )
                        }

                        if (pos != null) {
                            selectedImage = adapter.dataSet[pos]
                        }
                    }
                }
            }
        })

        lifecycleScope.launch {
            val images = sdk.myKycSessions.first().getNFTImages()
            adapter.setData(images)
            selectedImage = images[0]
        }
    }

    private fun setupSelectNFT() {
        binding.selectNFT.setOnClickListener {
            lifecycleScope.launch {
                //TODO: BRING DURATION HERE!!!
                //TODO: till the requestminting is running show loading
                selectedImage?.id?.let { it1 -> sdk.myKycSessions.first().requestMinting(it1, 3u) }
                navigateWithAction(SelectNFTImageFragmentDirections.toMintNFTFragment())
            }
        }
    }


    class NFTCellAdapter(val sessionId: String) :
        RecyclerView.Adapter<NFTCellAdapter.ViewHolder>() {

        val dataSet: MutableList<TokenImage> = mutableListOf()

        fun setData(data: List<TokenImage>) {
            dataSet.clear()
            dataSet.addAll(data)
            notifyDataSetChanged()
        }

        class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
            val artwork: ImageView

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
            viewHolder.artwork.load(dataSet[position].url)
        }

        override fun getItemCount() = dataSet.size
    }

}