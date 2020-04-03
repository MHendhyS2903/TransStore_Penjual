package id.asiatek.asiatrans.ui.tab_menu.tab_order

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import id.asiatek.asiatrans.R
import id.asiatek.asiatrans.viewmodel.OrderTabViewModel

class OrderTabFragment : Fragment() {

    companion object {
        fun newInstance() = OrderTabFragment()
    }

    private lateinit var viewModel: OrderTabViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.order_tab_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(OrderTabViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
