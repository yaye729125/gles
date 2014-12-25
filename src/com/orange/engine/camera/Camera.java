package com.orange.engine.camera;

import com.orange.engine.handler.IUpdateHandler;
import com.orange.engine.handler.UpdateHandlerList;
import com.orange.entity.IEntity;
import com.orange.entity.primitive.Line;
import com.orange.entity.shape.RectangularShape;
import com.orange.input.touch.TouchEvent;
import com.orange.opengl.util.GLState;
import com.orange.util.Constants;
import com.orange.util.adt.transformation.Transformation;
import com.orange.util.algorithm.collision.RectangularShapeCollisionChecker;
import com.orange.util.math.MathUtils;

/**
 * 摄像机
 * (c) OrangeGame 2012
 * @author OrangeGame <OGEngine@orangegame.cn>
 */
public class Camera implements IUpdateHandler {
	// ===========================================================
	// Constants
	// ===========================================================
	/**顶点数组*/
	static final float[] VERTICES_TMP = new float[2];
	/**默认更新处理分配优先级*/
	private static final int UPDATEHANDLERS_CAPACITY_DEFAULT = 4;

	// ===========================================================
	// Fields
	// ===========================================================
	/**X轴最小值*/
	protected float mXMin;
	/**X轴最大值*/
	protected float mXMax;
	/**Y轴最小值*/
	protected float mYMin;
	/**Y轴最大值*/
	protected float mYMax;
	
	private float mZNear = -1.0f;
	private float mZFar = 1.0f;

	/**参照物*/
	private IEntity mChaseEntity;
	/**旋转值*/
	protected float mRotation = 0;
	/**相机场景旋转角度*/
	protected float mCameraSceneRotation = 0;
	
	protected int mSurfaceX;
	protected int mSurfaceY;
	protected int mSurfaceWidth;
	protected int mSurfaceHeight;
	/**SurfaceSize调整变更*/
	protected boolean mResizeOnSurfaceSizeChanged;
	/**更新处理列表*/
	protected UpdateHandlerList mUpdateHandlers;

	// ===========================================================
	// Constructors
	// ===========================================================
	/**
	 * Camera构造函数
	 * @param pX x坐标
	 * @param pY y坐标
	 * @param pWidth  宽度
	 * @param pHeight 高度
	 */
	public Camera(final float pX, final float pY, final float pWidth, final float pHeight) {
		this.set(pX, pY, pX + pWidth, pY + pHeight);
	}

	// ===========================================================
	// Getter & Setter
	// ===========================================================

	public float getXMin() {
		return this.mXMin;
	}

	public void setXMin(final float pXMin) {
		this.mXMin = pXMin;
	}

	public float getXMax() {
		return this.mXMax;
	}

	public void setXMax(final float pXMax) {
		this.mXMax = pXMax;
	}

	public float getYMin() {
		return this.mYMin;
	}

	public void setYMin(final float pYMin) {
		this.mYMin = pYMin;
	}

	public float getYMax() {
		return this.mYMax;
	}

	public void setYMax(final float pYMax) {
		this.mYMax = pYMax;
	}

	public void set(final float pXMin, final float pYMin, final float pXMax, final float pYMax) {
		this.mXMin = pXMin;
		this.mXMax = pXMax;
		this.mYMin = pYMin;
		this.mYMax = pYMax;
	}

	public float getZNear() {
		return this.mZNear;
	}

	public float getZFar() {
		return this.mZFar;
	}

	public void setZNear(final float pZNear) {
		this.mZNear = pZNear;
	}

	public void setZFar(final float pZFar) {
		this.mZFar = pZFar;
	}

	public void setZClippingPlanes(final float pNearZClippingPlane, final float pFarZClippingPlane) {
		this.mZNear = pNearZClippingPlane;
		this.mZFar = pFarZClippingPlane;
	}

	public float getWidth() {
		return this.mXMax - this.mXMin;
	}

	public float getHeight() {
		return this.mYMax - this.mYMin;
	}

	public float getWidthRaw() {
		return this.mXMax - this.mXMin;
	}

	public float getHeightRaw() {
		return this.mYMax - this.mYMin;
	}

	public float getCenterX() {
		return (this.mXMin + this.mXMax) * 0.5f;
	}

	public float getCenterY() {
		return (this.mYMin + this.mYMax) * 0.5f;
	}
	
	/**
	 * 设置中心位置
	 * @param pCenterX 中心点x值
	 * @param pCenterY 中心点y值
	 */	 
	public void setCenter(final float pCenterX, final float pCenterY) {
		final float dX = pCenterX - this.getCenterX();
		final float dY = pCenterY - this.getCenterY();

		this.mXMin += dX;
		this.mXMax += dX;
		this.mYMin += dY;
		this.mYMax += dY;
	}
	
	/**
	 * 抵消中心位置
	 * @param pX
	 * @param pY
	 */
	public void offsetCenter(final float pX, final float pY) {
		this.setCenter(this.getCenterX() + pX, this.getCenterY() + pY);
	}

	/**
	 * 设置跟随实体
	 * @param pChaseEntity
	 */
	public void setChaseEntity(final IEntity pChaseEntity) {
		this.mChaseEntity = pChaseEntity;
	}
	
	/**
	 * 判断是否旋转
	 * @return
	 */
	public boolean isRotated() {
		return this.mRotation != 0;
	}

	public float getRotation() {
		return this.mRotation;
	}

	public void setRotation(final float pRotation) {
		this.mRotation = pRotation;
	}

	public float getCameraSceneRotation() {
		return this.mCameraSceneRotation;
	}

	public void setCameraSceneRotation(final float pCameraSceneRotation) {
		this.mCameraSceneRotation = pCameraSceneRotation;
	}

	public int getSurfaceX() {
		return this.mSurfaceX;
	}

	public int getSurfaceY() {
		return this.mSurfaceY;
	}

	public int getSurfaceWidth() {
		return this.mSurfaceWidth;
	}

	public int getSurfaceHeight() {
		return this.mSurfaceHeight;
	}
	
	/**
	 * 设置SurfaceSize
	 * @param pSurfaceX
	 * @param pSurfaceY
	 * @param pSurfaceWidth
	 * @param pSurfaceHeight
	 */
	public void setSurfaceSize(final int pSurfaceX, final int pSurfaceY, final int pSurfaceWidth, final int pSurfaceHeight) {
		if(this.mSurfaceHeight == 0 && this.mSurfaceWidth == 0) {
			this.onSurfaceSizeInitialized(pSurfaceX, pSurfaceY, pSurfaceWidth, pSurfaceHeight);
		} else if(this.mSurfaceWidth != pSurfaceWidth || this.mSurfaceHeight != pSurfaceHeight) {
			this.onSurfaceSizeChanged(this.mSurfaceX, this.mSurfaceY, this.mSurfaceWidth, this.mSurfaceHeight, pSurfaceX, pSurfaceY, pSurfaceWidth, pSurfaceHeight);
		}
	}

	public boolean isResizeOnSurfaceSizeChanged() {
		return this.mResizeOnSurfaceSizeChanged;
	}

	public void setResizeOnSurfaceSizeChanged(final boolean pResizeOnSurfaceSizeChanged) {
		this.mResizeOnSurfaceSizeChanged = pResizeOnSurfaceSizeChanged;
	}

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================

	@Override
	public void onUpdate(final float pSecondsElapsed) {
		if(this.mUpdateHandlers != null) {
			this.mUpdateHandlers.onUpdate(pSecondsElapsed);
		}

		this.updateChaseEntity();
	}

	@Override
	public void reset() {

	}

	// ===========================================================
	// Methods
	// ===========================================================
	/**更新跟随实体*/
	public void updateChaseEntity() {
		if(this.mChaseEntity != null) {
			final float[] centerCoordinates = this.mChaseEntity.getSceneCenterCoordinates();
			this.setCenter(centerCoordinates[Constants.VERTEX_INDEX_X], centerCoordinates[Constants.VERTEX_INDEX_Y]);
		}
	}
	
	/**line是否可见*/
	public boolean isLineVisible(final Line pLine) {
		return RectangularShapeCollisionChecker.isVisible(this, pLine);
	}

	/**RectangularShape是否可见*/
	public boolean isRectangularShapeVisible(final RectangularShape pRectangularShape) {
		return RectangularShapeCollisionChecker.isVisible(this, pRectangularShape);
	}

	/**RectangularShape是否可见*/
	public boolean isRectangularShapeVisible(final float pX, final float pY, final float pWidth, final float pHeight, final Transformation pLocalToSceneTransformation) {
		return RectangularShapeCollisionChecker.isVisible(this, pX, pY, pWidth, pHeight, pLocalToSceneTransformation);
	}
	
	/**
	 * 场景矩阵应用
	 * @param pGLState
	 */
	public void onApplySceneMatrix(final GLState pGLState) {
		pGLState.orthoProjectionGLMatrixf(this.getXMin(), this.getXMax(), this.getYMax(), this.getYMin(), this.mZNear, this.mZFar);

		final float rotation = this.mRotation;
		if(rotation != 0) {
			Camera.applyRotation(pGLState, this.getCenterX(), this.getCenterY(), rotation);
		}
	}
	
	/**
	 * 场景背景矩阵应用
	 * @param pGLState
	 */
	public void onApplySceneBackgroundMatrix(final GLState pGLState) {
		final float widthRaw = this.getWidthRaw();
		final float heightRaw = this.getHeightRaw();

		pGLState.orthoProjectionGLMatrixf(0, widthRaw, heightRaw, 0, this.mZNear, this.mZFar);

		final float rotation = this.mRotation;
		if(rotation != 0) {
			Camera.applyRotation(pGLState, widthRaw * 0.5f, heightRaw * 0.5f, rotation);
		}
	}
	
	/**
	 * 相机中场景矩阵应用
	 * @param pGLState
	 */
	public void onApplyCameraSceneMatrix(final GLState pGLState) {
		final float widthRaw = this.getWidthRaw();
		final float heightRaw = this.getHeightRaw();
		pGLState.orthoProjectionGLMatrixf(0, widthRaw, heightRaw, 0, this.mZNear, this.mZFar);

		final float cameraSceneRotation = this.mCameraSceneRotation;
		if(cameraSceneRotation != 0) {
			Camera.applyRotation(pGLState, widthRaw * 0.5f, heightRaw * 0.5f, cameraSceneRotation);
		}
	}
	
	/**
	 * 旋转
	 * @param pGLState
	 * @param pRotationCenterX 选择中心点X值
	 * @param pRotationCenterY 选择中心点Y值
	 * @param pAngle 
	 */
	private static void applyRotation(final GLState pGLState, final float pRotationCenterX, final float pRotationCenterY, final float pAngle) {
		pGLState.translateProjectionGLMatrixf(pRotationCenterX, pRotationCenterY, 0);
		pGLState.rotateProjectionGLMatrixf(pAngle, 0, 0, 1);
		pGLState.translateProjectionGLMatrixf(-pRotationCenterX, -pRotationCenterY, 0);
	}

	/**
	 * 相机场景转化事件
	 * @param pSceneTouchEvent
	 */
	public void convertSceneToCameraSceneTouchEvent(final TouchEvent pSceneTouchEvent) {
		this.unapplySceneRotation(pSceneTouchEvent);

		this.applySceneToCameraSceneOffset(pSceneTouchEvent);

		this.applyCameraSceneRotation(pSceneTouchEvent);
	}

	/**
	 * 获取摄像机中坐标
	 * @param pSceneX
	 * @param pSceneY
	 * @return
	 */
	public float[] getCameraSceneCoordinatesFromSceneCoordinates(final float pSceneX, final float pSceneY) {
		Camera.VERTICES_TMP[Constants.VERTEX_INDEX_X] = pSceneX;
		Camera.VERTICES_TMP[Constants.VERTEX_INDEX_Y] = pSceneY;

		return this.getCameraSceneCoordinatesFromSceneCoordinates(Camera.VERTICES_TMP);
	}
	
	/**
	 * 获取摄像机中坐标
	 * @param pSceneCoordinates
	 * @return
	 */
	public float[] getCameraSceneCoordinatesFromSceneCoordinates(final float[] pSceneCoordinates) {
		this.unapplySceneRotation(pSceneCoordinates);

		this.applySceneToCameraSceneOffset(pSceneCoordinates);

		this.applyCameraSceneRotation(pSceneCoordinates);

		return pSceneCoordinates;
	}

	/**
	 * 相机场景->实际场景转化事件
	 * @param pSceneTouchEvent
	 */
	public void convertCameraSceneToSceneTouchEvent(final TouchEvent pCameraSceneTouchEvent) {
		this.unapplyCameraSceneRotation(pCameraSceneTouchEvent);

		this.unapplySceneToCameraSceneOffset(pCameraSceneTouchEvent);

		this.applySceneRotation(pCameraSceneTouchEvent);
	}

	/**
	 * 获取实际场景中坐标
	 * @param pCameraSceneX
	 * @param pCameraSceneY
	 * @return
	 */
	public float[] getSceneCoordinatesFromCameraSceneCoordinates(final float pCameraSceneX, final float pCameraSceneY) {
		Camera.VERTICES_TMP[Constants.VERTEX_INDEX_X] = pCameraSceneX;
		Camera.VERTICES_TMP[Constants.VERTEX_INDEX_Y] = pCameraSceneY;

		return this.getSceneCoordinatesFromCameraSceneCoordinates(Camera.VERTICES_TMP);
	}

	/**
	 * 获取实际场景中坐标
	 * @param pCameraSceneCoordinates
	 * @return
	 */
	public float[] getSceneCoordinatesFromCameraSceneCoordinates(final float[] pCameraSceneCoordinates) {
		this.unapplyCameraSceneRotation(pCameraSceneCoordinates);

		this.unapplySceneToCameraSceneOffset(pCameraSceneCoordinates);

		this.applySceneRotation(pCameraSceneCoordinates);

		return pCameraSceneCoordinates;
	}

	/**
	 * 实际场景到摄像机场景偏移应用
	 * @param pSceneTouchEvent
	 */
	protected void applySceneToCameraSceneOffset(final TouchEvent pSceneTouchEvent) {
		pSceneTouchEvent.offset(-this.mXMin, -this.mYMin);
	}

	/**
	 * 实际场景到摄像机场景偏移应用
	 * @param pSceneCoordinates
	 */
	protected void applySceneToCameraSceneOffset(final float[] pSceneCoordinates) {
		pSceneCoordinates[Constants.VERTEX_INDEX_X] -= this.mXMin;
		pSceneCoordinates[Constants.VERTEX_INDEX_Y] -= this.mYMin;
	}

	/**
	 * 实际场景到摄像机场景偏移应用
	 * @param pSceneCoordinates
	 */
	protected void unapplySceneToCameraSceneOffset(final TouchEvent pCameraSceneTouchEvent) {
		pCameraSceneTouchEvent.offset(this.mXMin, this.mYMin);
	}

	/**
	 * 实际场景到摄像机场景偏移再应用
	 * @param pCameraSceneCoordinates
	 */
	protected void unapplySceneToCameraSceneOffset(final float[] pCameraSceneCoordinates) {
		pCameraSceneCoordinates[Constants.VERTEX_INDEX_X] += this.mXMin;
		pCameraSceneCoordinates[Constants.VERTEX_INDEX_Y] += this.mYMin;
	}

	/**
	 * 场景旋转应用
	 * @param pCameraSceneCoordinates
	 */
	private void applySceneRotation(final float[] pCameraSceneCoordinates) {
		final float rotation = this.mRotation;
		if(rotation != 0) {
			MathUtils.rotateAroundCenter(pCameraSceneCoordinates, -rotation, this.getCenterX(), this.getCenterY());
		}
	}

	/**
	 * 场景旋转应用
	 * @param pCameraSceneTouchEvent
	 */
	private void applySceneRotation(final TouchEvent pCameraSceneTouchEvent) {
		final float rotation = this.mRotation;
		if(rotation != 0) {
			Camera.VERTICES_TMP[Constants.VERTEX_INDEX_X] = pCameraSceneTouchEvent.getX();
			Camera.VERTICES_TMP[Constants.VERTEX_INDEX_Y] = pCameraSceneTouchEvent.getY();

			MathUtils.rotateAroundCenter(Camera.VERTICES_TMP, -rotation, this.getCenterX(), this.getCenterY());

			pCameraSceneTouchEvent.set(Camera.VERTICES_TMP[Constants.VERTEX_INDEX_X], Camera.VERTICES_TMP[Constants.VERTEX_INDEX_Y]);
		}
	}
	
	/**
	 * 华庭
	 * @param pSceneCoordinates
	 */
	private void unapplySceneRotation(final float[] pSceneCoordinates) {
		final float rotation = this.mRotation;

		if(rotation != 0) {
			MathUtils.revertRotateAroundCenter(pSceneCoordinates, rotation, this.getCenterX(), this.getCenterY());
		}
	}

	/**
	 * 华庭
	 * @param pSceneCoordinates
	 */
	private void unapplySceneRotation(final TouchEvent pSceneTouchEvent) {
		final float rotation = this.mRotation;

		if(rotation != 0) {
			Camera.VERTICES_TMP[Constants.VERTEX_INDEX_X] = pSceneTouchEvent.getX();
			Camera.VERTICES_TMP[Constants.VERTEX_INDEX_Y] = pSceneTouchEvent.getY();

			MathUtils.revertRotateAroundCenter(Camera.VERTICES_TMP, rotation, this.getCenterX(), this.getCenterY());

			pSceneTouchEvent.set(Camera.VERTICES_TMP[Constants.VERTEX_INDEX_X], Camera.VERTICES_TMP[Constants.VERTEX_INDEX_Y]);
		}
	}

	/**
	 * 镜头场景旋转应用
	 * @param pCameraSceneTouchEvent
	 */
	private void applyCameraSceneRotation(final float[] pSceneCoordinates) {
		final float cameraSceneRotation = -this.mCameraSceneRotation;

		if(cameraSceneRotation != 0) {
			MathUtils.rotateAroundCenter(pSceneCoordinates, cameraSceneRotation, (this.mXMax - this.mXMin) * 0.5f, (this.mYMax - this.mYMin) * 0.5f);
		}
	}

	/**
	 * 镜头场景旋转应用
	 * @param pSceneTouchEvent
	 */
	private void applyCameraSceneRotation(final TouchEvent pSceneTouchEvent) {
		final float cameraSceneRotation = -this.mCameraSceneRotation;

		if(cameraSceneRotation != 0) {
			Camera.VERTICES_TMP[Constants.VERTEX_INDEX_X] = pSceneTouchEvent.getX();
			Camera.VERTICES_TMP[Constants.VERTEX_INDEX_Y] = pSceneTouchEvent.getY();

			MathUtils.rotateAroundCenter(Camera.VERTICES_TMP, cameraSceneRotation, (this.mXMax - this.mXMin) * 0.5f, (this.mYMax - this.mYMin) * 0.5f);

			pSceneTouchEvent.set(Camera.VERTICES_TMP[Constants.VERTEX_INDEX_X], Camera.VERTICES_TMP[Constants.VERTEX_INDEX_Y]);
		}
	}

	/**
	 * 华庭
	 * @param pSceneCoordinates
	 */
	private void unapplyCameraSceneRotation(final float[] pCameraSceneCoordinates) {
		final float cameraSceneRotation = -this.mCameraSceneRotation;

		if(cameraSceneRotation != 0) {
			MathUtils.revertRotateAroundCenter(pCameraSceneCoordinates, cameraSceneRotation, (this.mXMax - this.mXMin) * 0.5f, (this.mYMax - this.mYMin) * 0.5f);
		}
	}

	/**
	 * 华庭
	 * @param pSceneCoordinates
	 */
	private void unapplyCameraSceneRotation(final TouchEvent pCameraSceneTouchEvent) {
		final float cameraSceneRotation = -this.mCameraSceneRotation;

		if(cameraSceneRotation != 0) {
			Camera.VERTICES_TMP[Constants.VERTEX_INDEX_X] = pCameraSceneTouchEvent.getX();
			Camera.VERTICES_TMP[Constants.VERTEX_INDEX_Y] = pCameraSceneTouchEvent.getY();

			MathUtils.revertRotateAroundCenter(Camera.VERTICES_TMP, cameraSceneRotation, (this.mXMax - this.mXMin) * 0.5f, (this.mYMax - this.mYMin) * 0.5f);

			pCameraSceneTouchEvent.set(Camera.VERTICES_TMP[Constants.VERTEX_INDEX_X], Camera.VERTICES_TMP[Constants.VERTEX_INDEX_Y]);
		}
	}

	/**
	 * 华庭
	 * @param pSceneCoordinates
	 */
	public void convertSurfaceToSceneTouchEvent(final TouchEvent pSurfaceTouchEvent, final int pSurfaceWidth, final int pSurfaceHeight) {
		final float relativeX;
		final float relativeY;

		final float surfaceTouchEventX = pSurfaceTouchEvent.getX();
		final float surfaceTouchEventY = pSurfaceTouchEvent.getY();

		final float rotation = this.mRotation;
		if(rotation == 0) {
			relativeX = surfaceTouchEventX / pSurfaceWidth;
			relativeY = surfaceTouchEventY / pSurfaceHeight;
		} else if(rotation == 180) {
			relativeX = 1 - (surfaceTouchEventX / pSurfaceWidth);
			relativeY = 1 - (surfaceTouchEventY / pSurfaceHeight);
		} else {
			Camera.VERTICES_TMP[Constants.VERTEX_INDEX_X] = surfaceTouchEventX;
			Camera.VERTICES_TMP[Constants.VERTEX_INDEX_Y] = surfaceTouchEventY;

			MathUtils.rotateAroundCenter(Camera.VERTICES_TMP, rotation, pSurfaceWidth >> 1, pSurfaceHeight >> 1);

			relativeX = Camera.VERTICES_TMP[Constants.VERTEX_INDEX_X] / pSurfaceWidth;
			relativeY = Camera.VERTICES_TMP[Constants.VERTEX_INDEX_Y] / pSurfaceHeight;
		}

		this.convertAxisAlignedSurfaceToSceneTouchEvent(pSurfaceTouchEvent, relativeX, relativeY);
	}

	/**
	 * 华庭
	 * @param pSceneCoordinates
	 */
	private void convertAxisAlignedSurfaceToSceneTouchEvent(final TouchEvent pSurfaceTouchEvent, final float pRelativeX, final float pRelativeY) {
		final float xMin = this.getXMin();
		final float xMax = this.getXMax();
		final float yMin = this.getYMin();
		final float yMax = this.getYMax();

		final float x = xMin + pRelativeX * (xMax - xMin);
		final float y = yMin + pRelativeY * (yMax - yMin);

		pSurfaceTouchEvent.set(x, y);
	}

	/**
	 * 华庭
	 * @param pSceneCoordinates
	 */
	public void convertSceneToSurfaceTouchEvent(final TouchEvent pSceneTouchEvent, final int pSurfaceWidth, final int pSurfaceHeight) {
		this.convertAxisAlignedSceneToSurfaceTouchEvent(pSceneTouchEvent, pSurfaceWidth, pSurfaceHeight);

		final float rotation = this.mRotation;
		if(rotation == 0) {
			/* Nothing to do. */
		} else if(rotation == 180) {
			pSceneTouchEvent.set(pSurfaceWidth - pSceneTouchEvent.getX(), pSurfaceHeight - pSceneTouchEvent.getY());
		} else {
			Camera.VERTICES_TMP[Constants.VERTEX_INDEX_X] = pSceneTouchEvent.getX();
			Camera.VERTICES_TMP[Constants.VERTEX_INDEX_Y] = pSceneTouchEvent.getY();

			MathUtils.revertRotateAroundCenter(Camera.VERTICES_TMP, rotation, pSurfaceWidth >> 1, pSurfaceHeight >> 1);

			pSceneTouchEvent.set(Camera.VERTICES_TMP[Constants.VERTEX_INDEX_X], Camera.VERTICES_TMP[Constants.VERTEX_INDEX_Y]);
		}
	}

	/**
	 * 华庭
	 * @param pSceneCoordinates
	 */
	private void convertAxisAlignedSceneToSurfaceTouchEvent(final TouchEvent pSceneTouchEvent, final int pSurfaceWidth, final int pSurfaceHeight) {
		final float xMin = this.getXMin();
		final float xMax = this.getXMax();
		final float yMin = this.getYMin();
		final float yMax = this.getYMax();

		final float relativeX = (pSceneTouchEvent.getX() - xMin) / (xMax - xMin);
		final float relativeY = (pSceneTouchEvent.getY() - yMin) / (yMax - yMin);

		pSceneTouchEvent.set(relativeX * pSurfaceWidth, relativeY * pSurfaceHeight);
	}

	/**
	 * 注册更新
	 * @param pUpdateHandler 更新处理线程
	 */
	public void registerUpdateHandler(final IUpdateHandler pUpdateHandler) {
		if(this.mUpdateHandlers == null) {
			this.allocateUpdateHandlers();
		}
		this.mUpdateHandlers.add(pUpdateHandler);
	}

	/**
	 * 反注册更新
	 * @param pUpdateHandler 更新处理线程
	 */
	public boolean unregisterUpdateHandler(final IUpdateHandler pUpdateHandler) {
		if(this.mUpdateHandlers == null) {
			return false;
		}
		return this.mUpdateHandlers.remove(pUpdateHandler);
	}

	/**
	 * 反注册更新、
	 * @param pUpdateHandlerMatcher 更新处理线程列表
	 * @return
	 */
	public boolean unregisterUpdateHandlers(final IUpdateHandlerMatcher pUpdateHandlerMatcher) {
		if(this.mUpdateHandlers == null) {
			return false;
		}
		return this.mUpdateHandlers.removeAll(pUpdateHandlerMatcher);
	}

	public void clearUpdateHandlers() {
		if(this.mUpdateHandlers == null) {
			return;
		}
		this.mUpdateHandlers.clear();
	}
	
	/**更新处理线程分配*/
	private void allocateUpdateHandlers() {
		this.mUpdateHandlers = new UpdateHandlerList(Camera.UPDATEHANDLERS_CAPACITY_DEFAULT);
	}

	/**
	 * SurfaceSize初始化
	 * @param pSurfaceX
	 * @param pSurfaceY
	 * @param pSurfaceWidth
	 * @param pSurfaceHeight
	 */
	protected void onSurfaceSizeInitialized(final int pSurfaceX, final int pSurfaceY, final int pSurfaceWidth, final int pSurfaceHeight) {
		this.mSurfaceX = pSurfaceX;
		this.mSurfaceY = pSurfaceY;
		this.mSurfaceWidth = pSurfaceWidth;
		this.mSurfaceHeight = pSurfaceHeight;
	}

	/**
	 * 改变SurfaceSize
	 * @param pOldSurfaceX
	 * @param pOldSurfaceY
	 * @param pOldSurfaceWidth
	 * @param pOldSurfaceHeight
	 * @param pNewSurfaceX
	 * @param pNewSurfaceY
	 * @param pNewSurfaceWidth
	 * @param pNewSurfaceHeight
	 */
	protected void onSurfaceSizeChanged(final int pOldSurfaceX, final int pOldSurfaceY, final int pOldSurfaceWidth, final int pOldSurfaceHeight, final int pNewSurfaceX, final int pNewSurfaceY, final int pNewSurfaceWidth, final int pNewSurfaceHeight) {
		if(this.mResizeOnSurfaceSizeChanged) {
			final float surfaceWidthRatio = (float)pNewSurfaceWidth / pOldSurfaceWidth;
			final float surfaceHeightRatio = (float)pNewSurfaceHeight / pOldSurfaceHeight;

			final float centerX = this.getCenterX();
			final float centerY = this.getCenterY();

			final float newWidthRaw = this.getWidthRaw() * surfaceWidthRatio;
			final float newHeightRaw = this.getHeightRaw() * surfaceHeightRatio;

			final float newWidthRawHalf = newWidthRaw * 0.5f;
			final float newHeightRawHalf = newHeightRaw * 0.5f;

			final float xMin = centerX - newWidthRawHalf;
			final float yMin = centerY - newHeightRawHalf;
			final float xMax = centerX + newWidthRawHalf;
			final float yMax = centerY + newHeightRawHalf;

			this.set(xMin, yMin, xMax, yMax);
		}

		this.mSurfaceX = pNewSurfaceX;
		this.mSurfaceY = pNewSurfaceY;
		this.mSurfaceWidth = pNewSurfaceWidth;
		this.mSurfaceHeight = pNewSurfaceHeight;
	}

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================
}
