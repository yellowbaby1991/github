package app.yellow.test;

import android.app.Activity;
import android.os.Bundle;
import android.util.Base64;

import com.mukesh.MarkdownView;

public class MainActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String c = "UmV0cm9maXQKPT09PT09PT0KClR5cGUtc2FmZSBIVFRQIGNsaWVudCBmb3Ig\\nQW5kcm9pZCBhbmQgSmF2YSBieSBTcXVhcmUsIEluYy4KCkZvciBtb3JlIGlu\\nZm9ybWF0aW9uIHBsZWFzZSBzZWUgW3RoZSB3ZWJzaXRlXVsxXS4KCgpEb3du\\nbG9hZAotLS0tLS0tLQoKRG93bmxvYWQgW3RoZSBsYXRlc3QgSkFSXVsyXSBv\\nciBncmFiIHZpYSBNYXZlbjoKYGBgeG1sCjxkZXBlbmRlbmN5PgogIDxncm91\\ncElkPmNvbS5zcXVhcmV1cC5yZXRyb2ZpdDI8L2dyb3VwSWQ+CiAgPGFydGlm\\nYWN0SWQ+cmV0cm9maXQ8L2FydGlmYWN0SWQ+CiAgPHZlcnNpb24+Mi4zLjA8\\nL3ZlcnNpb24+CjwvZGVwZW5kZW5jeT4KYGBgCm9yIEdyYWRsZToKYGBgZ3Jv\\nb3Z5CmNvbXBpbGUgJ2NvbS5zcXVhcmV1cC5yZXRyb2ZpdDI6cmV0cm9maXQ6\\nMi4zLjAnCmBgYAoKU25hcHNob3RzIG9mIHRoZSBkZXZlbG9wbWVudCB2ZXJz\\naW9uIGFyZSBhdmFpbGFibGUgaW4gW1NvbmF0eXBlJ3MgYHNuYXBzaG90c2Ag\\ncmVwb3NpdG9yeV1bc25hcF0uCgpSZXRyb2ZpdCByZXF1aXJlcyBhdCBtaW5p\\nbXVtIEphdmEgNyBvciBBbmRyb2lkIDIuMy4KCgpQcm9HdWFyZAotLS0tLS0t\\nLQoKSWYgeW91IGFyZSB1c2luZyBQcm9HdWFyZCB5b3UgbWlnaHQgbmVlZCB0\\nbyBhZGQgdGhlIGZvbGxvd2luZyBvcHRpb25zOgpgYGAKLWRvbnR3YXJuIG9r\\naW8uKioKLWRvbnR3YXJuIGphdmF4LmFubm90YXRpb24uKioKYGBgCgoKTGlj\\nZW5zZQo9PT09PT09CgogICAgQ29weXJpZ2h0IDIwMTMgU3F1YXJlLCBJbmMu\\nCgogICAgTGljZW5zZWQgdW5kZXIgdGhlIEFwYWNoZSBMaWNlbnNlLCBWZXJz\\naW9uIDIuMCAodGhlICJMaWNlbnNlIik7CiAgICB5b3UgbWF5IG5vdCB1c2Ug\\ndGhpcyBmaWxlIGV4Y2VwdCBpbiBjb21wbGlhbmNlIHdpdGggdGhlIExpY2Vu\\nc2UuCiAgICBZb3UgbWF5IG9idGFpbiBhIGNvcHkgb2YgdGhlIExpY2Vuc2Ug\\nYXQKCiAgICAgICBodHRwOi8vd3d3LmFwYWNoZS5vcmcvbGljZW5zZXMvTElD\\nRU5TRS0yLjAKCiAgICBVbmxlc3MgcmVxdWlyZWQgYnkgYXBwbGljYWJsZSBs\\nYXcgb3IgYWdyZWVkIHRvIGluIHdyaXRpbmcsIHNvZnR3YXJlCiAgICBkaXN0\\ncmlidXRlZCB1bmRlciB0aGUgTGljZW5zZSBpcyBkaXN0cmlidXRlZCBvbiBh\\nbiAiQVMgSVMiIEJBU0lTLAogICAgV0lUSE9VVCBXQVJSQU5USUVTIE9SIENP\\nTkRJVElPTlMgT0YgQU5ZIEtJTkQsIGVpdGhlciBleHByZXNzIG9yIGltcGxp\\nZWQuCiAgICBTZWUgdGhlIExpY2Vuc2UgZm9yIHRoZSBzcGVjaWZpYyBsYW5n\\ndWFnZSBnb3Zlcm5pbmcgcGVybWlzc2lvbnMgYW5kCiAgICBsaW1pdGF0aW9u\\ncyB1bmRlciB0aGUgTGljZW5zZS4KCgogWzFdOiBodHRwOi8vc3F1YXJlLmdp\\ndGh1Yi5pby9yZXRyb2ZpdC8KIFsyXTogaHR0cHM6Ly9zZWFyY2gubWF2ZW4u\\nb3JnL3JlbW90ZV9jb250ZW50P2c9Y29tLnNxdWFyZXVwLnJldHJvZml0MiZh\\nPXJldHJvZml0JnY9TEFURVNUCiBbc25hcF06IGh0dHBzOi8vb3NzLnNvbmF0\\neXBlLm9yZy9jb250ZW50L3JlcG9zaXRvcmllcy9zbmFwc2hvdHMvCg==\\n";

        c = c.replaceAll("\\\\n","");

        byte[] m = Base64.decode(c,Base64.DEFAULT);// 解码后

        MarkdownView markdownView = (MarkdownView) findViewById(R.id.markdown_view);
        markdownView.setMarkDownText(new String((m))); //Displays markdown text

    }
}

