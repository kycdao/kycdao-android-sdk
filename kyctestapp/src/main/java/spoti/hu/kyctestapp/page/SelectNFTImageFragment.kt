package spoti.hu.kyctestapp.page

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.kycdao.android.sdk.model.TokenImage
import kotlinx.coroutines.launch
import okhttp3.Headers
import spoti.hu.kyctestapp.R
import spoti.hu.kyctestapp.base.BaseFragment
import spoti.hu.kyctestapp.databinding.FragmentSelectNftImageBinding
import spoti.hu.kyctestapp.manager.SDKManager

class SelectNFTImageFragment : BaseFragment<FragmentSelectNftImageBinding>() {

    private val args by navArgs<SelectNFTImageFragmentArgs>()

    private val adapter = NFTCellAdapter(sdk)

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
        setupRegenerateNFTs()
    }

    private fun setupRegenerateNFTs() {
        binding.regenerateNFT.setOnClickListener {
            lifecycleScope.launch{
                val newImages = sdk.getVerificationSession().regenerateNFTImages()
                adapter.setData(newImages)
                selectedImage = newImages[0]
            }
        }
    }

    private var selectedImage: TokenImage? = null

    private fun setupNFTs() {
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
            val images = sdk.getVerificationSession().getNFTImages()
            adapter.setData(images)
            selectedImage = images[0]
        }
    }

    private fun setupSelectNFT() {
        binding.selectNFT.setOnClickListener {
            lifecycleScope.launch {
                showAuthorizing()
                try {
                    selectedImage?.let { img ->
                        val duration = args.duration
                        sdk.getVerificationSession().requestMinting(img.id, duration)
                        navigateWithAction(SelectNFTImageFragmentDirections.toMintNFTFragment(img.getUrl()))
                    }

                } catch (e: Exception) {
                    e.printStackTrace()
                    dismissAuthorizing()
                }
            }
        }
    }

    private fun showAuthorizing() {
        childFragmentManager.beginTransaction()
            .add(R.id.loaderContainer, AuthorizeMintingFragment()).commit()
    }

    private fun dismissAuthorizing() {
        childFragmentManager.popBackStack()
    }


    class NFTCellAdapter(val sdk : SDKManager) :
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
            viewHolder.artwork.load(dataSet[position].getUrl())
        }

        override fun getItemCount() = dataSet.size
    }

}