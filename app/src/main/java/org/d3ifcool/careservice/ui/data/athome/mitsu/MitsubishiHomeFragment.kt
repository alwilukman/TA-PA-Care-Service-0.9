package org.d3ifcool.careservice.ui.data.athome.mitsu

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import org.d3ifcool.careservice.databinding.FragmentMitsubishiHomeBinding
import org.d3ifcool.careservice.firebase.mitsuhome.MitshuHomeDb

class MitsubishiHomeFragment : Fragment() {

    private  lateinit var binding: FragmentMitsubishiHomeBinding

    private val viewModel: MitsubishiHomeViewModel by lazy {
        val db = MitshuHomeDb.getInstance().dao
        val factory = MitsubishiHomeViewModelFactory(db)
        ViewModelProvider(this, factory)[MitsubishiHomeViewModel::class.java]
    }
    private lateinit var myAdapter: MitsubishiHomeAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMitsubishiHomeBinding.inflate(
            layoutInflater, container, false
        )
        myAdapter = MitsubishiHomeAdapter(ItemListener { item ->
            view?.findNavController()?.navigate(MitsubishiHomeFragmentDirections.actionMitsubishiHomeFragmentToTransaksi(item.nama))
        })
        with(binding.recyclerView){
            adapter = myAdapter
            setHasFixedSize(true)
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.data.observe(viewLifecycleOwner, {
            myAdapter.updateData(it)
        })
    }

}