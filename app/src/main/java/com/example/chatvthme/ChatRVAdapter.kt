package com.example.chatvthme

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.chatvthme.ChatRVAdapter.BotViewHolder
import com.example.chatvthme.ChatRVAdapter.UserViewHolder


class ChatRVAdapter(messageModalArrayList: ArrayList<ChatsModal>, context: Context) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    // variable for our array list and context.
    private val messageModalArrayList: ArrayList<ChatsModal>
    private val context: Context
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view: View
        if (viewType==0)
             {
                view = LayoutInflater.from(parent.context).inflate(R.layout.user_msg_rv_item, parent, false)
                return UserViewHolder(view)
            }
        else {
                // below line we are inflating bot message layout.
                view = LayoutInflater.from(parent.context).inflate(R.layout.bot_msg_rv_item, parent, false)
                return BotViewHolder(view)
            }

    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        // this method is use to set data to our layout file.
        val modal: ChatsModal = messageModalArrayList[position]
        when (modal.sender) {
            "user" ->                // below line is to set the text to our text view of user layout
                (holder as UserViewHolder).userTV.setText(modal.message)
            "bot" ->                // below line is to set the text to our text view of bot layout
                (holder as BotViewHolder).botTV.setText(modal.message)
        }
    }

    override fun getItemCount(): Int {
        // return the size of array list
        return messageModalArrayList.size
    }

    override fun getItemViewType(position: Int): Int {
        // below line of code is to set position.
        return when (messageModalArrayList[position].sender) {
            "user" -> 0
            "bot" -> 1
            else -> -1
        }
    }

    class UserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        // creating a variable
        // for our text view.
        var userTV: TextView
        init {
            userTV = itemView.findViewById(R.id.idTvUser)
        }
    }
    class BotViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        // creating a variable
        // for our text view.
        var botTV: TextView

        init {
            // initializing with id.
            botTV = itemView.findViewById(R.id.idTvBot)
        }
    }

    // constructor class.
    init {
        this.messageModalArrayList = messageModalArrayList
        this.context = context
    }


}


