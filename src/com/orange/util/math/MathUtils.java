package com.orange.util.math;

import java.util.Random;

import com.orange.util.debug.Debug;

import android.util.FloatMath;

/**
 * (c) OrangeGame 2012 
 * 
 * @author OrangeGame <OGEngine@orangegame.cn>
 */
public final class MathUtils {
	// ===========================================================
	// Constants
	// ===========================================================

	public static final Random RANDOM = new Random(System.nanoTime());

	// ===========================================================
	// Fields
	// ===========================================================

	// ===========================================================
	// Constructors
	// ===========================================================

	// ===========================================================
	// Getter & Setter
	// ===========================================================

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================

	// ===========================================================
	// Methods
	// ===========================================================

	public static final float atan2(final float dY, final float dX) {
		return (float) Math.atan2(dY, dX);
	}

	public static final float radToDeg(final float pRad) {
		return MathConstants.RAD_TO_DEG * pRad;
	}

	public static final float degToRad(final float pDegree) {
		return MathConstants.DEG_TO_RAD * pDegree;
	}

	public static final int signum(final int n) {
		if (n == 0) {
			return 0;
		} else if (n > 0) {
			return 1;
		} else {
			return -1;
		}
	}

	public static final int randomSign() {
		if (RANDOM.nextBoolean()) {
			return 1;
		} else {
			return -1;
		}
	}

	public static final float random(final float pMin, final float pMax) {
		return pMin + RANDOM.nextFloat() * (pMax - pMin);
	}

	/**
	 * @param pMin
	 *            inclusive!
	 * @param pMax
	 *            inclusive!
	 * @return
	 */
	public static final int random(final int pMin, final int pMax) {
		return pMin + RANDOM.nextInt(pMax - pMin + 1);
	}
	
	public static final boolean randomBoolean() {
		return MathUtils.random(0, 1) == 1;
	}

	public static final boolean isPowerOfTwo(final int n) {
		return ((n != 0) && (n & (n - 1)) == 0);
	}

	public static final int nextPowerOfTwo(final float f) {
		return MathUtils.nextPowerOfTwo((int) (FloatMath.ceil(f)));
	}

	public static final int nextPowerOfTwo(final int n) {
		int k = n;

		if (k == 0) {
			return 1;
		}

		k--;

		for (int i = 1; i < 32; i <<= 1) {
			k = k | k >> i;
		}

		return k + 1;
	}

	public static final int sum(final int[] pValues) {
		int sum = 0;
		for (int i = pValues.length - 1; i >= 0; i--) {
			sum += pValues[i];
		}

		return sum;
	}

	public static final void arraySumInternal(final int[] pValues) {
		final int valueCount = pValues.length;
		for (int i = 1; i < valueCount; i++) {
			pValues[i] = pValues[i - 1] + pValues[i];
		}
	}

	public static final void arraySumInternal(final long[] pValues) {
		final int valueCount = pValues.length;
		for (int i = 1; i < valueCount; i++) {
			pValues[i] = pValues[i - 1] + pValues[i];
		}
	}

	public static final void arraySumInternal(final long[] pValues, final long pFactor) {
		pValues[0] = pValues[0] * pFactor;
		final int valueCount = pValues.length;
		for (int i = 1; i < valueCount; i++) {
			pValues[i] = pValues[i - 1] + pValues[i] * pFactor;
		}
	}

	public static final void arraySumInto(final long[] pValues, final long[] pTargetValues, final long pFactor) {
		pTargetValues[0] = pValues[0] * pFactor;
		final int valueCount = pValues.length;
		for (int i = 1; i < valueCount; i++) {
			pTargetValues[i] = pTargetValues[i - 1] + pValues[i] * pFactor;
		}
	}

	public static final float arraySum(final float[] pValues) {
		float sum = 0;
		final int valueCount = pValues.length;
		for (int i = 0; i < valueCount; i++) {
			sum += pValues[i];
		}
		return sum;
	}

	public static final float arrayAverage(final float[] pValues) {
		return MathUtils.arraySum(pValues) / pValues.length;
	}

	public static float[] rotateAroundCenter(final float[] pVertices, final float pRotation, final float pRotationCenterX, final float pRotationCenterY) {
		if (pRotation != 0) {
			final float rotationRad = MathUtils.degToRad(pRotation);
			final float sinRotationRad = FloatMath.sin(rotationRad);
			final float cosRotationInRad = FloatMath.cos(rotationRad);

			for (int i = pVertices.length - 2; i >= 0; i -= 2) {
				final float pX = pVertices[i];
				final float pY = pVertices[i + 1];
				pVertices[i] = pRotationCenterX + (cosRotationInRad * (pX - pRotationCenterX) - sinRotationRad * (pY - pRotationCenterY));
				pVertices[i + 1] = pRotationCenterY + (sinRotationRad * (pX - pRotationCenterX) + cosRotationInRad * (pY - pRotationCenterY));
			}
		}
		return pVertices;
	}

	public static float[] scaleAroundCenter(final float[] pVertices, final float pScaleX, final float pScaleY, final float pScaleCenterX, final float pScaleCenterY) {
		if (pScaleX != 1 || pScaleY != 1) {
			for (int i = pVertices.length - 2; i >= 0; i -= 2) {
				pVertices[i] = pScaleCenterX + (pVertices[i] - pScaleCenterX) * pScaleX;
				pVertices[i + 1] = pScaleCenterY + (pVertices[i + 1] - pScaleCenterY) * pScaleY;
			}
		}

		return pVertices;
	}

	public static float[] rotateAndScaleAroundCenter(final float[] pVertices, final float pRotation, final float pRotationCenterX, final float pRotationCenterY, final float pScaleX,
			final float pScaleY, final float pScaleCenterX, final float pScaleCenterY) {
		MathUtils.rotateAroundCenter(pVertices, pRotation, pRotationCenterX, pRotationCenterY);
		return MathUtils.scaleAroundCenter(pVertices, pScaleX, pScaleY, pScaleCenterX, pScaleCenterY);
	}

	public static float[] revertScaleAroundCenter(final float[] pVertices, final float pScaleX, final float pScaleY, final float pScaleCenterX, final float pScaleCenterY) {
		return MathUtils.scaleAroundCenter(pVertices, 1 / pScaleX, 1 / pScaleY, pScaleCenterX, pScaleCenterY);
	}

	public static float[] revertRotateAroundCenter(final float[] pVertices, final float pRotation, final float pRotationCenterX, final float pRotationCenterY) {
		return MathUtils.rotateAroundCenter(pVertices, -pRotation, pRotationCenterX, pRotationCenterY);
	}

	public static float[] revertRotateAndScaleAroundCenter(final float[] pVertices, final float pRotation, final float pRotationCenterX, final float pRotationCenterY, final float pScaleX,
			final float pScaleY, final float pScaleCenterX, final float pScaleCenterY) {
		MathUtils.revertScaleAroundCenter(pVertices, pScaleX, pScaleY, pScaleCenterX, pScaleCenterY);
		return MathUtils.revertRotateAroundCenter(pVertices, pRotation, pRotationCenterX, pRotationCenterY);
	}

	public static final boolean isInBounds(final int pMinValue, final int pMaxValue, final int pValue) {
		return pValue >= pMinValue && pValue <= pMaxValue;
	}

	public static final boolean isInBounds(final float pMinValue, final float pMaxValue, final float pValue) {
		return pValue >= pMinValue && pValue <= pMaxValue;
	}

	/**
	 * @param pMinValue
	 *            inclusive!
	 * @param pMaxValue
	 *            inclusive!
	 * @param pValue
	 * @return
	 */
	public static final int bringToBounds(final int pMinValue, final int pMaxValue, final int pValue) {
		return Math.max(pMinValue, Math.min(pMaxValue, pValue));
	}

	/**
	 * @param pMinValue
	 *            inclusive!
	 * @param pMaxValue
	 *            inclusive!
	 * @param pValue
	 * @return
	 */
	public static final float bringToBounds(final float pMinValue, final float pMaxValue, final float pValue) {
		return Math.max(pMinValue, Math.min(pMaxValue, pValue));
	}

	/**
	 * @return the euclidean distance between the points (pX1, pY1) and (pX2,
	 *         pY2).
	 */
	public static final float distance(final float pX1, final float pY1, final float pX2, final float pY2) {
		final float dX = pX2 - pX1;
		final float dY = pY2 - pY1;
		return FloatMath.sqrt((dX * dX) + (dY * dY));
	}

	/**
	 * @return the euclidean distance between the origin (0, 0) and (pX, pY).
	 */
	public static final float length(final float pX, final float pY) {
		return FloatMath.sqrt((pX * pX) + (pY * pY));
	}

	/**
	 * @param pX
	 * @param pY
	 * @param pMix
	 *            [0...1]
	 * @return pX * (1 - pMix) + pY * pMix
	 */
	public static final float mix(final float pX, final float pY, final float pMix) {
		return pX * (1 - pMix) + pY * pMix;
	}

	/**
	 * @param pX
	 * @param pY
	 * @param pMix
	 *            [0...1]
	 * @return (int)Math.round(pX * (1 - pMix) + pY * pMix)
	 */
	public static final int mix(final int pX, final int pY, final float pMix) {
		return Math.round(pX * (1 - pMix) + pY * pMix);
	}

	public static final boolean isEven(final int n) {
		return n % 2 == 0;
	}

	public static final boolean isOdd(final int n) {
		return n % 2 == 1;
	}

	public static float dot(final float pXA, final float pYA, final float pXB, final float pYB) {
		return pXA * pXB + pYA * pYB;
	}

	public static float cross(final float pXA, final float pYA, final float pXB, final float pYB) {
		return pXA * pYB - pXB * pYA;
	}

	// ===========================================================
	// 额外添加
	// ===========================================================

	/**
	 * 求以PX1,PY1点为顶点，12点钟方向顶点连线及X2Y2顶点连线的夹角
	 * 
	 * @param pX1
	 * @param pY1
	 * @param pX2
	 * @param pY2
	 * @return
	 */
	public static double getAngleForTwelve(float pX1, float pY1, float pX2, float pY2) {
		if (pX1 == pX2 && pY1 == pY2) {
			return 0;
		}
		double x4 = pX1;
		double y4 = pY1 - 1;
		double ma_x = pX2 - pX1;
		double ma_y = pY2 - pY1;
		double mb_x = x4 - pX1;
		double mb_y = y4 - pY1;
		if (mb_y == 0) {
			mb_y = 1;
		}
		double v1 = (ma_x * mb_x) + (ma_y * mb_y);
		double ma_val = Math.sqrt(ma_x * ma_x + ma_y * ma_y);
		double mb_val = Math.sqrt(mb_x * mb_x + mb_y * mb_y);
		double cosA = v1 / (ma_val * mb_val);
		double angle = Math.acos(cosA) * 180.0f / Math.PI;
		if ((ma_x < 0 && ma_y >= 0) || (ma_x < 0 && ma_y <= 0)) {
			angle = 360.0f - angle;
		}
		return angle;
	}

	/**
	 * 求以PX1,PY1点为顶点，6点钟方向顶点连线及X2Y2顶点连线的夹角
	 * 
	 * @param pX1
	 * @param pY1
	 * @param pX2
	 * @param pY2
	 * @return
	 */
	public static double getAngleForSix(float pX1, float pY1, float pX2, float pY2) {
		if (pX1 == pX2 && pY1 == pY2) {
			return 0;
		}
		double x4 = pX1;
		double y4 = pY1 + 1;
		double ma_x = pX2 - pX1;
		double ma_y = pY2 - pY1;
		double mb_x = x4 - pX1;
		double mb_y = y4 - pY1;
		if (mb_y == 0) {
			mb_y = -1;
		}
		double v1 = (ma_x * mb_x) + (ma_y * mb_y);
		double ma_val = Math.sqrt(ma_x * ma_x + ma_y * ma_y);
		double mb_val = Math.sqrt(mb_x * mb_x + mb_y * mb_y);
		double cosA = v1 / (ma_val * mb_val);
		double angle = Math.acos(cosA) * 180.0f  / Math.PI;
		if ((ma_x > 0 && ma_y >= 0) || (ma_x > 0 && ma_y <= 0)) {
			angle = 360.0f - angle;
		}
		return angle;
	}

	/**
	 * 
	 * @param a
	 *            长轴长度的一半
	 * @param b
	 *            短轴长度的一半
	 * @param aX
	 *            中点坐标
	 * @param aY
	 *            中点坐标
	 * @param angle
	 *            椭圆上某点坐标和中点的连线 与 正长轴的夹角
	 * @return
	 */
	public static Coordinate getCoordinateForOval(float a, float b, float aX, float aY, float angle) {
		float x = a * (float) Math.cos(Math.toRadians(angle));
		float y = b * (float) Math.sin(Math.toRadians(angle));
		Coordinate coordinate = new Coordinate(aX - x, aY - y);
		return coordinate;
	}

	/**
	 * 计算圆形中圆周上某点的坐标
	 * 
	 * @param aX
	 *            顶点坐标X
	 * @param aY
	 *            顶点坐标Y
	 * @param r
	 *            半径
	 * @param aAngle1
	 *            边（顶点与6点钟方向）与边（顶点与所求坐标）的角度
	 */
	public static Coordinate getCoordinateForCircleSix(float aX, float aY, float r, float aAngle1) {
		aAngle1 = aAngle1 + 90;
		float Y = (float) (Math.sin(Math.toRadians(aAngle1)) * r);
		float X = (float) (Math.cos(Math.toRadians(aAngle1)) * r);
		Coordinate coordinate = new Coordinate(aX + X, aY + Y);
		return coordinate;
	}

	public static class Coordinate {

		public float x = 0;
		public float y = 0;

		public Coordinate(float x, float y) {
			this.x = x;
			this.y = y;
		}
	}

	/**
	 * 点到直线的最短距离的判断 点（x0,y0） 到由两点组成的线段（x1,y1） ,( x2,y2 )
	 * 
	 * @param x1
	 *            线段
	 * @param y1
	 *            线段
	 * @param x2
	 *            线段
	 * @param y2
	 *            线段
	 * @param x0
	 *            点
	 * @param y0
	 *            点
	 * @return
	 */
	public static float pointToLine(float x0, float y0, float x1, float y1, float x2, float y2) {
		float space = 0;
		float a, b, c;
		a = distance(x1, y1, x2, y2);// 线段的长度
		b = distance(x1, y1, x0, y0);// (x1,y1)到点的距离
		c = distance(x2, y2, x0, y0);// (x2,y2)到点的距离
		if (c <= 0.000001 || b <= 0.000001) {
			space = 0;
			return space;
		}
		if (a <= 0.000001) {
			space = b;
			return space;
		}
		if (c * c >= a * a + b * b) {
			space = b;
			return space;
		}
		if (b * b >= a * a + c * c) {
			space = c;
			return space;
		}
		float p = (a + b + c) / 2;// 半周长
		float s = FloatMath.sqrt(p * (p - a) * (p - b) * (p - c));// 海伦公式求面积
		space = 2 * s / a;// 返回点到线的距离（利用三角形面积公式求高）
		return space;
	}

	/**
	 * 求x0是否在正方形内
	 * 
	 * @param x0
	 * @param y0
	 * @param x1
	 * @param y1
	 * @return
	 */
	public static boolean isPointInSquare(float x0, float y0, float[] x1, float[] y1) {
		if (x1.length != y1.length) {
			return false;
		}
		boolean left = false;
		boolean right = false;
		boolean top = false;
		boolean bottom = false;
		for (int i = 0; i < y1.length; i++) {
			if (pointInQuadrant(x0, y0, x1[i], y1[i]) == 7) {
				return true;
			}
			if (pointInQuadrant(x0, y0, x1[i], y1[i]) == 3 || pointInQuadrant(x0, y0, x1[i], y1[i]) == 4 || pointInQuadrant(x0, y0, x1[i], y1[i]) == -5) {
				left = true;
			} else if (pointInQuadrant(x0, y0, x1[i], y1[i]) == 1 || pointInQuadrant(x0, y0, x1[i], y1[i]) == 2 || pointInQuadrant(x0, y0, x1[i], y1[i]) == 5) {
				right = true;
			}
			if (pointInQuadrant(x0, y0, x1[i], y1[i]) == 1 || pointInQuadrant(x0, y0, x1[i], y1[i]) == 4 || pointInQuadrant(x0, y0, x1[i], y1[i]) == -6) {
				top = true;
			} else if (pointInQuadrant(x0, y0, x1[i], y1[i]) == 3 || pointInQuadrant(x0, y0, x1[i], y1[i]) == 2 || pointInQuadrant(x0, y0, x1[i], y1[i]) == 6) {
				bottom = true;
			}
		}
		if ((left && right) && (top && bottom)) {
			return true;
		}
		return false;
	}

	/**
	 * X1在X0的第几象限
	 * 
	 * @param x0
	 * @param y0
	 * @param x1
	 * @param y1
	 * @return -5左边 5右边 -6上边 6下面 7中点
	 */
	public static int pointInQuadrant(float x0, float y0, float x1, float y1) {
		int code = 0;
		if (x0 > x1) {
			if (y0 > y1) {
				code = 4;
			} else if (y0 < y1) {
				code = 3;
			} else {
				code = -5;
			}
		} else if (x0 < x1) {
			if (y0 > y1) {
				code = 1;
			} else if (y0 < y1) {
				code = 2;
			} else {
				code = 5;
			}
		} else {
			if (y0 > y1) {
				code = -6;
			} else if (y0 < y1) {
				code = 6;
			} else {
				code = 7;
			}
		}
		return code;
	}

	/**
	 * 圆到正方形的距离
	 * 
	 * @param x0
	 *            圆中点
	 * @param y0
	 * @param x
	 *            正方形左上顶点
	 * @param y
	 * @param width
	 *            正方形宽
	 * @param height
	 * @param rotation
	 *            正方形转动角度
	 * @return
	 */
	public static int circleToSquare(float x0, float y0, float x, float y, float width, float height, float rotation) {
		float centreX = x + width / 2;
		float centreY = y + height / 2;

		float xx[] = new float[4];
		float yy[] = new float[4];

		float diagonal = FloatMath.sqrt(width * width + height * height) / 2;
		Coordinate coordinate = null;
		coordinate = getCoordinateForCircleSix(centreX, centreY, diagonal, 135 + rotation); // 左上顶点
		float x1 = coordinate.x;
		float y1 = coordinate.y;
		xx[0] = x1;
		yy[0] = y1;
		coordinate = getCoordinateForCircleSix(centreX, centreY, diagonal, 225 + rotation); // 右上顶点
		float x2 = coordinate.x;
		float y2 = coordinate.y;
		xx[1] = x2;
		yy[1] = y2;
		coordinate = getCoordinateForCircleSix(centreX, centreY, diagonal, 315 + rotation); // 右下顶点
		float x3 = coordinate.x;
		float y3 = coordinate.y;
		xx[2] = x3;
		yy[2] = y3;
		coordinate = getCoordinateForCircleSix(centreX, centreY, diagonal, 45 + rotation); // 左下顶点
		float x4 = coordinate.x;
		float y4 = coordinate.y;
		xx[3] = x4;
		yy[3] = y4;

		float space1 = pointToLine(x0, y0, x1, y1, x2, y2);
		float space2 = pointToLine(x0, y0, x1, y1, x4, y4);
		float space3 = pointToLine(x0, y0, x3, y3, x2, y2);
		float space4 = pointToLine(x0, y0, x3, y3, x4, y4);

		float space5 = space1 < space2 ? space1 : space2;
		float space6 = space3 < space4 ? space3 : space4;
		float space = space5 < space6 ? space5 : space6;

		if (isPointInSquare(x0, y0, xx, yy)) {
			return (int) space * -1;
		}
		return (int) space;
	}

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================
}
