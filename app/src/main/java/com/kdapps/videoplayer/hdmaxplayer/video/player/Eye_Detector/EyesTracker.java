package com.kdapps.videoplayer.hdmaxplayer.video.player.Eye_Detector;

import static androidx.constraintlayout.widget.Constraints.TAG;

import android.content.Context;
import android.util.Log;

import com.google.android.gms.vision.Detector;
import com.google.android.gms.vision.Tracker;
import com.google.android.gms.vision.face.Face;
import com.kdapps.videoplayer.hdmaxplayer.video.player.Activity.MainActivity;
import com.kdapps.videoplayer.hdmaxplayer.video.player.Extra.VideoStanderd;

public class EyesTracker extends Tracker<Face> {
    private final float THRESHOLD = 0.75f;
    private Context context;
    private VideoStanderd videoStanderd;



    public EyesTracker(Context context) {
        this.context = context;
    }
//
    @Override
    public void onUpdate(Detector.Detections<Face> detections, Face face) {
        videoStanderd=new VideoStanderd(context);
        if (face.getIsLeftEyeOpenProbability() > THRESHOLD || face.getIsRightEyeOpenProbability() > THRESHOLD) {
            Log.i(TAG, "onUpdate: Open Eyes Detected");
            videoStanderd.updateMainView(Condition.USER_EYES_OPEN);
        }
        else {
            Log.i(TAG, "onUpdate: Close Eyes Detected");
            videoStanderd.updateMainView(Condition.USER_EYES_CLOSED);
        }
    }

    @Override
    public void onMissing(Detector.Detections<Face> detections) {
        super.onMissing(detections);

        Log.i(TAG, "onUpdate: Face Not Detected!");
        videoStanderd.updateMainView(Condition.FACE_NOT_FOUND);
    }

    @Override
    public void onDone() {
        super.onDone();
    }
}
