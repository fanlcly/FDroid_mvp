package fancy.com.rxmvp.mvp;

import android.content.Context;
import android.view.View;
import android.widget.Toast;

/**
 * V层委拖类的实现
 *
 * @author fanlei
 * @version 1.0 2019/4/16 0016
 * @since JDK 1.8
 */
public class VDelegateImpl implements VDelegate{
    private Context context;

    private VDelegateImpl(Context context) {
        this.context = context;
    }

    public static VDelegate create(Context context) {
        return new VDelegateImpl(context);
    }

    @Override
    public void resume() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void destory() {

    }

    @Override
    public void visible(boolean flag, View view) {
        if (flag) view.setVisibility(View.VISIBLE);
    }

    @Override
    public void gone(boolean flag, View view) {
        if (flag) view.setVisibility(View.GONE);
    }

    @Override
    public void inVisible(View view) {
        view.setVisibility(View.INVISIBLE);
    }

    @Override
    public void toastShort(String msg) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void toastLong(String msg) {
        Toast.makeText(context, msg, Toast.LENGTH_LONG).show();
    }
}
