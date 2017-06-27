package app.yellow.test;

import android.app.Activity;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
    /**
     * Called when the activity is first created.
     */
    private TextView mClickableText;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LinearLayout layout = new LinearLayout(this);
        mClickableText = new TextView(this);
        mClickableText.setClickable(true);
        mClickableText.setTextSize(20);

        layout.addView(mClickableText);
        setContentView(layout);
        mClickableText.setText(getClickableSpan());
        mClickableText.setMovementMethod(LinkMovementMethod.getInstance());
    }

    private SpannableString getClickableSpan() {
        View.OnClickListener l = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Click Success", Toast.LENGTH_SHORT).show();
            }
        };

        SpannableString spanableInfo = new SpannableString("This is a test, Click Me");
        int start = 16;
        int end = spanableInfo.length();
        spanableInfo.setSpan(new Clickable(l), start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        return spanableInfo;
    }
}

class Clickable extends ClickableSpan implements OnClickListener {
    private final View.OnClickListener mListener;

    public Clickable(View.OnClickListener l) {
        mListener = l;
    }

    @Override
    public void onClick(View v) {
        mListener.onClick(v);
    }
}
