package fancy.com.rx_android_mvp.ui;

import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import butterknife.BindView;
import fancy.com.rx_android_mvp.R;
import fancy.com.rxmvp.mvp.XBaseActivity;

/**
 * file explain
 *
 * @author fanlei
 * @version 1.0 2019/4/18 0018
 * @since JDK 1.8
 */
public class TestActivity extends XBaseActivity {

    @BindView(R.id.ll_layout)
    LinearLayout linearLayout;
    @BindView(R.id.tv_view1)
    TextView tvView1;
    @BindView(R.id.tv_view2)
    TextView tvView2;

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        return super.dispatchTouchEvent(ev);
    }


    @Override
    public void initData(Bundle savedInstanceState) {



        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("TestActivity", "onClick: 点击了ViewGroup");
            }
        });

        tvView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("TestActivity", "onClick: 点击了View1");
            }
        });

        tvView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("TestActivity", "onClick: 点击了View2");
            }
        });
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_test;
    }

    @Override
    public Object newP() {
        return null;
    }
}
