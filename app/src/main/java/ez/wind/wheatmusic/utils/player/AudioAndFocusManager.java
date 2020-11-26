package ez.wind.wheatmusic.utils.player;

import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.AudioFocusRequest;
import android.media.AudioManager;
import android.media.session.MediaSession;
import android.os.Build;
import android.util.Log;


import androidx.annotation.RequiresApi;

import ez.wind.wheatmusic.service.MusicPlayerService;


/**
 * 音频管理类
 * 主要用来管理音频焦点
 */
@RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
public class AudioAndFocusManager {

    private AudioManager mAudioManager;
    private ComponentName mediaButtonReceiverComponent;
    private PendingIntent mPendingIntent;
    private MediaSession mediaSession;
    private MusicPlayerService.MusicPlayerHandler mHandler;
    public AudioAndFocusManager(Context mContext, MusicPlayerService.MusicPlayerHandler mHandler) {
        this.mHandler = mHandler;
        initAudioManager(mContext);
    }

    /**
     * 初始化AudioManager&Receiver
     *
     * @param mContext
     */

    private void initAudioManager(Context mContext) {
        mediaSession = new MediaSession(mContext, "AudioAndFocusManager");
        mAudioManager = (AudioManager) mContext.getSystemService(Context.AUDIO_SERVICE);
        mediaButtonReceiverComponent = new ComponentName(mContext.getPackageName(),
                MediaButtonIntentReceiver.class.getName());
        mContext.getPackageManager().setComponentEnabledSetting(mediaButtonReceiverComponent,
                PackageManager.COMPONENT_ENABLED_STATE_ENABLED, PackageManager.DONT_KILL_APP);
        mAudioManager.registerMediaButtonEventReceiver(mediaButtonReceiverComponent);
        Intent mediaButtonIntent = new Intent(Intent.ACTION_MEDIA_BUTTON);
        mediaButtonIntent.setComponent(mediaButtonReceiverComponent);
        mPendingIntent = PendingIntent.getBroadcast(mContext, 0,
                mediaButtonIntent, PendingIntent.FLAG_CANCEL_CURRENT);

        mediaSession.setMediaButtonReceiver(mPendingIntent);
    }

    /**
     * 请求音频焦点
     */
    public void requestAudioFocus() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            AudioFocusRequest mAudioFocusRequest = new AudioFocusRequest.Builder(AudioManager.AUDIOFOCUS_GAIN)
                    .setOnAudioFocusChangeListener(audioFocusChangeListener)
                    .build();
            int res = mAudioManager.requestAudioFocus(mAudioFocusRequest);
            if (res == 1) {
            }
        } else {
            if (audioFocusChangeListener != null) {
                boolean result = AudioManager.AUDIOFOCUS_REQUEST_GRANTED ==
                        mAudioManager.requestAudioFocus(audioFocusChangeListener,
                                AudioManager.STREAM_MUSIC,
                                AudioManager.AUDIOFOCUS_GAIN);
                Log.e("AudioManager","requestAudioFocus=" + result);
            }
        }
    }

    /**
     * 关闭音频焦点
     */
    public void abandonAudioFocus() {
        if (audioFocusChangeListener != null) {
            boolean result = AudioManager.AUDIOFOCUS_REQUEST_GRANTED ==
                    mAudioManager.abandonAudioFocus(audioFocusChangeListener);
            Log.e("AudioManager","AudioManager=" + result);
        }
    }


    /**
     * 音频焦点改变监听器
     */
    private AudioManager.OnAudioFocusChangeListener audioFocusChangeListener = new AudioManager.OnAudioFocusChangeListener() {
        @Override
        public void onAudioFocusChange(int focusChange) {
            Log.e("AudioManager", focusChange + "---");
            mHandler.obtainMessage(MusicPlayerService.AUDIO_FOCUS_CHANGE, focusChange, 0).sendToTarget();
        }
    };

}
