package me.ztiany.imageloader;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.base.image.DisplayConfig;
import com.android.base.image.ImageLoader;
import com.android.base.image.ImageLoaderFactory;
import com.android.base.image.LoadListener;
import com.android.base.image.ProgressListener;
import com.bumptech.glide.Glide;

import java.util.Arrays;
import java.util.List;

import me.ztiany.base.imageloader.exmpale.R;


public class ImageListFragment extends Fragment {

    private final ImageLoader mImageLoader = ImageLoaderFactory.getImageLoader();

    private final DisplayConfig mDisplayConfig = DisplayConfig.create()
            .setErrorDrawable(new ColorDrawable(Color.BLUE))
            .setLoadingDrawable(new ColorDrawable(Color.RED));

    private final List<String> strings = Arrays.asList(
            "http://www.noaanews.noaa.gov/stories/images/goes-12%2Dfirstimage-large081701%2Ejpg",
            "http://www.spektyr.com/PrintImages/Cerulean%20Cross%203%20Large.jpg",
            "https://cdn.photographylife.com/wp-content/uploads/2014/06/Nikon-D810-Image-Sample-6.jpg",
            "https://upload.wikimedia.org/wikipedia/commons/5/5b/Ultraviolet_image_of_the_Cygnus_Loop_Nebula_crop.jpg",
            "https://upload.wikimedia.org/wikipedia/commons/c/c5/Polarlicht_2_kmeans_16_large.png",
            "https://www.hq.nasa.gov/alsj/a15/M1123519889LCRC_isometric_min-8000_g0dot5_enhanced_labeled.jpg",
            "http://oceanexplorer.noaa.gov/explorations/02fire/logs/hirez/octopus_hires.jpg",
            "https://upload.wikimedia.org/wikipedia/commons/b/bf/GOES-13_First_Image_jun_22_2006_1730Z.jpg",
            "http://www.zastavki.com/pictures/originals/2013/Photoshop_Image_of_the_horse_053857_.jpg",
            "http://www.marcogiordanotd.com/blog/wp-content/uploads/2014/01/image9Kcomp.jpg",
            "https://cdn.photographylife.com/wp-content/uploads/2014/06/Nikon-D810-Image-Sample-7.jpg",
            "https://www.apple.com/v/imac-with-retina/a/images/overview/5k_image.jpg",
            "https://www.gimp.org/tutorials/Lite_Quickies/lordofrings_hst_big.jpg",
            "http://www.cesbio.ups-tlse.fr/multitemp/wp-content/uploads/2015/07/Mad%C3%A8re-022_0_1.jpg",
            "https://www.spacetelescope.org/static/archives/fitsimages/large/slawomir_lipinski_04.jpg",
            "https://upload.wikimedia.org/wikipedia/commons/b/b4/Mardin_1350660_1350692_33_images.jpg",
            "http://4k.com/wp-content/uploads/2014/06/4k-image-tiger-jumping.jpg"
    );

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        assert container != null;
        RecyclerView list = new RecyclerView(container.getContext());
        list.setLayoutParams(new ViewGroup.MarginLayoutParams(ViewGroup.MarginLayoutParams.MATCH_PARENT, ViewGroup.MarginLayoutParams.MATCH_PARENT));
        list.setLayoutManager(new LinearLayoutManager(container.getContext()));
        list.setAdapter(new ProgressAdapter(strings));
        return list;
    }

    private class ProgressViewHolder extends RecyclerView.ViewHolder {

        private final ImageView image;
        private final TextView text;
        private final LoadListener<Drawable> mLoadListenerAdapter;
        private final ProgressListener mProgressListener;

        @SuppressLint("SetTextI18n")
        ProgressViewHolder(View root) {
            super(root);
            image = root.findViewById(R.id.image);
            text = root.findViewById(R.id.text);

            mProgressListener = (url, progressInfo) ->
                    text.setText(progressInfo.getContentLength() + "--" + progressInfo.getCurrentBytes() + "--" + progressInfo.getProgress() + "--" + progressInfo.isFinished() + "++" + progressInfo.getId());

            mLoadListenerAdapter = new LoadListener<>() {

                @SuppressLint("SetTextI18n")
                @Override
                public void onLoadFail() {
                    text.setText("Error");
                }

                @Override
                @SuppressLint("SetTextI18n")
                public void onLoadSuccess(Drawable bitmap) {
                    text.setText("Success");
                }

                @Override
                @SuppressLint("SetTextI18n")
                public void onLoadStart() {
                    text.setText("Start");
                }
            };
        }

        void bind(String url) {
            text.setTextSize(10);
            text.setText("");
            mImageLoader.setListener(url, mProgressListener);
            mImageLoader.display(ImageListFragment.this, image, url, mDisplayConfig, mLoadListenerAdapter);
        }
    }

    private class ProgressAdapter extends RecyclerView.Adapter<ProgressViewHolder> {

        private final List<String> models;

        ProgressAdapter(List<String> models) {
            this.models = models;
        }

        @NonNull
        @Override
        public ProgressViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_github_232_item, parent, false);
            return new ProgressViewHolder(view);
        }

        @Override
        public void onBindViewHolder(ProgressViewHolder holder, int position) {
            holder.bind(models.get(position));
        }

        @Override
        public int getItemCount() {
            return models.size();
        }
    }

}