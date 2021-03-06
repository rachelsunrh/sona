/*
 * Tencent is pleased to support the open source community by making Angel available.
 *
 * Copyright (C) 2017-2018 THL A29 Limited, a Tencent company. All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in
 * compliance with the License. You may obtain a copy of the License at
 *
 * https://opensource.org/licenses/Apache-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 *
 */
package org.apache.spark.angel.ml.tree.stats.quantile;

import java.io.Serializable;

public abstract class QuantileSketch implements Serializable {
    // total number of data items appeared
    protected long n;
    // estimated total number of data items there will be,
    // if not -1, sufficient space will be allocated at once
    protected long estimateN;

    protected float minValue;
    protected float maxValue;

    public QuantileSketch(long estimateN) {
        this.estimateN = estimateN > 0 ? estimateN : -1L;
    }

    public QuantileSketch() {
        this(-1L);
    }

    public abstract void reset();

    public abstract void update(float value);

    public abstract void merge(QuantileSketch other);

    public abstract float getQuantile(float fraction);

    public abstract float[] getQuantiles(float[] fractions);

    public abstract float[] getQuantiles(int evenPartition);

    public abstract float[] tryDistinct(int maxItemNums);

    public boolean isEmpty() {
        return n == 0;
    }

    public long getN() {
        return n;
    }

    public long getEstimateN() {
        return estimateN;
    }

    public float getMinValue() {
        return minValue;
    }

    public float getMaxValue() {
        return maxValue;
    }
}