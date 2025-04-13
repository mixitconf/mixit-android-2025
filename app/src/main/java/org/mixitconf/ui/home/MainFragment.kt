package org.mixitconf.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import org.mixitconf.R
import org.mixitconf.databinding.FragmentHomeBinding
import org.mixitconf.model.enums.TalkFormat
import org.mixitconf.service.AppPreferences
import org.mixitconf.ui.BaseFragment
import org.mixitconf.ui.talk.TalksViewModel
import org.mixitconf.workers.DataManualSynchronizationWorker
import java.util.*

class MainFragment : BaseFragment<FragmentHomeBinding>() {

    private val viewModel: TalksViewModel by sharedViewModel()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        viewModel.search().observe(viewLifecycleOwner) { talks ->
            val appContext = activity?.applicationContext
            val lastSavedYear  = AppPreferences.synchronizedYear
            if((lastSavedYear == null || lastSavedYear != "2025") && appContext !=null) {
                AppPreferences.synchronizedYear = "2025"
                DataManualSynchronizationWorker.enqueueManualWorker(appContext)
                Toast.makeText(appContext, R.string.info_sync_start, Toast.LENGTH_LONG).show()
            }
        }
        return FragmentHomeBinding.inflate(inflater, container, false).let {
            setViewBinding(it)
            viewBinding.root
        }
    }

}