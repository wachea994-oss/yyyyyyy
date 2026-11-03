package com.example.videowallpaper;

import android.media.MediaPlayer;
import android.service.wallpaper.WallpaperService;
import android.view.SurfaceHolder;
import java.io.IOException;

public class VideoWallpaperService extends WallpaperService {
    @Override
    public Engine onCreateEngine() {
        return new VideoWallpaperEngine();
    }

    private class VideoWallpaperEngine extends Engine {
        private MediaPlayer mediaPlayer;

        @Override
        public void onCreate(SurfaceHolder surfaceHolder) {
            super.onCreate(surfaceHolder);
        }

        @Override
        public void onSurfaceCreated(SurfaceHolder holder) {
            super.onSurfaceCreated(holder);
            mediaPlayer = new MediaPlayer();
            mediaPlayer.setSurface(holder.getSurface());
            try {
                // پخش ویدیوی نمونه یا پیش‌فرض
                mediaPlayer.setDataSource("android.resource://" + getPackageName() + "/" + R.raw.sample_video);
                mediaPlayer.setLooping(true);
                mediaPlayer.setVolume(0f, 0f); // بی‌صدا برای پس‌زمینه
                mediaPlayer.prepare();
                mediaPlayer.start();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onDestroy() {
            super.onDestroy();
            if (mediaPlayer != null) {
                mediaPlayer.stop();
                mediaPlayer.release();
                mediaPlayer = null;
            }
        }
    }
}
