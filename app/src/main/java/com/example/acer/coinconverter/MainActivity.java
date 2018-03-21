package com.example.acer.coinconverter;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.acer.coinconverter.MODEL.Coin;
import com.example.acer.coinconverter.REMOTE.CoinService;
import com.jaredrummler.materialspinner.MaterialSpinner;
import com.squareup.picasso.Picasso;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    CoinService mService;

    RadioButton coin2coin,money2coin,coin2money;
    MaterialSpinner fromspinner,tospinner;
    RadioGroup radio;
    Button bttn;
    ImageView coinimage;
    TextView text2;
    String[] money = {"USD","EUR","INR"};
    String[] coin = {"BTC","ETH","LTC","XMR","DASH","MAID","AUR","XEM"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mService = Common.getCoinService();
        fromspinner = (MaterialSpinner) findViewById(R.id.fromspinner);
        tospinner = (MaterialSpinner) findViewById(R.id.tospinner);
        bttn = (Button)findViewById(R.id.bttn);
        text2 = (TextView) findViewById(R.id.text2);
        bttn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calculateValue();

            }
        });
        radio = (RadioGroup) findViewById(R.id.radio);
        radio.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (i==R.id.coin2coin)
                    setcoin2coinSource();
                else if(i==R.id.money2coin)
                    setmoney2coinSource();
                else if(i==R.id.coin2money)
                    setcoin2moneySource();
            }
        });
        coin2coin=(RadioButton)findViewById(R.id.coin2coin);
        money2coin=(RadioButton)findViewById(R.id.money2coin);
        coin2money=(RadioButton)findViewById(R.id.coin2money);
        coinimage=(ImageView)findViewById(R.id.coinimage);
        loadCoinList();
    }

    private void loadCoinList() {
        if(coin2money.isSelected())
            setcoin2moneySource();
        else if(coin2coin.isSelected())
            setcoin2coinSource();
        else if (money2coin.isSelected())
            setmoney2coinSource();
    }

    private void setcoin2moneySource() {
        fromspinner.setItems(coin);
        tospinner.setItems(money);
    }

    private void setmoney2coinSource() {
        fromspinner.setItems(money);
        tospinner.setItems(coin);
    }

    private void setcoin2coinSource() {
        fromspinner.setItems(coin);
        tospinner.setItems(coin);
    }

    private void calculateValue() {

        final ProgressDialog mDialog = new ProgressDialog(MainActivity.this);
        mDialog.setMessage("Please waitig...");
        mDialog.show();

        final String coinName = tospinner.getItems().get(tospinner.getSelectedIndex()).toString();
        String fromCoin = fromspinner.getItems().get(fromspinner.getSelectedIndex()).toString();
        mService.calculateValue(fromCoin,coinName).enqueue(new Callback<Coin>() {
            @Override
            public void onResponse(Call<Coin> call, Response<Coin> response) {
                mDialog.dismiss();
                if (coinName.equals("BTC"))
                    showData(response.body().getBTC());
                if (coinName.equals("ETC"))
                    showData(response.body().getETC());
                if (coinName.equals("LTC"))
                    showData(response.body().getLTC());
                if (coinName.equals("ETH"))
                    showData(response.body().getETH());
                if (coinName.equals("XMR"))
                    showData(response.body().getXMR());
                if (coinName.equals("XEM"))
                    showData(response.body().getXEM());
                if (coinName.equals("DASH"))
                    showData(response.body().getDASH());
                if (coinName.equals("MAID"))
                    showData(response.body().getMAID());
                if (coinName.equals("AUR"))
                    showData(response.body().getAUR());
                if (coinName.equals("USD"))
                    showData(response.body().getUSD());
                if (coinName.equals("INR"))
                    showData(response.body().getINR());
                if (coinName.equals("EUR"))
                    showData(response.body().getEUR());

            }

            @Override
            public void onFailure(Call<Coin> call, Throwable t) {
                Log.e("ERROR",t.getMessage());
                mDialog.dismiss();

            }
        });
    }

    private void showData(String value) {
        if (tospinner.getItems().get(tospinner.getSelectedIndex()).toString().equals("BTC"))
        {

            Picasso.with(MainActivity.this).load(Common.BTC_IMAGE)
                    .into(coinimage);
            text2.setText(value);
        }
        else if (tospinner.getItems().get(tospinner.getSelectedIndex()).toString().equals("ETC"))
        {

            Picasso.with(MainActivity.this).load(Common.ETC_IMAGE)
                    .into(coinimage);
            text2.setText(value);
        }

        else if (tospinner.getItems().get(tospinner.getSelectedIndex()).toString().equals("ETH"))
        {

            Picasso.with(MainActivity.this).load(Common.ETH_IMAGE)
                    .into(coinimage);
            text2.setText(value);
        }else if (tospinner.getItems().get(tospinner.getSelectedIndex()).toString().equals("XMR"))
        {

            Picasso.with(MainActivity.this).load(Common.XMR_IMAGE)
                    .into(coinimage);
            text2.setText(value);
        }

        else if (tospinner.getItems().get(tospinner.getSelectedIndex()).toString().equals("DASH"))
        {

            Picasso.with(MainActivity.this).load(Common.DASH_IMAGE)
                    .into(coinimage);
            text2.setText(value);
        }
        else if (tospinner.getItems().get(tospinner.getSelectedIndex()).toString().equals("XEM"))
        {

            Picasso.with(MainActivity.this).load(Common.XEM_IMAGE)
                    .into(coinimage);
            text2.setText(value);
        }

        else if (tospinner.getItems().get(tospinner.getSelectedIndex()).toString().equals("MAID"))
        {

            Picasso.with(MainActivity.this).load(Common.MAID_IMAGE)
                    .into(coinimage);
            text2.setText(value);
        }
        else if (tospinner.getItems().get(tospinner.getSelectedIndex()).toString().equals("AUR"))
        {

            Picasso.with(MainActivity.this).load(Common.AUR_IMAGE)
                    .into(coinimage);
            text2.setText(value);

        }

        else if (tospinner.getItems().get(tospinner.getSelectedIndex()).toString().equals("LTC"))
        {

            Picasso.with(MainActivity.this).load(Common.LTC_IMAGE)
                    .into(coinimage);
            text2.setText(value);
        }
        else if (tospinner.getItems().get(tospinner.getSelectedIndex()).toString().equals("USD"))
        {


            text2.setText("$"  +  value);
        }

        else if (tospinner.getItems().get(tospinner.getSelectedIndex()).toString().equals("INR"))
        {


            text2.setText("Rs"  +  value);
        }
        else if (tospinner.getItems().get(tospinner.getSelectedIndex()).toString().equals("EUR"))
        {


            text2.setText("EUR"   +  value);
        }



    }
}
