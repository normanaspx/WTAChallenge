package com.normanaspx.codechallenge.ui

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.normanaspx.codechallenge.R
import com.normanaspx.codechallenge.model.Character

/**
Created by Norman on 7/7/2021
 **/
class CharacterAdapter( val clickListener: ClickListener,
                        private val clickCallback: ((Character) -> Unit)?
): ListAdapter<Character, CharacterAdapter.CharacterViewHolder>(CharactersComparator()) {

    val onUpdateListener: ClickListener? = clickListener

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CharacterAdapter.CharacterViewHolder {
        return CharacterViewHolder.create(parent, onUpdateListener)
    }

    override fun onBindViewHolder(holder: CharacterAdapter.CharacterViewHolder, position: Int) {
        val character = getItem(position)
        holder.bind(character)
        holder.itemView.setOnClickListener() {
            clickCallback?.invoke(character)
        }
    }

    class CharacterViewHolder(itemView: View, updateClickListener: ClickListener?) : RecyclerView.ViewHolder(itemView) {
        private val nameTv: TextView = itemView.findViewById(R.id.txtName)
        private val nicknameTv: TextView = itemView.findViewById(R.id.txtNickname)
        private val avatar: ImageView = itemView.findViewById(R.id.imgActor)
        private val imgFav: ImageView = itemView.findViewById(R.id.imgFavorite)

        val lister = updateClickListener
        fun bind(character: Character) {

            nameTv.text = character.name
            nicknameTv.text = character.nickname

            if (character.img != null) {
                Glide.with(itemView)
                    .load(character.img)
                    .fitCenter()
                    .error(R.drawable.ic_error)
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .into(avatar)
            }

            //favorite trigger
            checkLike(character, imgFav)
            imgFav.setOnClickListener{
                // Log.e("RecyclerView", "BIND：${character.char_id}");
                character.isLiked = !character.isLiked
                checkLike(character, imgFav)
                lister?.onUpdateButton(character)

            }



        }

        companion object {
            fun create(parent: ViewGroup, onDeleteButtonClickListener: ClickListener?): CharacterViewHolder {
                val view: View = LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_character, parent, false)
                return CharacterViewHolder(view, onDeleteButtonClickListener)
            }
        }

        fun checkLike(character: Character, img: ImageView) {
            if (character.isLiked) {
                Glide.with(itemView)
                    .load(R.drawable.ic_baseline_favorite_red_24)
                    .fitCenter()
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .into(img)
            } else {
                Glide.with(itemView)
                    .load(R.drawable.ic_baseline_favorite_border_24)
                    .fitCenter()
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .into(img)
            }
        }
    }


}

class CharactersComparator: DiffUtil.ItemCallback<Character>(){
    override fun areItemsTheSame(oldItem: Character, newItem: Character): Boolean {
        return oldItem.char_id == newItem.char_id
    }

    override fun areContentsTheSame(oldItem: Character, newItem: Character): Boolean {
        return oldItem.name == newItem.name &&
                oldItem.nickname == newItem.nickname &&
                oldItem.img == newItem.img
                && oldItem.isLiked == newItem.isLiked
    }

}
