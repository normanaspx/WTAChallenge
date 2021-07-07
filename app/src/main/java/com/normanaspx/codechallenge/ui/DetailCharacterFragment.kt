package com.normanaspx.codechallenge.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.normanaspx.codechallenge.R
import com.normanaspx.codechallenge.databinding.FragmentCharacterBinding
import com.normanaspx.codechallenge.databinding.FragmentDetailCharacterBinding
import com.normanaspx.codechallenge.model.Character
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class DetailCharacterFragment : Fragment() {


    private val args: DetailCharacterFragmentArgs by navArgs()
    private val characterViewModel by activityViewModels<CharacterViewModel>()

    private var _binding: FragmentDetailCharacterBinding? =null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detail_character, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentDetailCharacterBinding.bind(view)

        binding.apply {
            val character: Character = args.item

            Glide.with(this@DetailCharacterFragment)
                .load(args.item.img)
                .error(R.drawable.ic_error)
                .fitCenter()
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(imgDetailCharacter)

            txtDetailName.text = character.name
            txtDetailNickname.text = character.nickname
            txtDetailStatus.text= character.status
            txtDetailOccupations.text = character.occupation.joinToString (", " )

            checkLike( character, imgDetailLike)

            imgDetailLike.setOnClickListener{
                character.isLiked = !character.isLiked
                checkLike( character, imgDetailLike)
                characterViewModel.update(character)
            }
        }

    }

    fun checkLike(character: Character, img: ImageView){
        if(character.isLiked){
            Glide.with(this)
                .load(R.drawable.ic_baseline_favorite_red_24)
                .fitCenter()
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(img)
        }else{
            Glide.with(this)
                .load(R.drawable.ic_baseline_favorite_border_24)
                .fitCenter()
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(img)
        }
    }

}