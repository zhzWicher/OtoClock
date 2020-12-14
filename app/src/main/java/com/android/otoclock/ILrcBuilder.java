package com.android.otoclock;

import java.util.List;

public interface ILrcBuilder {
    //解析歌词得到LrcRow的list
    List<LrcRow> getLrcRows (String lrcString);
}
