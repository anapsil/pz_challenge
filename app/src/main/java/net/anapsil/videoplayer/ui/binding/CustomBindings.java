package net.anapsil.videoplayer.ui.binding;

import android.databinding.BindingAdapter;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.util.Pair;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import net.anapsil.videoplayer.R;
import net.anapsil.videoplayer.util.TextWatcherWrapper;

/**
 * @author anapsil
 * @since 1.0.0
 */

public class CustomBindings {
    @BindingAdapter("binding")
    public static void bindText(TextView view, ObservableString text) {
        Pair<ObservableString, TextWatcher> pair = (Pair) view.getTag(R.id.bound);
        if (pair == null || pair.first != text) {
            if (pair != null) {
                view.removeTextChangedListener(pair.second);
            }

            TextWatcherWrapper watcher = new TextWatcherWrapper() {
                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                    text.set(charSequence.toString());
                }
            };

            view.setTag(R.id.bound, new Pair<>(text, watcher));
            view.addTextChangedListener(watcher);
        }

        String newValue = text.get();
        if (!view.getText().toString().equals(newValue)) {
            view.setText(newValue);
        }
    }

    @BindingAdapter("imageUrl")
    public static void loadImage(ImageView view, ObservableString imageUrl) {
        if (!TextUtils.isEmpty(imageUrl.get())) {
            Picasso picasso = new Picasso.Builder(view.getContext())
                    .listener((picasso1, uri, exception) -> Log.e("PICASSO ERROR", exception.getMessage() + " " + uri.getPath()))
                    .build();
            picasso.load(imageUrl.get())
                    .into(view);
        }
    }
}
