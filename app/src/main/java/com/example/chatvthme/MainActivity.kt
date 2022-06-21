package com.example.chatvthme

import android.annotation.SuppressLint
import android.content.ContentValues.TAG
import android.nfc.Tag
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.ArrayList
@SuppressLint("NotifyDataSetChanged")
class MainActivity : AppCompatActivity() {
    private val BOT_KEY:String="bot"
    private val USER_KEY:String="user"
    val messageModalArrayList: ArrayList<ChatsModal> = ArrayList<ChatsModal>()
    val chatRVAdapter:ChatRVAdapter= ChatRVAdapter(messageModalArrayList,this)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val chatsRV:RecyclerView=findViewById(R.id.idRVChats)
        val userMsgEdt:EditText=findViewById(R.id.idEdtMessage)
        val sendMsgFAB:FloatingActionButton=findViewById(R.id.idFABSend)
        val manager=LinearLayoutManager(this)
        chatsRV.layoutManager=manager
        chatsRV.adapter=chatRVAdapter
        sendMsgFAB.setOnClickListener(){
            if(userMsgEdt.text.toString().isEmpty()){
                Toast.makeText(this,"please enter the message",Toast.LENGTH_SHORT).show()
            }
            val message=userMsgEdt.text.toString()
            messageModalArrayList.add(ChatsModal(message,USER_KEY))
            chatRVAdapter.notifyDataSetChanged()
            val url:String="http://api.brainshop.ai/get?bid=167083&key=jIpZgrpRgALYSZ72&uid=[uid]&msg="+message
            val BASE_URL="http://api.brainshop.ai/"
            val retrofit=Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            val retrofitAPI=retrofit.create(RetrofitAPI::class.java)
            val call:retrofit2.Call<MsgModal> = retrofitAPI.getMessage(url)

            call.enqueue(object: Callback<MsgModal>{
                override fun onResponse(call: retrofit2.Call<MsgModal>, response: Response<MsgModal>) {
                    val modal: MsgModal = response.body()!!
                    messageModalArrayList.plusAssign(ChatsModal(modal.cnt, BOT_KEY))
                    chatRVAdapter.notifyDataSetChanged()
                }
                override fun onFailure(call: retrofit2.Call<MsgModal>, t: Throwable) {
                    messageModalArrayList.add(ChatsModal("please revert your question",BOT_KEY))
                    chatRVAdapter.notifyDataSetChanged()
                     Log.d(TAG, t.message.toString())
                }

            })

        }
            userMsgEdt.setText("")
        }

    }