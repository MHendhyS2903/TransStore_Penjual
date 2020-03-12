package id.asiatek.asiatrans.ui.tab_menu.tab_inbox

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import id.asiatek.asiatrans.R
import id.asiatek.asiatrans.viewmodel.InboxTabViewModel

class InboxTabFragment : Fragment() {

    companion object {
        fun newInstance() = InboxTabFragment()
    }

    private lateinit var viewModel: InboxTabViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.inbox_tab_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(InboxTabViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
