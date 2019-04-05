package com.quiz.helper;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.Array;

public class Timer{
    int _id;
    static int ID_COUNT = 0;

    static final float TICK = 1/60f;

    float cur_time;
    float max_time;

    int state;
    //0 = not running
    //1 = running
    //2 = finished
    //3 = after-finished
    //4 = paused
    static int STATE_RUNNING = 1;
    static int STATE_FINISHED = 2;

    int prev_state;

    Array<Timer> timers;

    /* Initializer */
    public Timer(){
        state = 0;
        prev_state = 0;

//        timers.add(this);

        _id = ID_COUNT++;
    }

    public void startTimer(float seconds){
        cur_time = 0f;
        max_time = seconds;
        state = 1;
        prev_state = 1;
    }

    /* States */
    public boolean isRunning(){
        return state == 1;
    }
    public boolean isNewlyFinished(){
        if(state == 2){ state = 3; return true; }
        return false;
    }
    public boolean isFinished(){
        return state == 3 || state == 2;
    }
    public boolean isPaused(){
        return state == 4;
    }

    public void pause(){
        prev_state = state;
        state = 4;

        //Remove from active timers
    }
    public void unpause(){
        state = prev_state;

        //Add to active timers
    }

    public float getCurTime(){ return cur_time; }
    public float getMaxTime(){ return max_time; }

    public float getPercentage(){ if(max_time == 0f)return 0; return cur_time / max_time; }

    public void tick(){
        if(state == STATE_RUNNING) {
            cur_time += TICK;

            if (cur_time >= max_time){
                cur_time = max_time;
                state = STATE_FINISHED;
            }
        }
    }

    public static void updateTimers(){
        //Loop through all active timers and update time
    }

    public void debug(){
        Gdx.app.log("timer instance ("+Integer.toString(_id)+")", "cur/max: "+Float.toString(cur_time)+"/"+Float.toString(max_time));
    }
}