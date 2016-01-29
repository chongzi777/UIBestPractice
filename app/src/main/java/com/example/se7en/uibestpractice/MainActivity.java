package com.example.se7en.uibestpractice;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ListView listView;
    private EditText editText;
    private Button button;
    private MsgAdapter msgAdapter;

    private List<Msg> msgList = new ArrayList<Msg>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initMsg();

        msgAdapter = new MsgAdapter(MainActivity.this,R.layout.msg_item,msgList);
        listView = (ListView)findViewById(R.id.msg_list_view);
        listView.setAdapter(msgAdapter);

        editText = (EditText)findViewById(R.id.input_text);
        button = (Button)findViewById(R.id.send);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String content = editText.getText().toString();
                if(!"".equals(content)){
                    Msg msg = new Msg(content,Msg.TYPE_SEND);
                    msgList.add(msg);
                    msgAdapter.notifyDataSetChanged();   //有新消息，刷新消息列表
                    listView.setSelection(msgList.size());  //消息列表定位到最后一行
                    editText.setText("");  //清空消息输入框
                }
            }
        });
    }

    public void initMsg(){
        Msg msg1 = new Msg("hello guy",Msg.TYPE_RECEIVED);
        msgList.add(msg1);
        Msg msg2 = new Msg("hello,who is that?",Msg.TYPE_SEND);
        msgList.add(msg2);
        Msg msg3 = new Msg("this is tom,nice talking to you",Msg.TYPE_RECEIVED);
        msgList.add(msg3);
    }
}
