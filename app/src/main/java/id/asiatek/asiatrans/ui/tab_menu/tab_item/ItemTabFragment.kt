package id.asiatek.asiatrans.ui.tab_menu.tab_item

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import id.asiatek.asiatrans.R
import id.asiatek.asiatrans.viewmodel.ItemTabViewModel

class ItemTabFragment : Fragment() {

    companion object {
        fun newInstance() = ItemTabFragment()
    }

    private lateinit var viewModel: ItemTabViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.item_tab_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(ItemTabViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
