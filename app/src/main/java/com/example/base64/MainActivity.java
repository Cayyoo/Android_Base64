package com.example.base64;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Base64;
import android.util.Log;
import android.view.View;

import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


/**
 * Base64编码算法是一种用64个字符（ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/）来表示任意二进制数据的方法。
 * 
 * 在加解密算法中，原始的数据和加密后的数据一般也是二进制数据，为了不传输出错，方便保存或者调试代码，一般需要对加密后的数据进行base64编码。 
 *
 * base64只是一种编码方式，并不是一种加密算法，不要使用base64来加密数据。
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    //编码再解码
    private EditText et_encrypt,et_decryption;
    private EditText et_decrypt_data,et_decrypt_result;

    //直接解码
    private Button btn_base64,btn_clear;
    private Button btn_base64_decrypt,btn_clear01;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        assert fab != null;
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        initView();
        initListener();

    }

    public void initView(){
        //编码再解码
        et_encrypt= (EditText) this.findViewById(R.id.et_encrypt);
        et_decryption= (EditText) this.findViewById(R.id.et_decryption);
        btn_base64= (Button) this.findViewById(R.id.btn_base64);
        btn_clear= (Button) this.findViewById(R.id.btn_clear);

        //直接解码
        et_decrypt_data= (EditText) this.findViewById(R.id.et_decrypt_data);
        et_decrypt_result= (EditText) this.findViewById(R.id.et_decrypt_result);
        btn_base64_decrypt= (Button) this.findViewById(R.id.btn_base64_decrypt);
        btn_clear01= (Button) this.findViewById(R.id.btn_clear01);
    }

    public void initListener(){
        btn_base64.setOnClickListener(this);
        btn_clear.setOnClickListener(this);
        btn_base64_decrypt.setOnClickListener(this);
        btn_clear01.setOnClickListener(this);
    }

    /**
     * 编码再解码
     */
    public void getData(){
        String strEncrypt=et_encrypt.getText().toString();//获得要编码的数据
        et_decryption.setText(useBase64(strEncrypt));//显示编码、解码的值
    }

    /**
     * 直接解码
     */
    public void decryptData(){
        String strDecrypt=et_decrypt_data.getText().toString();
        et_decrypt_result.setText(decryptBase64(strDecrypt));

        String str=et_decrypt_result.getText().toString();
        if (str.equals("")){
            Toast.makeText(this,"暂无解码结果",Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * Base64编码、解码
     */
    public static String useBase64(String str){
        //加密传入的数据是byte类型的，并非使用decode方法将原始数据转二进制，String类型的数据使用str.getBytes()即可获得byte类型

        //加密一：使用encode方式，返回的是byte类型加密数据，可使用new String转为String类型
        String strBase64 = new String(Base64.encode(str.getBytes(), Base64.DEFAULT));
        Log.i("Test", "encode >>>编码：" + strBase64);

        //加密二：使用encodeToString直接返回String类型的加密数据
        String enToStr = Base64.encodeToString(str.getBytes(), Base64.DEFAULT);
        Log.i("Test", "encodeToString >>>编码： " + enToStr);

        // 对base64加密后的数据进行解密
        String strDecryptBase64=new String(Base64.decode(strBase64.getBytes(), Base64.DEFAULT));
        Log.i("Test", "decode >>>解码：" + strDecryptBase64);

        String s=" encode编码："+strBase64+"\n encodeToString编码："+enToStr+"\n 解码："+strDecryptBase64;
        return s;
    }

    /**
     * Base64不编码，直接解码
     */
    public static String decryptBase64(String str){
        // 直接使用base64进行解码
        String strDecryptBase64=new String(Base64.decode(str.getBytes(), Base64.DEFAULT));
        Log.i("Test", "decode >>>不编码直接解码：" + strDecryptBase64);

        return strDecryptBase64;
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            //编码再解码
            case R.id.btn_base64:
                getData();
                break;
            //清空
            case R.id.btn_clear:
                et_encrypt.setText("");
                et_decryption.setText("");
                break;
            //直接解码
            case R.id.btn_base64_decrypt:
                decryptData();
                break;
            //清空
            case R.id.btn_clear01:
                et_decrypt_data.setText("");
                et_decrypt_result.setText("");
                break;
            default:
                break;
        }
    }

}
