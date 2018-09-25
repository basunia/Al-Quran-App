package com.lab.bitech.greentech.quranapp;

public interface PropagatePosition {
    void sendPositionToPagerHolder(int position);

    void sendPositionToNumberFragment(int position, String direction);
}
