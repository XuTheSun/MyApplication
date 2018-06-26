package com.example.athis.myapplication.MediaPlayer;

import android.Manifest;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.TextView;
import android.widget.VideoView;

import com.example.athis.myapplication.Base.BaseActivity;
import com.example.athis.myapplication.R;
import com.example.athis.myapplication.utils.FilePathUtils;
import com.shuyu.gsyvideoplayer.video.StandardGSYVideoPlayer;

import java.io.File;

import butterknife.BindView;
import cn.jzvd.JZVideoPlayerStandard;

public class MediaPlayActivity extends BaseActivity {

    @BindView(R.id.tv_desc)
    TextView tvDesc;
    @BindView(R.id.video)
    JZVideoPlayerStandard tvVideo;
    @BindView(R.id.video_g)
    StandardGSYVideoPlayer videoG;

    final String[] permissions = {Manifest.permission.WRITE_EXTERNAL_STORAGE};

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            String[] permissions = {Manifest.permission.WRITE_EXTERNAL_STORAGE};
            requestPermissions(permissions, 1110);
        }

//        tvVideo.setUp
//                ("http://static.verygrow.com/bigc/mv/20180625/0_141854536976.mp4"
//                        , JZVideoPlayerStandard.SCREEN_WINDOW_NORMAL, "食神");
        File cache = new File(FilePathUtils.getSdCardPath()+"/vgmap/cache");
        videoG.setUp("http://9890.vod.myqcloud.com/9890_4e292f9a3dd011e6b4078980237cc3d3.f30.mp4",
                true, "Title");
    }

    @Override
    public int getResLayout() {
        return R.layout.activity_media_play;
    }
}
