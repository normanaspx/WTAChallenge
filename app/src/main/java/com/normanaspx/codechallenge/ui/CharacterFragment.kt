package com.normanaspx.codechallenge.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.normanaspx.codechallenge.R
import com.normanaspx.codechallenge.databinding.FragmentCharacterBinding
import com.normanaspx.codechallenge.databinding.FragmentDetailCharacterBinding
import com.normanaspx.codechallenge.model.Character
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class CharacterFragment : Fragment(), ClickListener {

    private val characterViewModel  by activityViewModels<CharacterViewModel>()

    private var _binding: FragmentCharacterBinding? =null
    private val binding get() = _binding!!

    var adapter: CharacterAdapter =
        CharacterAdapter(this) { character ->
            findNavController().navigate(
                CharacterFragmentDirections.actionCharacterFragmentToDetailCharacterFragment(character)
            )
        }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        characterViewModel.refreshData()
        return inflater.inflate(R.layout.fragment_character, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentCharacterBinding.bind(view)

        binding.apply {
            rvCharacters.setHasFixedSize(false)
            rvCharacters.adapter = adapter
        }


        characterViewModel.characters.observe(
            viewLifecycleOwner, { characters ->
                adapter.submitList(characters)
            }
        )
    }



    override fun onUpdateButton(character: Character) {
        characterViewModel.update(character)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}