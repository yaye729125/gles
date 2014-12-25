package com.orange.entity;

import com.orange.engine.camera.Camera;
import com.orange.engine.handler.IUpdateHandler;
import com.orange.engine.handler.UpdateHandlerList;
import com.orange.entity.group.IEntityGroup;
import com.orange.entity.layout.EntityLayout.LayoutParams;
import com.orange.entity.modifier.EntityModifierList;
import com.orange.entity.modifier.IEntityModifier;
import com.orange.entity.modifier.IEntityModifier.IEntityModifierMatcher;
import com.orange.input.touch.TouchEvent;
import com.orange.opengl.util.GLState;
import com.orange.util.Constants;
import com.orange.util.adt.transformation.Transformation;
import com.orange.util.algorithm.collision.RectangularShapeCollisionChecker;
import com.orange.util.color.Color;
import com.orange.util.size.Size;

// ===========================================================
// 把childEntity从本类删除，移动到EntityGroup类
// ===========================================================
/**
 * (c) OrangeGame 2012 
 * 
 * @author OrangeGame <OGEngine@orangegame.cn>
 */
public class Entity implements IEntity {

	// ===========================================================
	// Constants
	// ===========================================================

	private static final int ENTITYMODIFIERS_CAPACITY_DEFAULT = 4;
	private static final int UPDATEHANDLERS_CAPACITY_DEFAULT = 4;

	private static final float[] VERTICES_SCENE_TO_LOCAL_TMP = new float[2];
	private static final float[] VERTICES_LOCAL_TO_SCENE_TMP = new float[2];

	// ===========================================================
	// Fields
	// ===========================================================

	protected boolean mDisposed = false;
	protected boolean mVisible = true;
	protected boolean mCullingEnabled = false;
	protected boolean mIgnoreUpdate = false;;
	/**
	 * 是否阻止触摸
	 * 
	 * @return
	 */
	protected boolean mIgnoreTouch = true;

	protected int mTag = IEntity.TAG_INVALID;

	protected int mZIndex = 0;

	private EntityModifierList mEntityModifiers;
	private UpdateHandlerList mUpdateHandlers;

	private IEntity mParent;

	protected Color mColor = new Color(1, 1, 1, 1);

	protected float mX;
	protected float mY;

	protected float mWidth = -1;
	protected float mHeight = -1;

	private LayoutParams mLayoutParams;

	protected float mRotation = 0;

	protected float mRotationCenterX = 0;
	protected float mRotationCenterY = 0;

	protected float mScaleX = 1;
	protected float mScaleY = 1;

	protected float mScaleCenterX = 0;
	protected float mScaleCenterY = 0;

	protected float mSkewX = 0;
	protected float mSkewY = 0;

	protected float mSkewCenterX = 0;
	protected float mSkewCenterY = 0;

	private boolean mLocalToParentTransformationDirty = true;
	private boolean mParentToLocalTransformationDirty = true;

	private Transformation mLocalToParentTransformation;
	private Transformation mParentToLocalTransformation;

	private Transformation mLocalToSceneTransformation;
	private Transformation mSceneToLocalTransformation;

	private Object mUserData;

	private float mMeasureWidth = 0.0f;
	private float mMeasureHeight = 0.0f;

	// ===========================================================
	// Constructors
	// ===========================================================

	public Entity() {
		this(0, 0);
	}

	public Entity(final float pX, final float pY) {
		this(pX, pY, Size.SIZE_INFINITE, Size.SIZE_INFINITE);
	}

	public Entity(final float pX, final float pY, final float pWidth, final float pHeight) {
		this.mX = pX;
		this.mY = pY;
		this.mWidth = pWidth;
		this.mHeight = pHeight;

		this.resetRotationCenter();
		this.resetScaleCenter();
		this.resetSkewCenter();
	}

	// ===========================================================
	// Methods
	// ===========================================================

	public void resetRotationCenter() {
		this.mRotationCenterX = this.mWidth * 0.5f;
		this.mRotationCenterY = this.mHeight * 0.5f;
	}

	public void resetScaleCenter() {
		this.mScaleCenterX = this.mWidth * 0.5f;
		this.mScaleCenterY = this.mHeight * 0.5f;
	}

	public void resetSkewCenter() {
		this.mSkewCenterX = this.mWidth * 0.5f;
		this.mSkewCenterY = this.mHeight * 0.5f;
	}

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================

	protected void onUpdateColor() {

	}

	@Override
	public boolean isDisposed() {
		return this.mDisposed;
	}

	@Override
	public boolean isVisible() {
		return this.mVisible;
	}

	@Override
	public void setVisible(final boolean pVisible) {
		this.mVisible = pVisible;
	}

	@Override
	public boolean isCullingEnabled() {
		return this.mCullingEnabled;
	}

	@Override
	public void setCullingEnabled(final boolean pCullingEnabled) {
		this.mCullingEnabled = pCullingEnabled;
	}

	@Override
	public boolean isCulled(final Camera pCamera) {
		return false;
	}

	@Override
	public boolean isIgnoreUpdate() {
		return this.mIgnoreUpdate;
	}

	@Override
	public void setIgnoreUpdate(final boolean pIgnoreUpdate) {
		this.mIgnoreUpdate = pIgnoreUpdate;
	}

	/**
	 * 是否阻止触摸，默认阻止
	 * 
	 * @return
	 */
	@Override
	public boolean isIgnoreTouch() {
		return mIgnoreTouch;
	}

	/**
	 * 设置是否阻止触摸
	 * 
	 * @return
	 */
	@Override
	public void setIgnoreTouch(boolean pIgnoreTouch) {
		this.mIgnoreTouch = pIgnoreTouch;
	}

	@Override
	public boolean hasParent() {
		return this.mParent != null;
	}

	@Override
	public IEntity getParent() {
		return this.mParent;
	}

	@Override
	public void setParent(final IEntity pEntity) {
		this.mParent = pEntity;
	}

	public boolean detachSelf() {
		IEntity parent = this.getParent();
		if (parent != null && parent instanceof IEntityGroup) {
			return ((IEntityGroup) parent).detachChild(this);
		} else {
			return false;
		}
	}

	@Override
	public int getTag() {
		return this.mTag;
	}

	@Override
	public void setTag(final int pTag) {
		this.mTag = pTag;
	}

	@Override
	public int getZIndex() {
		return this.mZIndex;
	}

	@Override
	public void setZIndex(final int pZIndex) {
		this.mZIndex = pZIndex;
	}

	@Override
	public float getX() {
		return this.mX;
	}

	@Override
	public float getY() {
		return this.mY;
	}

	@Override
	public void setX(final float pX) {
		this.mX = pX;

		this.mLocalToParentTransformationDirty = true;
		this.mParentToLocalTransformationDirty = true;

		this.notifyDrawRectChange();
	}

	@Override
	public void setY(final float pY) {
		this.mY = pY;

		this.mLocalToParentTransformationDirty = true;
		this.mParentToLocalTransformationDirty = true;

		this.notifyDrawRectChange();
	}

	@Override
	public void setPosition(final IEntity pOtherEntity) {
		this.setPosition(pOtherEntity.getX(), pOtherEntity.getY());

		this.notifyDrawRectChange();
	}

	@Override
	public void setPosition(final float pX, final float pY) {
		this.mX = pX;
		this.mY = pY;

		this.mLocalToParentTransformationDirty = true;
		this.mParentToLocalTransformationDirty = true;

		this.notifyDrawRectChange();
	}

	public float getMeasureWidth() {
		return mMeasureWidth;
	}

	public void setMeasureWidth(float pMeasureWidth) {
		this.mMeasureWidth = pMeasureWidth;
	}

	public float getMeasureHeight() {
		return mMeasureHeight;
	}

	public void setMeasureHeight(float pMeasureHeight) {
		this.mMeasureHeight = pMeasureHeight;
	}

	public LayoutParams getLayoutParams() {
		return mLayoutParams;
	}

	public void setLayoutParams(LayoutParams pLayoutParams) {
		this.mLayoutParams = pLayoutParams;
	}

	@Override
	public float getWidth() {
		return this.mWidth;
	}

	@Override
	public float getHeight() {
		return this.mHeight;
	}
	
	/**
	 * 获取一半宽
	 * @return
	 */
	@Override
	public float getWidthHalf(){
		return this.getWidth() / 2.0f;
	}
	
	/**
	 * 获取一半高
	 * @return
	 */
	@Override
	public float getHeightHalf(){
		return this.getHeight() / 2.0f;
	}

	@Override
	public void setWidth(final float pWidth) {
		this.mWidth = pWidth;
		this.notifyDrawRectChange();
	}

	@Override
	public void setHeight(final float pHeight) {
		this.mHeight = pHeight;
		this.notifyDrawRectChange();
	}

	@Override
	public void setSize(final float pWidth, final float pHeight) {
		this.mWidth = pWidth;
		this.mHeight = pHeight;
		this.notifyDrawRectChange();
	}

	@Override
	public float getWidthScaled() {
		return this.getWidth() * this.mScaleX;
	}

	@Override
	public float getHeightScaled() {
		return this.getHeight() * this.mScaleY;
	}
	
	/**
	 * 获取缩放后一半宽
	 * @return
	 */
	@Override
	public float getWidthScaledHalf(){
		return this.getWidthScaled() / 2.0f;
	}
	/**
	 * 获取缩放后一半高
	 * @return
	 */
	@Override
	public float getHeightScaledHalf(){
		return this.getHeightScaled() / 2.0f;
	}

	// ===========================================================
	// 左上 Getter & Setter
	// ===========================================================
	/**
	 * 设置左上X位置
	 * 
	 * @param pX
	 */
	public void setPositionX(float pX) {
		this.setPosition(pX, this.getY());
	}

	/**
	 * 设置左上Y位置
	 * 
	 * @param pY
	 */
	public void setPositionY(float pY) {
		this.setPosition(this.getX(), pY);
	}
	
	// ===========================================================
	// 左上 Getter & Setter
	// ===========================================================

	/**
	 * 获取左上X坐标
	 * 
	 * @return
	 */
	public float getTopX() {
		return this.getX();
	}

	/**
	 * 获取左上Y坐标
	 * 
	 * @return
	 */
	public float getTopY() {
		return this.getY();
	}

	/**
	 * 设置左上X位置
	 * 
	 * @param pX
	 */
	public void setTopPositionX(float pX) {
		this.setPosition(pX, this.getY());
	}

	/**
	 * 设置左上Y位置
	 * 
	 * @param pY
	 */
	public void setTopPositionY(float pY) {
		this.setPosition(this.getX(), pY);
	}

	/**
	 * 设置左上位置
	 * 
	 * @param pX
	 * @param pY
	 */
	public void setTopPosition(float pX, float pY) {
		this.setPosition(pX, pY);
	}

	// ===========================================================
	// 左下 Getter & Setter
	// ===========================================================

	/**
	 * 获取左下X坐标
	 * 
	 * @return
	 */
	public float getLeftX() {
		return this.getX();
	}

	/**
	 * 获取左下Y坐标
	 * 
	 * @return
	 */
	public float getLeftY() {
		return this.getY() + this.getHeight();
	}

	/**
	 * 设置左下X位置
	 * 
	 * @param pX
	 */
	public void setLeftPositionX(float pX) {
		this.setPosition(pX, this.getY());
	}

	/**
	 * 设置左下Y位置
	 * 
	 * @param pY
	 */
	public void setLeftPositionY(float pY) {
		pY = pY - this.getHeight();
		this.setPosition(this.getX(), pY);
	}

	/**
	 * 设置左下位置
	 * 
	 * @param pX
	 * @param pY
	 */
	public void setLeftPosition(float pX, float pY) {
		pY = pY - this.getHeight();
		this.setPosition(pX, pY);
	}

	// ===========================================================
	// 右上 Getter & Setter
	// ===========================================================

	/**
	 * 获取右上X坐标
	 * 
	 * @return
	 */
	public float getRightX() {
		return this.getX() + this.getWidth();
	}

	/**
	 * 获取右上Y坐标
	 * 
	 * @return
	 */
	public float getRightY() {
		return this.getY();
	}

	/**
	 * 设置右上X位置
	 * 
	 * @param pX
	 */
	public void setRightPositionX(float pX) {
		pX = pX - this.getWidth();
		this.setPosition(pX, this.getY());
	}

	/**
	 * 设置右上Y位置
	 * 
	 * @param pY
	 */
	public void setRightPositionY(float pY) {
		this.setPosition(this.getX(), pY);
	}

	/**
	 * 设置右上位置
	 * 
	 * @param pX
	 * @param pY
	 */
	public void setRightPosition(float pX, float pY) {
		pX = pX - this.getWidth();
		this.setPosition(pX, pY);
	}

	// ===========================================================
	// 右下 Getter & Setter
	// ===========================================================

	/**
	 * 获取右下X坐标
	 * 
	 * @return
	 */
	public float getBottomX() {
		return this.getX() + this.getWidth();
	}

	/**
	 * 获取右下Y坐标
	 * 
	 * @return
	 */
	public float getBottomY() {
		return this.getY() + this.getHeight();
	}

	/**
	 * 设置右下X位置
	 * 
	 * @param pX
	 */
	public void setBottomPositionX(float pX) {
		pX = pX - this.getWidth();
		this.setPosition(pX, this.getY());
	}

	/**
	 * 设置右下Y位置
	 * 
	 * @param pY
	 */
	public void setBottomPositionY(float pY) {
		pY = pY - this.getHeight();
		this.setPosition(this.getX(), pY);
	}

	/**
	 * 设置右下位置
	 * 
	 * @param pX
	 * @param pY
	 */
	public void setBottomPosition(float pX, float pY) {
		pX = pX - this.getWidth();
		pY = pY - this.getHeight();
		this.setPosition(pX, pY);
	}

	// ===========================================================
	// 中心 Getter & Setter
	// ===========================================================

	/**
	 * 获取中心X坐标
	 * 
	 * @return
	 */
	public float getCentreX() {
		return this.getX() + this.getWidth() / 2.0f;
	}

	/**
	 * 获取中心Y坐标
	 * 
	 * @return
	 */
	public float getCentreY() {
		return this.getY() + this.getHeight() / 2.0f;
	}

	/**
	 * 设置中心X位置
	 * 
	 * @param pCentreX
	 */
	public void setCentrePositionX(float pCentreX) {
		pCentreX = pCentreX - this.getWidth() / 2.0f;
		this.setPosition(pCentreX, this.getY());
	}

	/**
	 * 设置中心Y位置
	 * 
	 * @param pCentreY
	 */
	public void setCentrePositionY(float pCentreY) {
		pCentreY = pCentreY - this.getHeight() / 2.0f;
		this.setPosition(this.getX(), pCentreY);
	}

	/**
	 * 设置中心位置
	 * 
	 * @param pCentreX
	 * @param pCentreY
	 */
	public void setCentrePosition(float pCentreX, float pCentreY) {
		pCentreX = pCentreX - this.getWidth() / 2.0f;
		pCentreY = pCentreY - this.getHeight() / 2.0f;
		this.setPosition(pCentreX, pCentreY);
	}

	@Override
	public float getRotation() {
		return this.mRotation;
	}

	@Override
	public boolean isRotated() {
		return this.mRotation != 0;
	}

	@Override
	public void setRotation(final float pRotation) {
		this.mRotation = pRotation;

		this.mLocalToParentTransformationDirty = true;
		this.mParentToLocalTransformationDirty = true;

		this.notifyDrawRectChange();
	}

	@Override
	public float getRotationCenterX() {
		return this.mRotationCenterX;
	}

	@Override
	public float getRotationCenterY() {
		return this.mRotationCenterY;
	}

	@Override
	public void setRotationCenterX(final float pRotationCenterX) {
		this.mRotationCenterX = pRotationCenterX;

		this.mLocalToParentTransformationDirty = true;
		this.mParentToLocalTransformationDirty = true;
	}

	@Override
	public void setRotationCenterY(final float pRotationCenterY) {
		this.mRotationCenterY = pRotationCenterY;

		this.mLocalToParentTransformationDirty = true;
		this.mParentToLocalTransformationDirty = true;
	}

	@Override
	public void setRotationCenter(final float pRotationCenterX, final float pRotationCenterY) {
		this.mRotationCenterX = pRotationCenterX;
		this.mRotationCenterY = pRotationCenterY;

		this.mLocalToParentTransformationDirty = true;
		this.mParentToLocalTransformationDirty = true;
	}

	@Override
	public boolean isScaled() {
		return (this.mScaleX != 1) || (this.mScaleY != 1);
	}

	@Override
	public float getScaleX() {
		return this.mScaleX;
	}

	@Override
	public float getScaleY() {
		return this.mScaleY;
	}

	@Override
	public void setScaleX(final float pScaleX) {
		this.mScaleX = pScaleX;

		this.mLocalToParentTransformationDirty = true;
		this.mParentToLocalTransformationDirty = true;
	}

	@Override
	public void setScaleY(final float pScaleY) {
		this.mScaleY = pScaleY;

		this.mLocalToParentTransformationDirty = true;
		this.mParentToLocalTransformationDirty = true;
	}

	@Override
	public void setScale(final float pScale) {
		this.mScaleX = pScale;
		this.mScaleY = pScale;

		this.mLocalToParentTransformationDirty = true;
		this.mParentToLocalTransformationDirty = true;
	}

	@Override
	public void setScale(final float pScaleX, final float pScaleY) {
		this.mScaleX = pScaleX;
		this.mScaleY = pScaleY;

		this.mLocalToParentTransformationDirty = true;
		this.mParentToLocalTransformationDirty = true;
	}

	@Override
	public float getScaleCenterX() {
		return this.mScaleCenterX;
	}

	@Override
	public float getScaleCenterY() {
		return this.mScaleCenterY;
	}

	@Override
	public void setScaleCenterX(final float pScaleCenterX) {
		this.mScaleCenterX = pScaleCenterX;

		this.mLocalToParentTransformationDirty = true;
		this.mParentToLocalTransformationDirty = true;
	}

	@Override
	public void setScaleCenterY(final float pScaleCenterY) {
		this.mScaleCenterY = pScaleCenterY;

		this.mLocalToParentTransformationDirty = true;
		this.mParentToLocalTransformationDirty = true;
	}

	@Override
	public void setScaleCenter(final float pScaleCenterX, final float pScaleCenterY) {
		this.mScaleCenterX = pScaleCenterX;
		this.mScaleCenterY = pScaleCenterY;

		this.mLocalToParentTransformationDirty = true;
		this.mParentToLocalTransformationDirty = true;
	}

	@Override
	public boolean isSkewed() {
		return (this.mSkewX != 0) || (this.mSkewY != 0);
	}

	@Override
	public float getSkewX() {
		return this.mSkewX;
	}

	@Override
	public float getSkewY() {
		return this.mSkewY;
	}

	@Override
	public void setSkewX(final float pSkewX) {
		this.mSkewX = pSkewX;

		this.mLocalToParentTransformationDirty = true;
		this.mParentToLocalTransformationDirty = true;
	}

	@Override
	public void setSkewY(final float pSkewY) {
		this.mSkewY = pSkewY;

		this.mLocalToParentTransformationDirty = true;
		this.mParentToLocalTransformationDirty = true;
	}

	@Override
	public void setSkew(final float pSkew) {
		this.mSkewX = pSkew;
		this.mSkewY = pSkew;

		this.mLocalToParentTransformationDirty = true;
		this.mParentToLocalTransformationDirty = true;
	}

	@Override
	public void setSkew(final float pSkewX, final float pSkewY) {
		this.mSkewX = pSkewX;
		this.mSkewY = pSkewY;

		this.mLocalToParentTransformationDirty = true;
		this.mParentToLocalTransformationDirty = true;
	}

	@Override
	public float getSkewCenterX() {
		return this.mSkewCenterX;
	}

	@Override
	public float getSkewCenterY() {
		return this.mSkewCenterY;
	}

	@Override
	public void setSkewCenterX(final float pSkewCenterX) {
		this.mSkewCenterX = pSkewCenterX;

		this.mLocalToParentTransformationDirty = true;
		this.mParentToLocalTransformationDirty = true;
	}

	@Override
	public void setSkewCenterY(final float pSkewCenterY) {
		this.mSkewCenterY = pSkewCenterY;

		this.mLocalToParentTransformationDirty = true;
		this.mParentToLocalTransformationDirty = true;
	}

	@Override
	public void setSkewCenter(final float pSkewCenterX, final float pSkewCenterY) {
		this.mSkewCenterX = pSkewCenterX;
		this.mSkewCenterY = pSkewCenterY;

		this.mLocalToParentTransformationDirty = true;
		this.mParentToLocalTransformationDirty = true;
	}

	@Override
	public boolean isRotatedOrScaledOrSkewed() {
		return (this.mRotation != 0) || (this.mScaleX != 1) || (this.mScaleY != 1) || (this.mSkewX != 0) || (this.mSkewY != 0);
	}

	@Override
	public float getRed() {
		return this.mColor.getRed();
	}

	@Override
	public float getGreen() {
		return this.mColor.getGreen();
	}

	@Override
	public float getBlue() {
		return this.mColor.getBlue();
	}

	@Override
	public float getAlpha() {
		return this.mColor.getAlpha();
	}

	@Override
	public Color getColor() {
		return this.mColor;
	}

	@Override
	public void setColor(final Color pColor) {
		this.mColor.set(pColor);

		this.onUpdateColor();
	}

	/**
	 * @param pRed
	 *            from <code>0.0f</code> to <code>1.0f</code>
	 */
	@Override
	public void setRed(final float pRed) {
		if (this.mColor.setRedChecking(pRed)) {
			this.onUpdateColor();
		}
	}

	/**
	 * @param pGreen
	 *            from <code>0.0f</code> to <code>1.0f</code>
	 */
	@Override
	public void setGreen(final float pGreen) {
		if (this.mColor.setGreenChecking(pGreen)) {
			this.onUpdateColor();
		}
	}

	/**
	 * @param pBlue
	 *            from <code>0.0f</code> to <code>1.0f</code>
	 */
	@Override
	public void setBlue(final float pBlue) {
		if (this.mColor.setBlueChecking(pBlue)) {
			this.onUpdateColor();
		}
	}

	/**
	 * @param pAlpha
	 *            from <code>0.0f</code> (transparent) to <code>1.0f</code>
	 *            (opaque)
	 */
	@Override
	public void setAlpha(float pAlpha) {
		float parentAlpha = 1.0f;
		IEntity parent = this.getParent();
		if (parent != null) {
			parentAlpha = parent.getAlpha();
		}
		pAlpha = pAlpha * parentAlpha;
		if (this.mColor.setAlphaChecking(pAlpha)) {
			this.onUpdateColor();
		}
	}

	/**
	 * @param pRed
	 *            from <code>0.0f</code> to <code>1.0f</code>
	 * @param pGreen
	 *            from <code>0.0f</code> to <code>1.0f</code>
	 * @param pBlue
	 *            from <code>0.0f</code> to <code>1.0f</code>
	 */
	@Override
	public void setColor(final float pRed, final float pGreen, final float pBlue) {
		if (this.mColor.setChecking(pRed, pGreen, pBlue)) { // TODO Is this
															// check worth it?
			this.onUpdateColor();
		}
	}

	/**
	 * @param pRed
	 *            from <code>0.0f</code> to <code>1.0f</code>
	 * @param pGreen
	 *            from <code>0.0f</code> to <code>1.0f</code>
	 * @param pBlue
	 *            from <code>0.0f</code> to <code>1.0f</code>
	 * @param pAlpha
	 *            from <code>0.0f</code> (transparent) to <code>1.0f</code>
	 *            (opaque)
	 */
	@Override
	public void setColor(final float pRed, final float pGreen, final float pBlue, final float pAlpha) {
		if (this.mColor.setChecking(pRed, pGreen, pBlue, pAlpha)) { // TODO Is
																	// this
																	// check
																	// worth it?
			this.onUpdateColor();
		}
	}

	@Override
	public void registerUpdateHandler(final IUpdateHandler pUpdateHandler) {
		if (this.mUpdateHandlers == null) {
			this.allocateUpdateHandlers();
		}
		this.mUpdateHandlers.add(pUpdateHandler);
	}

	@Override
	public boolean unregisterUpdateHandler(final IUpdateHandler pUpdateHandler) {
		if (this.mUpdateHandlers == null) {
			return false;
		}
		return this.mUpdateHandlers.remove(pUpdateHandler);
	}

	@Override
	public boolean unregisterUpdateHandlers(final IUpdateHandlerMatcher pUpdateHandlerMatcher) {
		if (this.mUpdateHandlers == null) {
			return false;
		}
		return this.mUpdateHandlers.removeAll(pUpdateHandlerMatcher);
	}

	@Override
	public int getUpdateHandlerCount() {
		if (this.mUpdateHandlers == null) {
			return 0;
		}
		return this.mUpdateHandlers.size();
	}

	@Override
	public void clearUpdateHandlers() {
		if (this.mUpdateHandlers == null) {
			return;
		}
		this.mUpdateHandlers.clear();
	}

	@Override
	public void registerEntityModifier(final IEntityModifier pEntityModifier) {
		if (this.mEntityModifiers == null) {
			this.allocateEntityModifiers();
		}
		this.mEntityModifiers.add(pEntityModifier);
	}

	@Override
	public boolean unregisterEntityModifier(final IEntityModifier pEntityModifier) {
		if (this.mEntityModifiers == null) {
			return false;
		}
		return this.mEntityModifiers.remove(pEntityModifier);
	}

	@Override
	public boolean unregisterEntityModifiers(final IEntityModifierMatcher pEntityModifierMatcher) {
		if (this.mEntityModifiers == null) {
			return false;
		}
		return this.mEntityModifiers.removeAll(pEntityModifierMatcher);
	}

	@Override
	public int getEntityModifierCount() {
		if (this.mEntityModifiers == null) {
			return 0;
		}
		return this.mEntityModifiers.size();
	}

	@Override
	public void clearEntityModifiers() {
		if (this.mEntityModifiers == null) {
			return;
		}
		this.mEntityModifiers.clear();
	}

	@Override
	public float[] getSceneCenterCoordinates() {
		return this.convertLocalToSceneCoordinates(this.getWidthScaledHalf(), this.getHeightScaledHalf());
	}

	@Override
	public float[] getSceneCenterCoordinates(final float[] pReuse) {
		return this.convertLocalToSceneCoordinates(0, 0, pReuse);
	}

	@Override
	public Transformation getLocalToParentTransformation() {
		if (this.mLocalToParentTransformation == null) {
			this.mLocalToParentTransformation = new Transformation();
		}

		final Transformation localToParentTransformation = this.mLocalToParentTransformation;
		if (this.mLocalToParentTransformationDirty) {
			localToParentTransformation.setToIdentity();

			/* Scale. */
			final float scaleX = this.mScaleX;
			final float scaleY = this.mScaleY;
			if ((scaleX != 1) || (scaleY != 1)) {
				final float scaleCenterX = this.mScaleCenterX;
				final float scaleCenterY = this.mScaleCenterY;

				/*
				 * TODO Check if it is worth to check for scaleCenterX == 0 &&
				 * scaleCenterY == 0 as the two postTranslate can be saved. The
				 * same obviously applies for all similar occurrences of this
				 * pattern in this class.
				 */

				localToParentTransformation.postTranslate(-scaleCenterX, -scaleCenterY);
				localToParentTransformation.postScale(scaleX, scaleY);
				localToParentTransformation.postTranslate(scaleCenterX, scaleCenterY);
			}

			/* Skew. */
			final float skewX = this.mSkewX;
			final float skewY = this.mSkewY;
			if ((skewX != 0) || (skewY != 0)) {
				final float skewCenterX = this.mSkewCenterX;
				final float skewCenterY = this.mSkewCenterY;

				localToParentTransformation.postTranslate(-skewCenterX, -skewCenterY);
				localToParentTransformation.postSkew(skewX, skewY);
				localToParentTransformation.postTranslate(skewCenterX, skewCenterY);
			}

			/* Rotation. */
			final float rotation = this.mRotation;
			if (rotation != 0) {
				final float rotationCenterX = this.mRotationCenterX;
				final float rotationCenterY = this.mRotationCenterY;

				localToParentTransformation.postTranslate(-rotationCenterX, -rotationCenterY);
				localToParentTransformation.postRotate(rotation);
				localToParentTransformation.postTranslate(rotationCenterX, rotationCenterY);
			}

			/* Translation. */
			localToParentTransformation.postTranslate(this.mX, this.mY);

			this.mLocalToParentTransformationDirty = false;
		}
		return localToParentTransformation;
	}

	@Override
	public Transformation getParentToLocalTransformation() {
		if (this.mParentToLocalTransformation == null) {
			this.mParentToLocalTransformation = new Transformation();
		}

		final Transformation parentToLocalTransformation = this.mParentToLocalTransformation;
		if (this.mParentToLocalTransformationDirty) {
			parentToLocalTransformation.setToIdentity();

			/* Translation. */
			parentToLocalTransformation.postTranslate(-this.mX, -this.mY);

			/* Rotation. */
			final float rotation = this.mRotation;
			if (rotation != 0) {
				final float rotationCenterX = this.mRotationCenterX;
				final float rotationCenterY = this.mRotationCenterY;

				parentToLocalTransformation.postTranslate(-rotationCenterX, -rotationCenterY);
				parentToLocalTransformation.postRotate(-rotation);
				parentToLocalTransformation.postTranslate(rotationCenterX, rotationCenterY);
			}

			/* Skew. */
			final float skewX = this.mSkewX;
			final float skewY = this.mSkewY;
			if ((skewX != 0) || (skewY != 0)) {
				final float skewCenterX = this.mSkewCenterX;
				final float skewCenterY = this.mSkewCenterY;

				parentToLocalTransformation.postTranslate(-skewCenterX, -skewCenterY);
				parentToLocalTransformation.postSkew(-skewX, -skewY);
				parentToLocalTransformation.postTranslate(skewCenterX, skewCenterY);
			}

			/* Scale. */
			final float scaleX = this.mScaleX;
			final float scaleY = this.mScaleY;
			if ((scaleX != 1) || (scaleY != 1)) {
				final float scaleCenterX = this.mScaleCenterX;
				final float scaleCenterY = this.mScaleCenterY;

				parentToLocalTransformation.postTranslate(-scaleCenterX, -scaleCenterY);
				parentToLocalTransformation.postScale(1 / scaleX, 1 / scaleY); // TODO
																				// Division
																				// could
																				// be
																				// replaced
																				// by
																				// a
																				// multiplication
																				// of
																				// 'scale(X/Y)Inverse'...
				parentToLocalTransformation.postTranslate(scaleCenterX, scaleCenterY);
			}

			this.mParentToLocalTransformationDirty = false;
		}
		return parentToLocalTransformation;
	}

	@Override
	public Transformation getLocalToSceneTransformation() {
		if (this.mLocalToSceneTransformation == null) {
			this.mLocalToSceneTransformation = new Transformation();
		}

		// TODO Cache if parent(recursive) not dirty.
		final Transformation localToSceneTransformation = this.mLocalToSceneTransformation;
		localToSceneTransformation.setTo(this.getLocalToParentTransformation());

		final IEntity parent = this.mParent;
		if (parent != null) {
			localToSceneTransformation.postConcat(parent.getLocalToSceneTransformation());
		}

		return localToSceneTransformation;
	}

	@Override
	public Transformation getSceneToLocalTransformation() {
		if (this.mSceneToLocalTransformation == null) {
			this.mSceneToLocalTransformation = new Transformation();
		}

		// TODO Cache if parent(recursive) not dirty.
		final Transformation sceneToLocalTransformation = this.mSceneToLocalTransformation;
		sceneToLocalTransformation.setTo(this.getParentToLocalTransformation());

		final IEntity parent = this.mParent;
		if (parent != null) {
			sceneToLocalTransformation.preConcat(parent.getSceneToLocalTransformation());
		}

		return sceneToLocalTransformation;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.orange.entity.IEntity#convertLocalToSceneCoordinates(float,
	 * float)
	 */
	@Override
	public float[] convertLocalToSceneCoordinates(final float pX, final float pY) {
		return this.convertLocalToSceneCoordinates(pX, pY, Entity.VERTICES_LOCAL_TO_SCENE_TMP);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.orange.entity.IEntity#convertLocalToSceneCoordinates(float,
	 * float, float[])
	 */
	@Override
	public float[] convertLocalToSceneCoordinates(final float pX, final float pY, final float[] pReuse) {
		final Transformation localToSceneTransformation = this.getLocalToSceneTransformation();

		pReuse[Constants.VERTEX_INDEX_X] = pX;
		pReuse[Constants.VERTEX_INDEX_Y] = pY;

		localToSceneTransformation.transform(pReuse);

		return pReuse;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.orange.entity.IEntity#convertLocalToSceneCoordinates(float[])
	 */
	@Override
	public float[] convertLocalToSceneCoordinates(final float[] pCoordinates) {
		return this.convertLocalToSceneCoordinates(pCoordinates, Entity.VERTICES_LOCAL_TO_SCENE_TMP);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.orange.entity.IEntity#convertLocalToSceneCoordinates(float[],
	 * float[])
	 */
	@Override
	public float[] convertLocalToSceneCoordinates(final float[] pCoordinates, final float[] pReuse) {
		final Transformation localToSceneTransformation = this.getLocalToSceneTransformation();

		pReuse[Constants.VERTEX_INDEX_X] = pCoordinates[Constants.VERTEX_INDEX_X];
		pReuse[Constants.VERTEX_INDEX_Y] = pCoordinates[Constants.VERTEX_INDEX_Y];

		localToSceneTransformation.transform(pReuse);

		return pReuse;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.orange.entity.IEntity#convertSceneToLocalCoordinates(float,
	 * float)
	 */
	@Override
	public float[] convertSceneToLocalCoordinates(final float pX, final float pY) {
		return this.convertSceneToLocalCoordinates(pX, pY, Entity.VERTICES_SCENE_TO_LOCAL_TMP);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.orange.entity.IEntity#convertSceneToLocalCoordinates(float,
	 * float, float[])
	 */
	@Override
	public float[] convertSceneToLocalCoordinates(final float pX, final float pY, final float[] pReuse) {
		pReuse[Constants.VERTEX_INDEX_X] = pX;
		pReuse[Constants.VERTEX_INDEX_Y] = pY;

		this.getSceneToLocalTransformation().transform(pReuse);

		return pReuse;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.orange.entity.IEntity#convertSceneToLocalCoordinates(float[])
	 */
	@Override
	public float[] convertSceneToLocalCoordinates(final float[] pCoordinates) {
		return this.convertSceneToLocalCoordinates(pCoordinates, Entity.VERTICES_SCENE_TO_LOCAL_TMP);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.orange.entity.IEntity#convertSceneToLocalCoordinates(float[],
	 * float[])
	 */
	@Override
	public float[] convertSceneToLocalCoordinates(final float[] pCoordinates, final float[] pReuse) {
		pReuse[Constants.VERTEX_INDEX_X] = pCoordinates[Constants.VERTEX_INDEX_X];
		pReuse[Constants.VERTEX_INDEX_Y] = pCoordinates[Constants.VERTEX_INDEX_Y];

		this.getSceneToLocalTransformation().transform(pReuse);

		return pReuse;
	}

	@Override
	public void onAttached() {
		this.setAlpha(this.getAlpha());
		this.requestLayout();
	}

	@Override
	public void onDetached() {
		this.requestLayout();
	}

	public final void notifyDrawRectChange() {
		IEntity parent = this.getParent();
		if (parent != null && this.isNeedNotifyDrawRectChanged()) {
			parent.notifyDrawRectChange();
		}
	}

	public void onDrawRectChange() {

	}

	public boolean isNeedNotifyDrawRectChanged() {
		return false;
	}

	public final void layout() {
		this.onLayout();
	}

	/**
	 * 在布局里面设置的位置
	 * 
	 * @param pX
	 * @param pY
	 */
	public final void layoutX(float pX) {
		this.mX = pX;

		this.mLocalToParentTransformationDirty = true;
		this.mParentToLocalTransformationDirty = true;
	}

	/**
	 * 在布局里面设置的位置
	 * 
	 * @param pX
	 * @param pY
	 */
	public final void layoutY(float pY) {
		this.mY = pY;

		this.mLocalToParentTransformationDirty = true;
		this.mParentToLocalTransformationDirty = true;
	}

	/**
	 * 在布局里面设置的位置
	 * 
	 * @param pX
	 * @param pY
	 */
	public final void layout(float pX, float pY) {
		this.mX = pX;
		this.mY = pY;

		this.mLocalToParentTransformationDirty = true;
		this.mParentToLocalTransformationDirty = true;
	}

	@Override
	public void onLayout() {
		// TODO Auto-generated method stub

	}

	public final void requestLayout() {
		IEntity rootLayoutParent = this.getRootLayoutParent();
		if (rootLayoutParent != null) {
			rootLayoutParent.measure(rootLayoutParent.getWidth(), rootLayoutParent.getHeight());
			rootLayoutParent.layout();
		} else if (this.isNeedLayout()) {
			this.measure(this.getWidth(), this.getHeight());
			this.layout();
		}
	}

	/**
	 * 测量
	 */
	public final void measure(float pWidthMeasureSpec, float pHeightMeasureSpec) {
		this.onMeasure(pWidthMeasureSpec, pHeightMeasureSpec);
	}

	/**
	 * 测量动作
	 */
	@Override
	public void onMeasure(float pWidthMeasureSpec, float pHeightMeasureSpec) {
		this.setMeasuredDimension(this.getWidth(), this.getHeight());
	}

	/**
	 * 设置测量结果
	 * 
	 * @param pMeasureWidth
	 * @param pMeasureHeight
	 */
	public void setMeasuredDimension(float pMeasureWidth, float pMeasureHeight) {
		this.mMeasureWidth = pMeasureWidth;
		this.mMeasureHeight = pMeasureHeight;
	}

	/**
	 * 获取根父节点
	 * 
	 * @return null为没有父节点
	 */
	public IEntity getRootLayoutParent() {
		return this.getRootLayoutParent(null);
	}

	/**
	 * 获取某个Entity的根父节点
	 * 
	 * @param pParent
	 *            null为自己的根父节点
	 * @return null为没有父节点
	 */
	public IEntity getRootLayoutParent(IEntity pEntity) {
		if (pEntity == null) {
			IEntity parent = this.getParent();
			if (parent != null && parent.isNeedLayout()) {
				return getRootLayoutParent(parent);
			} else {
				return null;
			}
		} else {
			IEntity parent = pEntity.getParent();
			if (parent != null && parent.isNeedLayout()) {
				return getRootLayoutParent(parent);
			} else {
				return pEntity;
			}
		}
	}

	/**
	 * 是否需要布局
	 * 
	 * @return
	 */
	public boolean isNeedLayout() {
		return false;
	}

	@Override
	public Object getUserData() {
		return this.mUserData;
	}

	@Override
	public void setUserData(final Object pUserData) {
		this.mUserData = pUserData;
	}

	@Override
	public final void onDraw(final GLState pGLState, final Camera pCamera) {
		if (this.mVisible && !(this.mCullingEnabled && this.isCulled(pCamera))) {
			this.onManagedDraw(pGLState, pCamera);
		}
	}

	@Override
	public final void onUpdate(final float pSecondsElapsed) {
		if (!this.mIgnoreUpdate) {
			this.onManagedUpdate(pSecondsElapsed);
		}
	}

	@Override
	public void reset() {
		this.mVisible = true;
		this.mCullingEnabled = false;
		this.mIgnoreUpdate = false;

		this.mRotation = 0;
		this.mScaleX = 1;
		this.mScaleY = 1;
		this.mSkewX = 0;
		this.mSkewY = 0;

		this.mColor.reset();

		if (this.mEntityModifiers != null) {
			this.mEntityModifiers.reset();
		}

		this.resetRotationCenter();
		this.resetScaleCenter();
		this.resetSkewCenter();
	}

	@Override
	public void dispose() {
		if (!this.mDisposed) {
			this.mDisposed = true;
		} else {
			throw new AlreadyDisposedException();
		}
	}

	@Override
	protected void finalize() throws Throwable {
		super.finalize();

		if (!this.mDisposed) {
			this.dispose();
		}
	}

	@Override
	public String toString() {
		final StringBuilder stringBuilder = new StringBuilder();
		this.toString(stringBuilder);
		return stringBuilder.toString();
	}

	@Override
	public void toString(final StringBuilder pStringBuilder) {
		pStringBuilder.append(this.getClass().getSimpleName());
	}

	// ===========================================================
	// Methods
	// ===========================================================

	/**
	 * @param pGLState
	 *            the currently active {@link GLState} i.e. to apply
	 *            transformations to.
	 * @param pCamera
	 *            the currently active {@link Camera} i.e. to be used for
	 *            culling.
	 */
	protected void preDraw(final GLState pGLState, final Camera pCamera) {

	}

	/**
	 * @param pGLState
	 *            the currently active {@link GLState} i.e. to apply
	 *            transformations to.
	 * @param pCamera
	 *            the currently active {@link Camera} i.e. to be used for
	 *            culling.
	 */
	protected void draw(final GLState pGLState, final Camera pCamera) {

	}

	/**
	 * @param pGLState
	 *            the currently active {@link GLState} i.e. to apply
	 *            transformations to.
	 * @param pCamera
	 *            the currently active {@link Camera} i.e. to be used for
	 *            culling.
	 */
	protected void postDraw(final GLState pGLState, final Camera pCamera) {

	}

	private void allocateEntityModifiers() {
		this.mEntityModifiers = new EntityModifierList(this, Entity.ENTITYMODIFIERS_CAPACITY_DEFAULT);
	}

	private void allocateUpdateHandlers() {
		this.mUpdateHandlers = new UpdateHandlerList(Entity.UPDATEHANDLERS_CAPACITY_DEFAULT);
	}

	protected void onApplyTransformations(final GLState pGLState) {
		/* Translation. */
		this.applyTranslation(pGLState);

		/* Rotation. */
		this.applyRotation(pGLState);

		/* Skew. */
		this.applySkew(pGLState);

		/* Scale. */
		this.applyScale(pGLState);
	}

	protected void applyTranslation(final GLState pGLState) {
		pGLState.translateModelViewGLMatrixf(this.mX, this.mY, 0);
	}

	protected void applyRotation(final GLState pGLState) {
		final float rotation = this.mRotation;

		if (rotation != 0) {
			final float rotationCenterX = this.mRotationCenterX;
			final float rotationCenterY = this.mRotationCenterY;

			pGLState.translateModelViewGLMatrixf(rotationCenterX, rotationCenterY, 0);
			pGLState.rotateModelViewGLMatrixf(rotation, 0, 0, 1);
			pGLState.translateModelViewGLMatrixf(-rotationCenterX, -rotationCenterY, 0);

			/*
			 * TODO There is a special, but very likely case when
			 * mRotationCenter and mScaleCenter are the same. In that case the
			 * last glTranslatef of the rotation and the first glTranslatef of
			 * the scale is superfluous. The problem is that applyRotation and
			 * applyScale would need to be "merged" in order to efficiently
			 * check for that condition.
			 */
		}
	}

	protected void applySkew(final GLState pGLState) {
		final float skewX = this.mSkewX;
		final float skewY = this.mSkewY;

		if ((skewX != 0) || (skewY != 0)) {
			final float skewCenterX = this.mSkewCenterX;
			final float skewCenterY = this.mSkewCenterY;

			pGLState.translateModelViewGLMatrixf(skewCenterX, skewCenterY, 0);
			pGLState.skewModelViewGLMatrixf(skewX, skewY);
			pGLState.translateModelViewGLMatrixf(-skewCenterX, -skewCenterY, 0);
		}
	}

	protected void applyScale(final GLState pGLState) {
		final float scaleX = this.mScaleX;
		final float scaleY = this.mScaleY;

		if ((scaleX != 1) || (scaleY != 1)) {
			final float scaleCenterX = this.mScaleCenterX;
			final float scaleCenterY = this.mScaleCenterY;

			pGLState.translateModelViewGLMatrixf(scaleCenterX, scaleCenterY, 0);
			pGLState.scaleModelViewGLMatrixf(scaleX, scaleY, 1);
			pGLState.translateModelViewGLMatrixf(-scaleCenterX, -scaleCenterY, 0);
		}
	}

	protected void onManagedDraw(final GLState pGLState, final Camera pCamera) {
		pGLState.pushModelViewGLMatrix();
		{
			this.onApplyTransformations(pGLState);
			/* Draw self. */
			this.preDraw(pGLState, pCamera);
			this.draw(pGLState, pCamera);
			this.postDraw(pGLState, pCamera);
		}
		pGLState.popModelViewGLMatrix();
	}

	protected void onManagedUpdate(final float pSecondsElapsed) {
		if (this.mEntityModifiers != null) {
			this.mEntityModifiers.onUpdate(pSecondsElapsed);
		}
		if (this.mUpdateHandlers != null) {
			this.mUpdateHandlers.onUpdate(pSecondsElapsed);
		}
	}

	@Override
	public boolean contains(float pX, float pY) {
		// TODO Auto-generated method stub
		if (this.getWidth() >= Size.SIZE_INFINITE || this.getHeight() >= Size.SIZE_INFINITE) {
			return true;
		}
		return RectangularShapeCollisionChecker.checkContains(this, pX, pY);
	}
	
	@Override
	public boolean onTouch(final TouchEvent pSceneTouchEvent, final float pTouchAreaLocalX, final float pTouchAreaLocalY){
		if (!this.isIgnoreTouch() && this.isVisible()) {
			final float sceneTouchEventX = pSceneTouchEvent.getX();
			final float sceneTouchEventY = pSceneTouchEvent.getY();
			final boolean isActionDown = pSceneTouchEvent.isActionDown();
			final float[] touchAreaLocalCoordinates = this.convertSceneToLocalCoordinates(sceneTouchEventX, sceneTouchEventY);
			final float touchAreaLocalX = touchAreaLocalCoordinates[Constants.VERTEX_INDEX_X];
			final float touchAreaLocalY = touchAreaLocalCoordinates[Constants.VERTEX_INDEX_Y];
			if (!isActionDown) {
				final boolean handledSelf = this.onAreaTouched(pSceneTouchEvent, touchAreaLocalX, touchAreaLocalY);
				if (handledSelf) {
					return true;
				}
			}
			if (this.contains(sceneTouchEventX, sceneTouchEventY)) {
				final boolean handledSelf = this.onAreaTouched(pSceneTouchEvent, touchAreaLocalX, touchAreaLocalY);
				return handledSelf;
			}else {
				return false;
			}
		}else {
			return false;
		}
	}

	@Override
	public boolean onAreaTouched(TouchEvent pSceneTouchEvent, float pTouchAreaLocalX, float pTouchAreaLocalY) {
		// TODO Auto-generated method stub
		return false;
	}

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================
}
