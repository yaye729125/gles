package com.orange.entity;

import com.orange.engine.camera.Camera;
import com.orange.engine.handler.IDrawHandler;
import com.orange.engine.handler.IUpdateHandler;
import com.orange.entity.layout.EntityLayout.LayoutParams;
import com.orange.entity.modifier.IEntityModifier;
import com.orange.entity.modifier.IEntityModifier.IEntityModifierMatcher;
import com.orange.entity.scene.ITouchArea;
import com.orange.util.IDisposable;
import com.orange.util.adt.transformation.Transformation;
import com.orange.util.color.Color;


//===========================================================
//把childEntity从本类删除，移动到IEntityGroup类
//===========================================================
/**
 * (c) OrangeGame 2012
 * 
 * 
 * @author OrangeGame <OGEngine@orangegame.cn>
 */
public interface IEntity extends IDrawHandler, IUpdateHandler, IDisposable, ITouchArea {
	// ===========================================================
	// Constants
	// ===========================================================

	public static final int TAG_INVALID = Integer.MIN_VALUE;

	// ===========================================================
	// Methods
	// ===========================================================
	

	public boolean isVisible();
	public void setVisible(final boolean pVisible);

	public boolean isIgnoreUpdate();
	public void setIgnoreUpdate(boolean pIgnoreUpdate);
	
	/**
	 * 是否阻止触摸，默认阻止
	 * 
	 * @return
	 */
	public boolean isIgnoreTouch();
	/**
	 * 设置是否阻止触摸
	 * 
	 * @return
	 */
	public void setIgnoreTouch(boolean pIgnoreTouch);

	public int getTag();
	public void setTag(final int pTag);

	/**
	 * Z坐标是相对父entity的
	 * @param pZIndex
	 */
	public int getZIndex();
	/**
	 * Z坐标是相对父entity的
	 * @param pZIndex
	 */
	public void setZIndex(final int pZIndex);

	public boolean hasParent();
	public IEntity getParent();
	public void setParent(final IEntity pEntity);
	
	public boolean detachSelf();

	public float getX();
	public float getY();
	public void setX(final float pX);
	public void setY(final float pY);

	public void setPosition(final IEntity pOtherEntity);
	public void setPosition(final float pX, final float pY);
	
	public float getWidth();
	public float getHeight();
	
	/**
	 * 获取一半宽
	 * @return
	 */
	public float getWidthHalf();
	/**
	 * 获取一半高
	 * @return
	 */
	public float getHeightHalf();

	public float getWidthScaled();
	public float getHeightScaled();

	/**
	 * 获取缩放后一半宽
	 * @return
	 */
	public float getWidthScaledHalf();
	/**
	 * 获取缩放后一半高
	 * @return
	 */
	public float getHeightScaledHalf();
	
	public void setHeight(final float pHeight);
	public void setWidth(final float pWidth);
	public void setSize(final float pWidth, final float pHeight);

	public boolean isRotated();
	public float getRotation();
	public void setRotation(final float pRotation);

	public float getRotationCenterX();
	public float getRotationCenterY();
	public void setRotationCenterX(final float pRotationCenterX);
	public void setRotationCenterY(final float pRotationCenterY);
	public void setRotationCenter(final float pRotationCenterX, final float pRotationCenterY);

	public boolean isScaled();
	public float getScaleX();
	public float getScaleY();
	public void setScaleX(final float pScaleX);
	public void setScaleY(final float pScaleY);
	public void setScale(final float pScale);
	public void setScale(final float pScaleX, final float pScaleY);

	public float getScaleCenterX();
	public float getScaleCenterY();
	public void setScaleCenterX(final float pScaleCenterX);
	public void setScaleCenterY(final float pScaleCenterY);
	public void setScaleCenter(final float pScaleCenterX, final float pScaleCenterY);

	public boolean isSkewed();
	public float getSkewX();
	public float getSkewY();
	public void setSkewX(final float pSkewX);
	public void setSkewY(final float pSkewY);
	public void setSkew(final float pSkew);
	public void setSkew(final float pSkewX, final float pSkewY);

	public float getSkewCenterX();
	public float getSkewCenterY();
	public void setSkewCenterX(final float pSkewCenterX);
	public void setSkewCenterY(final float pSkewCenterY);
	public void setSkewCenter(final float pSkewCenterX, final float pSkewCenterY);

	public boolean isRotatedOrScaledOrSkewed();

	public float getRed();
	public float getGreen();
	public float getBlue();
	public float getAlpha();
	public Color getColor();

	public void setRed(final float pRed);
	public void setGreen(final float pGreen);
	public void setBlue(final float pBlue);
	public void setAlpha(final float pAlpha);
	public void setColor(final Color pColor);
	public void setColor(final float pRed, final float pGreen, final float pBlue);
	public void setColor(final float pRed, final float pGreen, final float pBlue, final float pAlpha);

	/**
	 * @return a shared(!) float[] of length 2.
	 */
	public float[] getSceneCenterCoordinates();

	/**
	 * @param pReuse must be of length 2.
	 * @return <code>pReuse</code> as a convenience.
	 */
	public float[] getSceneCenterCoordinates(final float[] pReuse);

	/**
	 * @param pX
	 * @param pY
	 * @return a shared(!) float[] of length 2.
	 */
	public float[] convertLocalToSceneCoordinates(final float pX, final float pY);
	/**
	 * @param pX
	 * @param pY
	 * @param pReuse must be of length 2.
	 * @return <code>pReuse</code> as a convenience.
	 */
	public float[] convertLocalToSceneCoordinates(final float pX, final float pY, final float[] pReuse);
	/**
	 * @param pCoordinates must be of length 2.
	 * @return a shared(!) float[] of length 2.
	 */
	public float[] convertLocalToSceneCoordinates(final float[] pCoordinates);
	/**
	 * @param pCoordinates must be of length 2.
	 * @param pReuse must be of length 2.
	 * @return <code>pReuse</code> as a convenience.
	 */
	public float[] convertLocalToSceneCoordinates(final float[] pCoordinates, final float[] pReuse);

	/**
	 * @param pX
	 * @param pY
	 * @return a shared(!) float[] of length 2.
	 */
	public float[] convertSceneToLocalCoordinates(final float pX, final float pY);
	/**
	 * @param pX
	 * @param pY
	 * @param pReuse must be of length 2.
	 * @return <code>pReuse</code> as a convenience.
	 */
	public float[] convertSceneToLocalCoordinates(final float pX, final float pY, final float[] pReuse);
	/**
	 * @param pCoordinates must be of length 2.
	 * @return a shared(!) float[] of length 2.
	 */
	public float[] convertSceneToLocalCoordinates(final float[] pCoordinates);
	/**
	 * @param pCoordinates must be of length 2.
	 * @param pReuse must be of length 2.
	 * @return <code>pReuse</code> as a convenience.
	 */
	public float[] convertSceneToLocalCoordinates(final float[] pCoordinates, final float[] pReuse);

	public Transformation getLocalToSceneTransformation();
	public Transformation getSceneToLocalTransformation();

	public Transformation getLocalToParentTransformation();
	public Transformation getParentToLocalTransformation();

	public void onAttached();
	public void onDetached();
	
	
	
	// ===========================================================
	// 左上 Getter & Setter
	// ===========================================================
	/**
	 * 设置左上X位置
	 * 
	 * @param pX
	 */
	public void setPositionX(float pX);

	/**
	 * 设置左上Y位置
	 * 
	 * @param pY
	 */
	public void setPositionY(float pY);

	// ===========================================================
	// 左下 Getter & Setter
	// ===========================================================

	/**
	 * 获取左下X坐标
	 * 
	 * @return
	 */
	public float getLeftX();

	/**
	 * 获取左下Y坐标
	 * 
	 * @return
	 */
	public float getLeftY();

	/**
	 * 设置左下X位置
	 * 
	 * @param pX
	 */
	public void setLeftPositionX(float pX);
	/**
	 * 设置左下Y位置
	 * 
	 * @param pY
	 */
	public void setLeftPositionY(float pY);

	/**
	 * 设置左下位置
	 * 
	 * @param pX
	 * @param pY
	 */
	public void setLeftPosition(float pX, float pY);
	// ===========================================================
	// 右上 Getter & Setter
	// ===========================================================

	/**
	 * 获取右上X坐标
	 * 
	 * @return
	 */
	public float getRightX();

	/**
	 * 获取右上Y坐标
	 * 
	 * @return
	 */
	public float getRightY();

	/**
	 * 设置右上X位置
	 * 
	 * @param pX
	 */
	public void setRightPositionX(float pX);

	/**
	 * 设置右上Y位置
	 * 
	 * @param pY
	 */
	public void setRightPositionY(float pY);
	/**
	 * 设置右上位置
	 * 
	 * @param pX
	 * @param pY
	 */
	public void setRightPosition(float pX, float pY);

	// ===========================================================
	// 右下 Getter & Setter
	// ===========================================================

	/**
	 * 获取右下X坐标
	 * 
	 * @return
	 */
	public float getBottomX();
	/**
	 * 获取右下Y坐标
	 * 
	 * @return
	 */
	public float getBottomY();

	/**
	 * 设置右下X位置
	 * 
	 * @param pX
	 */
	public void setBottomPositionX(float pX);

	/**
	 * 设置右下Y位置
	 * 
	 * @param pY
	 */
	public void setBottomPositionY(float pY);
	/**
	 * 设置右下位置
	 * 
	 * @param pX
	 * @param pY
	 */
	public void setBottomPosition(float pX, float pY);

	// ===========================================================
	// 中心 Getter & Setter
	// ===========================================================

	/**
	 * 获取中心X坐标
	 * 
	 * @return
	 */
	public float getCentreX();
	/**
	 * 获取中心Y坐标
	 * 
	 * @return
	 */
	public float getCentreY();

	/**
	 * 设置中心X位置
	 * 
	 * @param pCentreX
	 */
	public void setCentrePositionX(float pCentreX);

	/**
	 * 设置中心Y位置
	 * 
	 * @param pCentreY
	 */
	public void setCentrePositionY(float pCentreY);

	/**
	 * 设置中心位置
	 * 
	 * @param pCentreX
	 * @param pCentreY
	 */
	public void setCentrePosition(float pCentreX, float pCentreY);

	
	
	/**
	 * 测量，主要用于调用onMeasure
	 * @param pWidthMeasureSpec
	 * @param pHeightMeasureSpec
	 */
	public void measure(float pWidthMeasureSpec, float pHeightMeasureSpec);
	/**
	 * 基本类型直接设置自己的宽高，在EntityGroup中，先自己的最大可用宽高，再循环设置child的宽高
	 * @param pWidthMeasureSpec
	 * @param pHeightMeasureSpec
	 */
	public void onMeasure(float pWidthMeasureSpec, float pHeightMeasureSpec);
	/**
	 * 布局，主要用于调用onLayout
	 */
	public void layout();
	/**
	 * 布局
	 */
	public void onLayout();
	public boolean isNeedLayout();
	/**
	 * 绘制区域改变通知
	 */
	public void notifyDrawRectChange();
	public void onDrawRectChange();
	public boolean isNeedNotifyDrawRectChanged();
	public LayoutParams getLayoutParams();
	public void setLayoutParams(LayoutParams pLayoutParams);
	/**
	 * 在布局里面设置的位置
	 * @param pX
	 * @param pY
	 */
	public void layoutX(float pX);
	/**
	 * 在布局里面设置的位置
	 * @param pX
	 * @param pY
	 */
	public void layoutY(float pY);
	
	/**
	 * 在布局里面设置的位置
	 * @param pX
	 * @param pY
	 */
	public void layout(float pX, float pY);

	public void registerUpdateHandler(final IUpdateHandler pUpdateHandler);
	public boolean unregisterUpdateHandler(final IUpdateHandler pUpdateHandler);
	public boolean unregisterUpdateHandlers(final IUpdateHandlerMatcher pUpdateHandlerMatcher);
	public int getUpdateHandlerCount();
	public void clearUpdateHandlers();

	public void registerEntityModifier(final IEntityModifier pEntityModifier);
	public boolean unregisterEntityModifier(final IEntityModifier pEntityModifier);
	public boolean unregisterEntityModifiers(final IEntityModifierMatcher pEntityModifierMatcher);
	public int getEntityModifierCount();
	public void clearEntityModifiers();

	public boolean isCullingEnabled();
	public void setCullingEnabled(final boolean pCullingEnabled);
	/**
	 * Will only be performed if {@link IEntity#isCullingEnabled()} is true.
	 *
	 * @param pCamera the currently active camera to perform culling checks against.
	 * @return <code>true</code> when this object is visible by the {@link Camera}, <code>false</code> otherwise.
	 */
	public boolean isCulled(final Camera pCamera);

	public void setUserData(final Object pUserData);
	public Object getUserData();

	public void toString(final StringBuilder pStringBuilder);

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================
}
